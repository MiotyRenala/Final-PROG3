package com.Federation.Final.entity.dto;

import com.Federation.Final.entity.Enum.ActivityType;
import com.Federation.Final.entity.MonthlyRecurrenceRule;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CreateCollectivityActivity {
    private String label;
    private ActivityType activityType;  // MEETING, TRAINING, OTHER
    private List<String> memberOccupationConcerned;
    private MonthlyRecurrenceRule recurrenceRule;
    private LocalDate executiveDate;
}
