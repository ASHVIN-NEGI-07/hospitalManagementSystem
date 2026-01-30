package com.ashvin.projects.hospitalManagment.repository;

import com.ashvin.projects.hospitalManagment.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}