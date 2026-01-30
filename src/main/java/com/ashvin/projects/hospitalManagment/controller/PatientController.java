package com.ashvin.projects.hospitalManagment.controller;

import com.ashvin.projects.hospitalManagment.dto.AppointmentResponseDto;
import com.ashvin.projects.hospitalManagment.dto.CreateAppointmentRequestDto;
import com.ashvin.projects.hospitalManagment.dto.PatientResponseDto;
import com.ashvin.projects.hospitalManagment.entity.Appointment;
import com.ashvin.projects.hospitalManagment.service.AppointmentService;
import com.ashvin.projects.hospitalManagment.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;
    private final AppointmentService appointmentService;

    @PostMapping("/appointments")
    public ResponseEntity<AppointmentResponseDto> createNewAppointment(@RequestBody CreateAppointmentRequestDto createAppointmentRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.createNewAppointment(createAppointmentRequestDto));
    }

    @GetMapping("/profile")
    public ResponseEntity<PatientResponseDto> getPatientProfile(){
        Long patientId = 4L;
        return ResponseEntity.ok(patientService.getPatientById(patientId));
    }
}
