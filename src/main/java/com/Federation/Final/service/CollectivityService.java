package com.Federation.Final.service;

import com.Federation.Final.entity.Collectivity;
import com.Federation.Final.entity.CollectivityStructure;
import com.Federation.Final.entity.Member;
import com.Federation.Final.repository.CollectivityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectivityService {

    private final CollectivityRepository repository;

    public CollectivityService(CollectivityRepository repository) {
        this.repository = repository;
    }

    public List<Collectivity> createCollectivities(List<Collectivity> collectivities) {

        if (collectivities == null || collectivities.isEmpty()) {
            throw new RuntimeException("Collectivities list cannot be empty");
        }

        for (Collectivity c : collectivities) {

            if (c.getCollectivityStructure() == null) {
                throw new RuntimeException("Structure is required");
            }

            CollectivityStructure s = c.getCollectivityStructure();

            if (s.getPresident() == null ||
                    s.getVicePresident() == null ||
                    s.getSecretary() == null ||
                    s.getTreasurer() == null) {

                throw new RuntimeException("All structure roles are required");
            }


            if (c.getMembers().size() == 10) {
                throw new RuntimeException("10 members allowed per collectivity");
            }

            if (!c.isFederationApproval()) {
                throw new RuntimeException("Federation approval required");
            }

            for (Member m : c.getMembers()) {
                if (m.getId() == null) {
                    throw new RuntimeException("Member ID missing");
                }
            }
        }

        return repository.createCollectivities(collectivities);
    }

}
