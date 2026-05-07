package com.Federation.Final.repository;

import com.Federation.Final.datasource.DataSource;
import com.Federation.Final.entity.dto.CollectivityLocalStatistics;
import com.Federation.Final.entity.dto.MemberDescription;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LocalStatisticRepository {


    private DataSource dataSource;

    public LocalStatisticRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<CollectivityLocalStatistics> getLocalStatistics(String collectivityId,
                                                                LocalDate from,
                                                                LocalDate to)
            throws SQLException {

        List<CollectivityLocalStatistics> statistics = new ArrayList<>();
        Connection conn = null;

        try {
            conn = dataSource.getConnection();

            List<MemberDescription> members = getMembersByCollectivityId(conn, collectivityId);

            for (MemberDescription member : members) {
                BigDecimal earnedAmount = getEarnedAmountByMember(conn, member.getId(), from, to);
                BigDecimal unpaidAmount = getUnpaidAmountByMember(conn, member.getId());


                CollectivityLocalStatistics stats = new CollectivityLocalStatistics();
                stats.setMemberDescription(member);
                stats.setEarnedAmount(earnedAmount);
                stats.setUnpaidAmount(unpaidAmount);

                statistics.add(stats);
            }

        } finally {
            if (conn != null) conn.close();
        }

        return statistics;
    }

    private List<MemberDescription> getMembersByCollectivityId(Connection conn, String collectivityId)
            throws SQLException {

        List<MemberDescription> members = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = """
                SELECT 
                    m.id,
                    m.first_name,
                    m.last_name,
                    m.email,
                    m.occupation
                FROM member m
                WHERE m.collectivity_id = ?
                ORDER BY m.last_name, m.first_name
            """;

            ps = conn.prepareStatement(sql);
            ps.setString(1, collectivityId);
            rs = ps.executeQuery();

            while (rs.next()) {
                MemberDescription member = new MemberDescription();
                member.setId(rs.getString("id"));
                member.setFirstName(rs.getString("first_name"));
                member.setLastName(rs.getString("last_name"));
                member.setEmail(rs.getString("email"));
                member.setOccupation(rs.getString("occupation"));
                members.add(member);
            }

        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }

        return members;
    }


    private BigDecimal getEarnedAmountByMember(Connection conn, String memberId, LocalDate from, LocalDate to)
            throws SQLException {

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = """
                SELECT 
                    COALESCE(SUM(mp.amount), 0) as earned_amount
                FROM member_payment mp
                WHERE mp.member_id = ?
                AND mp.creation_date BETWEEN ? AND ?
            """;

            ps = conn.prepareStatement(sql);
            ps.setString(1, memberId);
            ps.setDate(2, Date.valueOf(from));
            ps.setDate(3, Date.valueOf(to));
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getBigDecimal("earned_amount");
            }
            return BigDecimal.ZERO;

        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }
    }


    private BigDecimal getUnpaidAmountByMember(Connection conn, String memberId)
            throws SQLException {

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = """
                SELECT 
                    COALESCE(SUM(mf.amount), 0) -COALESCE(SUM(mp.amount), 0) as unpaid_amount
                FROM membership_fee mf
                LEFT JOIN member_payment mp ON mp.membership_fee_id = mf.id
                WHERE mp.member_id = ?
                AND mf.status = 'ACTIVE'
                AND mp.id IS NULL
            """;

            ps = conn.prepareStatement(sql);
            ps.setString(1, memberId);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getBigDecimal("unpaid_amount");
            }
            return BigDecimal.ZERO;

        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }
    }
}