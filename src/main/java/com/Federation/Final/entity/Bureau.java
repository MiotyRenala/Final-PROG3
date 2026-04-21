package com.Federation.Final.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Bureau {
    private Integer id;
    private String title;
    private Date start_date;
    private Date end_date;
    private Federation federation;

}
