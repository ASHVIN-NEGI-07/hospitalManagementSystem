package com.ashvin.projects.hospitalManagment.dto;

import lombok.Data;

@Data
public class OnBoardNewDoctorDto {
    private Long userId;
    private String specialization;
    // names may be different from patient profile ( ex : Dr. John Doe in doctor profile vs John Doe in patient profile )
    // we have given support for 2 names
    private String name;
}
