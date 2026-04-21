package com.Federation.Final.entity;

import com.Federation.Final.entity.Enum.PresenceEnum;
import lombok.Data;

@Data
public class Attendance {
    private Integer id;
    private PresenceEnum presence;
    private MemberShip memberShip;
    private Activity activity;
}
