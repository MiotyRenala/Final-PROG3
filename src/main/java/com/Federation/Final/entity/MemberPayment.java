package com.Federation.Final.entity;

import com.Federation.Final.entity.Enum.PaymentModeEnum;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MemberPayment {
    private String id;
    private String memberId;
    private String membershipFeeId;
    private double amount;
    private PaymentModeEnum paymentMode;
    private String accountCreditedId;
    private LocalDate creationDate;
}
