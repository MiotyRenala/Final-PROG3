package com.Federation.Final.service;

import com.Federation.Final.entity.dto.CollectivityInformation;
import com.Federation.Final.entity.dto.CollectivityLocalStatistics;
import com.Federation.Final.entity.dto.CollectivityOverallStatistics;
import com.Federation.Final.entity.dto.MemberDescription;
import com.Federation.Final.repository.LocalStatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LocalStatisticService {


    private LocalStatisticRepository localStatisticRepository;

    public LocalStatisticService(LocalStatisticRepository localStatisticRepository){
        this.localStatisticRepository = localStatisticRepository;
    }


    public List<CollectivityLocalStatistics> getLocalStatistics(String collectivityId, LocalDate from, LocalDate to) {
        if (collectivityId == null) {
            throw new IllegalArgumentException("Collectivity ID cannot be null");
        }
        if (from == null || to == null) {
            throw new IllegalArgumentException("Dates cannot be null");
        }
        if (from.isAfter(to)) {
            throw new IllegalArgumentException("Invalid date range");
        }

        List<Map<String, Object>> basicRows      = localStatisticRepository.getLocalStatistics(from, to);
        List<Map<String, Object>> breakdownRows  = localStatisticRepository.fetchLocalActivityBreakdown(from, to);
        List<Map<String, Object>> attendanceRows = localStatisticRepository.fetchLocalAttendanceStatistics(from, to);

        Map<String, Map<String, Object>> breakdownMap  = indexById(breakdownRows);
        Map<String, Map<String, Object>> attendanceMap = indexById(attendanceRows);

        List<CollectivityLocalStatistics> result = new ArrayList<>();

        for (Map<String, Object> row : basicRows) {
            String id = (String) row.get("id");

            Map<String, Object> breakdown  = breakdownMap.getOrDefault(id, new HashMap<>());
            Map<String, Object> attendance = attendanceMap.getOrDefault(id, new HashMap<>());

            CollectivityInformation info = new CollectivityInformation();
            info.setName((String) row.get("name"));
            info.setNumber((Integer) row.get("number"));

            CollectivityLocalStatistics stats = new CollectivityLocalStatistics();
            stats.setCollectivityInformation(info);
            stats.setTotalMembers((Integer) row.get("total_members"));

            stats.setTotalActivities((Integer) row.get("total_activities"));
            stats.setTotalOneTimeActivities((Integer) breakdown.get("total_one_time_activities"));
            stats.setTotalRecurringActivities((Integer) breakdown.get("total_recurring_activities"));
            stats.setTotalAttendanceRecords((Integer) attendance.get("total_attendance_records"));
            stats.setTotalAttended((Integer) attendance.get("total_attended"));
            stats.setTotalMissing((Integer) attendance.get("total_missing"));
            stats.setTotalUndefined((Integer) attendance.get("total_undefined"));

            Object attendanceRate = attendance.get("attendance_rate_percent");
            stats.setAttendanceRatePercent(
                    attendanceRate != null ? (BigDecimal) attendanceRate : BigDecimal.ZERO
            );

            result.add(stats);
        }

        return result;
    }

    public List<CollectivityLocalStatistics> getMemberAttendanceStatistics(String collectivityId, LocalDate from, LocalDate to) {
        if (collectivityId == null) {
            throw new IllegalArgumentException("Collectivity ID cannot be null");
        }
        if (from == null || to == null) {
            throw new IllegalArgumentException("Dates cannot be null");
        }
        if (from.isAfter(to)) {
            throw new IllegalArgumentException("Invalid date range");
        }

        List<Map<String, Object>> memberRows     = localStatisticRepository.fetchMemberList(collectivityId);
        List<Map<String, Object>> attendanceRows = localStatisticRepository.fetchMemberAttendanceStatistics(collectivityId, from, to);
        List<Map<String, Object>> breakdownRows  = localStatisticRepository.fetchMemberActivityBreakdown(collectivityId, from, to);

        // index by member id for easy lookup
        Map<String, Map<String, Object>> attendanceMap = indexById(attendanceRows, "member_id");
        Map<String, Map<String, Object>> breakdownMap  = indexById(breakdownRows, "member_id");

        List<CollectivityLocalStatistics> result = new ArrayList<>();

        for (Map<String, Object> row : memberRows) {
            String memberId = (String) row.get("member_id");

            Map<String, Object> attendance = attendanceMap.getOrDefault(memberId, new HashMap<>());
            Map<String, Object> breakdown  = breakdownMap.getOrDefault(memberId, new HashMap<>());

            CollectivityLocalStatistics stats = new CollectivityLocalStatistics();


            stats.setTotalAttendanceRecords((Integer) attendance.get("total_attendance_records"));
            stats.setTotalAttended((Integer) attendance.get("total_attended"));
            stats.setTotalMissing((Integer) attendance.get("total_missing"));
            stats.setTotalUndefined((Integer) attendance.get("total_undefined"));


            Object attendanceRate = attendance.get("attendance_rate_percent");
            stats.setAttendanceRatePercent(
                    attendanceRate != null ? (BigDecimal) attendanceRate : BigDecimal.ZERO
            );

            result.add(stats);
        }

        return result;
    }


    private Map<String, Map<String, Object>> indexById(List<Map<String, Object>> rows) {
        return indexById(rows, "id");
    }

    private Map<String, Map<String, Object>> indexById(List<Map<String, Object>> rows, String idKey) {
        Map<String, Map<String, Object>> map = new HashMap<>();
        for (Map<String, Object> row : rows) {
            String id = (String) row.get(idKey);
            if (id != null) {
                map.put(id, row);
            }
        }
        return map;
    }

}