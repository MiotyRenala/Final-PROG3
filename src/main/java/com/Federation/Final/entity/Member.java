package com.Federation.Final.entity;

import com.Federation.Final.entity.Enum.GenderEnum;
import com.Federation.Final.entity.Enum.MemberOccupationEnum;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Member {
    private String id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private GenderEnum genderEnum;
    private String address;
    private Float phoneNumber;
    private String email;
    private String profession;
    private MemberOccupationEnum memberOccupation;

    private Map<String, String> refereesInfo;
    private String CollectivityId;

    private Map<String, String> decodeReferees(String data) {

        Map<String, String> map = new HashMap<>();

        if (data == null || data.isEmpty()) {
            return map;
        }

        String[] pairs = data.split(",");

        for (String pair : pairs) {
            String[] parts = pair.split(":");
            map.put(parts[0], parts[1]);
        }

        return map;
    }

}
