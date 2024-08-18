package com.app.txt.load.generic;

import com.app.parser.LineParser;
import com.app.txt.load.Load;
import com.app.txt.transfer.Transfer;
import lombok.RequiredArgsConstructor;

import java.util.Map;

/**
 * An abstract implementation of the {@link Load} interface.
 * This class provides a generic way to load data from a file using a specified
 * {@link Transfer} mechanism and a {@link LineParser} to parse each line of the file.
 *
 * @param <T> the type of keys in the resulting map
 * @param <U> the type of values in the resulting map
 */
@RequiredArgsConstructor
public class AbstractLoad<T, U> implements Load<T, U> {

    /**
     * The transfer mechanism used to read data from a file.
     */
    private final Transfer<T, U> transfer;

    /**
     * Reads data from the specified file and parses it into a map using the provided
     * {@link LineParser}.
     *
     * @param filename the name of the file to read
     * @param lineParser the parser used to parse each line of the file
     * @return a map containing the parsed data
     */
    @Override
    public Map<T, U> read(String filename, LineParser<T, U> lineParser) {
        return transfer.read(filename, lineParser);
    }
}