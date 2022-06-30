package org.supremecorp.hospitalqueuemanagement.services.base;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.supremecorp.hospitalqueuemanagement.model.Admin;

public interface AdminService extends CrudService<Admin, String>, UserDetailsService {
}
