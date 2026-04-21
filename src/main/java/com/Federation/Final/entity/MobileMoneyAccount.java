package com.Federation.Final.entity;

import com.Federation.Final.entity.Enum.ServiceEnum;
import lombok.Data;

@Data
public class MobileMoneyAccount {
    private Integer id;
    private String userName;
    private Float phoneNumber;
    private ServiceEnum serviceEnum;
    private Collectivity collectivity;
}
