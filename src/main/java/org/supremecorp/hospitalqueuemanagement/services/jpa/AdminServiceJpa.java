package org.supremecorp.hospitalqueuemanagement.services.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.supremecorp.hospitalqueuemanagement.model.Admin;
import org.supremecorp.hospitalqueuemanagement.repositories.AdminRepo;
import org.supremecorp.hospitalqueuemanagement.services.base.AdminService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminServiceJpa implements AdminService {
    private final AdminRepo adminRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<Admin> findAll() {
        return (List<Admin>) adminRepo.findAll();
    }

    @Override
    public Admin save(Admin adminDetails) {
        Admin admin = new Admin();
        admin.setId("ad" + UUID.randomUUID());
        admin.setEmail(adminDetails.getEmail());
        admin.setPassword(passwordEncoder.encode(adminDetails.getPassword()));
        return adminRepo.save(admin);
    }

    @Override
    public Admin findById(String id) {
        return adminRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Id not found."));
    }

    @Override
    public void deleteById(String id) {
        adminRepo.deleteById(id);
    }

    @Override
    public void delete(Admin admin) {
        adminRepo.delete(admin);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Admin admin = adminRepo.findByEmail(email);
        return new User(email, admin.getPassword(), new ArrayList<SimpleGrantedAuthority>());
    }
}
