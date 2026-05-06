package com.Federation.Final.repository;

import com.Federation.Final.datasource.DataSource;
import com.Federation.Final.entity.Enum.AttendanceStatusEnum;
import com.Federation.Final.entity.dto.ActivityMemberAttendance;
import com.Federation.Final.entity.dto.CreateActivityMemberAttendance;
import com.Federation.Final.entity.dto.MemberDescription;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Repository
public class ActivityAttendanceRepository {
    private final DataSource dataSource;

    public List<ActivityMemberAttendance> saveAttendance(
            String activityId,
            List<CreateActivityMemberAttendance> attendances
    ) {

        String sql = """
            INSERT INTO activity_attendance (id, activity_id, member_id, status)
            VALUES (?, ?, ?, ?)
        """;

        List<ActivityMemberAttendance> result = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            for (CreateActivityMemberAttendance attendance : attendances) {

                preparedStatement.setString(2, attendance.getMemberIdentifier());
                preparedStatement.setString(3, attendance.getAttendanceStatusEnum().name());

                preparedStatement.addBatch();

                ActivityMemberAttendance entity = new ActivityMemberAttendance();
                entity.setAttendanceStatusEnum(attendance.getAttendanceStatusEnum());

                result.add(entity);
            }

            preparedStatement.executeBatch();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public List<ActivityMemberAttendance> findByActivityAttendanceId(String activityId) {

        String sql = """
        SELECT 
            aa.id,
            aa.status,
            m.id as member_id,
            m.first_name,
            m.last_name
        FROM activity_attendance aa
        JOIN member m ON aa.member_id = m.id
        WHERE aa.activity_id = ?
    """;

        List<ActivityMemberAttendance> result = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, activityId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                MemberDescription member = new MemberDescription();
                member.setId(rs.getString("member_id"));
                member.setFirstName(rs.getString("first_name"));
                member.setLastName(rs.getString("last_name"));

                ActivityMemberAttendance dto = new ActivityMemberAttendance();
                dto.setId(rs.getString("id"));
                dto.setAttendanceStatusEnum(
                        AttendanceStatusEnum.valueOf(rs.getString("status"))
                );
                dto.setMemberDescription(member);

                result.add(dto);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}
