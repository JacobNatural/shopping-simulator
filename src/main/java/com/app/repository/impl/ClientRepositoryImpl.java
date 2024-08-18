package com.app.repository.impl;

import com.app.parser.LineParser;
import com.app.client.Client;
import com.app.repository.ClientRepository;
import com.app.txt.load.Load;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of the {@link ClientRepository} interface that provides access to client data.
 * <p>
 * This class loads client data from a file using the {@link Load} interface and parses it using a {@link LineParser}.
 */
@Component
public class ClientRepositoryImpl implements ClientRepository {

    private final Map<Long, Client> clients;

    /**
     * Constructs a {@link ClientRepositoryImpl} with the specified file name, loader, and parser.
     *
     * @param clientFilename the name of the file containing client data
     * @param load           the {@link Load} implementation used to read the client data from the file
     * @param parser         the {@link LineParser} implementation used to parse the client data
     */
    public ClientRepositoryImpl(@Value("${client.filename}") String clientFilename, Load<Long, Client> load, LineParser<Long, Client> parser) {
        this.clients = load.read(clientFilename, parser);
    }

    /**
     * Retrieves all clients.
     *
     * @return a map containing all clients, with the client ID as the key and the {@link Client} object as the value
     */
    @Override
    public Map<Long, Client> getAll() {
        return new HashMap<>(clients);
    }

    /**
     * Retrieves a client by their ID.
     *
     * @param id the ID of the client to retrieve
     * @return the {@link Client} object with the specified ID
     * @throws IllegalArgumentException if a client with the specified ID is not found
     */
    @Override
    public Client getById(Long id) {
        if (clients.containsKey(id)) {
            return clients.get(id);
        }
        throw new IllegalArgumentException(String.format("Client with ID: %d not found", id));
    }
}