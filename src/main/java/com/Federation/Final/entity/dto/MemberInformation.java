package com.Federation.Final.entity.dto;

import com.Federation.Final.entity.Enum.GenderEnum;
import com.Federation.Final.entity.Enum.MemberOccupationEnum;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MemberInformation {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private GenderEnum gender;
    private String address;
    private String profession;
    private String phoneNumber;
    private String email;
    private MemberOccupationEnum occupation;
}
