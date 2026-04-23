package com.Federation.Final.controller;

import com.Federation.Final.entity.CollectivityTransaction;
import com.Federation.Final.entity.Enum.PaymentModeEnum;
import com.Federation.Final.service.CollectivityTransactionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/collectivities")
public class CollectivityTransactionController {

    private final CollectivityTransactionService transactionService;

    public CollectivityTransactionController(CollectivityTransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<CollectivityTransaction>> getTransactions(
            @PathVariable String id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
            @RequestParam(required = false) PaymentModeEnum paymentMode,
            @RequestParam(required = false) Double minAmount) throws SQLException {

        List<CollectivityTransaction> transactions;

        if (paymentMode != null) {
            transactions = transactionService.getTransactionsByPeriodAndPaymentMode(id, from, to, paymentMode);
        }
         else {
            transactions = transactionService.getTransactionsByPeriod(id, from, to);
        }

        return ResponseEntity.ok(transactions);
    }
}
