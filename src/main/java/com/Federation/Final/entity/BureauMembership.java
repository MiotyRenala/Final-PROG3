package com.Federation.Final.entity;

import lombok.Data;

@Data
public class BureauMembership {
    private Integer id;
    private Mandat mandat;
    private Collectivity collectivity;
    private Member member;
    private Position position;
}
