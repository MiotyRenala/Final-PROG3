package com.Federation.Final.entity.validator;

import com.Federation.Final.entity.Enum.MemberOccupationEnum;
import com.Federation.Final.entity.Member;
import com.Federation.Final.entity.dto.CreateMember;
import com.Federation.Final.repository.MemberRepository;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
public class MemberValidator {
    private final MemberRepository memberRepository;

    public MemberValidator(MemberRepository memberRepository) {
        this.memberRepository = memberRepository; }

    public void validate(CreateMember dto) throws SQLException {
        if (!dto.isRegistrationFeePaid() || !dto.isMembershipDuesPaid())
            throw new IllegalArgumentException("Membership Fee and Adhesion Fee should be paid");
        if (dto.getReferees() == null || dto.getReferees().size() < 2)
            throw new IllegalArgumentException("minimum 2 referees required");
        List<Member> referees = memberRepository.findByIds(dto.getReferees());
        if (referees.size() != dto.getReferees().size())
            throw new IllegalArgumentException("Referee(s) not existed");
        for (Member ref : referees) {
            if (ref.getOccupation() != MemberOccupationEnum.SENIOR)
                throw new IllegalArgumentException("The referee " + ref.getId() + " is not a confirmed member");
        }
        String targetCollectivity = dto.getCollectivityIdentifier();
        if (targetCollectivity != null && !targetCollectivity.isBlank()) {
            long inside = referees.stream().filter(r -> targetCollectivity.equals(r.getCollectivityId())).count();
            long outside = referees.size() - inside;
            if (inside < outside)
                throw new IllegalArgumentException("Referees conditions not respected : need the same number for extern referee and intern referee");
        }
    }
}
