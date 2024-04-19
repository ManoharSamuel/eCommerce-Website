package com.projects.productmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@EnableJpaRepositories("com.projects.productmicroservice.repositories")
@EnableElasticsearchRepositories("com.projects.productmicroservice.opensearchrepositories")
@SpringBootApplication(exclude = {ElasticsearchDataAutoConfiguration.class})
public class ProductMicroService {

    public static void main(String[] args) {
        SpringApplication.run(ProductMicroService.class, args);
    }

}
