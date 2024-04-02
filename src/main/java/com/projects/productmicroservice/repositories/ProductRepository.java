package com.projects.productmicroservice.repositories;

import com.projects.productmicroservice.models.Category;
import com.projects.productmicroservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Override
    Optional<Product> findById(Long id);

    List<Product> findByCategory(Category category);

    List<Product> findByPriceBetween(int startPrice, int endPrice);

    List<Product> findByCategory_Id(Long id);

    Product save(Product product);

}
