package com.app.validators;

import com.app.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Validator for checking the validity of preferences against available product categories.
 * <p>This class implements the {@link Validator} interface to ensure that preferences are
 * valid according to the categories available in the {@link ProductRepository}.</p>
 */
@Component
@RequiredArgsConstructor
public class PreferencesValidator implements Validator<Map<Long, String>> {

    private final ProductRepository productRepository;

    /**
     * Validates the provided preferences.
     * <p>The validation checks the following:</p>
     * <ul>
     *     <li>No duplicate categories are allowed in the preferences.</li>
     *     <li>Each category in the preferences must be one of the available categories in the {@link ProductRepository}.</li>
     * </ul>
     *
     * @param preferences a map where the key is the preference ID and the value is the category name
     * @return a list of validation error messages. If the list is empty, the preferences are valid.
     * @throws IllegalArgumentException if {@code preferences} is {@code null}
     */
    @Override
    public List<String> validate(Map<Long, String> preferences) {

        if (preferences == null) {
            throw new IllegalArgumentException("Preferences cannot be null");
        }

        var categories = productRepository.availableCategories();
        var errors = new ArrayList<String>();
        var valuesPreferences = preferences.values().stream().distinct().toList();

        // Check for duplicate categories in preferences
        if (valuesPreferences.size() != preferences.size()) {
            errors.add("Categories in preferences cannot be duplicated");
        }

        // Check if each preference category is available
        for (var category : valuesPreferences) {
            if (!categories.contains(category)) {
                errors.add(String.format("Category not available: %s", category));
            }
        }

        return errors;
    }
}