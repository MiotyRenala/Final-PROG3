package com.Federation.Final.entity;

import com.Federation.Final.entity.Enum.PaymentTypeEnum;
import com.Federation.Final.entity.Enum.ReasonEnum;
import lombok.Data;

@Data
public class Payment {
    private Integer id;
    private PaymentTypeEnum paymentTypeEnum;
    private ReasonEnum reasonEnum;
    private Member member;
}
