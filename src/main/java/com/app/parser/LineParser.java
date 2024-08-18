package com.app.parser;

import java.util.Map;

/**
 * A generic interface for parsing a line of text into a {@link Map}.
 *
 * @param <T> the type of the key in the resulting {@link Map}
 * @param <U> the type of the value in the resulting {@link Map}
 */
public interface LineParser<T, U> {

    /**
     * Parses a line of text into a {@link Map} of key-value pairs.
     *
     * @param line the line of text to parse
     * @return a {@link Map} where the key is of type {@code T} and the value is of type {@code U}
     * @throws IllegalArgumentException if the line is invalid or cannot be parsed
     */
    Map<T, U> parseLine(String line);
}