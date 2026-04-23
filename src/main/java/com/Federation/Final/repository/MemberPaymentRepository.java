package com.Federation.Final.repository;

import com.Federation.Final.datasource.DataSource;
import com.Federation.Final.entity.Enum.PaymentModeEnum;
import com.Federation.Final.entity.MemberPayment;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class MemberPaymentRepository {
    private final DataSource dataSource;

    public MemberPaymentRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public MemberPayment save(MemberPayment payment) throws SQLException {
        String sql = """
            INSERT INTO member_payment 
            (id, member_id, membership_fee_id, amount, payment_mode, account_credited_id, creation_date) 
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String id = UUID.randomUUID().toString();
            stmt.setString(1, id);
            stmt.setString(2, payment.getMemberId());
            stmt.setString(3, payment.getMembershipFeeId());
            stmt.setDouble(4, payment.getAmount());
            stmt.setString(5, payment.getPaymentMode().name());
            stmt.setString(6, payment.getAccountCreditedId());
            stmt.setDate(7, Date.valueOf(payment.getCreationDate()));

            stmt.executeUpdate();
            payment.setId(id);
            return payment;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<MemberPayment> findByMemberId(String memberId) throws SQLException {
        String sql = "SELECT * FROM member_payment WHERE member_id = ? ORDER BY creation_date DESC";
        List<MemberPayment> payments = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, memberId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                payments.add(map(rs));
            }
        }
        return payments;
    }

    private MemberPayment map(ResultSet rs) throws SQLException {
        MemberPayment payment = new MemberPayment();
        payment.setId(rs.getString("id"));
        payment.setMemberId(rs.getString("member_id"));
        payment.setMembershipFeeId(rs.getString("membership_fee_id"));
        payment.setAmount(rs.getDouble("amount"));
        payment.setPaymentMode(PaymentModeEnum.valueOf(rs.getString("payment_mode")));
        payment.setAccountCreditedId(rs.getString("account_credited_id"));
        payment.setCreationDate(rs.getDate("creation_date").toLocalDate());
        return payment;
    }
}

