package com.projects.ecommercewebsite.services;

import com.projects.ecommercewebsite.dtos.GenericProductDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ProductService {

    GenericProductDTO getProductById(long id);

    List<GenericProductDTO> getAllProducts();

    GenericProductDTO deleteProductById(long id);

    GenericProductDTO createProduct(GenericProductDTO genericProductDTO);

    GenericProductDTO updateProductById(GenericProductDTO genericProductDTO, long id);
}
