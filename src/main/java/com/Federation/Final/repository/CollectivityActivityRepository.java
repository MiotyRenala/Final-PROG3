package com.Federation.Final.repository;


import com.Federation.Final.datasource.DataSource;
import com.Federation.Final.entity.CollectivityActivity;
import com.Federation.Final.entity.Enum.ActivityType;
import com.Federation.Final.entity.MonthlyRecurrenceRule;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Repository
public class CollectivityActivityRepository {
    private final DataSource dataSource;

    public CollectivityActivityRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<CollectivityActivity> findActivityByCollectivityId(String collectivityId) throws SQLException {
        List<CollectivityActivity> activities = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();
            String sql = """
                SELECT id, label, activity_type, member_occupation_concerned,
                       recurrence_week_ordinal, recurrence_day_of_week, executive_date, collectivity_id
                FROM collectivity_activity
                WHERE collectivity_id = ?
                ORDER BY COALESCE(executive_date, DATE('9999-12-31')), id
            """;

            ps = conn.prepareStatement(sql);
            ps.setString(1, collectivityId);
            ps = conn.prepareStatement(sql);
            ps.setString(1, collectivityId);
            rs = ps.executeQuery();

            while (rs.next()) {
                activities.add(mapResultSetToActivity(rs));
            }

        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }

        return activities;
    }

    public List<CollectivityActivity> saveAll(String collectivityId, List<CollectivityActivity> activities)
            throws SQLException {

        List<CollectivityActivity> saved = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);

            String sql = """
                INSERT INTO collectivity_activity 
                (id, collectivity_id, label, activity_type, member_occupation_concerned, 
                 recurrence_week_ordinal, recurrence_day_of_week, executive_date)
                VALUES (?, ?, ?, ?::activity_type_enum, ?, ?, ?, ?)
            """;

            ps = conn.prepareStatement(sql);

            for (CollectivityActivity activity : activities) {
                if (activity.getId() == null) {
                    activity.setId(UUID.randomUUID().toString());
                }
                activity.setCollectivityId(collectivityId);

                ps.setString(1, activity.getId());
                ps.setString(2, collectivityId);
                ps.setString(3, activity.getLabel());
                ps.setString(4, activity.getActivityType().name());


                String occupations = String.join(",", activity.getMemberOccupationConcerned());
                ps.setString(5, occupations);


                if (activity.getRecurrenceRule() != null) {
                    ps.setInt(6, activity.getRecurrenceRule().getWeekOrdinal());
                    ps.setString(7, activity.getRecurrenceRule().getDayOfWeek());
                    ps.setDate(8, null);
                } else {
                    ps.setNull(6, Types.INTEGER);
                    ps.setNull(7, Types.VARCHAR);
                    ps.setDate(8, Date.valueOf(activity.getExecutiveDate()));
                }

                ps.addBatch();
                saved.add(activity);
            }

            ps.executeBatch();
            conn.commit();

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            throw e;
        } finally {
            if (ps != null) ps.close();
        }

        return saved;
    }

        public boolean collectivityExists (String collectivityId) throws SQLException {
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;

            try {
                conn = dataSource.getConnection();
                String sql = "SELECT COUNT(id) FROM collectivity WHERE id = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, collectivityId);
                rs = ps.executeQuery();

                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
                return false;

            } finally {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();


            }
        }
        private CollectivityActivity mapResultSetToActivity(ResultSet rs) throws SQLException {
            CollectivityActivity activity = new CollectivityActivity();
            activity.setId(rs.getString("id"));
            activity.setLabel(rs.getString("label"));
            activity.setActivityType(ActivityType.valueOf(rs.getString("activity_type")));
            activity.setCollectivityId(rs.getString("collectivity_id"));


            String occupations = rs.getString("member_occupation_concerned");
            if (occupations != null && !occupations.isEmpty()) {
                activity.setMemberOccupationConcerned(Arrays.asList(occupations.split(",")));
            }


            int weekOrdinal = rs.getInt("recurrence_week_ordinal");
            if (!rs.wasNull() && weekOrdinal > 0) {
                MonthlyRecurrenceRule rule = new MonthlyRecurrenceRule();
                rule.setWeekOrdinal(weekOrdinal);
                rule.setDayOfWeek(rs.getString("recurrence_day_of_week"));
                activity.setRecurrenceRule(rule);
            }
                Date executiveDate = rs.getDate("executive_date");
                if (executiveDate != null) {
                    activity.setExecutiveDate(executiveDate.toLocalDate());
                }

                return activity;
            }
    }