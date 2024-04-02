package com.projects.productmicroservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDTO {
    private String id;
    private String title;
    private String description;
    private int price;
    private String category;
    private String image;
}
