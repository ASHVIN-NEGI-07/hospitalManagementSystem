package com.ashvin.projects.hospitalManagment.entity.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PermissionType {
                    // resource : operation
       PATIENT_READ("patient:read"),
       PATIENT_WRITE("patient:write"),
       APPOINTMENT_READ("appointment:read"),
       APPOINTMENT_WRITE("appointment:write"),
       APPOINTMENT_DELETE("appointment:delete"),
       USER_MANAGE("user:manage"),  // for admin tasks
       REPORT_VIEW("report:view");

       private final String permission;
}
