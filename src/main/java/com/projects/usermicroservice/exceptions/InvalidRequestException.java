package com.projects.usermicroservice.exceptions;

public class InvalidRequestException extends Exception{
    public InvalidRequestException(String message) {
        super(message);
    }
}
