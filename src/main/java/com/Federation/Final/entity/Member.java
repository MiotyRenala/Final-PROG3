package com.Federation.Final.entity;

import com.Federation.Final.entity.Enum.GenderEnum;
import lombok.Data;
import java.util.Date;

@Data
public class Member {
    private Integer id;
    private String name;
    private GenderEnum gengerEnum;
    private String activity;
    private Float phoneNumber;
    private String email;
    private Date joining_date;
    private Boolean active;
}
