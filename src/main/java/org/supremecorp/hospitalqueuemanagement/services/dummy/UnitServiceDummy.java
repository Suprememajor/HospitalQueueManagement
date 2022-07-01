package org.supremecorp.hospitalqueuemanagement.services.dummy;

import org.springframework.stereotype.Service;
import org.supremecorp.hospitalqueuemanagement.model.Unit;
import org.supremecorp.hospitalqueuemanagement.services.base.UnitService;

import java.io.IOException;
import java.util.List;

@Service
public class UnitServiceDummy implements UnitService {
    @Override
    public List<Unit> findAll() {
        return List.of(
                new Unit("1", "Pediatrics"),
                new Unit("2", "Maternity"),
                new Unit("3", "Dentist"),
                new Unit("4", "Chiropractor")
        );
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
}
