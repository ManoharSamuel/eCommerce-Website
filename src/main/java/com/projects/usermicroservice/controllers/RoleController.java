package com.projects.usermicroservice.controllers;

import com.projects.usermicroservice.dtos.CreateRoleRequestDTO;
import com.projects.usermicroservice.models.Role;
import com.projects.usermicroservice.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }
    
    @PostMapping("/add")
    public ResponseEntity<Role> createRole(@RequestBody CreateRoleRequestDTO createRoleRequestDTO) {
        
        return null;
    }
}
