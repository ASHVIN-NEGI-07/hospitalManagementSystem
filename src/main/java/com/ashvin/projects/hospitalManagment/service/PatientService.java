package com.ashvin.projects.hospitalManagment.service;

import com.ashvin.projects.hospitalManagment.entity.Patient;
import com.ashvin.projects.hospitalManagment.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    @Transactional
    public Patient getPatientById(Long id) {
      Patient p1 =    patientRepository.findById(id).orElseThrow();
      Patient p2 =   patientRepository.findById(id).orElseThrow();

        System.out.println(p1 == p2);

        p1.setName("Harry");   // dirty check automatically updates if any changes made
      //  patientRepository.save(p1);

       return p1;
    }
}
