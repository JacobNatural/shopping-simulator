package com.app.service;

import com.app.product.Product;
import com.app.statistic.Statistic;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Service interface for managing product-related operations.
 * This interface provides methods for grouping and sorting products,
 * retrieving product statistics, and fetching all available products.
 */
public interface ProductService {

    /**
     * Groups products by a specified mapper function and sorts them within each group
     * according to a given comparator.
     *
     * @param <T>           the type of the grouping key.
     * @param productMapper a function to map products to their grouping key.
     * @param comparator    a comparator to sort products within each group.
     * @return a map where the keys are derived from the {@code productMapper}, and
     * the values are lists of products sorted by the {@code comparator}.
     * @throws IllegalArgumentException if {@code productMapper} or {@code comparator} is {@code null}.
     */
    <T> Map<T, List<Product>> groupAndSortProducts(Function<Product, T> productMapper, Comparator<Product> comparator);

    /**
     * Retrieves statistical data for products.
     *
     * @return a {@link Statistic} object containing statistics related to products,
     * with the type parameter {@link BigDecimal} for statistical measures and
     * {@link List<Product>} for the product list.
     */
    Statistic<BigDecimal, List<Product>> getProductsStatistic();

    /**
     * Retrieves a list of all available products.
     *
     * @return a list of {@link Product} objects that are available.
     */
    List<Product> getAllProducts();
}