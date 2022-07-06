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

    @RequestMapping(value = "/confirm/{identifier}")
    public Record confirmAppointment(@PathVariable String identifier){
        Appointment appointment = appointmentService.findByIdentifier(identifier);
        if (appointment.getPatientName() == null) return new Record("Not found", "Not found", false, false);
        if (appointment.isConfirmed()) return new Record(appointment.getPatientName(), appointment.getGender().name(), true, true);
        appointment.setConfirmed(true);
        appointmentService.resaveAppointment(appointment);
        return new Record(appointment.getPatientName(), appointment.getGender().name(), true, false);
    }
}
    record Record(String name, String gender, boolean confirmed, boolean wasConfirmed) {
    }
