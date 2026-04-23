package com.Federation.Final.entity;

import com.Federation.Final.entity.Enum.PaymentModeEnum;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CollectivityTransaction {
    private String id;
    private LocalDate creationDate;
    private double amount;
    private PaymentModeEnum paymentMode;

    private String collectivityId;

    private FinancialAccount accountCredited;
    private String memberDebitedId;
    private Member memberDebited;
}
