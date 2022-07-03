package org.supremecorp.hospitalqueuemanagement.services.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.supremecorp.hospitalqueuemanagement.model.Hospital;
import org.supremecorp.hospitalqueuemanagement.model.Unit;
import org.supremecorp.hospitalqueuemanagement.repositories.UnitRepo;
import org.supremecorp.hospitalqueuemanagement.services.base.UnitService;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UnitServiceJpa implements UnitService {
    private final UnitRepo unitRepo;

    @Override
    public List<Unit> listAll() {
        return (List<Unit>) unitRepo.findAll();
    }

    @Override
    public Unit save(Unit unit) throws IOException {
        unit.setId("un" + UUID.randomUUID());
        return unitRepo.save(unit);
    }

    @Override
    public Unit findById(String id) {
        return unitRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Unit not found."));
    }

    @Override
    public void deleteById(String id) {
        unitRepo.deleteById(id);
    }

    @Override
    public void delete(Unit unit) {
        unitRepo.delete(unit);
    }

    @Override
    public List<Unit> findAllByHospital(Hospital hospital) {
        return unitRepo.findAllByHospital(hospital);
    }
}
