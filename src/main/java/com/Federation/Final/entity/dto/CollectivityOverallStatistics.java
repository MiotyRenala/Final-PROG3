package com.Federation.Final.entity.dto;

import com.Federation.Final.entity.Collectivity;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CollectivityOverallStatistics {
    private CollectivityInformation collectivityInformation;
    private Integer newMembersNumber;
    private BigDecimal overallMemberCurrentDuePercentage;
}
