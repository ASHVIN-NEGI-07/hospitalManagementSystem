package com.ashvin.projects.hospitalManagment.service;

import com.ashvin.projects.hospitalManagment.dto.AppointmentResponseDto;
import com.ashvin.projects.hospitalManagment.dto.CreateAppointmentRequestDto;
import com.ashvin.projects.hospitalManagment.entity.Appointment;
import com.ashvin.projects.hospitalManagment.entity.Doctor;
import com.ashvin.projects.hospitalManagment.entity.Patient;
import com.ashvin.projects.hospitalManagment.repository.AppointmentRepository;
import com.ashvin.projects.hospitalManagment.repository.DoctorRepository;
import com.ashvin.projects.hospitalManagment.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

/*
    @Transactional
    public Appointment createNewAppointment(Appointment appointment, Long doctorId, Long patientId){

        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();
        Patient patient = patientRepository.findById(patientId).orElseThrow();

        if(appointment.getId() != null) throw new IllegalArgumentException("Appointment should not have an id");

        appointment.setDoctor(doctor);
        appointment.setPatient(patient);

        patient.getAppointments().add(appointment); // bi-directional mapping
        doctor.getAppointments().add(appointment);  // to mantain consistency

       return appointmentRepository.save(appointment);
    }
*/

    @Transactional
    public AppointmentResponseDto createNewAppointment(CreateAppointmentRequestDto createAppointmentRequestDto) {

        Long doctorId = createAppointmentRequestDto.getDoctorId();
        Long patientId = createAppointmentRequestDto.getPatientId();

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found with id : " + doctorId));

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with id : " + patientId));

        Appointment appointment = Appointment.builder()
                .reason(createAppointmentRequestDto.getReason())
                .appointmentTime(createAppointmentRequestDto.getAppointmentTime())
                .build();

        appointment.setDoctor(doctor);
        appointment.setPatient(patient);

        patient.getAppointments().add(appointment); // to mantain bidirectional consistency

        appointment = appointmentRepository.save(appointment);

        return modelMapper.map(appointment,AppointmentResponseDto.class);

    }

    @Transactional
    public Appointment reAssignAppointmentToAnotherDoctor(Long appointmentId,Long doctorId){

        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow();
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();

        appointment.setDoctor(doctor);  // this will automatically call update , because of dirty checking inside transactional context

        doctor.getAppointments().add(appointment);  // just for bidirectional consistency

        // no need to explicitly save due to dirty checking as appointment is in persistent state and inside transactional context
        return appointment;
    }

    public List<AppointmentResponseDto> getAllAppointmentsOfDoctor(Long doctorId){
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();

        return doctor.getAppointments()
                .stream()
                .map(appointment -> modelMapper.map(appointment,AppointmentResponseDto.class))
                .collect(Collectors.toList());
    }
}
