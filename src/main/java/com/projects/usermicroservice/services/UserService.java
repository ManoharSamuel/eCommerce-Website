package com.projects.usermicroservice.services;

import com.projects.usermicroservice.dtos.UserDTO;
import com.projects.usermicroservice.exceptions.UserDoesNotExistException;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<UserDTO> getUserDetails(long userId) throws UserDoesNotExistException;
    ResponseEntity<UserDTO> setUserRoles(long userId, List<Long> roleIds) throws UserDoesNotExistException;
}
