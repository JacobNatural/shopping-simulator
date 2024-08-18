package com.app.product;

import com.app.validators.Validator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Validator implementation for {@link Product} objects.
 * <p>
 * Validates {@link Product} instances based on minimum amount and price constraints.
 */
@Component
public class ProductValidator implements Validator<Product> {

    private final int minAmount;
    private final BigDecimal minPrice;

    /**
     * Constructs a {@link ProductValidator} with the specified minimum amount and price.
     *
     * @param minAmount the minimum amount for a product to be considered valid
     * @param minPrice  the minimum price for a product to be considered valid
     */
    public ProductValidator(
            @Value("${productMinAmount:1}") int minAmount,
            @Value("${productMinPrice:0.1}") BigDecimal minPrice) {
        this.minAmount = minAmount;
        this.minPrice = minPrice;
    }

    /**
     * Validates a {@link Product} against the defined constraints.
     *
     * @param product the {@link Product} to validate
     * @return a list of error messages if the product is invalid; otherwise, an empty list
     */
    @Override
    public List<String> validate(Product product) {

        var errors = new ArrayList<String>();

        if (product.id < 1) {
            errors.add("Product id cannot be less than 1");
        }

        if (product.amount < minAmount) {
            errors.add(STR."Product amount cannot be less than \{minAmount}");
        }

        if (product.price.compareTo(minPrice) < 0) {
            errors.add(STR."Product price cannot be less than \{minPrice}");
        }

        return errors;

    }
}