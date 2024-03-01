package com.projects.ecommercewebsite.services;

import org.springframework.web.bind.annotation.*;

public interface ProductService {

    String getProductById(long id);

    String getAllProducts();

    String deleteProductById(long id);

    String createProduct();

    String updateProductById(long id);
}
