package com.projects.productmicroservice.services;

import com.projects.productmicroservice.dtos.GenericProductDTO;
import com.projects.productmicroservice.models.Product;
import com.projects.productmicroservice.models.SortOrder;
import com.projects.productmicroservice.models.SortParams;
import com.projects.productmicroservice.opensearchrepositories.ProductOpenSearchRepository;
import com.projects.productmicroservice.repositories.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class SearchService {
    private final ProductRepository productRepository;
    private final ProductService productService;
    private final ProductOpenSearchRepository productOpenSearchRepository;
    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public SearchService(ProductRepository productRepository, @Qualifier("SelfProductService") ProductService productService,
                         ProductOpenSearchRepository productOpenSearchRepository, RedisTemplate<String, Object> redisTemplate
    ) {
        this.productRepository = productRepository;
        this.productService = productService;
        this.productOpenSearchRepository = productOpenSearchRepository;
        this.redisTemplate = redisTemplate;
    }
    
     //Search using Elastic search 
    
    public List<GenericProductDTO> searchProducts(String name, int productsPerPage, int pageNumber, List<SortParams> sortParamsList) {
        Sort sort = null;

        if (sortParamsList.get(0).getSortOrder().equals(SortOrder.ASC)) {
            sort = Sort.by(sortParamsList.get(0).getSortBy()).ascending();
        } else if (sortParamsList.get(0).getSortOrder().equals(SortOrder.DESC)) {
            sort = Sort.by(sortParamsList.get(0).getSortBy()).descending();
        }
        assert sort != null;
        for (int i = 1; i < sortParamsList.size(); ++i) {
            if (sortParamsList.get(i).getSortOrder().equals(SortOrder.ASC)) {
                sort.and(Sort.by(sortParamsList.get(0).getSortBy()).ascending());
            } else if (sortParamsList.get(0).getSortOrder().equals(SortOrder.DESC)) {
                sort.and(Sort.by(sortParamsList.get(0).getSortBy()).descending());
            }
        }

        PageRequest pageRequest = PageRequest.of(pageNumber, productsPerPage, sort);
        List<Product> productList = productOpenSearchRepository.findByNameContainsIgnoreCase(name, pageRequest);

        List<GenericProductDTO> genericProductDTOS = new ArrayList<>();

        for (Product product : productList) {
            genericProductDTOS.add(productService.convertToGenericProductDTO(product));
        }

        return genericProductDTOS;
    }

    // Search method without using Elastic Search
    
//    public List<GenericProductDTO> searchProducts(String name, int productsPerPage, int pageNumber, List<SortParams> sortParamsList) {
//        Sort sort = null;
//
//        if (sortParamsList.get(0).getSortOrder().equals(SortOrder.ASC)) {
//            sort = Sort.by(sortParamsList.get(0).getSortBy()).ascending();
//        } else if (sortParamsList.get(0).getSortOrder().equals(SortOrder.DESC)) {
//            sort = Sort.by(sortParamsList.get(0).getSortBy()).descending();
//        }
//        assert sort != null;
//        for (int i = 1; i < sortParamsList.size(); ++i) {
//            if (sortParamsList.get(i).getSortOrder().equals(SortOrder.ASC)) {
//                sort.and(Sort.by(sortParamsList.get(0).getSortBy()).ascending());
//            } else if (sortParamsList.get(0).getSortOrder().equals(SortOrder.DESC)) {
//                sort.and(Sort.by(sortParamsList.get(0).getSortBy()).descending());
//            }
//        }
//
//        PageRequest pageRequest = PageRequest.of(pageNumber, productsPerPage, sort);
//        List<Product> productList = productRepository.findByNameContainsIgnoreCase(name, pageRequest);
//
//        List<GenericProductDTO> genericProductDTOS = new ArrayList<>();
//
//        for (Product product : productList) {
//            genericProductDTOS.add(productService.convertToGenericProductDTO(product));
//        }
//
//        return genericProductDTOS;
//    }
}
