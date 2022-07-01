package org.supremecorp.hospitalqueuemanagement.services.base;

import org.supremecorp.hospitalqueuemanagement.model.Hospital;
import org.supremecorp.hospitalqueuemanagement.model.Unit;

import java.util.List;

public interface UnitService extends CrudService<Unit, String> {
    List<Unit> findAllByHospital(Hospital hospital);
}
