package com.projects.usermicroservice.services;

import com.projects.usermicroservice.dtos.*;
import com.projects.usermicroservice.exceptions.InvalidRequestException;
import com.projects.usermicroservice.exceptions.UserAlreadyExistsException;
import com.projects.usermicroservice.exceptions.UserDetailsDoesNotMatchException;
import com.projects.usermicroservice.exceptions.UserDoesNotExistException;
import com.projects.usermicroservice.models.Session;
import com.projects.usermicroservice.models.SessionStatus;
import com.projects.usermicroservice.models.User;
import com.projects.usermicroservice.repositories.SessionRepository;
import com.projects.usermicroservice.repositories.UserRepository;

import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service(value = "AuthServiceImpl")
public class AuthServiceImpl implements AuthService{
    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, SessionRepository sessionRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    
    private UserDTO getUserDTOFromUser(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getName());
        userDTO.setRoles(user.getRoles());
        return userDTO;
    }

    public ResponseEntity<UserDTO> login(String email, String password)
      throws UserDoesNotExistException, UserDetailsDoesNotMatchException {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new UserDoesNotExistException("User with the email Id "+email+ " does not exist.");
        }
        
        User user = userOptional.get();
        if (!user.getEmail().equals(email) || !bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new UserDetailsDoesNotMatchException("Invalid email or password. Please try again.");
        }
        
        UserDTO userDTO = getUserDTOFromUser(user);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
    
    public ResponseEntity<Void> logout(String token, long userId) throws InvalidRequestException {
        Optional<Session> sessionOptional = sessionRepository.findByTokenAndUserId(token, userId);
        if (sessionOptional.isEmpty()) {
            throw new InvalidRequestException("Invalid Request");
        }
        Session session = sessionOptional.get();
        session.setSessionStatus(SessionStatus.INACTIVE);
        sessionRepository.save(session);
        
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    public ResponseEntity<UserDTO> signUp(String name, String email, String password) throws UserAlreadyExistsException {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            throw new UserAlreadyExistsException("User with the email Id"+email+" is already registered.");
        }
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        User user1 = userRepository.save(user);
        
        return new ResponseEntity<>(getUserDTOFromUser(user1), HttpStatus.OK);
    }
    
    public ResponseEntity<SessionStatus> validateToken(String token, long userId) throws InvalidRequestException {
        Optional<Session> sessionOptional = sessionRepository.findByTokenAndUserId(token, userId);
        if (sessionOptional.isEmpty()) {
            throw new InvalidRequestException("Invalid Request");
        }
        Session session = sessionOptional.get();
        if (session.getSessionStatus().equals(SessionStatus.INACTIVE)) {
            return new ResponseEntity<>(SessionStatus.INACTIVE, HttpStatus.OK);
        }
        
        Date currentTime = new Date();
        if (session.getExpiringAt().before(currentTime)) {
            session.setSessionStatus(SessionStatus.INACTIVE);
            sessionRepository.save(session);
            return new ResponseEntity<>(SessionStatus.INACTIVE, HttpStatus.OK);
        }

        return new ResponseEntity<>(SessionStatus.ACTIVE, HttpStatus.OK);
    }
}
