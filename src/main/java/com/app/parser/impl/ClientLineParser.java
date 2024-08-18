package com.app.parser.impl;

import com.app.parser.LineParser;
import com.app.client.Client;
import com.app.validators.Validator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Implementation of {@link LineParser} for parsing client data from a line of text.
 *
 * This class parses a line of text to create a {@link Client} object and validates it using the provided {@link Validator}.
 */
@Component
public class ClientLineParser implements LineParser<Long, Client> {

    private final String clientLineRegex;
    private final Validator<Client> validator;

    /**
     * Constructs a {@link ClientLineParser} with the specified regular expression for line parsing and validator.
     *
     * @param clientLineRegex the regular expression used to validate the line format
     * @param validator the validator used to validate the created {@link Client} object
     */
    public ClientLineParser(
            @Value("${clientLineParser.regexp:.*}") String clientLineRegex,
            Validator<Client> validator) {
        this.clientLineRegex = clientLineRegex;
        this.validator = validator;
    }

    /**
     * Parses a line of text to create a {@link Client} object and validates it.
     *
     * @param line the line of text to parse
     * @return a map containing the parsed {@link Client} object with its ID as the key
     * @throws IllegalArgumentException if the line is null, empty, does not match the regex, or does not contain enough data fields
     */
    @Override
    public Map<Long, Client> parseLine(String line) {
        if (line == null) {
            throw new IllegalArgumentException("Line cannot be null");
        }

        if (line.isEmpty()) {
            throw new IllegalArgumentException("Line cannot be empty");
        }

        if (!line.matches(clientLineRegex)) {
            throw new IllegalArgumentException(String.format("Line does not match regex: %s", line));
        }

        var split = line.split(";");

        var id = Long.parseLong(split[0]);

        var client = new Client(id, split[1], // name
                split[2], // surname
                Integer.parseInt(split[3]), // age
                new BigDecimal(split[4]), // balance
                getPreferences(split[5]) // preferences
        );

        Validator.validate(client, validator);

        return Map.of(id, client);
    }

    /**
     * Converts a string of preferences into a list of Longs.
     *
     * @param preferences a string of preferences separated by colons
     * @return a list of preference IDs as Longs
     */
    private List<Long> getPreferences(String preferences) {
        return Arrays.stream(preferences.split(":"))
                .map(Long::parseLong)
                .toList();
    }
}