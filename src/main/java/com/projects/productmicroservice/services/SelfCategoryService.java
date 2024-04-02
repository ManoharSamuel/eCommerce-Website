package com.projects.productmicroservice.services;

import com.projects.productmicroservice.exceptions.CategoryDoesNotExistException;
import com.projects.productmicroservice.models.Category;
import com.projects.productmicroservice.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("SelfCategoryService")
public class SelfCategoryService implements CategoryService{
    private final CategoryRepository categoryRepository;

    @Autowired
    public SelfCategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public String getCategoryById(long id) throws CategoryDoesNotExistException {
        Optional<Category> categoryOptional = categoryRepository.findById(id);

        if (categoryOptional.isEmpty()) {
            throw new CategoryDoesNotExistException("Category with the id "+id+ " does not exist.");    
        } 
        return categoryOptional.get().getName();
    }

    @Override
    public List<String> getAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        
        List<String> categoryNames = new ArrayList<>();
        for (Category category : categoryList) {
            categoryNames.add(category.getName());
        }
        
        return categoryNames;
    }
}
