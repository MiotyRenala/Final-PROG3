package com.Federation.Final.entity;

import lombok.Data;

@Data
public class BankAccount extends FinancialAccount{
    private String holderName;
    private String bankName;
    private int bankCode;
    private int bankBranchCode;
    private int bankAccountNumber;
    private int bankAccountKey;
}
