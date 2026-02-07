package com.ashvin.projects.hospitalManagment.controller;

import com.ashvin.projects.hospitalManagment.dto.DoctorResponseDto;
import com.ashvin.projects.hospitalManagment.dto.OnBoardNewDoctorDto;
import com.ashvin.projects.hospitalManagment.dto.PatientResponseDto;
import com.ashvin.projects.hospitalManagment.entity.Patient;
import com.ashvin.projects.hospitalManagment.entity.User;
import com.ashvin.projects.hospitalManagment.service.DoctorService;
import com.ashvin.projects.hospitalManagment.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final PatientService patientService;
    private final DoctorService doctorService;

    @GetMapping("/patients")
    public ResponseEntity<List<PatientResponseDto>> getAllPatients(
            @RequestParam(value ="page" ,defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "size" ,defaultValue = "2") Integer pageSize
    ) {
        return ResponseEntity.ok(patientService.getAllPatients(pageNumber,pageSize));
    }

    @PostMapping("/onBoardNewDoctor")
    public ResponseEntity<DoctorResponseDto> onBoardNewDoctor(@RequestBody OnBoardNewDoctorDto onBoardNewDoctorDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.onBoardNewDoctor(onBoardNewDoctorDto));
    }
}
