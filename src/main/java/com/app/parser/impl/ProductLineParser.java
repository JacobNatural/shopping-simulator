package com.app.parser.impl;

import com.app.parser.LineParser;
import com.app.product.Product;
import com.app.validators.Validator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of {@link LineParser} for parsing product data from a line of text.
 *
 * This class parses a line of text to extract product information and returns it as a map with the product ID as the key
 * and the {@link Product} object as the value.
 */
@Component
public class ProductLineParser implements LineParser<Long, Product> {

    private final String productLineRegex;
    private final Validator<Product> validator;

    /**
     * Constructs a {@link ProductLineParser} with the specified regular expression for line parsing and a {@link Validator}.
     *
     * @param productLineRegex the regular expression used to validate the line format
     * @param validator the {@link Validator} used to validate the {@link Product} object
     */
    public ProductLineParser(@Value("${productLineParser.regexp}") String productLineRegex, Validator<Product> validator) {
        this.productLineRegex = productLineRegex;
        this.validator = validator;
    }

    /**
     * Parses a line of text to extract product information.
     *
     * @param line the line of text to parse
     * @return a map containing the parsed product information, with the product ID as the key and the {@link Product} object as the value
     * @throws IllegalArgumentException if the line is null, empty, does not match the regex, or does not contain enough data fields
     */
    @Override
    public Map<Long, Product> parseLine(String line) {
        if (line == null) {
            throw new IllegalArgumentException("Line cannot be null");
        }

        if (line.isEmpty()) {
            throw new IllegalArgumentException("Line cannot be empty");
        }

        if (!line.matches(productLineRegex)) {
            throw new IllegalArgumentException(String.format("Line does not match regex: %s", line));
        }

        var split = line.split(";");

        var id = Long.parseLong(split[0]);
        var product = new Product(
                id,
                split[1],          // Product name
                Integer.parseInt(split[2]), // Product amount
                new BigDecimal(split[3]),   // Product price
                split[4]           // Product category
        );

        Validator.validate(product, validator);

        return new HashMap<>(Map.of(id, product));
    }
}