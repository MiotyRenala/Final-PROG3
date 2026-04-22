package com.Federation.Final.entity;

import com.Federation.Final.entity.Enum.MobileMoneyServiceEnum;
import lombok.Data;

@Data
public class MobileMoneyAccount extends FinancialAccount{
    private String holderName;
    private MobileMoneyServiceEnum mobileMoneyService;
    private String mobileNumber;
}
