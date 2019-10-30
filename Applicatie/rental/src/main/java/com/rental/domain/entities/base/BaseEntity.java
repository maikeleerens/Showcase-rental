package com.rental.domain.entities.base;

import javax.persistence.*;
import java.util.UUID;

@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id", unique = true, nullable = false)
    private UUID id;

    public BaseEntity() {
    }

    public UUID getId() {
        return id;
    }
}
