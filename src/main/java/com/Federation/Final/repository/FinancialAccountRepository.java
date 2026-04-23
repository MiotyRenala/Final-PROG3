package com.Federation.Final.repository;

import com.Federation.Final.datasource.DataSource;
import com.Federation.Final.entity.*;
import com.Federation.Final.entity.Enum.AccountTypeEnum;
import com.Federation.Final.entity.Enum.BankEnum;
import com.Federation.Final.entity.Enum.MobileMoneyServiceEnum;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FinancialAccountRepository {

    private final DataSource dataSource;

    public FinancialAccountRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<FinancialAccount> findByCollectivityIdAndDate(String collectivityId, LocalDate date) throws SQLException {
        List<FinancialAccount> accounts = new ArrayList<>();

        String sql = """
            SELECT 
                fa.id,
                fa.type,
                COALESCE(SUM(ct.amount), 0) as balance
            FROM financial_account fa
            LEFT JOIN collectivity_transaction ct ON ct.account_credited_id = fa.id 
                AND ct.collectivity_id = ? 
                AND ct.creation_date <= ?
            WHERE fa.id IN (
                SELECT DISTINCT ct.account_credited_id 
                FROM collectivity_transaction ct 
                WHERE ct.collectivity_id = ?
            )
            GROUP BY fa.id, fa.type
        """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, collectivityId);
            stmt.setDate(2, Date.valueOf(date));
            stmt.setString(3, collectivityId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String id = rs.getString("id");
                String type = rs.getString("type");
                double balance = rs.getDouble("balance");

                FinancialAccount account = buildAccount(conn, id, type, balance);
                if (account != null) {
                    accounts.add(account);
                }
            }
        }

        return accounts;
    }

    private FinancialAccount buildAccount(Connection conn, String id, String type, double balance) throws SQLException {
        switch (type) {
            case "CASH":
                CashAccount cash = new CashAccount();
                cash.setId(id);
                cash.setType(AccountTypeEnum.CASH);
                cash.setAmount(balance);
                return cash;

            case "MOBILE_BANKING":
                String sql = "SELECT holder_name, mobile_banking_service, mobile_number FROM mobile_banking_account WHERE id = ?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        MobileMoneyAccount mobile = new MobileMoneyAccount();
                        mobile.setId(id);
                        mobile.setType(AccountTypeEnum.MOBILE_MONEY);
                        mobile.setAmount(balance);
                        mobile.setHolderName(rs.getString("holder_name"));
                        mobile.setMobileMoneyService(MobileMoneyServiceEnum.valueOf(rs.getString("mobile_banking_service")));
                        mobile.setMobileNumber(rs.getString("mobile_number"));
                        return mobile;
                    }
                }
                break;

            case "BANK":
                sql = "SELECT holder_name, bank_name, bank_code, bank_branch_code, bank_account_number, bank_account_key FROM bank_account WHERE id = ?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        BankAccount bank = new BankAccount();
                        bank.setId(id);
                        bank.setType(AccountTypeEnum.BANK);
                        bank.setAmount(balance);
                        bank.setHolderName(rs.getString("holder_name"));
                        String bankNameStr = rs.getString("bank_name");
                        bank.setBankName(String.valueOf(BankEnum.valueOf(bankNameStr)));
                        bank.setBankCode(rs.getInt("bank_code"));
                        bank.setBankBranchCode(rs.getInt("bank_branch_code"));
                        bank.setBankAccountNumber(rs.getInt("bank_account_number"));
                        bank.setBankAccountKey(rs.getInt("bank_account_key"));
                        return bank;
                    }
                }
                break;
        }
        return null;
    }
}