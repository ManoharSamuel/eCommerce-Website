package com.projects.usermicroservice.controllers;

import com.projects.usermicroservice.dtos.ExceptionDTO;
import com.projects.usermicroservice.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(InvalidRequestException.class)
    private ResponseEntity<ExceptionDTO> productDoesNotExistException(InvalidRequestException invalidRequestException) {
        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setMessage(invalidRequestException.getMessage());

        return new ResponseEntity<>(exceptionDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserDetailsDoesNotMatchException.class)
    private ResponseEntity<ExceptionDTO> userDetailsDoesNotMatchException(UserDetailsDoesNotMatchException userDetailsDoesNotMatchException) {
        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setMessage(userDetailsDoesNotMatchException.getMessage());

        return new ResponseEntity<>(exceptionDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserDoesNotExistException.class)
    private ResponseEntity<ExceptionDTO> userDoesNotExistException(UserDoesNotExistException userDoesNotExistException) {
        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setMessage(userDoesNotExistException.getMessage());

        return new ResponseEntity<>(exceptionDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RoleAlreadyExistsException.class)
    private ResponseEntity<ExceptionDTO> roleAlreadyExistsException(RoleAlreadyExistsException roleAlreadyExistsException) {
        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setMessage(roleAlreadyExistsException.getMessage());

        return new ResponseEntity<>(exceptionDTO, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    private ResponseEntity<ExceptionDTO> userAlreadyExistsException(UserAlreadyExistsException userAlreadyExistsException) {
        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setMessage(userAlreadyExistsException.getMessage());

        return new ResponseEntity<>(exceptionDTO, HttpStatus.METHOD_NOT_ALLOWED);
    }
}
