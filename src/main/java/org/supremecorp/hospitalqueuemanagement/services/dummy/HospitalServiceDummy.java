package org.supremecorp.hospitalqueuemanagement.services.dummy;

import org.supremecorp.hospitalqueuemanagement.model.Hospital;
import org.supremecorp.hospitalqueuemanagement.services.base.HospitalService;

import java.util.List;

public class HospitalServiceDummy implements HospitalService {
    @Override
    public List<Hospital> findAll() {
        return List.of(
                new Hospital("District Hospital Buea", "Buea, Cameroon", "675438782", "www.seemorebuea.com", "bueahospital@gmail.com"),
                new Hospital("District Hospital Limbe", "Limbe, Cameroon", "681748782", "www.seemoreLimbe.com", "limbehospital@gmail.com")
        );
    }

    @Override
    public Hospital save(Hospital hospital) {
        return null;
    }

    @Override
    public Hospital findById(String s) {
        return null;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(Hospital hospital) {

    }
}
