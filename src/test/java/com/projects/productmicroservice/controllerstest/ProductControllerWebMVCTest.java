package com.projects.productmicroservice.controllerstest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projects.productmicroservice.controllers.ProductController;
import com.projects.productmicroservice.dtos.GenericProductDTO;
import com.projects.productmicroservice.services.ProductService;
import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ProductController.class)
public class ProductControllerWebMVCTest {
    @Inject
    private MockMvc mockMvc;
    
    @Inject
    private ObjectMapper objectMapper;
    
    @MockBean(name = "SelfProductService")
    private ProductService productService;

  @Test
  public void getAllProductsTest() throws Exception {
    List<GenericProductDTO> genericProductDTO = new ArrayList<>();
    when(productService.getAllProducts()).thenReturn(genericProductDTO);
      
        mockMvc.perform(get("/products/"))
                .andExpect(status().is(200))
                .andExpect(content().string(objectMapper.writeValueAsString(genericProductDTO)));
                
    }
}
