package com.projects.usermicroservice.services;

import com.projects.usermicroservice.exceptions.RoleAlreadyExistsException;
import com.projects.usermicroservice.models.Role;
import org.springframework.http.ResponseEntity;

public interface RoleService {
    ResponseEntity<Role> createRole(String roleName) throws RoleAlreadyExistsException;
}
