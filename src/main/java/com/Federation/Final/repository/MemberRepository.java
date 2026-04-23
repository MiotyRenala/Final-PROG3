package com.Federation.Final.repository;

import com.Federation.Final.datasource.DataSource;
import com.Federation.Final.entity.Enum.GenderEnum;
import com.Federation.Final.entity.Enum.MemberOccupationEnum;
import com.Federation.Final.entity.Member;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

import lombok.*;

@Repository
public class MemberRepository {
    private final DataSource datasource;

    public MemberRepository(DataSource datasource) {
        this.datasource = datasource;
    }

    public Optional<Member> findById(String id) throws SQLException {
        String sql = "SELECT * FROM member WHERE id = ?";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = datasource.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);

            rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(map(rs));
            }

            return Optional.empty();

        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }

    public List<Member> findByIds(List<String> ids) throws SQLException {

        if (ids == null || ids.isEmpty()) return List.of();

        String placeholders = String.join(",", Collections.nCopies(ids.size(), "?"));
        String sql = "SELECT * FROM member WHERE id IN (" + placeholders + ")";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Member> members = new ArrayList<>();

        try {
            conn = datasource.getConnection();
            stmt = conn.prepareStatement(sql);

            for (int i = 0; i < ids.size(); i++) {
                stmt.setString(i + 1, ids.get(i));
            }

            rs = stmt.executeQuery();

            while (rs.next()) {
                members.add(map(rs));
            }

            return members;

        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }

    public Member save(Member m) throws SQLException {
        String id = UUID.randomUUID().toString();
        if (id == null || id.isEmpty()) {
            throw new SQLException("ID generation failed");

        }

        String sql = "INSERT INTO member (id, first_name, last_name, birth_date, gender, address, profession," +
                " phone_number, email, occupation , collectivity_id, active, membership_date) VALUES (?, ?, ?, ?, ?::gender_enum, ?, ?, ?, ?, ?::occupation_enum, ?, ?, ?)";
        try (Connection conn = datasource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.setString(2, m.getFirstName());
            stmt.setString(3, m.getLastName());
            stmt.setDate(4, Date.valueOf(m.getBirthDate()));
            stmt.setString(5, m.getGender().name());
            stmt.setString(6, m.getAddress());
            stmt.setString(7, m.getProfession());
            stmt.setString(8, m.getPhoneNumber());
            stmt.setString(9, m.getEmail());
            stmt.setString(10, m.getOccupation().name());
            stmt.setString(11, m.getCollectivityId());
            stmt.setBoolean(12, m.isActive());
            stmt.setDate(13, Date.valueOf(m.getMembershipDate()));
            System.out.println("Exécution de la requête SQL...");
            stmt.executeUpdate();
            System.out.println("Résultat:  ligne(s) insérée(s)");
            m.setId(id);
            return m;
        }

    }

    private Member map(ResultSet rs) throws SQLException {
        Member m = new Member();
        m.setId(rs.getString("id"));
        m.setFirstName(rs.getString("first_name"));
        m.setLastName(rs.getString("last_name"));
        m.setBirthDate(rs.getDate("birth_date").toLocalDate());
        m.setGender(GenderEnum.valueOf(rs.getString("gender")));
        m.setAddress(rs.getString("address"));
        m.setProfession(rs.getString("profession"));
        m.setPhoneNumber(rs.getString("phone_number"));
        m.setEmail(rs.getString("email"));
        m.setOccupation(MemberOccupationEnum.valueOf(rs.getString("occupation")));
        m.setCollectivityId(rs.getString("collectivity_id"));
        m.setActive(rs.getBoolean("active"));
        m.setMembershipDate(rs.getDate("membership_date").toLocalDate());
        return m;
    }
}
