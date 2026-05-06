package com.Federation.Final.controller;

import com.Federation.Final.entity.Collectivity;
import com.Federation.Final.entity.CollectivityActivity;
import com.Federation.Final.entity.FinancialAccount;
import com.Federation.Final.entity.dto.*;
import com.Federation.Final.service.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/collectivities")
public class CollectivityController {

    private final CollectivityService collectivityService;
    private final FinancialAccountService financialAccountService;
    private final LocalStatisticService localStatisticService;
    private final CollectivityOverallStatisticsService collectivityOverallStatisticsService;
    private final CollectivityActivityService collectivityActivityService;
    private final ActivityAttendanceService activityAttendanceService;


    public CollectivityController(CollectivityService collectivityService,
                                  FinancialAccountService financialAccountService,
                                  LocalStatisticService localStatisticService,
                                  CollectivityOverallStatisticsService collectivityOverallStatisticsService,
                                  CollectivityActivityService collectivityActivityService,
                                  ActivityAttendanceService activityAttendanceService
    ) {
        this.collectivityActivityService = collectivityActivityService;
        this.collectivityService = collectivityService;
        this.financialAccountService = financialAccountService;
        this.localStatisticService = localStatisticService;
        this.collectivityOverallStatisticsService = collectivityOverallStatisticsService;
        this.activityAttendanceService = activityAttendanceService;
    }

    @GetMapping("/{id}/statistics")
    public ResponseEntity<List<CollectivityLocalStatistics>> getLocalStatistics(
            @PathVariable("id") String collectivityId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {

        List<CollectivityLocalStatistics> statistics =
                localStatisticService.getLocalStatistics(collectivityId, from, to);

        if (statistics == null || statistics.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(statistics);
    }

    @PostMapping("/{id}/activities")
    public ResponseEntity<?> createActivities(
            @PathVariable("id") String collectivityId,
            @RequestBody List<CreateCollectivityActivity> dtos) {

        try {
            List<CollectivityActivity> created =
                    collectivityActivityService.createActivities(collectivityId, dtos);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating activities: " + e.getMessage());
        }
    }

    @GetMapping("/{id}/activities")
    public ResponseEntity<List<CollectivityActivity>> getActivities(
            @PathVariable("id") String collectivityId) {

        List<CollectivityActivity> activities =
                collectivityActivityService.getActivitiesByCollectivityId(collectivityId);

        if (activities == null || activities.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(activities);
    }

    @GetMapping("/statistics")
    public ResponseEntity<List<CollectivityOverallStatistics>> getOverallStatistics(
            @RequestParam("from")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate from,

            @RequestParam("to")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate to
    ){
        return ResponseEntity.ok(collectivityOverallStatisticsService.getOverallStatistics(from, to));
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

    @PostMapping("/{id}/activities/{activityId}/attendances")
    public ResponseEntity<List<ActivityMemberAttendance>> saveAttendance(
            @PathVariable String activityId,
            @RequestBody List<CreateActivityMemberAttendance> attendances
    ) {
        List<ActivityMemberAttendance> result = activityAttendanceService.saveAttendance(activityId, attendances);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    @GetMapping("/{id}/activities/{activityId}/attendances")
    public ResponseEntity<List<ActivityMemberAttendance>> getAttendanceByActivityId(
            @PathVariable String activityId
    ) {
        List<ActivityMemberAttendance> result = activityAttendanceService.getAttendanceByActivityId(activityId);
        return ResponseEntity.ok(result);
    }

}