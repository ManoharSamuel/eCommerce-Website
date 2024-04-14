package com.projects.usermicroservice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projects.usermicroservice.dtos.*;
import com.projects.usermicroservice.exceptions.InvalidRequestException;
import com.projects.usermicroservice.exceptions.UserAlreadyExistsException;
import com.projects.usermicroservice.exceptions.UserDetailsDoesNotMatchException;
import com.projects.usermicroservice.exceptions.UserDoesNotExistException;
import com.projects.usermicroservice.models.Token;
import com.projects.usermicroservice.models.User;
import com.projects.usermicroservice.repositories.TokenRepository;
import com.projects.usermicroservice.repositories.UserRepository;

import java.util.*;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import org.flywaydb.core.internal.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.web.bind.support.SessionStatus;

import javax.crypto.SecretKey;

@Service(value = "AuthServiceImpl")
public class AuthServiceImpl implements AuthService{
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private ObjectMapper objectMapper;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, TokenRepository tokenRepository, 
                           BCryptPasswordEncoder bCryptPasswordEncoder, KafkaTemplate<String, String> kafkaTemplate,
                           ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
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

        MacAlgorithm alg = Jwts.SIG.HS256;
        SecretKey key = alg.key().build();

        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("email", user.getEmail());
        jsonMap.put("roles", List.of(user.getRoles()));
        jsonMap.put("createdAt", new Date());
        jsonMap.put("expiryAt", DateUtils.addDaysToDate(new Date(), 30));
        
        String jws = Jwts.builder().claims(jsonMap).signWith(key, alg).compact();

        Token token = new Token();
        token.setValue(jws);
        token.setUser(user);
        token.setExpiringAt(DateUtils.addDaysToDate(new Date(), 30));
        token.setDeleted(false);
        tokenRepository.save(token);
        
        UserDTO userDTO = getUserDTOFromUser(user);
        MultiValueMapAdapter<String, String> headers = new MultiValueMapAdapter<>(new HashMap<>());
        headers.add(HttpHeaders.SET_COOKIE, "auth-token:"+jws);
        
        return new ResponseEntity<>(userDTO, headers, HttpStatus.OK);
    }



    public ResponseEntity<Void> logout(String token) throws InvalidRequestException {
        Optional<Token> tokenOptional = tokenRepository.findByValueAndDeletedEquals(token, false);
        if (tokenOptional.isEmpty()) {
            throw new InvalidRequestException("Invalid Request");
        }
        Token tokenOne = tokenOptional.get();
        tokenOne.setDeleted(true);
        tokenRepository.save(tokenOne);
        
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
        
        SendEmailEventDTO sendEmailEventDTO = new SendEmailEventDTO();
        sendEmailEventDTO.setTo(email);
        sendEmailEventDTO.setFrom("testsenderemail7@gmail.com");
        sendEmailEventDTO.setSubject("Welcome to Scaler");
        sendEmailEventDTO.setEmailBody("Thanks for signing up with Scaler. " +
                "Hope you have a wonderful upskilling journey.");

        try {
            kafkaTemplate.send(
                    "sendEmail",
                    objectMapper.writeValueAsString(sendEmailEventDTO)
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        
        return new ResponseEntity<>(getUserDTOFromUser(user1), HttpStatus.OK);
    }


    public ResponseEntity<UserDTO> validateToken(String token) throws InvalidRequestException {
        Optional<Token> tokenOptional = tokenRepository.
                findByValueAndDeletedEqualsAndExpiringAtGreaterThan(token, false, new Date());
        if (tokenOptional.isEmpty()) {
            throw new InvalidRequestException("Invalid Request");
        }
        Token token1 = tokenOptional.get();
        User user = token1.getUser();
        UserDTO userDTO = new UserDTO();
        userDTO.setRoles(user.getRoles());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}
