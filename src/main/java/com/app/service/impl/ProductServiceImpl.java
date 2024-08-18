package com.app.service.impl;

import com.app.collectors.ProductStatisticCollector;
import com.app.product.Product;
import com.app.product.ProductMapper;
import com.app.repository.impl.ProductRepositoryImpl;
import com.app.service.ProductService;
import com.app.statistic.Statistic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Implementation of the {@link ProductService} interface, providing methods to manage and analyze {@link Product} entities.
 * This service interacts with a {@link ProductRepositoryImpl} to perform various operations on products.
 */
@RequiredArgsConstructor
@Component
public class ProductServiceImpl implements ProductService {

    private final ProductRepositoryImpl productRepositoryImpl;


    /**
     * Groups and sorts products based on a given mapping function and comparator.
     *
     * @param <T>           the type of the key for grouping
     * @param productMapper a function to map products to their grouping key
     * @param comparator    a comparator to sort the products within each group
     * @return a map where the keys are derived from the {@code productMapper}, and the values are lists of sorted products
     * @throws IllegalArgumentException if {@code productMapper} or {@code comparator} is {@code null}
     */
    @Override
    public <T> Map<T, List<Product>> groupAndSortProducts(Function<Product, T> productMapper, Comparator<Product> comparator) {
        if (productMapper == null) {
            throw new IllegalArgumentException("Product mapper cannot be null");
        }

        if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot be null");
        }
        return productRepositoryImpl
                .getAll()
                .values()
                .stream()
                .collect(Collectors.groupingBy(
                        productMapper, Collectors.collectingAndThen(
                                Collectors.toList(),
                                products -> products
                                        .stream()
                                        .sorted(comparator)
                                        .toList()
                        )));
    }

    /**
     * Retrieves a list of all products from the repository.
     *
     * @return a list containing all {@link Product} entities
     */
    @Override
    public List<Product> getAllProducts() {
        return productRepositoryImpl.getAll().values().stream().toList();
    }

    /**
     * Generates statistical data about the products.
     *
     * @return a {@link Statistic} object containing the statistics for the products
     */
    @Override
    public Statistic<BigDecimal, List<Product>> getProductsStatistic() {
        return productRepositoryImpl
                .getAll()
                .values()
                .stream()
                .collect(new ProductStatisticCollector());
    }

    /**
     * Calculates the total quantity of products in a given list.
     *
     * @param products a list of {@link Product} entities
     * @return the total quantity of products
     */
    static long amountOfProduct(List<Product> products) {
        return products
                .stream()
                .map(ProductMapper.toAmount::applyAsInt)
                .collect(Collectors.summarizingInt(amount -> amount)).getSum();
    }

    /**
     * Calculates the total price for all products in a given list.
     *
     * @param products a list of {@link Product} entities
     * @return the total price as a {@link BigDecimal}
     */
    static BigDecimal totalPriceForAllProducts(List<Product> products) {
        return products
                .stream()
                .map(Product::getTotalPrice)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }
}