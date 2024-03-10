package com.projects.ecommercewebsite.services;

import com.projects.ecommercewebsite.exceptions.CategoryDoesNotExistException;
import com.projects.ecommercewebsite.models.Category;
import java.util.List;

public interface CategoryService {
    String getCategoryById(long id) throws CategoryDoesNotExistException;
    List<String> getAllCategories();
}
