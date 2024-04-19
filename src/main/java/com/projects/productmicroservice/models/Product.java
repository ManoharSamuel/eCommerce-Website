package com.projects.productmicroservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;

@Getter
@Setter
@Entity
@Document(indexName = "elasticsearch_product")
public class Product extends BaseModel{
    private String name;
    private String description;
    private int price;
    @ManyToOne
    private Category category;
    private String imageURL;
}
