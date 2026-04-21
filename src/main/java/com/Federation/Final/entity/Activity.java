package com.Federation.Final.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Activity {
    private Integer id;
    private Date activity_date;
    private String title;
    private Collectivity collectivity;
    private Federation federation;
}
