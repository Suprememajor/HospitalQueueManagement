package org.supremecorp.hospitalqueuemanagement.repositories;

import org.springframework.data.repository.CrudRepository;
import org.supremecorp.hospitalqueuemanagement.model.Unit;

public interface UnitRepo extends CrudRepository<Unit, String> {
}
