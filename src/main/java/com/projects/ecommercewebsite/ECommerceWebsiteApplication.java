package com.projects.ecommercewebsite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ECommerceWebsiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(ECommerceWebsiteApplication.class, args);
    }

}
