package com.ashvin.projects.hospitalManagment;

import com.ashvin.projects.hospitalManagment.entity.Patient;
import com.ashvin.projects.hospitalManagment.repository.PatientRepository;
import com.ashvin.projects.hospitalManagment.service.PatientService;
import jakarta.persistence.EntityNotFoundException;
import lombok.ToString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class PatientTests {

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientRepository patientRepository;

    @Test
    public void testPatientRepository() {
       List<Patient> patientList = patientRepository.findAll();
        System.out.println(patientList);
    }

    @Test
    public void testTransactionMethods() {
       /* Patient patient = patientService.getPatientById(1L);
        System.out.println(patient);

        Patient patient1 = patientRepository.findById(2L).orElseThrow(() ->
                new EntityNotFoundException("Patient not found with id: " + 1L));
                */

      /*  Patient patient = patientRepository.findByName("Diya Patel");
        System.out.println(patient);*/

        /*List<Patient> patientList = patientRepository.findByBirthDateOrEmail(LocalDate.of
                (1993,07,11),"neha.iyer@example.com");
*/
         List<Patient> patientList = patientRepository.findByNameContainingOrderByIdDesc("Di");

        System.out.println(patientList);
    }
}

