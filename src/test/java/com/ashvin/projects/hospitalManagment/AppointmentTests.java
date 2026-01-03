package com.ashvin.projects.hospitalManagment;

import com.ashvin.projects.hospitalManagment.entity.Appointment;
import com.ashvin.projects.hospitalManagment.service.AppointmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class AppointmentTests {

    @Autowired
    private AppointmentService appointmentService;


    @Test
    public void testCreateAppointment(){

        Appointment appointment = Appointment.builder()
                .appointmentTime(LocalDateTime.of(2025,12,31,10,00,00))
                .reason("cancer")
                .build();

        // will work with first createNewAppointment method in AppointmentService

    /* var newAppointment = appointmentService.createNewAppointment(appointment,1L,2L);
        System.out.println(newAppointment);

      var updatedAppointment =  appointmentService.reAssignAppointmentToAnotherDoctor(newAppointment.getId(),3L);
        System.out.println(updatedAppointment);*/
    }
}
