package com.Federation.Final.repository;

import com.Federation.Final.datasource.DataSource;
import com.Federation.Final.entity.*;
import com.Federation.Final.entity.Enum.MobileMoneyServiceEnum;
import com.Federation.Final.entity.Enum.PaymentModeEnum;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class CollectivityTransactionRepository {

    private final DataSource dataSource;

    public CollectivityTransactionRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private FinancialAccount buildFinancialAccount(
            Connection connection,
            String accountId,
            String type,
            double amount
    ) throws SQLException {

        if (accountId == null) return null;
        if (type == null) return null;

        switch (type) {
            case "CASH" -> {
                CashAccount cash = new CashAccount();
                cash.setId(accountId);
                cash.setAmount(amount);
                return cash;
            }

            case "MOBILE_BANKING" -> {
                String sql = """
                    SELECT holder_name, mobile_banking_service, mobile_number
                    FROM mobile_banking_account
                    WHERE id = ?
                """;

                try (PreparedStatement ps = connection.prepareStatement(sql)) {
                    ps.setString(1, accountId);
                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        MobileMoneyAccount mobile = new MobileMoneyAccount();
                        mobile.setId(accountId);
                        mobile.setAmount(amount);
                        mobile.setHolderName(rs.getString("holder_name"));
                        mobile.setMobileMoneyService(
                                MobileMoneyServiceEnum.valueOf(
                                        rs.getString("mobile_banking_service")
                                )
                        );
                        mobile.setMobileNumber(rs.getString("mobile_number"));
                        return mobile;
                    }
                }
            }

            case "BANK" -> {
                String sql = """
                    SELECT holder_name, bank_name, bank_code,
                           bank_branch_code, bank_account_number, bank_account_key
                    FROM bank_account
                    WHERE id = ?
                """;

                try (PreparedStatement ps = connection.prepareStatement(sql)) {
                    ps.setString(1, accountId);
                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        BankAccount bank = new BankAccount();
                        bank.setId(accountId);
                        bank.setAmount(amount);
                        bank.setHolderName(rs.getString("holder_name"));
                        bank.setBankName(rs.getString("bank_name"));
                        bank.setBankCode(rs.getInt("bank_code"));
                        bank.setBankBranchCode(rs.getInt("bank_branch_code"));
                        bank.setBankAccountNumber(rs.getInt("bank_account_number"));
                        bank.setBankAccountKey(rs.getInt("bank_account_key"));
                        return bank;
                    }
                }
            }
        }

        return null;
    }

    private Member findMemberById(Connection connection, String memberId) throws SQLException {
        if (memberId == null) return null;

        String sql = "SELECT id, first_name, last_name, email, phone_number FROM member WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, memberId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Member member = new Member();
                member.setId(rs.getString("id"));
                member.setFirstName(rs.getString("first_name"));
                member.setLastName(rs.getString("last_name"));
                member.setEmail(rs.getString("email"));
                member.setPhoneNumber(rs.getString("phone_number"));
                return member;
            }
        }
        return null;
    }

    public List<CollectivityTransaction> findByCollectivityAndPeriod(
            String collectivityId,
            LocalDate from,
            LocalDate to
    ) {

        List<CollectivityTransaction> transactions = new ArrayList<>();

        String sql = """
            SELECT 
                ct.id,
                ct.creation_date,
                ct.amount,
                ct.payment_mode,
                ct.account_credited_id,
                ct.member_debited_id,
                fa.type AS type,
                fa.amount AS account_amount
            FROM collectivity_transaction ct
            LEFT JOIN financial_account fa 
                ON ct.account_credited_id = fa.id
            WHERE ct.collectivity_id = ?
              AND ct.creation_date BETWEEN ? AND ?
            ORDER BY ct.creation_date DESC
        """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, collectivityId);
            ps.setDate(2, Date.valueOf(from));
            ps.setDate(3, Date.valueOf(to));

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                CollectivityTransaction transaction = new CollectivityTransaction();

                transaction.setId(rs.getString("id"));

                Date creationDate = rs.getDate("creation_date");
                if (creationDate != null) {
                    transaction.setCreationDate(creationDate.toLocalDate());
                }

                transaction.setAmount(rs.getDouble("amount"));

                // Gestion du cas où payment_mode est NULL
                String paymentModeStr = rs.getString("payment_mode");
                if (paymentModeStr != null && !paymentModeStr.isEmpty()) {
                    transaction.setPaymentMode(PaymentModeEnum.valueOf(paymentModeStr));
                } else {
                    // Valeur par défaut si NULL
                    transaction.setPaymentMode(PaymentModeEnum.CASH);
                }

                transaction.setCollectivityId(collectivityId);

                String accountId = rs.getString("account_credited_id");
                String accountType = rs.getString("type");
                double accountAmount = rs.getDouble("account_amount");

                if (accountId != null && accountType != null) {
                    FinancialAccount account = buildFinancialAccount(connection, accountId, accountType, accountAmount);
                    transaction.setAccountCredited(account);
                }

                String memberId = rs.getString("member_debited_id");
                if (memberId != null) {
                    Member member = findMemberById(connection, memberId);
                    transaction.setMemberDebited(member);
                }

                transactions.add(transaction);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching transactions: " + e.getMessage(), e);
        }

        return transactions;
    }

    public CollectivityTransaction save(CollectivityTransaction transaction) throws SQLException {
        String sql = """
            INSERT INTO collectivity_transaction 
            (id, collectivity_id, creation_date, amount, payment_mode, account_credited_id, member_debited_id) 
            VALUES (?, ?, ?, ?, ?::payment_mode_enum, ?, ?)
        """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String id = UUID.randomUUID().toString();
            stmt.setString(1, id);
            stmt.setString(2, transaction.getCollectivityId());
            stmt.setDate(3, Date.valueOf(transaction.getCreationDate()));
            stmt.setDouble(4, transaction.getAmount());

            String paymentMode = transaction.getPaymentMode() != null ? transaction.getPaymentMode().name() : "CASH";
            stmt.setString(5, paymentMode);

            String accountCreditedId = null;
            if (transaction.getAccountCredited() != null) {
                accountCreditedId = transaction.getAccountCredited().getId();
            }
            stmt.setString(6, accountCreditedId);

            String memberDebitedId = null;
            if (transaction.getMemberDebited() != null) {
                memberDebitedId = transaction.getMemberDebited().getId();
            }
            stmt.setString(7, memberDebitedId);

            stmt.executeUpdate();
            transaction.setId(id);
            return transaction;
        }
    }
}