package com.Federation.Final.service;

import com.Federation.Final.entity.Member;
import com.Federation.Final.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> createMembers(List<Member> members) {

        for (Member member : members) {

            Map<String, String> referees = member.getRefereesInfo();

            // rule 1
            if (referees == null || referees.size() < 2) {
                throw new RuntimeException("2 referees minimum required");
            }

            // get Referees'collectivities
            Map<String, String> refereesCollectivities =
                    memberRepository.getRefereesCollectivities(new ArrayList<>(referees.keySet()));

            int same = 0;
            int other = 0;

            for (String refereeId : referees.keySet()) {
                String collectivity = refereesCollectivities.get(refereeId);

                if (collectivity.equals(member.getCollectivityId())) {
                    same++;
                } else {
                    other++;
                }
            }

            // rule 2
            if (same < 1 && other > 1) {
                throw new RuntimeException(
                        "Inscription declined : there should be at least one referee from the collectivity to enter"
                );
            }

            for (Map.Entry<String, String> e : referees.entrySet()) {
                if (e.getValue() == null || e.getValue().isBlank()) {
                    throw new RuntimeException("Relationship obligatory " + e.getKey());
                }
            }
        }

        return memberRepository.createMembers(members);
    }
}
