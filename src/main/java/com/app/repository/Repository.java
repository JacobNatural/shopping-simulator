package com.app.repository;

import java.util.Map;

/**
 * A generic interface for managing a repository of entities.
 *
 * @param <T> the type of the identifier used to access entities (e.g., {@link Long})
 * @param <U> the type of the entity being managed by the repository
 */
public interface Repository<T, U> {

    /**
     * Retrieves all entities in the repository.
     *
     * @return a {@link Map} where the keys are of type {@code T} representing the entity identifiers,
     *         and the values are of type {@code U} representing the entities.
     */
    Map<T, U> getAll();

    /**
     * Retrieves an entity by its identifier.
     *
     * @param t the identifier of the entity to retrieve
     * @return the entity of type {@code U} associated with the specified identifier
     * @throws IllegalArgumentException if the entity with the specified identifier is not found
     */
    U getById(T t);
}