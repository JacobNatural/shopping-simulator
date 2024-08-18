package com.app.txt.transfer;

import com.app.parser.LineParser;

import java.util.Map;
import java.util.function.Function;

/**
 * Interface for transferring data between files and objects.
 * <p>This interface defines methods for reading data from a file and writing data to a file.</p>
 *
 * @param <T> the type of the key in the map used for reading and writing
 * @param <U> the type of the value in the map used for reading
 */
public interface Transfer<T, U> {

    /**
     * Reads data from a file and parses it into a map.
     *
     * <p>Each line of the file is processed using the provided {@link LineParser}, which parses
     * each line into a key-value pair. The resulting map contains the data read from the file.</p>
     *
     * @param filename   the name of the file to read from
     * @param lineParser a {@link LineParser} used to parse each line into a map entry
     * @return a map containing the data read from the file
     * @throws IllegalArgumentException if {@code filename} or {@code lineParser} is {@code null} or empty
     * @throws RuntimeException         if an I/O error occurs during reading
     */
    Map<T, U> read(String filename, LineParser<T, U> lineParser);

    /**
     * Writes data to a file.
     *
     * <p>The object {@code t} is converted to a string using the provided {@link Function},
     * and the resulting string is written to the specified file.</p>
     *
     * @param filename the name of the file to write to
     * @param t        the object to be written to the file
     * @param prepare  a {@link Function} that converts the object to a string for writing
     * @throws IllegalArgumentException if {@code filename}, {@code t}, or {@code prepare} is {@code null}
     * @throws RuntimeException         if an I/O error occurs during writing
     */
    void write(String filename, T t, Function<T, String> prepare);
}