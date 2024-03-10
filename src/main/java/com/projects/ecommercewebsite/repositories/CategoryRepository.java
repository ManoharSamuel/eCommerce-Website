package com.projects.ecommercewebsite.repositories;

import com.projects.ecommercewebsite.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Override
    Optional<Category> findById(Long id);
    
    Optional<Category> findByName(String categoryName);

    @Override
    Category save(Category category);
}
