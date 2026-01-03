package com.ashvin.projects.hospitalManagment.controller;


import com.ashvin.projects.hospitalManagment.dto.AppointmentResponseDto;
import com.ashvin.projects.hospitalManagment.entity.Doctor;
import com.ashvin.projects.hospitalManagment.entity.Patient;
import com.ashvin.projects.hospitalManagment.repository.PatientRepository;
import com.ashvin.projects.hospitalManagment.service.AppointmentService;
import com.ashvin.projects.hospitalManagment.service.PatientService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorController {

   private final AppointmentService appointmentService;

   @GetMapping("/appointments")
    public ResponseEntity<List<AppointmentResponseDto>> getAllAppointmentsOfDoctor(){
       return ResponseEntity.ok(appointmentService.getAllAppointmentsOfDoctor(1L));
   }
}
