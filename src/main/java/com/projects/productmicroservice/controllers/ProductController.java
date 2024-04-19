package com.projects.productmicroservice.controllers;

import com.projects.productmicroservice.dtos.GenericProductDTO;
import com.projects.productmicroservice.exceptions.ProductDoesNotExistException;
import com.projects.productmicroservice.services.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    private final ProductService productService;
    private static final Logger LOG = LogManager.getLogger(ProductController.class);

    @Autowired
    public ProductController(@Qualifier("SelfProductService") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public GenericProductDTO getProductById(@PathVariable long id) throws ProductDoesNotExistException {
        ThreadContext.put("productId", String.valueOf(id));
        LOG.info("Fetching details for product with id " + id);
        GenericProductDTO genericProductDTO = productService.getProductById(id);
        LOG.info("Details of product" + genericProductDTO.toString());
        ThreadContext.clearAll();
        return genericProductDTO;
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
