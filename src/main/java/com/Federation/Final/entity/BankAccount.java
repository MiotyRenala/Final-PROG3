package com.Federation.Final.entity;

import com.Federation.Final.entity.Enum.BankNameEnum;
import lombok.Data;

@Data
public class BankAccount {
    private Integer id;
    private String holderName;
    private BankNameEnum bankNameEnum;
    private Float code;
    private Collectivity collectivity;
}
