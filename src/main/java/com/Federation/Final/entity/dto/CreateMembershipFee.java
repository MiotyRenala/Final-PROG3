package com.Federation.Final.entity.dto;

import com.Federation.Final.entity.Enum.FrequencyEnum;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateMembershipFee {
    private LocalDate eligibleFrom;
    private FrequencyEnum frequency;
    private Double amount;
    private String label;
}
