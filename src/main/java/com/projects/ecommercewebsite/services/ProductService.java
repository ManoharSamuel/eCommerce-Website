package com.projects.ecommercewebsite.services;

import com.projects.ecommercewebsite.dtos.GenericProductDTO;
import com.projects.ecommercewebsite.exceptions.ProductDoesNotExistException;
import java.util.List;
import org.springframework.web.bind.annotation.*;

public interface ProductService {

    GenericProductDTO getProductById(long id) throws ProductDoesNotExistException;
    List<GenericProductDTO> getAllProducts();
    GenericProductDTO deleteProductById(long id) throws ProductDoesNotExistException;
    GenericProductDTO createProduct(GenericProductDTO genericProductDTO);
    GenericProductDTO updateProductById(GenericProductDTO genericProductDTO, long id) throws ProductDoesNotExistException;
    GenericProductDTO replaceProductById(GenericProductDTO genericProductDTO, long id) throws ProductDoesNotExistException;

    
}
