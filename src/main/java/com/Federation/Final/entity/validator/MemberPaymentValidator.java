package com.Federation.Final.entity.validator;

import com.Federation.Final.entity.dto.CreateMemberPayment;

public class MemberPaymentValidator {
    public void validate(CreateMemberPayment dto) {
        if (dto.getAmount() <= 0) {
            throw new IllegalArgumentException("Amount must not be null or negative");
        }

        if (dto.getPaymentMode() == null) {
            throw new IllegalArgumentException("Payment mode is required");
        }

        if (dto.getMembershipFeeIdentifier() == null || dto.getMembershipFeeIdentifier().isBlank()) {
            throw new IllegalArgumentException("Membership fee identifier is required");
        }

        if (dto.getAccountCreditedIdentifier() == null || dto.getAccountCreditedIdentifier().isBlank()) {
            throw new IllegalArgumentException("Account credited identifier is required");
        }
    }
}
