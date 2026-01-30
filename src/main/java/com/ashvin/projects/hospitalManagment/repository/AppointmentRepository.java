package com.ashvin.projects.hospitalManagment.repository;

import com.ashvin.projects.hospitalManagment.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}