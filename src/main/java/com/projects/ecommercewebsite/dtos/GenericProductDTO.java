package com.projects.ecommercewebsite.dtos;

import com.projects.ecommercewebsite.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericProductDTO {
    private String id;
    private String name;
    private String description;
    private int price;
    private String categoryName;
    private String imageURL;
}
