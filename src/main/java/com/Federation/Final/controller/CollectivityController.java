package com.Federation.Final.controller;

import com.Federation.Final.entity.Collectivity;
import com.Federation.Final.entity.FinancialAccount;
import com.Federation.Final.entity.dto.CollectivityResponse;
import com.Federation.Final.entity.dto.CreateCollectivity;
import com.Federation.Final.service.CollectivityService;
import com.Federation.Final.service.FinancialAccountService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/collectivities")
public class CollectivityController {

    private final CollectivityService collectivityService;
    private final FinancialAccountService financialAccountService;

    // Constructeur corrigé
    public CollectivityController(CollectivityService collectivityService,
                                  FinancialAccountService financialAccountService) {
        this.collectivityService = collectivityService;
        this.financialAccountService = financialAccountService;
    }

    @PostMapping
    public ResponseEntity<?> createCollectivities(@RequestBody List<CreateCollectivity> dtos) {
        try {
            List<CollectivityResponse> created = collectivityService.createCollectivities(dtos);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Collectivity> getCollectivityById(@PathVariable String id) throws SQLException {
        Collectivity collectivity = collectivityService.findById(id);
        return ResponseEntity.ok(collectivity);
    }

    @GetMapping("/{id}/financialAccounts")
    public ResponseEntity<List<FinancialAccount>> getFinancialAccounts(
            @PathVariable String id,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate at) throws SQLException {

        LocalDate date = at != null ? at : LocalDate.now();
        List<FinancialAccount> accounts = financialAccountService.getAccountsByCollectivity(id, date);
        return ResponseEntity.ok(accounts);
    }
}