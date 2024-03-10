package com.projects.ecommercewebsite.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericProductDTO {
    private Long id;
    private String name;
    private String description;
    private Integer price;
    private String categoryName;
    private String imageURL;
}
