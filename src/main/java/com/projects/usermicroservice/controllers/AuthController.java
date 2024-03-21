package com.projects.usermicroservice.controllers;

import com.projects.usermicroservice.dtos.*;
import com.projects.usermicroservice.exceptions.InvalidRequestException;
import com.projects.usermicroservice.exceptions.UserAlreadyExistsException;
import com.projects.usermicroservice.exceptions.UserDetailsDoesNotMatchException;
import com.projects.usermicroservice.exceptions.UserDoesNotExistException;
import com.projects.usermicroservice.models.SessionStatus;
import com.projects.usermicroservice.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    
    @Autowired
    public AuthController(@Qualifier("AuthServiceImpl") AuthService authService) {
        this.authService = authService;
    }
    
    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) 
            throws UserDetailsDoesNotMatchException, UserDoesNotExistException {
        return authService.login(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());
    }
    
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDTO logoutRequestDTO) 
            throws InvalidRequestException {
        return authService.logout(logoutRequestDTO.getToken(), logoutRequestDTO.getUserId());
    }
    
    @PostMapping("/signUp")
    public ResponseEntity<UserDTO> signUp(@RequestBody SignUpRequestDTO signUpRequestDTO) 
            throws UserAlreadyExistsException {
        return authService.signUp(signUpRequestDTO.getName(), signUpRequestDTO.getEmail(), signUpRequestDTO.getPassword());
    }
    
    @PostMapping("/validate")
    public ResponseEntity<SessionStatus> validateToken(@RequestBody ValidateTokenRequestDTO validateTokenRequestDTO) throws InvalidRequestException {
        return authService.validateToken(validateTokenRequestDTO.getToken(), validateTokenRequestDTO.getUserId());
    }
}
