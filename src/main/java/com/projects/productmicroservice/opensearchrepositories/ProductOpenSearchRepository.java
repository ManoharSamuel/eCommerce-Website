package com.projects.productmicroservice.opensearchrepositories;


import com.projects.productmicroservice.models.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOpenSearchRepository extends ElasticsearchRepository<Product, Long> {
    List<Product> findByNameContainsIgnoreCase(String name, Pageable pageable);
    
    Product save(Product productOpenSearch);
}
