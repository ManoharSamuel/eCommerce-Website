package com.projects.ecommercewebsite.thirdpartyclients.FakeStoreClient;

import com.projects.ecommercewebsite.dtos.FakeStoreProductDTO;
import com.projects.ecommercewebsite.exceptions.ProductDoesNotExistException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

@Component
public class FakeStoreClient {
    private final RestTemplateBuilder restTemplateBuilder;
    private final String singleProductURL;
    private final String allProductsURL;

    @Autowired
    public FakeStoreClient(RestTemplateBuilder restTemplateBuilder,
                           @Value("${fakeStore.api.url}") String fakeStoreUrl,
                           @Value("${fakeStore.api.all.products}") String fakeStoreAllProductsUrl,
                           @Value("${fakeStore.api.single.product}") String fakeStoreSingleProductUrl
                           ) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.singleProductURL = fakeStoreUrl + fakeStoreSingleProductUrl;
        this.allProductsURL = fakeStoreUrl + fakeStoreAllProductsUrl;
    }

    public FakeStoreProductDTO getProductById(long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> responseEntity =
                restTemplate.getForEntity(singleProductURL, FakeStoreProductDTO.class,id);
        return Objects.requireNonNull(responseEntity.getBody());
    }

    public List<FakeStoreProductDTO> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO[]> responseEntity =
                restTemplate.getForEntity(allProductsURL, FakeStoreProductDTO[].class);
        return Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
    }

    public FakeStoreProductDTO createProduct(FakeStoreProductDTO fakeStoreProductDTO) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> responseEntity =
                restTemplate.postForEntity(allProductsURL,fakeStoreProductDTO, FakeStoreProductDTO.class);
        return Objects.requireNonNull(responseEntity.getBody());
    }

    public FakeStoreProductDTO deleteProductById(long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDTO.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDTO>> responseExtractor =
                restTemplate.responseEntityExtractor(FakeStoreProductDTO.class);
        ResponseEntity<FakeStoreProductDTO> responseEntity =
                restTemplate.execute(singleProductURL, HttpMethod.DELETE, requestCallback, responseExtractor, id);

        if (responseEntity.getBody() == null) {
            throw new ProductDoesNotExistException("Product with the id "+id+" does not exist.");
        }

        return Objects.requireNonNull(responseEntity.getBody());
    }

    public FakeStoreProductDTO updateProductById(FakeStoreProductDTO fakeStoreProductDTO, long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDTO, FakeStoreProductDTO.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDTO>> responseExtractor =
                restTemplate.responseEntityExtractor(FakeStoreProductDTO.class);
        ResponseEntity<FakeStoreProductDTO> responseEntity =
                restTemplate.execute(singleProductURL, HttpMethod.PUT, requestCallback, responseExtractor, id);

        if (responseEntity.getBody() == null) {
            throw new ProductDoesNotExistException("Product with the id "+id+" does not exist.");
        }
        return Objects.requireNonNull(responseEntity.getBody());
    }
}
