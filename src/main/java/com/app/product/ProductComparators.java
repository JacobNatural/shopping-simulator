package com.app.product;

import java.util.Comparator;

/**
 * Provides comparators for {@link Product} instances.
 */
public interface ProductComparators {

    /**
     * A comparator that compares {@link Product} objects based on their price.
     * <p>
     * This comparator sorts products in ascending order of their price.
     * </p>
     */
    Comparator<Product> compareByPrice = Comparator.comparing(n -> n.price);
}