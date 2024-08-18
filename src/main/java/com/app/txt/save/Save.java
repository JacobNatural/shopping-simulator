package com.app.txt.save;

import java.util.function.Function;

/**
 * Defines a contract for saving objects to a file.
 *
 * @param <T> the type of objects to be saved
 */
public interface Save<T> {

    /**
     * Writes the specified object to a file.
     *
     * <p>This method uses the provided {@link Function} to prepare the object for saving.
     * The function typically converts the object into a format suitable for writing to a file.</p>
     *
     * @param filename the name of the file to which the object will be saved
     * @param t        the object to be saved
     * @param prepare  a {@link Function} that prepares the object for saving, converting it to a {@link String}
     */
    void write(String filename, T t, Function<T, String> prepare);
}