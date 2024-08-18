package com.app.txt.save.impl;

import com.app.client.Client;
import com.app.txt.save.Save;
import com.app.txt.save.generic.AbstractSave;
import com.app.txt.transfer.Transfer;
import org.springframework.stereotype.Component;

/**
 * Implementation of the {@link Save} interface for {@link Client} objects.
 *
 * <p>This class provides functionality to save {@link Client} instances to a file. It extends
 * the generic {@link AbstractSave} class and uses a {@link Transfer} instance to handle the
 * actual saving process.</p>
 */
@Component
public class ClientSave extends AbstractSave<Client> implements Save<Client> {

    /**
     * Constructs a {@link ClientSave} with the specified {@link Transfer}.
     *
     * @param transfer the {@link Transfer} instance used for saving {@link Client} objects
     */
    public ClientSave(Transfer<Client, Client> transfer) {
        super(transfer);
    }
}