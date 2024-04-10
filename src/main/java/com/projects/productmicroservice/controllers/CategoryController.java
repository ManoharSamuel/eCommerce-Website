package com.projects.productmicroservice.controllers;

import com.projects.productmicroservice.exceptions.CategoryDoesNotExistException;
import com.projects.productmicroservice.services.CategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(@Qualifier("SelfCategoryService") CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    
    @GetMapping("/{id}")
    public String getProductById(@PathVariable long id) throws CategoryDoesNotExistException {
        return categoryService.getCategoryById(id);
    }
    
    @GetMapping("/")
    public List<String> getAllCategories() {
        return categoryService.getAllCategories();
    }
}
