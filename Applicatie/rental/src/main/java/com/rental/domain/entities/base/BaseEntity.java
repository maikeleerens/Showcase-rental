package com.rental.domain.entities.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import javax.persistence.*;
import java.util.UUID;

@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    @JsonInclude(Include.NON_NULL)
    private UUID id;

    @Transient
    @JsonInclude(Include.NON_DEFAULT)
    private boolean error;

    @Transient
    @JsonInclude(Include.NON_NULL)
    private String errorMessage;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public boolean getError() {
        return error;
    }

    public void setError(String errorMessage) {
        this.error = true;
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void clearError() {
        error = false;
        errorMessage = null;
    }
}
