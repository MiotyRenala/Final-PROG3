package com.Federation.Final.entity;

import com.Federation.Final.entity.Enum.AccountTypeEnum;
import lombok.Data;

@Data
public class FinancialAccount {
    private String id;
    private double amount;
    private AccountTypeEnum type;
}
