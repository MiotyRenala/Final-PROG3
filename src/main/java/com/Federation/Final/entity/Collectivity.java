package com.Federation.Final.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Collectivity {
    private Integer id;
    private String name;
    private String city;
    private Date creationDate;
    private String speciality;
    private com.Federation.Final.entity.StatusEnum statusEnum;

}
