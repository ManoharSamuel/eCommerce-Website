package com.projects.productmicroservice.exceptions;

public class CategoryDoesNotExistException extends Exception{
    public CategoryDoesNotExistException(String message) {
        super(message);
    }
}
