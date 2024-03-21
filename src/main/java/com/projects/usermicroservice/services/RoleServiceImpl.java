package com.projects.usermicroservice.services;

import com.projects.usermicroservice.exceptions.RoleAlreadyExistsException;
import com.projects.usermicroservice.models.Role;
import com.projects.usermicroservice.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service(value = "RoleServiceImpl")
public class RoleServiceImpl implements RoleService{
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public ResponseEntity<Role> createRole(String roleName) throws RoleAlreadyExistsException {
        Optional<Role> roleOptional = roleRepository.findByName(roleName);
        if (roleOptional.isPresent()) {
            throw new RoleAlreadyExistsException("Role "+roleName+" already exists.");
        }
        Role role = new Role();
        role.setName(roleName);
        roleRepository.save(role);
        
        return new ResponseEntity<>(role, HttpStatus.OK);
    }
}
