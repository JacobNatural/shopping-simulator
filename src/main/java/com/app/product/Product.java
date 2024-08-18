package com.app.product;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * Represents a product with an ID, name, amount, price, and category.
 */
@RequiredArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Product {
    final long id;
    final String name;
    final int amount;
    final BigDecimal price;
    final String category;

    /**
     * Calculates the total price of the product based on the amount and unit price.
     *
     * @return the total price as a {@link BigDecimal}
     */
    public BigDecimal getTotalPrice() {
        return price.multiply(BigDecimal.valueOf(amount));
    }
}