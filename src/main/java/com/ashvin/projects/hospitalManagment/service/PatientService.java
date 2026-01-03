package com.ashvin.projects.hospitalManagment.service;

import com.ashvin.projects.hospitalManagment.dto.PatientResponseDto;
import com.ashvin.projects.hospitalManagment.entity.Patient;
import com.ashvin.projects.hospitalManagment.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    // to test transactional method

/*
    @Transactional
    public Patient getPatientById(Long id) {
      Patient p1 =    patientRepository.findById(id).orElseThrow();
      Patient p2 =   patientRepository.findById(id).orElseThrow();

        System.out.println(p1 == p2);

        p1.setName("Harry");   // dirty check automatically updates if any changes made
      //  patientRepository.save(p1);

       return p1;
    }
*/

    @Transactional
    public PatientResponseDto getPatientById(Long patientId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new EntityNotFoundException
                ("Patient not foune with id : " + patientId));
        return modelMapper.map(patient,PatientResponseDto.class);
    }

    public List<PatientResponseDto> getAllPatients(Integer pageNumber, Integer pageSize) {
        return patientRepository.findAllPatients(PageRequest.of(pageNumber,pageSize))
                .stream()
                .map(patient -> modelMapper.map(patient,PatientResponseDto.class))
                .collect(Collectors.toList());

    }
}
