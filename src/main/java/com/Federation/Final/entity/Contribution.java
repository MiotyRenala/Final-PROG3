package com.Federation.Final.entity;

import com.Federation.Final.entity.Enum.PaymentFrequencyEnum;
import com.Federation.Final.entity.Enum.PaymentTypeEnum;
import lombok.Data;

import java.util.Date;

@Data
public class Contribution {
    private Integer id;
    private PaymentTypeEnum paymentTypeEnum;
    private MemberShip memberShip;
    private Date paymentDate;
    private PaymentFrequencyEnum paymentFrequencyEnum;
    private Collectivity collectivity;
}
