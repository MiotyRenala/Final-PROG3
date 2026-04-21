package com.Federation.Final.entity;

import lombok.Data;

@Data
public class MemberShip {
    private Integer id;
    private Collectivity collectivity;
    private Position position;
    private Mandat mandat;
    private Member member;
}
