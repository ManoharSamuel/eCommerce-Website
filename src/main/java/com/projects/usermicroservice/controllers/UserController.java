package com.projects.usermicroservice.controllers;

import com.projects.usermicroservice.dtos.SetRoleRequestDTO;
import com.projects.usermicroservice.dtos.UserDTO;
import com.projects.usermicroservice.exceptions.UserDoesNotExistException;
import com.projects.usermicroservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    
    @Autowired
    public UserController(@Qualifier("UserServiceImpl") UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserDetails(@PathVariable("id") long userId) throws UserDoesNotExistException {
        return userService.getUserDetails(userId);
    }
    
    @PostMapping("/{id}/roles")
    public ResponseEntity<UserDTO> setUserRoles(@PathVariable("id") long userId, 
               @RequestBody SetRoleRequestDTO setRoleRequestDTO) throws UserDoesNotExistException {
        return userService.setUserRoles(userId, setRoleRequestDTO.getRoleIds());
    }
}
