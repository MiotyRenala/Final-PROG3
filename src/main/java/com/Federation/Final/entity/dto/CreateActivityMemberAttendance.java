package com.Federation.Final.entity.dto;

import com.Federation.Final.entity.Enum.AttendanceStatusEnum;
import lombok.Data;

@Data
public class CreateActivityMemberAttendance {
    private String memberIdentifier;
    private AttendanceStatusEnum attendanceStatusEnum;
}
