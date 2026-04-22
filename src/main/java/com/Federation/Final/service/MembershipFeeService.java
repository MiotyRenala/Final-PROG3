package com.Federation.Final.service;

import com.Federation.Final.entity.Enum.ActivityStatusEnum;
import com.Federation.Final.entity.MembershipFee;
import com.Federation.Final.repository.MembershipFeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MembershipFeeService {
    private final MembershipFeeRepository membershipFeeRepository;

    public MembershipFeeService(MembershipFeeRepository membershipFeeRepository) {
        this.membershipFeeRepository = membershipFeeRepository;
    }


    public List<MembershipFee> getByCollectivity(String collectivityId) {

        if (!membershipFeeRepository.existsCollectivityById(collectivityId)) {
            throw new RuntimeException("Collectivity not found");
        }
        return membershipFeeRepository.findByCollectivityId(collectivityId);
    }

    public List<MembershipFee> createAll(String collectivityId, List<MembershipFee> fees) {


        if (!membershipFeeRepository.existsCollectivityById(collectivityId)) {
            throw new RuntimeException("Collectivity not found");
        }


        for (MembershipFee fee : fees) {
            validate(fee);
        }

        for (MembershipFee fee : fees) {
            fee.setId(UUID.randomUUID().toString());
            fee.setStatus(ActivityStatusEnum.ACTIVATE);
        }

        membershipFeeRepository.saveAll(collectivityId, fees);

        return fees;
    }

    private void validate(MembershipFee fee) {

        if (fee.getAmount() <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        if (fee.getEligibleFrom() == null) {
            throw new IllegalArgumentException("eligibleFrom is required");
        }

        if (fee.getFrequency() == null) {
            throw new IllegalArgumentException("frequency is required");
        }
    }
}
