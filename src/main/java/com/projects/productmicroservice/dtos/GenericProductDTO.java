package com.projects.productmicroservice.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GenericProductDTO {
    private Long id;
    private String name;
    private String description;
    private Integer price;
    private String categoryName;
    private String imageURL;
}
