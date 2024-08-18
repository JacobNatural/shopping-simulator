package com.app.txt.load.impl;

import com.app.client.Client;
import com.app.txt.transfer.Transfer;
import com.app.txt.load.Load;
import com.app.txt.load.generic.AbstractLoad;
import org.springframework.stereotype.Component;

/**
 * A concrete implementation of the {@link Load} interface for loading {@link Client} data.
 * This class extends {@link AbstractLoad} and is used specifically to handle the loading
 * of client data from a file, using a specified {@link Transfer} mechanism.
 *
 * <p>This class is marked as a Spring {@link Component} to be automatically detected
 * and managed by the Spring container.</p>
 */
@Component
public class ClientLoad extends AbstractLoad<Long, Client> implements Load<Long, Client> {

    /**
     * Constructs a {@link ClientLoad} instance with the specified {@link Transfer} mechanism.
     *
     * @param transfer the {@link Transfer} mechanism used to read client data from a file
     */
    public ClientLoad(Transfer<Long, Client> transfer) {
        super(transfer);
    }
}