package com.projects.productmicroservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchProductDTO {
    public String name;
    public int itemsPerPage;
    public int pageNumber;
}
