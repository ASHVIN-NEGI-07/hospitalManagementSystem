package com.ashvin.projects.hospitalManagment.repository;

import com.ashvin.projects.hospitalManagment.entity.Patient;
import com.ashvin.projects.hospitalManagment.entity.type.BloodGroupType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

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


    // SQL is a case-insensitive language and JPQL for most part is case-insensitive too except for java entity and field names

    @Query("SELECT p FROM Patient p WHERE p.bloodGroup = ?1")
    List<Patient> findByBloodGroup( @Param("bloodGroup") BloodGroupType bloodGroup);

    @Query("SELECT p FROM Patient p WHERE p.birthDate > :birthDate")
    List<Patient> findByBornAfterDate(@Param("birthDate") LocalDate birthdate);

    @Query("select p.bloodGroup, count(p) from Patient p group by p.bloodGroup")
    List<Object[]> countEachBloodGroupType();

    // native query = SQL query

    @Query(value = "select * from patient", nativeQuery = true)
    List<Patient> findAllPatients();

    @Transactional
    @Modifying
    @Query("UPDATE Patient p SET p.name = :name WHERE p.id = :id")
    int updateNameWithId(@Param("name") String name, @Param("id") Long id);

}
