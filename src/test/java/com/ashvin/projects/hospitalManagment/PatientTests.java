package com.ashvin.projects.hospitalManagment;

import com.ashvin.projects.hospitalManagment.dto.BloodGroupCountResponseEntity;
import com.ashvin.projects.hospitalManagment.entity.Patient;
import com.ashvin.projects.hospitalManagment.entity.type.BloodGroupType;
import com.ashvin.projects.hospitalManagment.repository.PatientRepository;
import com.ashvin.projects.hospitalManagment.service.PatientService;
import jakarta.persistence.EntityNotFoundException;
import lombok.ToString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

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
//       List<Patient> patientList = patientRepository.findAll();
//        System.out.println(patientList);

        List<Patient> patientList = patientRepository.findAllPatientsWithAppointment();
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
//         List<Patient> patientList = patientRepository.findByNameContainingOrderByIdDesc("Di");

    //    List<Patient> patientList = patientRepository.findByBloodGroup(BloodGroupType.A_POSITIVE);

      /*  List<Patient> patientList = patientRepository.findByBornAfterDate(LocalDate.of(1993,03,14));

        for(Patient patient : patientList) {
            System.out.println(patient);
        }

        List<Object[]> bloodGroupList = patientRepository.countEachBloodGroupType();

        for(Object[] objects : bloodGroupList) {
            System.out.println(objects[0] + " : " + objects[1]);
        }

        List<Patient> patientList2 = patientRepository.findAllPatients();
        System.out.println(patientList2);*/

      /*  int rowsUpdated = patientRepository.updateNameWithId("Arav Sharma",1L);
        System.out.println(rowsUpdated);*/

      /*  List<BloodGroupCountResponseEntity> bloodGroupList = patientRepository.countEachBloodGroupType();

        for(BloodGroupCountResponseEntity bloodGroupCountResponse : bloodGroupList) {
            System.out.println(bloodGroupCountResponse);
        }*/

        Page<Patient> patientList3 = patientRepository.findAllPatients(PageRequest.of(0,2, Sort.by("name")));
        for(Patient patient : patientList3) {
            System.out.println(patient);
        }
    }
}

