package com.app.txt.transfer.impl;

import com.app.parser.LineParser;
import com.app.txt.transfer.Transfer;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Provides implementation for transferring data between files and objects.
 * <p>This class handles both reading data from a file and writing data to a file.</p>
 *
 * @param <T> the type of the key in the map
 * @param <U> the type of the value in the map
 */
@Component
public class TransferImpl<T, U> implements Transfer<T, U> {

    /**
     * Reads data from a file and parses it into a map using the provided {@link LineParser}.
     *
     * <p>The file is read line by line, and each line is parsed into a map entry. The resulting
     * map contains the data from the file as key-value pairs.</p>
     *
     * @param filename   the name of the file to read from
     * @param lineParser a {@link LineParser} used to parse each line into a map entry
     * @return a map containing the data read from the file
     * @throws IllegalArgumentException if any of the arguments are null or empty
     * @throws RuntimeException         if an I/O error occurs
     */
    @Override
    @SneakyThrows
    public Map<T, U> read(String filename, LineParser<T, U> lineParser) {
        if (filename == null) {
            throw new IllegalArgumentException("Filename cannot be null");
        }
        if (filename.isEmpty()) {
            throw new IllegalArgumentException("Filename cannot be empty");
        }
        if (lineParser == null) {
            throw new IllegalArgumentException("Line parser cannot be null");
        }

        try (var lines = Files.lines(Paths.get(filename))) {
            return lines
                    .map(lineParser::parseLine)
                    .flatMap(m -> m.entrySet().stream())
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue
                    ));
        }
    }

    /**
     * Writes an object to a file using the provided {@link Function} to prepare the data.
     *
     * <p>The object is converted to a string using the provided function, and then written to the file.</p>
     *
     * @param filename the name of the file to write to
     * @param t        the object to be written to the file
     * @param prepare  a {@link Function} that prepares the object for writing by converting it to a string
     * @throws IllegalArgumentException if any of the arguments are null or empty
     * @throws RuntimeException         if an I/O error occurs
     */
    @Override
    @SneakyThrows
    public void write(String filename, T t, Function<T, String> prepare) {
        if (filename == null) {
            throw new IllegalArgumentException("Filename cannot be null");
        }
        if (filename.isEmpty()) {
            throw new IllegalArgumentException("Filename cannot be empty");
        }
        if (t == null) {
            throw new IllegalArgumentException("T cannot be null");
        }
        if (prepare == null) {
            throw new IllegalArgumentException("Prepare cannot be null");
        }

        try (var fileWriter = new FileWriter(filename); var printWriter = new PrintWriter(fileWriter)) {
            printWriter.println(prepare.apply(t));
        }
    }
}