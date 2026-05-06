package com.Federation.Final.repository;

import com.Federation.Final.datasource.DataSource;
import com.Federation.Final.entity.dto.CollectivityLocalStatistics;
import com.Federation.Final.entity.dto.MemberDescription;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class LocalStatisticRepository {


    private DataSource dataSource;

    public LocalStatisticRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Map<String, Object>> getLocalStatistics(LocalDate from, LocalDate to) {
        String sql = """
           SELECT
                          c.id,
                          c.number,
                          c.name,
                          COUNT(DISTINCT m.id) AS total_members,
                          COUNT(DISTINCT ca.id) AS total_activities
                      FROM collectivity c
                      LEFT JOIN member m ON m.collectivity_id = c.id
                      LEFT JOIN collectivity_activity ca\s
                          ON ca.collectivity_id = c.id
                         AND (
                              ca.executive_date BETWEEN ? AND ?
                              OR ca.recurrence_day_of_week IS NOT NULL
                         )
                      GROUP BY c.id, c.number, c.name
                      ORDER BY c.number
        """;
        return executeQuery(sql, from, to);
    }

    public List<Map<String, Object>> fetchLocalActivityBreakdown(LocalDate from, LocalDate to) {
        String sql = """
            SELECT
                c.id,
                COUNT(DISTINCT CASE WHEN ca.executive_date IS NOT NULL THEN ca.id END)        AS total_one_time_activities,
                COUNT(DISTINCT CASE WHEN ca.recurrence_day_of_week IS NOT NULL THEN ca.id END) AS total_recurring_activities
            FROM collectivity c
            LEFT JOIN collectivity_activity ca ON ca.collectivity_id = c.id
            WHERE (ca.executive_date BETWEEN ? AND ?)
               OR (ca.recurrence_day_of_week IS NOT NULL)
            GROUP BY c.id
        """;
        return executeQuery(sql, from, to);
    }

    public List<Map<String, Object>> fetchLocalAttendanceStatistics(LocalDate from, LocalDate to) {
        String sql = """
            SELECT
                                         m.id AS member_id,
                                         COUNT(DISTINCT aa.id) AS total_attendance_records,
                                         COUNT(DISTINCT CASE WHEN aa.attendance_status = 'ATTENDED' THEN aa.id END) AS total_attended,
                                         COUNT(DISTINCT CASE WHEN aa.attendance_status = 'MISSING' THEN aa.id END) AS total_missing,
                                         COUNT(DISTINCT CASE WHEN aa.attendance_status = 'UNDEFINED' THEN aa.id END) AS total_undefined,
                                         CASE
                                             WHEN COUNT(CASE WHEN aa.attendance_status IN ('ATTENDED','MISSING') THEN 1 END) = 0 THEN NULL
                                             ELSE ROUND(
                                                 COUNT(CASE WHEN aa.attendance_status = 'ATTENDED' THEN 1 END) * 100.0
                                                 / COUNT(CASE WHEN aa.attendance_status IN ('ATTENDED','MISSING') THEN 1 END), 2
                                             )
                                         END AS attendance_rate_percent
                                     FROM member m
                                     LEFT JOIN collectivity_activity ca\s
                                         ON ca.collectivity_id = m.collectivity_id
                                        AND (
                                             ca.executive_date BETWEEN ? AND ?
                                             OR ca.recurrence_day_of_week IS NOT NULL
                                        )
                                     LEFT JOIN activity_attendance aa\s
                                         ON aa.activity_id = ca.id AND aa.member_id = m.id
                                     WHERE m.collectivity_id = ?
                                     GROUP BY m.id
        """;
        return executeQuery(sql, from, to);
    }


    public List<Map<String, Object>> fetchMemberList(String collectivityId) {
        String sql = """
             SELECT
                          m.id AS member_id,
                          m.first_name,
                          m.last_name
                      FROM member m
                      WHERE m.collectivity_id = ?
        """;
        return executeQuery(sql, collectivityId);
    }

    public List<Map<String, Object>> fetchMemberAttendanceStatistics(String collectivityId, LocalDate from, LocalDate to) {
        String sql = """
            SELECT
                m.id AS member_id,
                COUNT(DISTINCT aa.id) AS total_attendance_records,
                COUNT(DISTINCT CASE WHEN aa.attendance_status = 'ATTENDED'  THEN aa.id END) AS total_attended,
                COUNT(DISTINCT CASE WHEN aa.attendance_status = 'MISSING'   THEN aa.id END) AS total_missing,
                COUNT(DISTINCT CASE WHEN aa.attendance_status = 'UNDEFINED' THEN aa.id END) AS total_undefined,
                CASE
                    WHEN COUNT(CASE WHEN aa.attendance_status IN ('ATTENDED', 'MISSING') THEN 1 END) = 0 THEN NULL
                    ELSE ROUND(
                        COUNT(CASE WHEN aa.attendance_status = 'ATTENDED' THEN 1 END) * 100.0
                        / COUNT(CASE WHEN aa.attendance_status IN ('ATTENDED', 'MISSING') THEN 1 END), 2
                    )
                END AS attendance_rate_percent
            FROM collectivity c
            
            JOIN member m              ON m.id = c
            .member_id
            LEFT JOIN collectivity_activity ca ON ca.collectivity_id = c
            .id
            LEFT JOIN activity_attendance aa   ON aa.activity_id = ca.id AND aa.member_id = m.id
            WHERE m
            .id = ?
              AND (
                  (ca.executive_date BETWEEN ? AND ?)
                  OR (ca.recurrence_day_of_week IS NOT NULL)
              )
            GROUP BY m.id
        """;
        return executeQuery(sql, from, to, collectivityId);
    }

    public List<Map<String, Object>> fetchMemberActivityBreakdown(String collectivityId, LocalDate from, LocalDate to) {
        String sql = """
            SELECT
                                         m.id AS member_id,
                                         COUNT(DISTINCT CASE WHEN ca.executive_date IS NOT NULL THEN aa.id END) AS total_one_time_attended,
                                         COUNT(DISTINCT CASE WHEN ca.recurrence_day_of_week IS NOT NULL THEN aa.id END) AS total_recurring_attended
                                     FROM member m
                                     LEFT JOIN collectivity_activity ca
                                         ON ca.collectivity_id = m.collectivity_id
                                        AND (
                                             ca.executive_date BETWEEN ? AND ?
                                             OR ca.recurrence_day_of_week IS NOT NULL
                                        )
                                     LEFT JOIN activity_attendance aa
                                         ON aa.activity_id = ca.id AND aa.member_id = m.id
                                     WHERE m.collectivity_id = ?
                                     GROUP BY m.id
        """;
        return executeQuery(sql, from, to, collectivityId);
    }


    private List<Map<String, Object>> executeQuery(String sql, Object... params) {
        List<Map<String, Object>> results = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }

            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData meta = rs.getMetaData();
            int columnCount = meta.getColumnCount();

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.put(meta.getColumnLabel(i), rs.getObject(i));
                }
                results.add(row);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return results;
    }
}