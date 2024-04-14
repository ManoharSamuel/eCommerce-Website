package com.projects.productmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ProductMicroService {

    public static void main(String[] args) {
        SpringApplication.run(ProductMicroService.class, args);
    }

}
