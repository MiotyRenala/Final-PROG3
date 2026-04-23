package com.Federation.Final.service;

import com.Federation.Final.entity.FinancialAccount;
import com.Federation.Final.repository.CollectivityRepository;
import com.Federation.Final.repository.FinancialAccountRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Service
public class FinancialAccountService {

    private final FinancialAccountRepository financialAccountRepository;
    private final CollectivityRepository collectivityRepository;

    public FinancialAccountService(FinancialAccountRepository financialAccountRepository,
                                   CollectivityRepository collectivityRepository) {
        this.financialAccountRepository = financialAccountRepository;
        this.collectivityRepository = collectivityRepository;
    }

    public List<FinancialAccount> getAccountsByCollectivity(String collectivityId, LocalDate date) throws SQLException {
        // Vérifier que la collectivité existe
        if (!collectivityRepository.existsById(collectivityId)) {
            throw new IllegalArgumentException("Collectivity not found with id: " + collectivityId);
        }

        return financialAccountRepository.findByCollectivityIdAndDate(collectivityId, date);
    }
}