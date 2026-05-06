package com.Federation.Final.entity.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CollectivityLocalStatistics {
    private MemberDescription memberDescription;
    private BigDecimal earnedAmount;
    private BigDecimal unpaidAmount;
}

