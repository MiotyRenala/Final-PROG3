package com.Federation.Final.entity;

import com.Federation.Final.entity.Enum.GenderEnum;
import com.Federation.Final.entity.Enum.MemberOccupationEnum;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class Member {
    private String id;
    private String firstName;
    private String lastName;
    private GenderEnum genderEnum;
    private String address;
    private Float phoneNumber;
    private String email;
    private String profession;
    private MemberOccupationEnum memberOccupation;
    private List<Member> referees;

}
