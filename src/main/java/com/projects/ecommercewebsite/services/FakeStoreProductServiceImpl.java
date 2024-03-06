package com.projects.ecommercewebsite.services;

import com.projects.ecommercewebsite.dtos.FakeStoreProductDTO;
import com.projects.ecommercewebsite.dtos.GenericProductDTO;
import com.projects.ecommercewebsite.exceptions.ProductDoesNotExistException;
import org.apache.coyote.Response;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class FakeStoreProductServiceImpl implements ProductService{
    private final RestTemplateBuilder restTemplateBuilder;
    private final String singleProductURL = "https://fakestoreapi.com/products/{id}";
    private final String allProductsURL = "https://fakestoreapi.com/products";

    public FakeStoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    private GenericProductDTO convertToGenericProductDTO(FakeStoreProductDTO fakeStoreProductDTO) {
        GenericProductDTO genericProductDTO = new GenericProductDTO();

        genericProductDTO.setCategoryName(fakeStoreProductDTO.getCategory());
        genericProductDTO.setName(fakeStoreProductDTO.getTitle());
        genericProductDTO.setPrice(fakeStoreProductDTO.getPrice());
        genericProductDTO.setDescription(fakeStoreProductDTO.getDescription());
        genericProductDTO.setImageURL(fakeStoreProductDTO.getImage());
        genericProductDTO.setId(fakeStoreProductDTO.getId());

        return genericProductDTO;
    }

    private FakeStoreProductDTO convertToFakeStoreProductDTO(GenericProductDTO genericProductDTO) {
        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();

        fakeStoreProductDTO.setPrice(genericProductDTO.getPrice());
        fakeStoreProductDTO.setDescription(genericProductDTO.getDescription());
        fakeStoreProductDTO.setTitle(genericProductDTO.getName());
        fakeStoreProductDTO.setImage(genericProductDTO.getImageURL());
        fakeStoreProductDTO.setCategory(genericProductDTO.getCategoryName());

        return fakeStoreProductDTO;
    }
    @Override
    public GenericProductDTO getProductById(long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> responseEntity =
                        restTemplate.getForEntity(singleProductURL, FakeStoreProductDTO.class,id);

        return convertToGenericProductDTO(Objects.requireNonNull(responseEntity.getBody()));
    }



    @Override
    public List<GenericProductDTO> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO[]> responseEntity =
                        restTemplate.getForEntity(allProductsURL, FakeStoreProductDTO[].class);
        List<GenericProductDTO> genericProductDTOList = new ArrayList<>();
        for (FakeStoreProductDTO fakeStoreProductDTO : Objects.requireNonNull(responseEntity.getBody())) {
            genericProductDTOList.add(convertToGenericProductDTO(fakeStoreProductDTO));
        }
       return genericProductDTOList;
    }

    @Override
    public GenericProductDTO createProduct(GenericProductDTO genericProductDTO) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDTO fakeStoreProductDTO = convertToFakeStoreProductDTO(genericProductDTO);
        ResponseEntity<FakeStoreProductDTO> responseEntity =
                        restTemplate.postForEntity(allProductsURL,fakeStoreProductDTO, FakeStoreProductDTO.class);
        return convertToGenericProductDTO(Objects.requireNonNull(responseEntity.getBody()));
    }

    @Override
    public GenericProductDTO deleteProductById(long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDTO.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDTO>> responseExtractor =
                restTemplate.responseEntityExtractor(FakeStoreProductDTO.class);
        ResponseEntity<FakeStoreProductDTO> responseEntity =
                restTemplate.execute(singleProductURL, HttpMethod.DELETE, requestCallback, responseExtractor, id);

        if (responseEntity.getBody() == null) {
            throw new ProductDoesNotExistException("Product with the id "+id+" does not exist.");
        }

        return convertToGenericProductDTO(Objects.requireNonNull(responseEntity.getBody()));
    }

    @Override
    public GenericProductDTO updateProductById(GenericProductDTO genericProductDTO, long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDTO fakeStoreProductDTO = convertToFakeStoreProductDTO(genericProductDTO);

        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDTO, FakeStoreProductDTO.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDTO>> responseExtractor =
                restTemplate.responseEntityExtractor(FakeStoreProductDTO.class);
        ResponseEntity<FakeStoreProductDTO> responseEntity =
                restTemplate.execute(singleProductURL, HttpMethod.PUT, requestCallback, responseExtractor, id);

        if (responseEntity.getBody() == null) {
            throw new ProductDoesNotExistException("Product with the id "+id+" does not exist.");
        }
        return convertToGenericProductDTO(Objects.requireNonNull(responseEntity.getBody()));
    }
}
