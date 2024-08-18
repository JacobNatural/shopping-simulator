package com.app.product;

import java.math.BigDecimal;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.function.UnaryOperator;

/**
 * Provides various mappings and functions for {@link Product} instances.
 */
public interface ProductMapper {

    /**
     * A function that extracts the category from a {@link Product}.
     *
     */
    Function<Product, String> toCategory = product -> product.category;

    /**
     * A function that extracts the price from a {@link Product}.
     *
     */
    Function<Product, BigDecimal> toPrice = product -> product.price;

    /**
     * A function that extracts the amount from a {@link Product}.
     *
     */
    ToIntFunction<Product> toAmount = pr -> pr.amount;

    /**
     * A unary operator that returns the same {@link Product} instance.
     *
     */
    UnaryOperator<Product> toProduct = product -> product;
}