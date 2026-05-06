package com.Federation.Final.entity.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class CollectivityRawData {
    private String collectivityId;
    private String collectivityName;

    private String memberId;
    private LocalDate membershipDate;
    private boolean memberActive;

    private String feeStatus;
    private String paymentId;

}
