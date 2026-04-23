package com.Federation.Final.entity;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
public class Collectivity {
    private String id;
    private String name;
    private Integer uniqueNumber;
    private String location;
    private List<Member> members;
    private Member president;
    private Member vicePresident;
    private Member treasurer;
    private Member secretary;
    private LocalDate creationDate;
    private boolean federationApproval;

}
