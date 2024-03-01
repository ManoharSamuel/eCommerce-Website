package com.projects.ecommercewebsite.services;

import org.springframework.stereotype.Service;

@Service
public class FakeStoreProductServiceImpl implements ProductService{
    @Override
    public String getProductById(long id) {
        return "Request to get product by id : "+id;
    }

    @Override
    public String getAllProducts() {
        return "Request to get all products.";
    }

    @Override
    public String deleteProductById(long id) {
        return "Request to delete product with id :" +id;
    }

    @Override
    public String createProduct() {
        return "Request to create a new product";
    }

    @Override
    public String updateProductById(long id) {
        return "Request to update product with id :" +id;
    }
}
