package com.Federation.Final.entity.dto;

import com.Federation.Final.entity.Enum.PaymentModeEnum;
import lombok.Data;

@Data
public class CreateMemberPayment {
    private double amount;
    private String membershipFeeIdentifier;
    private String accountCreditedIdentifier;
    private PaymentModeEnum paymentMode;
}
