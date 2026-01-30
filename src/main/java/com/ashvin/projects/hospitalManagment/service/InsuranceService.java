package com.ashvin.projects.hospitalManagment.service;

import com.ashvin.projects.hospitalManagment.entity.Insurance;
import com.ashvin.projects.hospitalManagment.entity.Patient;
import com.ashvin.projects.hospitalManagment.repository.InsuranceRepository;
import com.ashvin.projects.hospitalManagment.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public Patient assignInsuranceToPatient(Insurance insurance, Long patientId){
        Patient patient = patientRepository.findById(patientId).
                orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + patientId));

        patient.setInsurance(insurance);   // dirty checking will save the insurance entity
        insurance.setPatient(patient);  // bidirectional consitency mantainence

        return  patient;
    }

    @Transactional
    public Patient dissociateInsuranceFromPatient(Long patientId){
        Patient patient = patientRepository.findById(patientId).orElseThrow();

        patient.setInsurance(null);  // patient gets dirty due to this change , so insurance entity will be removed from DB

        return patient;
    }
}
