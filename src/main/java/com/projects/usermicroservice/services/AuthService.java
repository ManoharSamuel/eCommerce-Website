package com.projects.usermicroservice.services;

import com.projects.usermicroservice.dtos.UserDTO;
import com.projects.usermicroservice.exceptions.InvalidRequestException;
import com.projects.usermicroservice.exceptions.UserAlreadyExistsException;
import com.projects.usermicroservice.exceptions.UserDetailsDoesNotMatchException;
import com.projects.usermicroservice.exceptions.UserDoesNotExistException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.support.SessionStatus;

public interface AuthService {
    ResponseEntity<UserDTO> login(String email, String password)
            throws UserDoesNotExistException, UserDetailsDoesNotMatchException;

    ResponseEntity<Void> logout(String token) throws InvalidRequestException;

    ResponseEntity<UserDTO> signUp(String name, String email, String password) throws UserAlreadyExistsException;

    ResponseEntity<UserDTO> validateToken(String token) throws InvalidRequestException;
}
