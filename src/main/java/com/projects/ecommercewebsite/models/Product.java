package com.projects.ecommercewebsite.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel{
    private String name;
    private String description;
    private int price;
    private Category category;
    private String imageURL;
}
