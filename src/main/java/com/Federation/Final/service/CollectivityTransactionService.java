package com.Federation.Final.service;

import com.Federation.Final.entity.CollectivityTransaction;
import com.Federation.Final.entity.Enum.PaymentModeEnum;
import com.Federation.Final.repository.CollectivityRepository;
import com.Federation.Final.repository.CollectivityTransactionRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Service
public class CollectivityTransactionService {

    private final CollectivityTransactionRepository transactionRepository;
    private final CollectivityRepository collectivityRepository;

    public CollectivityTransactionService(
            CollectivityTransactionRepository transactionRepository,
            CollectivityRepository collectivityRepository) {
        this.transactionRepository = transactionRepository;
        this.collectivityRepository = collectivityRepository;
    }

    public List<CollectivityTransaction> getTransactionsByPeriod(
            String collectivityId,
            LocalDate from,
            LocalDate to) throws SQLException {

        // Vérifier que la collectivité existe
        if (!collectivityRepository.existsById(collectivityId)) {
            throw new IllegalArgumentException("Collectivity not found with id: " + collectivityId);
        }

        // Vérifier les dates
        if (from == null || to == null) {
            throw new IllegalArgumentException("'from' and 'to' dates are required");
        }

        if (from.isAfter(to)) {
            throw new IllegalArgumentException("'from' date must be before or equal to 'to' date");
        }

        return transactionRepository.findByCollectivityAndPeriod(collectivityId, from, to);
    }

    public List<CollectivityTransaction> getTransactionsByPeriodAndPaymentMode(
            String collectivityId,
            LocalDate from,
            LocalDate to,
            PaymentModeEnum paymentMode) throws SQLException {

        List<CollectivityTransaction> transactions = getTransactionsByPeriod(collectivityId, from, to);

        if (paymentMode != null) {
            transactions = transactions.stream()
                    .filter(t -> t.getPaymentMode() == paymentMode)
                    .toList();
        }

        return transactions;
    }
}