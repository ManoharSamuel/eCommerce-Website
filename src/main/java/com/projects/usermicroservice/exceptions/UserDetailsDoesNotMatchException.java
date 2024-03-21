package com.projects.usermicroservice.exceptions;

public class UserDetailsDoesNotMatchException extends Exception{
    public UserDetailsDoesNotMatchException(String message) {
        super(message);
    }
}
