package com.Federation.Final.service;

import com.Federation.Final.entity.dto.CollectivityInformation;
import com.Federation.Final.entity.dto.CollectivityOverallStatistics;
import com.Federation.Final.entity.dto.CollectivityRawData;
import com.Federation.Final.repository.CollectivityOverallStatisticsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;

import static java.awt.SystemColor.info;

@AllArgsConstructor
@Service
public class CollectivityOverallStatisticsService {
    private final CollectivityOverallStatisticsRepository repository;


    public List<CollectivityOverallStatistics> getOverallStatistics(LocalDate from, LocalDate to) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("Dates cannot be null");
        }

        if (from.isAfter(to)) {
            throw new IllegalArgumentException("Invalid date range");
        }

        List<Map<String, Object>> rows = repository.fetchOverallStatistics(from, to);

        List<CollectivityOverallStatistics> result = new ArrayList<>();

        for (Map<String, Object> row : rows) {

            CollectivityInformation info = new CollectivityInformation();
            info.setName((String) row.get("name"));
            info.setNumber((Integer) row.get("number"));

            CollectivityOverallStatistics stats = new CollectivityOverallStatistics();
            stats.setCollectivityInformation(info);
            stats.setTotalMembers((Integer) row.get("totalMembers"));
            stats.setTotalActivities((Integer) row.get("totalActivities"));
            stats.setTotalOneTimeActivities((Integer) row.get("totalOneTimeActivities"));
            stats.setTotalRecurringActivities((Integer) row.get("totalRecurringActivities"));
            stats.setTotalAttendanceRecords((Integer) row.get("totalAttendanceRecords"));
            stats.setTotalAttended((Integer) row.get("totalAttended"));
            stats.setTotalMissing((Integer) row.get("totalMissing"));
            stats.setTotalUndefined((Integer) row.get("totalUndefined"));

            Object attendanceRate = row.get("attendanceRatePercent");

            if (attendanceRate != null) {
                stats.setAttendanceRatePercent((BigDecimal) attendanceRate);
            } else {
                stats.setAttendanceRatePercent(BigDecimal.ZERO);
            }

            result.add(stats);
        }

        return result;
    }


}
