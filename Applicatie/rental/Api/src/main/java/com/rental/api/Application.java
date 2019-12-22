package com.rental.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.rental.services", "com.rental.api.controllers", "com.rental.infrastructure.repositories", "com.rental.api.service", "com.rental.api.config", "com.rental.api.filters"})
@EntityScan({"com.rental.infrastructure.datamodels", "com.rental.domain.entities"})
@EnableJpaRepositories("com.rental.infrastructure.repositories")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    }
