package org.supremecorp.hospitalqueuemanagement.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.supremecorp.hospitalqueuemanagement.model.Appointment;

import java.util.List;

public interface AppointmentRepo extends CrudRepository<Appointment, String> {
    List<Appointment> findAll(Sort sort);
}
