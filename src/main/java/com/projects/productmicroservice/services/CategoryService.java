package com.projects.productmicroservice.services;

import com.projects.productmicroservice.exceptions.CategoryDoesNotExistException;

import java.util.List;

public interface CategoryService {
    String getCategoryById(long id) throws CategoryDoesNotExistException;
    List<String> getAllCategories();
}
