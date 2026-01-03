package com.ashvin.projects.hospitalManagment.controller;

import com.ashvin.projects.hospitalManagment.dto.PatientResponseDto;
import com.ashvin.projects.hospitalManagment.entity.Patient;
import com.ashvin.projects.hospitalManagment.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final PatientService patientService;

    @GetMapping("/patients")
    public ResponseEntity<List<PatientResponseDto>> getAllPatients(
            @RequestParam(value ="page" ,defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "size" ,defaultValue = "2") Integer pageSize
    ) {
        return ResponseEntity.ok(patientService.getAllPatients(pageNumber,pageSize));
    }
}
