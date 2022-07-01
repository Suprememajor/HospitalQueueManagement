package org.supremecorp.hospitalqueuemanagement.services.dummy;

import org.supremecorp.hospitalqueuemanagement.model.Hospital;
import org.supremecorp.hospitalqueuemanagement.model.Unit;
import org.supremecorp.hospitalqueuemanagement.services.base.UnitService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UnitServiceDummy implements UnitService {
    @Override
    public List<Unit> findAll() {
        return new ArrayList<>();
    }

    @Override
    public Unit save(Unit unit) throws IOException {
        return null;
    }

    @Override
    public Unit findById(String s) {
        return null;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(Unit unit) {

    }

    @Override
    public List<Unit> findAllByHospital(Hospital hospital) {
        return null;
    }
}
