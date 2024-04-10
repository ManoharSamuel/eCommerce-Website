package com.projects.productmicroservice.services;

import com.projects.productmicroservice.dtos.GenericProductDTO;
import com.projects.productmicroservice.exceptions.ProductDoesNotExistException;
import com.projects.productmicroservice.models.Product;

import java.util.List;

public interface ProductService {

    GenericProductDTO getProductById(long id) throws ProductDoesNotExistException;
    List<GenericProductDTO> getAllProducts();
    GenericProductDTO deleteProductById(long id) throws ProductDoesNotExistException;
    GenericProductDTO createProduct(GenericProductDTO genericProductDTO);
    GenericProductDTO updateProductById(GenericProductDTO genericProductDTO, long id) throws ProductDoesNotExistException;
    GenericProductDTO replaceProductById(GenericProductDTO genericProductDTO, long id) throws ProductDoesNotExistException;

    GenericProductDTO convertToGenericProductDTO(Product product);
}
