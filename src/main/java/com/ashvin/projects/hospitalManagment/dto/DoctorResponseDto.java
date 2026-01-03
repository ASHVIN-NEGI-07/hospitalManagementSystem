package com.ashvin.projects.hospitalManagment.dto;

import lombok.Data;

@Data
public class DoctorResponseDto {

    private Long id;
    private String name;
    private String specialization;
    private String email;
}
