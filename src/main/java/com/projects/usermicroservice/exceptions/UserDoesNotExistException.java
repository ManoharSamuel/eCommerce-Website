package com.projects.usermicroservice.exceptions;

public class UserDoesNotExistException extends Exception{
    public UserDoesNotExistException(String message) {
        super(message);
    }
}
