package com.projects.productmicroservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SortParams {
    private String sortBy;
    private SortOrder sortOrder;
}
