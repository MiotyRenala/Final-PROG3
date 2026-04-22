package com.Federation.Final.entity.validator;

import com.Federation.Final.entity.dto.CreateMembershipFee;

public class MembershipFeeValidator {

    private void validate(CreateMembershipFee dto) {

        if (dto.getAmount() == null || dto.getAmount() <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        if (dto.getEligibleFrom() == null) {
            throw new IllegalArgumentException("eligibleFrom is required");
        }

        if (dto.getFrequency() == null || dto.getFrequency().isBlank()) {
            throw new IllegalArgumentException("frequency is required");
        }
    }
}
