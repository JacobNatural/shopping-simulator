package com.app.txt.save.generic;

import com.app.txt.transfer.Transfer;
import com.app.txt.save.Save;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;

/**
 * Abstract implementation of the {@link Save} interface.
 *
 * <p>This generic class provides a basic implementation for saving data to a file. It uses
 * a {@link Transfer} instance to handle the actual writing of data and a {@link Function} to
 * prepare the data for saving.</p>
 *
 * @param <T> the type of data to be saved
 */
@RequiredArgsConstructor
public class AbstractSave<T> implements Save<T> {

    private final Transfer<T, T> transfer;

    /**
     * Writes the specified data to a file after preparing it using the provided function.
     *
     * @param filename the name of the file to write data to
     * @param t        the data to be saved
     * @param prepare  a {@link Function} used to prepare the data for saving
     */
    @Override
    public void write(String filename, T t, Function<T, String> prepare) {
        transfer.write(filename, t, prepare);
    }
}