package com.Federation.Final.repository;

import com.Federation.Final.datasource.DataSource;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Repository
public class CollectivityOverallStatisticsRepository {
     private final DataSource dataSource;

    public List<Map<String, Object>> fetchOverallStatistics(LocalDate from, LocalDate to) {

        List<Map<String, Object>> results = new ArrayList<>();

        String sql = """
     
     
         SELECT
     
             c.number,
     
             c.name,
     
             COUNT(DISTINCT cm.id) AS total_members,
     
             COUNT(DISTINCT ca.id) AS total_activities,
     
             COUNT(DISTINCT CASE
     
                 WHEN ca.executive_date IS NOT NULL
     
                 THEN ca.id END) AS total_one_time_activities,
     
             COUNT(DISTINCT CASE
     
                 WHEN ca.recurrence_day_of_week IS NOT NULL
     
                 THEN ca.id END) AS total_recurring_activities,
     
             COUNT(DISTINCT aa.id) AS total_attendance_records,
     
             COUNT(DISTINCT CASE
     
                 WHEN aa.attendance_status = 'ATTENDED'
     
                 THEN aa.id END) AS total_attended,
     
             COUNT(DISTINCT CASE
     
                 WHEN aa.attendance_status = 'MISSING'
     
                 THEN aa.id END) AS total_missing,
     
             COUNT(DISTINCT CASE
     
                 WHEN aa.attendance_status = 'UNDEFINED'
     
                 THEN aa.id END) AS total_undefined,
     
             CASE
     
                 WHEN COUNT(CASE WHEN aa.attendance_status IN ('ATTENDED', 'MISSING') THEN 1 END) = 0
     
                 THEN NULL
     
                 ELSE ROUND(
     
                     COUNT(CASE WHEN aa.attendance_status = 'ATTENDED' THEN 1 END) * 100.0
     
                     /
     
                     COUNT(CASE WHEN aa.attendance_status IN ('ATTENDED', 'MISSING') THEN 1 END),
     
                     2
     
                 )
     
             END AS attendance_rate_percent
     
         FROM collectivity c
     
         LEFT JOIN member cm
     
             ON cm.collectivity_id = c.id
     
         LEFT JOIN collectivity_activity ca
     
             ON ca.collectivity_id = c.id
     
         LEFT JOIN activity_attendance aa
     
             ON aa.activity_id = ca.id
     
         WHERE (ca.executive_date BETWEEN ? AND ?)
     
            OR (ca.recurrence_day_of_week IS NOT NULL)
     
         GROUP BY
     
             c.id,
     
             c.name,
     
             c.number
     
         ORDER BY
     
             attendance_rate_percent DESC NULLS LAST
     
     
    """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setObject(1, from);
            stmt.setObject(2, to);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();

                row.put("name", rs.getString("name"));
                row.put("number", rs.getInt("number"));
                row.put("totalMembers", rs.getInt("total_members"));
                row.put("totalActivities", rs.getInt("total_activities"));
                row.put("totalOneTimeActivities", rs.getInt("total_one_time_activities"));
                row.put("totalRecurringActivities", rs.getInt("total_recurring_activities"));
                row.put("totalAttendanceRecords", rs.getInt("total_attendance_records"));
                row.put("totalAttended", rs.getInt("total_attended"));
                row.put("totalMissing", rs.getInt("total_missing"));
                row.put("totalUndefined", rs.getInt("total_undefined"));
                row.put("attendanceRatePercent", rs.getBigDecimal("attendance_rate_percent"));

                results.add(row);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return results;
    }

}
