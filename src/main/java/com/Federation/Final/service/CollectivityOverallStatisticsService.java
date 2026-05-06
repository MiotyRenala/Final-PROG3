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
            stats.setNewMembersNumber((Integer) row.get("newMembers"));

            Object percentageObj = row.get("duePercentage");

            if (percentageObj != null) {
                stats.setOverallMemberCurrentDuePercentage((BigDecimal) percentageObj);
            } else {
                stats.setOverallMemberCurrentDuePercentage(BigDecimal.ZERO);
            }

            result.add(stats);
        }

        return result;
    }
}
