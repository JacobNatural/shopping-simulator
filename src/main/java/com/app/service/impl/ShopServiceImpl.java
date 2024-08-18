//package com.app.service.impl;
//
//import com.app.client.Client;
//import com.app.product.Product;
//import com.app.product.ProductComparators;
//import com.app.product.ProductMapper;
//import com.app.repository.PreferencesRepository;
//import com.app.repository.ShopRepository;
//import com.app.service.ProductService;
//import com.app.service.ShopService;
//import com.app.statistic.Statistic;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.math.BigDecimal;
//import java.util.*;
//import java.util.function.Function;
//import java.util.stream.Collectors;
//
//@RequiredArgsConstructor
//@Component
//public class ShopServiceImpl implements ShopService {
//
//    private final ShopRepository shopRepository;
//    private final ProductService productService;
//    private final PreferencesRepository preferencesRepository;
//
//    @PostConstruct
//    public void postConstructAction(){
//        makeShopping();
//    }
//
//    public Statistic<BigDecimal, List<Product>> getAllProductsStatistic() {
//        return productService.getProductsStatistic();
//    }
//
//    public List<Product> getAllAvailableProducts() {
//        return productService.getAllProducts();
//    }
//
//
//
//    public List<Client> getClientWithBiggestAmountOfProduct() {
//        return getMaxKeyAndReturnValue(
//                groupByProductListPropertiesAndClients(ProductServiceImpl::amountOfProduct));
//    }
//
//    public List<Client> getClientWithBiggerPayment() {
//        return getMaxKeyAndReturnValue(
//                groupByProductListPropertiesAndClients(ProductServiceImpl::totalPriceForAllProducts));
//    }
//
//    public List<Product> getMostOftenProduct() {
//        return groupByAmountOfProductAndProduct(groupByProductAndAmountOfClient()).entrySet()
//                .stream()
//                .max(Map.Entry.comparingByKey())
//                .map(Map.Entry::getValue)
//                .orElseThrow(() -> new IllegalArgumentException("The list is empty, no value to return"));
//    }
//
//    public List<Product> getRatherProduct() {
//        return groupByAmountOfProductAndProduct(groupByProductAndAmountOfClient()).entrySet()
//                .stream()
//                .min(Map.Entry.comparingByKey())
//                .map(Map.Entry::getValue)
//                .orElseThrow(() -> new IllegalArgumentException("The list is empty, no value to return"));
//    }
//
//    public Map<Product, Integer> groupByProductAndAmountOfClient() {
//        return groupByProductPropertiesAndClients(ProductMapper.toProduct)
//                .entrySet()
//                .stream()
//                .collect(Collectors.toMap(Map.Entry::getKey, m -> m.getValue().size()));
//    }
//
//    public List<String> getCategoriesSortedByPopularity(Comparator<Integer> comparator) {
//        if (comparator == null) {
//            throw new IllegalArgumentException("Comparator cannot be null");
//        }
//
//        return groupByProductPropertiesAndClients(ProductMapper.toCategory)
//                .entrySet()
//                .stream()
//                .collect(Collectors.toMap(Map.Entry::getKey, m -> m.getValue().size()))
//                .entrySet()
//                .stream()
//                .sorted((m1, m2) -> comparator.compare(m2.getValue(), m1.getValue()))
//                .map(Map.Entry::getKey)
//                .toList();
//    }
//
//    private void makeShopping() {
//        var groupedProductsByCategory =
//                getAllProductsGroupedByAndSortedBy(
//                        ProductMapper.toCategory, ProductComparators.compareByPrice);
//
//        shopRepository.getAll()
//                .keySet()
//                .forEach(client -> {
//                    var availableProducts = client.getAvailableProducts(
//                            groupedProductsByCategory,
//                            preferencesRepository);
//                    shopRepository.updateProductList(
//                            client,
//                            availableProducts);
//                });
//    }
//
//    private static Map<Integer, List<Product>> groupByAmountOfProductAndProduct(Map<Product, Integer> map) {
//        return map
//                .entrySet()
//                .stream()
//                .collect(Collectors.groupingBy(
//                        Map.Entry::getValue,
//                        Collectors.mapping(
//                                Map.Entry::getKey,
//                                Collectors.toList())));
//
//    }
//
//    private <T> Map<T, List<Product>> getAllProductsGroupedByAndSortedBy(
//            Function<Product, T> productMapper, Comparator<Product> productComparator) {
//        return productService.groupAndSortProducts(
//                productMapper, productComparator);
//    }
//
//    private static <T extends Comparable<T>> List<Client> getMaxKeyAndReturnValue(Map<T, List<Client>> map) {
//
//        return map.entrySet()
//                .stream()
//                .max(Map.Entry.comparingByKey())
//                .map(Map.Entry::getValue)
//                .orElseThrow(() -> new IllegalArgumentException("The map is empty, no value to return"));
//    }
//
//    private <T> Map<T, List<Client>> groupByProductPropertiesAndClients(Function<Product, T> productMapper) {
//
//        return shopRepository
//                .getAll()
//                .entrySet()
//                .stream()
//                .filter(shop -> !shop.getValue().isEmpty())
//                .flatMap(
//                        m -> m.getValue()
//                                .stream()
//                                .map(
//                                        product ->
//                                                new AbstractMap.SimpleEntry<>(
//                                                        productMapper.apply(product), m.getKey())))
//                .collect(
//                        Collectors.groupingBy(
//                                AbstractMap.SimpleEntry::getKey,
//                                Collectors.mapping(
//                                        AbstractMap.SimpleEntry::getValue,
//                                        Collectors.toList())));
//    }
//
//    private <T> Map<T, List<Client>> groupByProductListPropertiesAndClients(Function<List<Product>, T> function) {
//        return shopRepository
//                .getAll()
//                .entrySet()
//                .stream()
//                .filter(shop -> !shop.getValue().isEmpty())
//                .map(shop -> new AbstractMap.SimpleEntry<>(
//                        function.apply(shop.getValue()), shop.getKey()))
//                .collect(Collectors.groupingBy(
//                        AbstractMap.SimpleEntry::getKey,
//                        Collectors.mapping(
//                                AbstractMap.SimpleEntry::getValue, Collectors.toList())));
//    }
//
//    @Override
//    public String toString(){
//        return shopRepository.getAll().toString();
//    }
//}
package com.app.service.impl;

import com.app.client.Client;
import com.app.product.Product;
import com.app.product.ProductComparators;
import com.app.product.ProductMapper;
import com.app.repository.PreferencesRepository;
import com.app.repository.ShopRepository;
import com.app.service.ProductService;
import com.app.service.ShopService;
import com.app.statistic.Statistic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Implementation of the ShopService interface that provides services
 * related to shopping, products, and clients.
 * This class is managed by Spring as a component.
 */
@RequiredArgsConstructor
@Component
public class ShopServiceImpl implements ShopService {

    /**
     * Repository for storing and managing shop data.
     */
    private final ShopRepository shopRepository;

    /**
     * Service for handling product-related operations.
     */
    private final ProductService productService;

    /**
     * Repository for managing client preferences.
     */
    private final PreferencesRepository preferencesRepository;

    /**
     * This method is executed after the object is created and dependencies are injected.
     * It triggers the shopping process.
     */
    @PostConstruct
    public void postConstructAction(){
        makeShopping();
    }

    /**
     * Retrieves statistics for all products, including price information.
     *
     * @return a {@link Statistic} object containing the total price and a list of products.
     */
    public Statistic<BigDecimal, List<Product>> getAllProductsStatistic() {
        return productService.getProductsStatistic();
    }

    /**
     * Retrieves a list of all available products.
     *
     * @return a list of {@link Product} objects.
     */
    public List<Product> getAllAvailableProducts() {
        return productService.getAllProducts();
    }

    /**
     * Finds and returns a list of clients who have the largest quantity of products.
     *
     * @return a list of {@link Client} objects with the largest quantity of products.
     */
    public List<Client> getClientWithBiggestAmountOfProduct() {
        return getMaxKeyAndReturnValue(
                groupByProductListPropertiesAndClients(ProductServiceImpl::amountOfProduct));
    }

    /**
     * Finds and returns a list of clients who have made the largest total payments.
     *
     * @return a list of {@link Client} objects with the largest payments.
     */
    public List<Client> getClientWithBiggerPayment() {
        return getMaxKeyAndReturnValue(
                groupByProductListPropertiesAndClients(ProductServiceImpl::totalPriceForAllProducts));
    }

    /**
     * Retrieves the most frequently bought product.
     *
     * @return a list of the most often purchased {@link Product} objects.
     * @throws IllegalArgumentException if the list is empty.
     */
    public List<Product> getMostOftenProduct() {
        return groupByAmountOfProductAndProduct(groupByProductAndAmountOfClient()).entrySet()
                .stream()
                .max(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .orElseThrow(() -> new IllegalArgumentException("The list is empty, no value to return"));
    }

    /**
     * Retrieves the least frequently bought product.
     *
     * @return a list of the least often purchased {@link Product} objects.
     * @throws IllegalArgumentException if the list is empty.
     */
    public List<Product> getRatherProduct() {
        return groupByAmountOfProductAndProduct(groupByProductAndAmountOfClient()).entrySet()
                .stream()
                .min(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .orElseThrow(() -> new IllegalArgumentException("The list is empty, no value to return"));
    }

    /**
     * Groups products by the number of clients who bought them and returns the map.
     *
     * @return a map of {@link Product} objects and the number of clients who bought them.
     */
    public Map<Product, Integer> groupByProductAndAmountOfClient() {
        return groupByProductPropertiesAndClients(ProductMapper.toProduct)
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, m -> m.getValue().size()));
    }

    /**
     * Retrieves categories sorted by popularity based on the provided comparator.
     *
     * @param comparator a {@link Comparator} to define the order of popularity.
     * @return a list of category names sorted by popularity.
     * @throws IllegalArgumentException if the comparator is null.
     */
    public List<String> getCategoriesSortedByPopularity(Comparator<Integer> comparator) {
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot be null");
        }

        return groupByProductPropertiesAndClients(ProductMapper.toCategory)
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, m -> m.getValue().size()))
                .entrySet()
                .stream()
                .sorted((m1, m2) -> comparator.compare(m2.getValue(), m1.getValue()))
                .map(Map.Entry::getKey)
                .toList();
    }

    /**
     * Executes the shopping process, grouping products by category and sorting them by price.
     */
    private void makeShopping() {
        var groupedProductsByCategory =
                getAllProductsGroupedByAndSortedBy(
                        ProductMapper.toCategory, ProductComparators.compareByPrice);

        shopRepository.getAll()
                .keySet()
                .forEach(client -> {
                    var availableProducts = client.getAvailableProducts(
                            groupedProductsByCategory,
                            preferencesRepository);
                    shopRepository.updateProductList(
                            client,
                            availableProducts);
                });
    }

    /**
     * Groups products by the number of products bought by clients.
     *
     * @param map a map of products and the number of clients who bought them.
     * @return a map of product quantities and a list of corresponding products.
     */
    private static Map<Integer, List<Product>> groupByAmountOfProductAndProduct(Map<Product, Integer> map) {
        return map
                .entrySet()
                .stream()
                .collect(Collectors.groupingBy(
                        Map.Entry::getValue,
                        Collectors.mapping(
                                Map.Entry::getKey,
                                Collectors.toList())));

    }

    /**
     * Groups all products by a given property and sorts them by a given comparator.
     *
     * @param productMapper a function to map products to a specific property.
     * @param productComparator a comparator to sort products.
     * @param <T> the type of the property used for grouping.
     * @return a map of grouped products by the specified property.
     */
    private <T> Map<T, List<Product>> getAllProductsGroupedByAndSortedBy(
            Function<Product, T> productMapper, Comparator<Product> productComparator) {
        return productService.groupAndSortProducts(
                productMapper, productComparator);
    }

    /**
     * Finds the maximum key in a map and returns the corresponding value.
     *
     * @param map a map where keys are comparable and values are lists of clients.
     * @param <T> the type of the keys, which must be comparable.
     * @return a list of clients corresponding to the maximum key.
     * @throws IllegalArgumentException if the map is empty.
     */
    private static <T extends Comparable<T>> List<Client> getMaxKeyAndReturnValue(Map<T, List<Client>> map) {

        return map.entrySet()
                .stream()
                .max(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .orElseThrow(() -> new IllegalArgumentException("The map is empty, no value to return"));
    }

    /**
     * Groups products by their properties and maps them to corresponding clients.
     *
     * @param productMapper a function to map products to a specific property.
     * @param <T> the type of the property used for grouping.
     * @return a map of grouped clients by the specified property of products.
     */
    private <T> Map<T, List<Client>> groupByProductPropertiesAndClients(Function<Product, T> productMapper) {

        return shopRepository
                .getAll()
                .entrySet()
                .stream()
                .filter(shop -> !shop.getValue().isEmpty())
                .flatMap(
                        m -> m.getValue()
                                .stream()
                                .map(
                                        product ->
                                                new AbstractMap.SimpleEntry<>(
                                                        productMapper.apply(product), m.getKey())))
                .collect(
                        Collectors.groupingBy(
                                AbstractMap.SimpleEntry::getKey,
                                Collectors.mapping(
                                        AbstractMap.SimpleEntry::getValue,
                                        Collectors.toList())));
    }

    /**
     * Groups products by their list properties and maps them to corresponding clients.
     *
     * @param function a function that applies to a list of products and returns a property.
     * @param <T> the type of the property used for grouping.
     * @return a map of grouped clients by the specified property of product lists.
     */
    private <T> Map<T, List<Client>> groupByProductListPropertiesAndClients(Function<List<Product>, T> function) {
        return shopRepository
                .getAll()
                .entrySet()
                .stream()
                .filter(shop -> !shop.getValue().isEmpty())
                .map(shop -> new AbstractMap.SimpleEntry<>(
                        function.apply(shop.getValue()), shop.getKey()))
                .collect(Collectors.groupingBy(
                        AbstractMap.SimpleEntry::getKey,
                        Collectors.mapping(
                                AbstractMap.SimpleEntry::getValue, Collectors.toList())));
    }
}