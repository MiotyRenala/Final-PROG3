package com.Federation.Final.entity.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CollectivityOverallStatistics {
    private CollectivityInformation collectivityInformation;
    private BigDecimal attendanceRatePercent;
    private Integer totalMembers;
    private Integer totalActivities;
    private Integer totalOneTimeActivities;
    private Integer totalRecurringActivities;
    private Integer totalAttendanceRecords;
    private Integer totalAttended;
    private Integer totalMissing;
    private Integer totalUndefined;
}

