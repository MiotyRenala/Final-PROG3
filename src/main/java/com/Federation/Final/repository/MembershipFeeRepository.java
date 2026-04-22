package com.Federation.Final.repository;

import com.Federation.Final.datasource.DataSource;
import com.Federation.Final.entity.Enum.ActivityStatusEnum;
import com.Federation.Final.entity.Enum.FrequencyEnum;
import com.Federation.Final.entity.MembershipFee;
import org.springframework.stereotype.Repository;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MembershipFeeRepository {
    private final DataSource dataSource;

    public MembershipFeeRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<MembershipFee> findByCollectivityId(String collectivityId) {

        List<MembershipFee> result = new ArrayList<>();

        String sql = """
            SELECT id, eligible_from, frequency, amount, label, status
            FROM membership_fee
            WHERE collectivity_id = ?
        """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, collectivityId);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    MembershipFee entity = new MembershipFee();

                    entity.setId(rs.getString("id"));

                    Date date = rs.getDate("eligible_from");
                    if (date != null) {
                        entity.setEligibleFrom(date.toLocalDate());
                    }

                    entity.setFrequency(FrequencyEnum.valueOf(rs.getString("frequency")));
                    entity.setAmount(rs.getDouble("amount"));
                    entity.setLabel(rs.getString("label"));
                    entity.setStatus(ActivityStatusEnum.valueOf(rs.getString("status")));

                    result.add(entity);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching membership fees", e);
        }

        return result;
    }

    public void saveAll(String collectivityId, List<MembershipFee> fees) {

        String sql = """
            INSERT INTO membership_fee
            (id, eligible_from, frequency, amount, label, status, collectivity_id)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            for (MembershipFee fee : fees) {

                ps.setString(1, fee.getId());

                if (fee.getEligibleFrom() != null) {
                    ps.setDate(2, Date.valueOf(fee.getEligibleFrom()));
                } else {
                    ps.setDate(2, null);
                }

                ps.setString(3, fee.getFrequency().toString());
                ps.setDouble(4, fee.getAmount());
                ps.setString(5, fee.getLabel());
                ps.setString(6, fee.getStatus().toString());
                ps.setString(7, collectivityId);

                ps.addBatch();
            }

            ps.executeBatch();

        } catch (SQLException e) {
            throw new RuntimeException("Error saving membership fees", e);
        }
    }

    public boolean existsCollectivityById(String collectivityId) {

        String sql = "SELECT COUNT (id) FROM collectivity WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, collectivityId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error checking collectivity existence", e);
        }

        return false;
    }
}

