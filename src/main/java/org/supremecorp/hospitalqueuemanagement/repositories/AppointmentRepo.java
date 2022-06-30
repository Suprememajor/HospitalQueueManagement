package org.supremecorp.hospitalqueuemanagement.repositories;

import org.springframework.data.repository.CrudRepository;
import org.supremecorp.hospitalqueuemanagement.model.Appointment;

public interface AppointmentRepo extends CrudRepository<Appointment, String> {
}
