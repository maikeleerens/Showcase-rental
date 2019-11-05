package com.rental.domain.entities.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@MappedSuperclass
public class BaseEntity {

    //region Private attributes
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", unique = true, nullable = false)
    @JsonInclude(Include.NON_NULL)
    private UUID id;

    @Transient
    @JsonInclude(Include.NON_DEFAULT)
    private boolean error;

    @Transient
    @JsonInclude(Include.NON_NULL)
    private String errorMessage;
    //endregion

    //region Getters and setters
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
    //endregion

    //region Public methods
    public void clearError() {
        error = false;
        errorMessage = null;
    }
    //endregion
}
