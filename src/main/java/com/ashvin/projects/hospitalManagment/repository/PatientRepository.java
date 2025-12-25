package com.ashvin.projects.hospitalManagment.repository;

import com.ashvin.projects.hospitalManagment.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {

    // query is generated based on method name
    // you can write multiple queries in a inteface

    Patient findByName(String name);
    Patient findByBirthDate(LocalDate birthDate);
    Patient findByEmail(String email);
    List<Patient> findByBirthDateOrEmail(LocalDate birthDate,String email);    // if any of 2 fields match it will return that patient
    List<Patient> findByBirthDateBetween(LocalDate startDate, LocalDate endDate);  // patients born between startDate and endDate
    List<Patient> findByNameContainingOrderByIdDesc(String query);   // patients whose name contains the given query string, ordered by ID in descending order

}
