package com.app.validators;

import java.util.List;

/**
 * Interface for validating objects of type {@code T}.
 * <p>This interface provides a contract for validating instances of type {@code T} and collecting
 * validation errors.</p>
 *
 * @param <T> the type of object to be validated
 */
public interface Validator<T> {

    /**
     * Validates the provided object.
     * <p>This method should implement the specific validation logic for the object of type {@code T}.
     * It returns a list of validation error messages, where an empty list indicates that the object is valid.</p>
     *
     * @param t the object to be validated
     * @return a list of validation error messages. If the list is empty, the object is considered valid.
     * @throws IllegalArgumentException if {@code t} is {@code null}
     */
    List<String> validate(T t);

    /**
     * Validates the provided object using the given {@link Validator}.
     * <p>This static method uses the provided validator to validate the object and throws an {@link IllegalArgumentException}
     * if there are any validation errors. The errors are combined into a single string with each error message on a new line.</p>
     *
     * @param t         the object to be validated
     * @param validator the {@link Validator} used to validate the object
     * @param <T>       the type of object to be validated
     * @throws IllegalArgumentException if {@code t} is {@code null} or if the validator is {@code null}
     * @throws IllegalArgumentException if there are validation errors
     */
    static <T> void validate(T t, Validator<T> validator) {
        if (t == null) {
            throw new IllegalArgumentException("Object to be validated cannot be null");
        }
        if (validator == null) {
            throw new IllegalArgumentException("Validator cannot be null");
        }

        var errors = validator.validate(t);

        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(String.join("\n", errors));
        }
    }
}