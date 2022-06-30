package org.supremecorp.hospitalqueuemanagement.repositories;

import org.springframework.data.repository.CrudRepository;
import org.supremecorp.hospitalqueuemanagement.model.Admin;
import org.supremecorp.hospitalqueuemanagement.model.Hospital;

import java.util.List;

public interface AdminRepo extends CrudRepository<Admin, String> {
    Admin findByEmail(String email);
    List<Admin> findAllByHospital(Hospital hospital);
}
