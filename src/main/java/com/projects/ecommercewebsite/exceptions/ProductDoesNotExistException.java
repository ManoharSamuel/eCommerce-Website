package com.projects.ecommercewebsite.exceptions;


//@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductDoesNotExistException extends RuntimeException{
    public ProductDoesNotExistException(String message) {
        super(message);
    }
}
