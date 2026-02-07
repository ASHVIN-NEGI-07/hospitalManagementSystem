package com.ashvin.projects.hospitalManagment.service;

import com.ashvin.projects.hospitalManagment.dto.DoctorResponseDto;
import com.ashvin.projects.hospitalManagment.dto.OnBoardNewDoctorDto;
import com.ashvin.projects.hospitalManagment.entity.Doctor;
import com.ashvin.projects.hospitalManagment.entity.User;
import com.ashvin.projects.hospitalManagment.entity.type.RoleType;
import com.ashvin.projects.hospitalManagment.repository.DoctorRepository;
import com.ashvin.projects.hospitalManagment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.print.Doc;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public List<DoctorResponseDto> getAllDoctors() {
        return doctorRepository.findAll()
                .stream()
                .map(doctor -> modelMapper.map(doctor, DoctorResponseDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public DoctorResponseDto onBoardNewDoctor(OnBoardNewDoctorDto onBoardNewDoctorDto) {
       User user = userRepository.findById(onBoardNewDoctorDto.getUserId()).orElseThrow();

       if(doctorRepository.existsById(onBoardNewDoctorDto.getUserId())){
           throw new IllegalArgumentException("Doctor already exists with user id : " + onBoardNewDoctorDto.getUserId());
       }

       Doctor doctor = Doctor.builder()
               .name(onBoardNewDoctorDto.getName())
               .specialization(onBoardNewDoctorDto.getSpecialization())
               .user(user)
               .build();

       user.getRoles().add(RoleType.DOCTOR);

       return modelMapper.map(doctorRepository.save(doctor), DoctorResponseDto.class);
    }
}
