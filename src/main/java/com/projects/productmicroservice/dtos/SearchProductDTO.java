package com.projects.productmicroservice.dtos;

import com.projects.productmicroservice.models.SortParams;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchProductDTO {
    private String name;
    private int itemsPerPage;
    private int pageNumber;
    private List<SortParams> sortParamsList;
}
