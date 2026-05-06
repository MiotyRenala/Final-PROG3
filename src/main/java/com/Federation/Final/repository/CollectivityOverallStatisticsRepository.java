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
           c.id AS collectivity_id,
           c.location,
       
           COUNT(DISTINCT CASE
               WHEN m.active = true THEN m.id
           END) AS total_members,
       
           COUNT(DISTINCT CASE
               WHEN m.active = true
               AND mf.status = 'ACTIVE'
               AND mp.id IS NOT NULL
               THEN m.id
           END) AS up_to_date_members,
       
           COUNT(DISTINCT CASE
               WHEN m.membership_date BETWEEN ? AND ?
               THEN m.id
           END) AS new_members,
       
           CASE
               WHEN COUNT(DISTINCT CASE WHEN m.active = true THEN m.id END) = 0
               THEN 0
               ELSE ROUND(
                   COUNT(DISTINCT CASE
                       WHEN m.active = true
                       AND mf.status = 'ACTIVE'
                       AND mp.id IS NOT NULL
                       THEN m.id
                   END)::numeric
                   /
                   COUNT(DISTINCT CASE
                       WHEN m.active = true THEN m.id
                   END) * 100
               , 2)
           END AS due_percentage
       
       FROM collectivity c
       
       LEFT JOIN member m ON m.collectivity_id = c.id
       LEFT JOIN membership_fee mf ON mf.collectivity_id = c.id
       LEFT JOIN member_payment mp
           ON mp.member_id = m.id
           AND mp.membership_fee_id = mf.id
       
       GROUP BY c.id, c.location;
    """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setObject(1, from);
            stmt.setObject(2, to);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();

                row.put("collectivityId", rs.getString("collectivity_id"));
                row.put("location", rs.getString("location"));
                row.put("totalMembers", rs.getInt("total_members"));
                row.put("upToDateMembers", rs.getInt("up_to_date_members"));
                row.put("newMembers", rs.getInt("new_members"));

                results.add(row);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return results;
    }
}
