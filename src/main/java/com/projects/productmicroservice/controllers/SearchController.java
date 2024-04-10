package com.projects.productmicroservice.controllers;

import com.projects.productmicroservice.dtos.GenericProductDTO;
import com.projects.productmicroservice.dtos.SearchProductDTO;
import com.projects.productmicroservice.services.SearchService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {
    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }
    
    @PostMapping("/")
    public List<GenericProductDTO> searchProducts(@RequestBody SearchProductDTO searchProductDTO) {
        return searchService.searchProducts(searchProductDTO.getName(), searchProductDTO.getItemsPerPage(),
                searchProductDTO.getPageNumber());
    }
}
