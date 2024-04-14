package com.projects.productmicroservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class FakeStoreProductDTO implements Serializable {
    private String id;
    private String title;
    private String description;
    private int price;
    private String category;
    private String image;
}
