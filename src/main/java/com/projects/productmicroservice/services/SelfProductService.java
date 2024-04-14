package com.projects.productmicroservice.services;

import com.projects.productmicroservice.dtos.GenericProductDTO;
import com.projects.productmicroservice.exceptions.ProductDoesNotExistException;
import com.projects.productmicroservice.models.Category;
import com.projects.productmicroservice.models.Product;
import com.projects.productmicroservice.repositories.CategoryRepository;
import com.projects.productmicroservice.repositories.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SelfProductService")
public class SelfProductService implements ProductService{
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    //private final ProductOpenSearchRepository productOpenSearchRepository;

    @Autowired
    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository
                              //ProductOpenSearchRepository productOpenSearchRepository
                              ) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        //this.productOpenSearchRepository = productOpenSearchRepository;
    }

    public GenericProductDTO convertToGenericProductDTO(Product product) {
        GenericProductDTO genericProductDTO = new GenericProductDTO();

        genericProductDTO.setCategoryName(product.getCategory().getName());
        genericProductDTO.setName(product.getName());
        genericProductDTO.setPrice(product.getPrice());
        genericProductDTO.setDescription(product.getDescription());
        genericProductDTO.setImageURL(product.getImageURL());
        genericProductDTO.setId(product.getId());

        return genericProductDTO;
    }
    
    private Product convertToProduct(GenericProductDTO genericProductDTO) {
        Product product = new Product();
    
        product.setPrice(genericProductDTO.getPrice());
        product.setDescription(genericProductDTO.getDescription());
        product.setName(genericProductDTO.getName());
        product.setImageURL(genericProductDTO.getImageURL());

        Category categoryOfProduct = findOrCreateCategory(genericProductDTO);
        
        product.setCategory(categoryOfProduct);
        
        return product;
    }
    @Override
    public GenericProductDTO getProductById(long id) throws ProductDoesNotExistException {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            throw new ProductDoesNotExistException("Product with the id " +id+ " does not exist.");    
        }
        return convertToGenericProductDTO(productOptional.get());
    }

    @Override
    public List<GenericProductDTO> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        
        List<GenericProductDTO> genericProductDTOList = new ArrayList<>();
        
        for (Product product : productList) {
            genericProductDTOList.add(convertToGenericProductDTO(product));
        }
        
        return genericProductDTOList;
    }

    @Override
    public GenericProductDTO deleteProductById(long id) throws ProductDoesNotExistException {
        Optional<Product> productOptional = productRepository.findById(id);
        
        if (productOptional.isEmpty()) {
            throw new ProductDoesNotExistException("Product with the id "+id+" does not exist.");
        }
        Product product = productOptional.get();
        
        productRepository.delete(product);
        return convertToGenericProductDTO(product);
    }

    @Override
    public GenericProductDTO createProduct(GenericProductDTO genericProductDTO) {
        Category categoryOfProduct = findOrCreateCategory(genericProductDTO);
        
        Product product = convertToProduct(genericProductDTO);
        product.setCategory(categoryOfProduct);
        
        //productOpenSearchRepository.save(product);
        
        return convertToGenericProductDTO(productRepository.save(product));
    }

    private Category findOrCreateCategory(GenericProductDTO genericProductDTO) {
        Optional<Category> categoryOptional = categoryRepository.findByName(genericProductDTO.getCategoryName());
        Category categoryOfProduct;
        if (categoryOptional.isEmpty()) {
            Category category = new Category();
            category.setName(genericProductDTO.getCategoryName());
            categoryOfProduct = categoryRepository.save(category);    
        } else {
            categoryOfProduct = categoryOptional.get();
        }
        return categoryOfProduct;
    }

    @Override
    public GenericProductDTO updateProductById(GenericProductDTO genericProductDTO, long id) 
                                                    throws ProductDoesNotExistException {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            throw new ProductDoesNotExistException("Product with the id "+id+" does not exist.");
        }
        Product product = productOptional.get();
        
        if (genericProductDTO.getCategoryName() != null) {
            Category categoryOfProduct = findOrCreateCategory(genericProductDTO);
            product.setCategory(categoryOfProduct);
        } if (genericProductDTO.getPrice() != null ) {
            product.setPrice(genericProductDTO.getPrice());
        }
        if (genericProductDTO.getDescription() != null) {
            product.setDescription(genericProductDTO.getDescription());
        }
        if (genericProductDTO.getName() != null) {
            product.setName(genericProductDTO.getName());
        }
        if (genericProductDTO.getImageURL() != null) {
            product.setImageURL(genericProductDTO.getImageURL());
        }
        
        return convertToGenericProductDTO(productRepository.save(product));
    }

    @Override
    public GenericProductDTO replaceProductById(GenericProductDTO genericProductDTO, long id) {
        Product product = convertToProduct(genericProductDTO);
        product.setId(id);
        Product newProduct = productRepository.save(product);
        return convertToGenericProductDTO(newProduct);
    }

}
