package com.Federation.Final.entity;

import com.Federation.Final.entity.Enum.GenderEnum;
import com.Federation.Final.entity.Enum.MemberOccupationEnum;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

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

    private List<String> refereesId;
    private String CollectivityId;

}
