package com.projects.ecommercewebsite.controllers;

import com.projects.ecommercewebsite.dtos.GenericProductDTO;
import com.projects.ecommercewebsite.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public GenericProductDTO getProductById(@PathVariable long id){
        return productService.getProductById(id);
    }

    @GetMapping("/")
    public List<GenericProductDTO> getAllProducts(){
        return productService.getAllProducts();
    }

    @DeleteMapping("/{id}")
    public String deleteProductById(@PathVariable long id) {
        return productService.deleteProductById(id);
    }

    @PostMapping("/")
    public GenericProductDTO createProduct(@RequestBody GenericProductDTO genericProductDTO) {
        return productService.createProduct(genericProductDTO);
    }

    @PutMapping("/{id}")
    public String updateProductById(@PathVariable long id) {
        return productService.updateProductById(id);
    }
}
