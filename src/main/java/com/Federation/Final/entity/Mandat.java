package com.Federation.Final.entity;

import lombok.Data;
import java.util.Date;

@Data
public class Mandat {
    private Integer id;
    private Date start_date;
    private Date end_date;
    private Integer year;
    private Collectivity collectivity;
}
