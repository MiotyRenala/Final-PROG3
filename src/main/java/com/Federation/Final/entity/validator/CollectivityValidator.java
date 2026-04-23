package com.Federation.Final.entity.validator;

import com.Federation.Final.entity.Member;
import com.Federation.Final.entity.dto.CreateCollectivity;
import com.Federation.Final.entity.dto.CreateCollectivityStructure;
import com.Federation.Final.repository.MemberRepository;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class CollectivityValidator {
    private final MemberRepository memberRepository;

    public CollectivityValidator(MemberRepository memberRepository) {
        this.memberRepository = memberRepository; }

    public void validate(CreateCollectivity dto) throws SQLException {
        if (!dto.isFederationApproval())
            throw new IllegalArgumentException("Federation permission required");

        if (dto.getMembers() == null || dto.getMembers().size() < 10)
            throw new IllegalArgumentException("At least 10 members required");

        List<Member> members = memberRepository.findByIds(dto.getMembers());
        if (members.size() != dto.getMembers().size())
            throw new IllegalArgumentException("Members doesn't exist");

        long count = members.stream().filter(m -> ChronoUnit.MONTHS.between(m.getMembershipDate(), LocalDate.now()) >= 6).count();
        if (count < 5)
            throw new IllegalArgumentException("At least 5 members need more than 6 months of seniority");

        CreateCollectivityStructure struct = dto.getStructure();
        if (struct == null) throw new IllegalArgumentException("Required structure");

        List<String> roleIds = List.of(struct.getPresident(), struct.getVicePresident(), struct.getTreasurer(), struct.getSecretary());
        if (roleIds.stream().anyMatch(id -> id == null || id.isBlank()))
            throw new IllegalArgumentException("All specific positions must be filled.");

        List<Member> roleMembers = memberRepository.findByIds(roleIds);
        if (roleMembers.size() != roleIds.size())
            throw new IllegalArgumentException("Some members of the structure do not exist");

        List<String> memberIds = dto.getMembers();
        for (String roleId : roleIds) {
            if (!memberIds.contains(roleId))
                throw new IllegalArgumentException("Member : " + roleId + " is not in the founder list");
        }
    }
}
