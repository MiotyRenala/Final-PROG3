package com.Federation.Final.repository;

import com.Federation.Final.datasource.DataSource;
import com.Federation.Final.entity.Collectivity;
import com.Federation.Final.entity.Member;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.UUID;

@Component
public class CollectivityRepository {
    private final DataSource dataSource;

    public CollectivityRepository(DataSource dataSource) {

        this.dataSource = dataSource;
    }

    
    public Collectivity save(Collectivity c) throws SQLException {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);

            String id = UUID.randomUUID().toString();

            // 1. Insert collectivity
            String sqlColl = "INSERT INTO collectivity (id, name, number, location, creation_date, federation_approval) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sqlColl)) {
                stmt.setString(1, id);
                stmt.setString(2, c.getName());
                stmt.setInt(3, c.getUniqueNumber());
                stmt.setString(4, c.getLocation());
                stmt.setDate(5, Date.valueOf(c.getCreationDate()));
                stmt.setBoolean(6, c.isFederationApproval());
                stmt.executeUpdate();
            }
            c.setId(id);

            // 2. Update members with collectivity_id
            String sqlUpd = "UPDATE member SET collectivity_id = ? WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sqlUpd)) {
                for (Member m : c.getMembers()) {
                    stmt.setString(1, id);
                    stmt.setString(2, m.getId());
                    stmt.addBatch();
                }
                stmt.executeBatch();
            }

            // 3. Insert structure (roles)
            String sqlStruct = "INSERT INTO collectivity_structure (collectivity_id, role, member_id) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sqlStruct)) {
                stmt.setString(1, id);
                stmt.setString(2, "PRESIDENT");
                stmt.setString(3, c.getPresident().getId());
                stmt.addBatch();
                stmt.setString(2, "VICE_PRESIDENT");
                stmt.setString(3, c.getVicePresident().getId());
                stmt.addBatch();
                stmt.setString(2, "TREASURER");
                stmt.setString(3, c.getTreasurer().getId());
                stmt.addBatch();
                stmt.setString(2, "SECRETARY");
                stmt.setString(3, c.getSecretary().getId());
                stmt.addBatch();
                stmt.executeBatch();
            }

            conn.commit();
            return c;
        } catch (SQLException e) {
            if (conn != null) conn.rollback();
            throw e;
        } finally {
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
    }
    public boolean existsById(String id) throws SQLException {
        String sql = "SELECT COUNT(*) FROM collectivity WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

            return false;
        }
    }
    public Collectivity findById(String id) throws SQLException {
        String sql = "SELECT id, name, number, location, creation_date, federation_approval FROM collectivity WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Collectivity collectivity = new Collectivity();
                collectivity.setId(rs.getString("id"));
                collectivity.setName(rs.getString("name"));
                collectivity.setUniqueNumber(rs.getInt("number"));
                collectivity.setLocation(rs.getString("location"));
                collectivity.setCreationDate(rs.getDate("creation_date").toLocalDate());
                collectivity.setFederationApproval(rs.getBoolean("federation_approval"));
                return collectivity;
            }

            return null;
        }
    }


}
