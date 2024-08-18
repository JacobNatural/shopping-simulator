package com.app.repository;

import com.app.product.Product;
import java.util.List;

/**
 * Interface representing a repository for managing products.
 * Extends the generic {@link Repository} interface with {@link Long} as the key type
 * and {@link Product} as the value type.
 *
 * This repository provides an additional method to retrieve a list of available product categories.
 */
public interface ProductRepository extends Repository<Long, Product> {

    /**
     * Retrieves a list of available product categories.
     *
     * @return a {@link List} of unique category names as {@link String}s
     */
    List<String> availableCategories();
}