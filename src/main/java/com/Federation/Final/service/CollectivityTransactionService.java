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

        if (!collectivityRepository.existsById(collectivityId)) {
            throw new IllegalArgumentException("Collectivity not found with id: " + collectivityId);
        }

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

    public List<CollectivityTransaction> getTransactionsByPeriodAndMinAmount(
            String collectivityId,
            LocalDate from,
            LocalDate to,
            Double minAmount) throws SQLException {

        List<CollectivityTransaction> transactions = getTransactionsByPeriod(collectivityId, from, to);

        if (minAmount != null && minAmount > 0) {
            transactions = transactions.stream()
                    .filter(t -> t.getAmount() >= minAmount)
                    .toList();
        }

        return transactions;
    }

    public double getTotalTransactionAmount(String collectivityId, LocalDate from, LocalDate to) throws SQLException {
        List<CollectivityTransaction> transactions = getTransactionsByPeriod(collectivityId, from, to);
        return transactions.stream()
                .mapToDouble(CollectivityTransaction::getAmount)
                .sum();
    }

    public java.util.Map<PaymentModeEnum, Double> getTransactionAmountByPaymentMode(
            String collectivityId,
            LocalDate from,
            LocalDate to) throws SQLException {

        List<CollectivityTransaction> transactions = getTransactionsByPeriod(collectivityId, from, to);
        java.util.Map<PaymentModeEnum, Double> amountsByMode = new java.util.HashMap<>();

        for (PaymentModeEnum mode : PaymentModeEnum.values()) {
            amountsByMode.put(mode, 0.0);
        }

        for (CollectivityTransaction transaction : transactions) {
            PaymentModeEnum mode = transaction.getPaymentMode();
            amountsByMode.put(mode, amountsByMode.get(mode) + transaction.getAmount());
        }

        return amountsByMode;
    }

    public boolean hasTransactions(String collectivityId, LocalDate from, LocalDate to) throws SQLException {
        List<CollectivityTransaction> transactions = getTransactionsByPeriod(collectivityId, from, to);
        return !transactions.isEmpty();
    }

    public List<CollectivityTransaction> getLatestTransactions(String collectivityId, int limit) throws SQLException {
        LocalDate to = LocalDate.now();
        LocalDate from = to.minusYears(1);

        List<CollectivityTransaction> transactions = getTransactionsByPeriod(collectivityId, from, to);

        if (transactions.size() > limit) {
            transactions = transactions.subList(0, limit);
        }

        return transactions;
    }
}
