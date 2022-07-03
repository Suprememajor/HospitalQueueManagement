package org.supremecorp.hospitalqueuemanagement.services.base;

import org.supremecorp.hospitalqueuemanagement.model.Appointment;

import java.io.IOException;

public interface AppointmentService extends CrudService<Appointment, String> {
    void generateBarcode(Appointment appointment) throws IOException;
    void createPdf() throws IOException;
}
