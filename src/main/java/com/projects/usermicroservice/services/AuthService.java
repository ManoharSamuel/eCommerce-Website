package com.projects.usermicroservice.services;

import com.projects.usermicroservice.dtos.UserDTO;
import com.projects.usermicroservice.exceptions.InvalidRequestException;
import com.projects.usermicroservice.exceptions.UserAlreadyExistsException;
import com.projects.usermicroservice.exceptions.UserDetailsDoesNotMatchException;
import com.projects.usermicroservice.exceptions.UserDoesNotExistException;
import com.projects.usermicroservice.models.SessionStatus;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<UserDTO> login(String email, String password)
            throws UserDoesNotExistException, UserDetailsDoesNotMatchException;

    ResponseEntity<Void> logout(String token, long userId) throws InvalidRequestException;

    ResponseEntity<UserDTO> signUp(String name, String email, String password) throws UserAlreadyExistsException;

    ResponseEntity<SessionStatus> validateToken(String token, long userId) throws InvalidRequestException;
}
