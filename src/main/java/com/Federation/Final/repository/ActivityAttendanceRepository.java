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
            INSERT INTO activity_attendance (id, activity_id, member_id, attendance_status)
            VALUES (?, ?, ?, ?::attendance_status_enum)
        """;

        List<ActivityMemberAttendance> result = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            for (CreateActivityMemberAttendance attendance : attendances) {

                String id = UUID.randomUUID().toString();

                preparedStatement.setString(1, id);
                preparedStatement.setString(2, activityId);
                preparedStatement.setString(3, attendance.getMemberIdentifier());
                preparedStatement.setString(4, attendance.getAttendanceStatusEnum().name());

                preparedStatement.addBatch();

                ActivityMemberAttendance dto = new ActivityMemberAttendance();
                dto.setId(id);
                dto.setAttendanceStatusEnum(attendance.getAttendanceStatusEnum());

                result.add(dto);
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
            aa.attendance_status,
            m.id as member_id,
            m.first_name,
            m.last_name,
            m.email,
            m.occupation
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
                member.setEmail(rs.getString("email"));
                member.setOccupation(rs.getString("occupation"));

                ActivityMemberAttendance dto = new ActivityMemberAttendance();
                dto.setId(rs.getString("id"));
                dto.setAttendanceStatusEnum(
                        AttendanceStatusEnum.valueOf(rs.getString("attendance_status"))
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
