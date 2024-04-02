package com.projects.ecommercewebsite.controllerstest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.projects.productmicroservice.controllers.ProductController;
import com.projects.productmicroservice.dtos.GenericProductDTO;
import com.projects.productmicroservice.exceptions.ProductDoesNotExistException;
import com.projects.productmicroservice.services.ProductService;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class ProductControllerTest {
    @Inject
    private ProductController productController;
    @MockBean(name = "SelfProductService")
    private ProductService productService;
    
    //@MockBean
//    public ProductControllerTest(@Qualifier("SelfProductService") ProductService productService) {
//        this.productService = productService;
//    }

    @Test
    public void getProductById() throws ProductDoesNotExistException {
        GenericProductDTO genericProductDTO = new GenericProductDTO();
        when(productService.getProductById(10L)).thenReturn(genericProductDTO);
        
        assertEquals(productController.getProductById(10L), genericProductDTO);
    }
}
