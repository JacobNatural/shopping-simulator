package com.app.repository.impl;

import com.app.parser.LineParser;
import com.app.product.Product;
import com.app.product.ProductMapper;
import com.app.repository.ProductRepository;
import com.app.txt.load.Load;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of the {@link ProductRepository} interface that provides access to product data.
 * <p>
 * This class loads product data from a file using the {@link Load} interface and parses it using a {@link LineParser}.
 */
@Component
public class ProductRepositoryImpl implements ProductRepository {

    private final Map<Long, Product> products;

    /**
     * Constructs a {@link ProductRepositoryImpl} with the specified file name, loader, and parser.
     *
     * @param productFilename the name of the file containing product data
     * @param load            the {@link Load} implementation used to read the product data from the file
     * @param lineParser      the {@link LineParser} implementation used to parse the product data
     */
    public ProductRepositoryImpl(@Value("${productFilename:products.txt}")
                                 String productFilename,
                                 Load<Long, Product> load,
                                 LineParser<Long, Product> lineParser) {
        this.products = load.read(productFilename, lineParser);
    }

    /**
     * Retrieves a list of available product categories.
     *
     * @return a list of unique product categories
     */
    @Override
    public List<String> availableCategories() {
        return products
                .values()
                .stream()
                .map(ProductMapper.toCategory)
                .distinct()
                .toList();
    }

    /**
     * Retrieves all products.
     *
     * @return a map containing all products, with the product ID as the key and the {@link Product} object as the value
     */
    @Override
    public Map<Long, Product> getAll() {
        return new HashMap<>(products);
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param id the ID of the product to retrieve
     * @return the {@link Product} object associated with the specified ID
     * @throws IllegalArgumentException if a product with the specified ID is not found
     */
    @Override
    public Product getById(Long id) {
        if (products.containsKey(id)) {
            return products.get(id);
        }
        throw new IllegalArgumentException(String.format("Product with ID: %d not found", id));
    }
}