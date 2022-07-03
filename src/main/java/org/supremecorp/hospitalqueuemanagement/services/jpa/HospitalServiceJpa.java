package org.supremecorp.hospitalqueuemanagement.services.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.supremecorp.hospitalqueuemanagement.model.Hospital;
import org.supremecorp.hospitalqueuemanagement.repositories.HospitalRepo;
import org.supremecorp.hospitalqueuemanagement.services.base.HospitalService;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class HospitalServiceJpa implements HospitalService {
    private final HospitalRepo hospitalRepo;

    @Override
    public List<Hospital> listAll() {
        return (List<Hospital>) hospitalRepo.findAll();
    }

    @Override
    public Hospital save(Hospital hospital) {
        hospital.setId("hp" + UUID.randomUUID());
        return hospitalRepo.save(hospital);
    }

    @Override
    public Hospital findById(String id) {
        return hospitalRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Hospital with id: " + id +
                        "not found."));
    }

    @Override
    public void deleteById(String id) {
        hospitalRepo.deleteById(id);
    }

    @Override
    public void delete(Hospital hospital) {
        hospitalRepo.delete(hospital);
    }
}
