package com.app.repository;

/**
 * Interface representing a repository for managing preferences.
 * Extends the generic {@link Repository} interface with {@link Long} as the key type
 * and {@link String} as the value type.
 *
 * This repository provides an additional method to check if a preference ID exists.
 */
public interface PreferencesRepository extends Repository<Long, String> {

    /**
     * Checks if the repository contains a preference with the given ID.
     *
     * @param id the ID of the preference to check
     * @return {@code true} if the preference ID exists, {@code false} otherwise
     */
    boolean containId(Long id);
}