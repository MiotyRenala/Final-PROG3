package com.Federation.Final.service;

import com.Federation.Final.entity.dto.ActivityMemberAttendance;
import com.Federation.Final.entity.dto.CreateActivityMemberAttendance;
import com.Federation.Final.repository.ActivityAttendanceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ActivityAttendanceService {
    private final ActivityAttendanceRepository activityAttendanceRepository;

    public List<ActivityMemberAttendance> saveAttendance(
            String activityId,
            List<CreateActivityMemberAttendance> attendances
    ) {
        if (activityId == null || activityId.isBlank()) {
            throw new IllegalArgumentException("Activity ID must not be null or blank");
        }

        if (attendances == null || attendances.isEmpty()) {
            throw new IllegalArgumentException("Attendances list must not be null or empty");
        }

        return activityAttendanceRepository.saveAttendance(activityId, attendances);
    }

    public List<ActivityMemberAttendance> getAttendanceByActivityId(String activityId) {
        if (activityId == null || activityId.isBlank()) {
            throw new IllegalArgumentException("Activity ID must not be null or blank");
        }

        return activityAttendanceRepository.findByActivityAttendanceId(activityId);
    }
}

