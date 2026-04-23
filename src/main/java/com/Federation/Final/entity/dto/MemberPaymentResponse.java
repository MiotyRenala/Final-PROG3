package com.Federation.Final.entity.dto;

import com.Federation.Final.entity.Enum.PaymentModeEnum;
import com.Federation.Final.entity.FinancialAccount;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MemberPaymentResponse {
    private String id;
    private double amount;
    private PaymentModeEnum paymentMode;
    private FinancialAccount accountCredited;
    private LocalDate creationDate;
}
