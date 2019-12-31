package com.rental.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.rental.api", "com.rental.services", "com.rental.infrastructure"})
@EntityScan({"com.rental.infrastructure.datamodels", "com.rental.domain.entities", "com.rental.services.models"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
