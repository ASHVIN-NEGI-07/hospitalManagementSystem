package com.ashvin.projects.hospitalManagment.service;

import com.ashvin.projects.hospitalManagment.entity.Appointment;
import com.ashvin.projects.hospitalManagment.entity.Doctor;
import com.ashvin.projects.hospitalManagment.entity.Patient;
import com.ashvin.projects.hospitalManagment.repository.AppointmentRepository;
import com.ashvin.projects.hospitalManagment.repository.DoctorRepository;
import com.ashvin.projects.hospitalManagment.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.print.Doc;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

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

    @Transactional
    public Appointment reAssignAppointmentToAnotherDoctor(Long appointmentId,Long doctorId){

        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow();
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();

        appointment.setDoctor(doctor);  // this will automatically call update , because of dirty checking inside transactional context

        doctor.getAppointments().add(appointment);  // just for bidirectional consistency

        // no need to explicitly save due to dirty checking as appointment is in persistent state and inside transactional context
        return appointment;
    }
}
