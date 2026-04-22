package com.Federation.Final.entity;

import lombok.Data;

@Data
public class MobileMoneyAccount extends FinancialAccount{
    private String holderName;
    private MobileMoneyAccount mobileMoneyAccount;
    private String mobileNumber;
}
