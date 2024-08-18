package com.app.service;

import com.app.client.Client;
import com.app.product.Product;
import com.app.statistic.Statistic;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Service interface for managing shop-related operations.
 * This interface provides methods for performing shopping actions, retrieving client and product statistics,
 * and sorting products based on different criteria.
 */
public interface ShopService {

    /**
     * Executes the shopping process, updating the product lists for each client based on available products
     * and client preferences.
     */
    //void makeShopping();

    /**
     * Retrieves a list of clients who have the largest amount of products.
     *
     * @return a list of {@link Client} objects who have the most products.
     */
    List<Client> getClientWithBiggestAmountOfProduct();

    /**
     * Retrieves a list of clients who have made the largest payments.
     *
     * @return a list of {@link Client} objects who have the highest payments.
     */
    List<Client> getClientWithBiggerPayment();

    /**
     * Retrieves a list of products that are most frequently purchased.
     *
     * @return a list of {@link Product} objects that are purchased most often.
     */
    List<Product> getMostOftenProduct();

    /**
     * Retrieves a list of products that are least frequently purchased.
     *
     * @return a list of {@link Product} objects that are purchased least often.
     */
    List<Product> getRatherProduct();

    /**
     * Groups products by their amount for each client.
     *
     * @return a map where the keys are {@link Product} objects and the values are the number of clients
     * who have that product.
     */
    Map<Product, Integer> groupByProductAndAmountOfClient();

    /**
     * Retrieves a list of product categories sorted by their popularity.
     *
     * @param comparator a comparator used to sort the categories by popularity.
     * @return a list of category names sorted according to the specified {@code comparator}.
     * @throws IllegalArgumentException if {@code comparator} is {@code null}.
     */
    List<String> getCategoriesSortedByPopularity(Comparator<Integer> comparator);

    /**
     * Retrieves statistical data for products.
     *
     * @return a {@link Statistic} object containing statistics related to products,
     * with the type parameter {@link BigDecimal} for statistical measures and
     * {@link Product} for the product itself.
     */
    Statistic<BigDecimal, List<Product>> getAllProductsStatistic();

    /**
     * Retrieves a list of all available products.
     *
     * @return a list of {@link Product} objects that are available.
     */
    List<Product> getAllAvailableProducts();
}