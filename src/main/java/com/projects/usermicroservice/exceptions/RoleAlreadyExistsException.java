package com.projects.usermicroservice.exceptions;

public class RoleAlreadyExistsException extends Exception{
    public RoleAlreadyExistsException(String message) {
        super(message);
    }
}
