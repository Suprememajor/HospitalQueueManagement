package org.supremecorp.hospitalqueuemanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.supremecorp.hospitalqueuemanagement.model.Appointment;
import org.supremecorp.hospitalqueuemanagement.services.base.AppointmentService;

@RestController
@RequiredArgsConstructor
public class ScannerController {

    private final AppointmentService appointmentService;

    @RequestMapping(value = "/confirm/{appointmentId}")
    public String confirmAppointment(@PathVariable String appointmentId){
        Appointment appointment = appointmentService.findById(appointmentId);
        appointment.setConfirmed(true);
        appointmentService.resaveAppointment(appointment);
        return appointment.getPatientName();
    }
}
