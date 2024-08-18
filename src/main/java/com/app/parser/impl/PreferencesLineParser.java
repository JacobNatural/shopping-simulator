package com.app.parser.impl;

import com.app.parser.LineParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Implementation of {@link LineParser} for parsing preference data from a line of text.
 *
 * This class parses a line of text to extract preference information and returns it as a map with the preference ID as the key
 * and the preference value as the value.
 */
@Component
public class PreferencesLineParser implements LineParser<Long, String> {

    private final String preferencesLineRegex;

    /**
     * Constructs a {@link PreferencesLineParser} with the specified regular expression for line parsing.
     *
     * @param preferencesLineRegex the regular expression used to validate the line format
     */
    public PreferencesLineParser(@Value("${preferenceLineParser.regexp:.*}") String preferencesLineRegex) {
        this.preferencesLineRegex = preferencesLineRegex;
    }

    /**
     * Parses a line of text to extract preference information.
     *
     * @param line the line of text to parse
     * @return a map containing the parsed preference information, with the preference ID as the key and the preference value as the value
     * @throws IllegalArgumentException if the line is null, empty, does not match the regex, or does not contain enough data fields
     */
    @Override
    public Map<Long, String> parseLine(String line) {
        if (line == null) {
            throw new IllegalArgumentException("Line cannot be null");
        }

        if (line.isEmpty()) {
            throw new IllegalArgumentException("Line cannot be empty");
        }

        if (!line.matches(preferencesLineRegex)) {
            throw new IllegalArgumentException(String.format("Line does not match regex: %s", line));
        }

        var split = line.split(";");

        var id = Long.parseLong(split[0]);
        var preference = split[1];

        return Map.of(id, preference);
    }
}