package com.app.client;

import com.app.repository.PreferencesRepository;
import com.app.validators.Validator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Validator for {@link Client} instances.
 * <p>
 * Validates client properties such as ID, age, balance, and preferences based on configured constraints.
 * </p>
 */
@Component
public class ClientValidator implements Validator<Client> {

    private final int minAge;
    private final BigDecimal minBalance;
    private final PreferencesRepository preferencesRepository;

    /**
     * Constructs a {@link ClientValidator} with the specified minimum age, minimum balance, and preferences repository.
     *
     * @param minAge                the minimum age for a valid client
     * @param minBalance            the minimum balance for a valid client
     * @param preferencesRepository the repository to check if preferences are valid
     */
    public ClientValidator(
            @Value("${clientMinAge:0}") int minAge,
            @Value("${clientMinBalance:0}") BigDecimal minBalance,
            PreferencesRepository preferencesRepository) {
        this.minAge = minAge;
        this.minBalance = minBalance;
        this.preferencesRepository = preferencesRepository;
    }

    /**
     * Validates the given {@link Client} instance.
     * <p>
     * Checks if the client has a valid ID, age, balance, and if all preferences are recognized.
     * </p>
     *
     * @param client the client to validate
     * @return a list of error messages if the client is invalid, otherwise an empty list
     */
    @Override
    public List<String> validate(Client client) {

        var errors = new ArrayList<String>();

        if (client.id < 1) {
            errors.add("ID cannot be negative.");
        }

        if (client.age < minAge) {
            errors.add(STR."Age cannot be less than \{minAge}.");
        }

        if (client.balance.compareTo(minBalance) < 0) {
            errors.add(STR."Balance cannot be less than \{minBalance}.");
        }

        for (var preference : client.preferences) {

            if (!preferencesRepository.containId(preference)) {
                errors.add(STR."Preference ID not available \{preference}.");
            }
        }

        return errors;
    }
}