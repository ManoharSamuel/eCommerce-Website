package com.projects.productmicroservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
public class Product extends BaseModel{
    private String name;
    private String description;
    private int price;
    @ManyToOne
    private Category category;
    private String imageURL;
}
