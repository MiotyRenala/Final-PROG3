package com.Federation.Final.entity.dto;

import com.Federation.Final.entity.Enum.AttendanceStatusEnum;
import lombok.Data;

@Data
public class ActivityMemberAttendance {
    private String id;
    private MemberDescription memberDescription;
    private AttendanceStatusEnum attendanceStatusEnum;
}
