package org.supremecorp.hospitalqueuemanagement.repositories;

import org.springframework.data.repository.CrudRepository;
import org.supremecorp.hospitalqueuemanagement.model.Hospital;

public interface HospitalRepo extends CrudRepository<Hospital, String> {
}
