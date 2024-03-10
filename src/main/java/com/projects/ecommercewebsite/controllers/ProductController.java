package com.projects.ecommercewebsite.controllers;

import com.projects.ecommercewebsite.dtos.GenericProductDTO;
import com.projects.ecommercewebsite.exceptions.ProductDoesNotExistException;
import com.projects.ecommercewebsite.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    ProductService productService;

    @Autowired
    public ProductController(@Qualifier("SelfProductService") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public GenericProductDTO getProductById(@PathVariable long id) throws ProductDoesNotExistException {
        return productService.getProductById(id);
    }

    @GetMapping("/")
    public List<GenericProductDTO> getAllProducts(){
        return productService.getAllProducts();
    }

    @DeleteMapping("/{id}")
    public GenericProductDTO deleteProductById(@PathVariable long id) throws ProductDoesNotExistException {
        return productService.deleteProductById(id);
    }

    @PostMapping("/")
    public GenericProductDTO createProduct(@RequestBody GenericProductDTO genericProductDTO) {
        return productService.createProduct(genericProductDTO);
    }

    @PatchMapping("/{id}")
    public GenericProductDTO updateProductById(@PathVariable long id, @RequestBody GenericProductDTO genericProductDTO) 
                                                        throws ProductDoesNotExistException {
        return productService.updateProductById(genericProductDTO, id);
    }

    @PutMapping("/{id}")
    public GenericProductDTO replaceProductById(@PathVariable long id, @RequestBody GenericProductDTO genericProductDTO) 
                                                        throws ProductDoesNotExistException {
        return productService.replaceProductById(genericProductDTO, id);
    }
}
