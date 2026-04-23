package com.Federation.Final.entity;

import com.Federation.Final.entity.Enum.ActivityStatusEnum;
import com.Federation.Final.entity.Enum.FrequencyEnum;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MembershipFee {
    private String id;
    private String collectivityId;
    private LocalDate eligibleFrom;
    private FrequencyEnum frequency;
    private Double amount;
    private String label;
    private ActivityStatusEnum status;
}
