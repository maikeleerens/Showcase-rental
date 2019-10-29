package com.rental.domain.entities.base;

import javax.persistence.Id;
import java.util.UUID;

public class BaseEntity {
    @Id
    private UUID id;

    public BaseEntity() {
    }

    public UUID getId() {
        return id;
    }
}
