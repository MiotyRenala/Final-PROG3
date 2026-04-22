package com.Federation.Final.repository;

import com.Federation.Final.entity.Enum.GenderEnum;
import com.Federation.Final.entity.Enum.MemberOccupationEnum;
import com.Federation.Final.entity.Member;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.*;

@Repository
public class MemberRepository {
    Connection connection;

    public List<Member> createMembers(List<Member> members){

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO member (" +
                            "id, first_name, last_name, birth_date, gender, address, profession, " +
                            "phone_number, email, occupation, referee_id" +
                            ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
            );
            for (Member member : members) {

                preparedStatement.setString(1, member.getId());
                preparedStatement.setString(2, member.getFirstName());
                preparedStatement.setString(3, member.getLastName());
                Timestamp  birthDate = Timestamp.valueOf(member.getBirthDate().atStartOfDay());
                preparedStatement.setTimestamp(4,birthDate);
                preparedStatement.setString(5, member.getGenderEnum().name());
                preparedStatement.setString(6, member.getAddress());
                preparedStatement.setString(7, member.getProfession());
                preparedStatement.setFloat(8, member.getPhoneNumber());
                preparedStatement.setString(9, member.getEmail());
                preparedStatement.setString(10, member.getMemberOccupation().name());
                preparedStatement.setString(11, member.getRefereesId().get(1));
                preparedStatement.setString(11, member.getRefereesId().get(2));

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return members;
    }

    public Map<String, String> getRefereesCollectivities(List<String> refereeIds) {

        Map<String, String> result = new HashMap<>();

        String placeholders = refereeIds.stream()
                .map(id -> "?")
                .collect(Collectors.joining(","));

        String sql = "SELECT id, collectivity_id FROM member WHERE id IN (" + placeholders + ")";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            for (int i = 0; i < refereeIds.size(); i++) {
                ps.setString(i + 1, refereeIds.get(i));
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                result.put(
                        rs.getString("id"),
                        rs.getString("collectivity_id")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}
