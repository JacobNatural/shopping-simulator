package com.app.repository;

import com.app.client.Client;

/**
 * Interface representing a repository for managing {@link Client} entities.
 * Extends the generic {@link Repository} interface with {@link Long} as the key type
 * and {@link Client} as the value type.
 */
public interface ClientRepository extends Repository<Long, Client> {
}