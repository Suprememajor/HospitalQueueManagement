package org.supremecorp.hospitalqueuemanagement.repositories;

import org.springframework.data.repository.CrudRepository;
import org.supremecorp.hospitalqueuemanagement.model.Hospital;
import org.supremecorp.hospitalqueuemanagement.model.Unit;

import java.util.List;

public interface UnitRepo extends CrudRepository<Unit, String> {
    List<Unit> findAllByHospital(Hospital hospital);
}
