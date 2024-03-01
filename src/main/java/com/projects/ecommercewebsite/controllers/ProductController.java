package com.projects.ecommercewebsite.controllers;

import com.projects.ecommercewebsite.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public String getProductById(@PathVariable long id){
        return productService.getProductById(id);
    }

    @GetMapping("/")
    public String getAllProducts(){
        return productService.getAllProducts();
    }

    @DeleteMapping("/{id}")
    public String deleteProductById(@PathVariable long id) {
        return productService.deleteProductById(id);
    }

    @PostMapping("/")
    public String createProduct() {
        return productService.createProduct();
    }

    @PutMapping("/{id}")
    public String updateProductById(@PathVariable long id) {
        return productService.updateProductById(id);
    }
}
