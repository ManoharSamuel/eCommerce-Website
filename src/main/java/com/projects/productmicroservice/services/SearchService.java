package com.projects.productmicroservice.services;

import com.projects.productmicroservice.dtos.GenericProductDTO;
import com.projects.productmicroservice.models.Product;
import com.projects.productmicroservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {
    private final ProductRepository productRepository;
    private final ProductService productService;

    @Autowired
    public SearchService(ProductRepository productRepository, @Qualifier("SelfProductService") ProductService productService) {
        this.productRepository = productRepository;
        this.productService = productService;
    }
    
    public List<GenericProductDTO> searchProducts(String name, int productsPerPage, int pageNumber) {
        PageRequest pageRequest = PageRequest.of(pageNumber, productsPerPage);
        List<Product> productList = productRepository.findByNameContainsIgnoreCase(name, pageRequest);
        
        List<GenericProductDTO> genericProductDTOS = new ArrayList<>();
        
        for (Product product : productList) {
            genericProductDTOS.add(productService.convertToGenericProductDTO(product));
        }
        
        return genericProductDTOS;
    }
}
