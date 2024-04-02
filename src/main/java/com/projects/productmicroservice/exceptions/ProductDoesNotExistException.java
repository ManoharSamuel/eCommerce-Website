package com.projects.productmicroservice.exceptions;


//@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductDoesNotExistException extends Exception{
    public ProductDoesNotExistException(String message) {
        super(message);
    }
}
