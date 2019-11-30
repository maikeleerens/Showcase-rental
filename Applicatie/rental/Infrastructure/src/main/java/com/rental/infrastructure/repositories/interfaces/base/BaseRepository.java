package com.rental.infrastructure.repositories.interfaces.base;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BaseRepository<T> {
    List<? extends T> getAll();

    Optional<T> getById(UUID id);

    Optional<T> save(T t);

    Optional<T> update(T t);

    boolean delete(UUID id);
}
