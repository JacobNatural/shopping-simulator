package com.app.txt.load;

import com.app.parser.LineParser;

import java.util.Map;

/**
 * Interface for loading data from a file.
 *
 * <p>This generic interface defines a method to read data from a file and parse it into a
 * map of key-value pairs using a {@link LineParser}. Implementations of this interface
 * are expected to provide the actual logic for reading and parsing data.</p>
 *
 * @param <T> the type of the key in the resulting map
 * @param <U> the type of the value in the resulting map
 */
public interface Load<T, U> {

    /**
     * Reads data from the specified file and parses it into a map using the provided
     * {@link LineParser}.
     *
     * @param filename   the name of the file to read data from
     * @param lineParser the {@link LineParser} used to parse lines of the file into key-value pairs
     * @return a {@link Map} where the keys and values are parsed from the file
     */
    Map<T, U> read(String filename, LineParser<T, U> lineParser);
}