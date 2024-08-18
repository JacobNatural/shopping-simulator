package com.app.txt.load.impl;

import com.app.txt.load.Load;
import com.app.txt.load.generic.AbstractLoad;
import com.app.txt.transfer.Transfer;
import org.springframework.stereotype.Component;

/**
 * A concrete implementation of the {@link Load} interface for loading preferences data.
 * This class extends {@link AbstractLoad} and is used specifically to handle the loading
 * of preferences data from a file, using a specified {@link Transfer} mechanism.
 *
 * <p>This class is marked as a Spring {@link Component} to be automatically detected
 * and managed by the Spring container.</p>
 */
@Component
public class PreferencesLoad extends AbstractLoad<Long, String> implements Load<Long, String> {

    /**
     * Constructs a {@link PreferencesLoad} instance with the specified {@link Transfer} mechanism.
     *
     * @param transfer the {@link Transfer} mechanism used to read preferences data from a file
     */
    public PreferencesLoad(Transfer<Long, String> transfer) {
        super(transfer);
    }
}