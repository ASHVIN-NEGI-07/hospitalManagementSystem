package com.ashvin.projects.hospitalManagment.service;

import com.ashvin.projects.hospitalManagment.repository.AppointmentRepository;
import com.ashvin.projects.hospitalManagment.repository.DoctorRepository;
import com.ashvin.projects.hospitalManagment.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;


}
