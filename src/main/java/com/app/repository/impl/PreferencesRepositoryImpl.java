package com.app.repository.impl;

import com.app.parser.LineParser;
import com.app.repository.PreferencesRepository;
import com.app.txt.load.Load;
import com.app.validators.Validator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of the {@link PreferencesRepository} interface that provides access to preference data.
 * <p>
 * This class loads preferences data from a file using the {@link Load} interface, parses it using a {@link LineParser},
 * and validates it using a {@link Validator}.
 */
@Component
public class PreferencesRepositoryImpl implements PreferencesRepository {

    private final Map<Long, String> preferences;

    /**
     * Constructs a {@link PreferencesRepositoryImpl} with the specified file name, loader, parser, and validator.
     *
     * @param preferencesFilename  the name of the file containing preferences data
     * @param load                 the {@link Load} implementation used to read the preferences data from the file
     * @param lineParser           the {@link LineParser} implementation used to parse the preferences data
     * @param preferencesValidator the {@link Validator} implementation used to validate the parsed preferences data
     */
    public PreferencesRepositoryImpl(@Value("${preferencesFilename:preferences.txt}") String preferencesFilename,
                                     Load<Long, String> load,
                                     LineParser<Long, String> lineParser,
                                     Validator<Map<Long, String>> preferencesValidator) {
        var preferences = load.read(preferencesFilename, lineParser);
        Validator.validate(preferences, preferencesValidator);
        this.preferences = preferences;
    }

    /**
     * Checks if a preference with the specified ID exists.
     *
     * @param id the ID of the preference to check
     * @return true if the preference exists, false otherwise
     */
    @Override
    public boolean containId(Long id) {
        return preferences.containsKey(id);
    }

    /**
     * Retrieves all preferences.
     *
     * @return a map containing all preferences, with the preference ID as the key and the preference name as the value
     */
    @Override
    public Map<Long, String> getAll() {
        return new HashMap<>(preferences);
    }

    /**
     * Retrieves a preference by its ID.
     *
     * @param id the ID of the preference to retrieve
     * @return the preference name associated with the specified ID
     * @throws IllegalArgumentException if a preference with the specified ID is not found
     */
    @Override
    public String getById(Long id) {
        if (containId(id)) {
            return preferences.get(id);
        }
        throw new IllegalArgumentException(String.format("Preference with ID: %d not found", id));
    }
}