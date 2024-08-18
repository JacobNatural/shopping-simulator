package com.app.client;

import com.app.product.Product;
import com.app.repository.PreferencesRepository;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents a client with an ID, name, surname, age, balance, and product preferences.
 * Provides functionality to get available products based on client preferences and balance.
 */
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Client {
    /**
     * The unique identifier for the client.
     */
    final long id;

    /**
     * The first name of the client.
     */
    final String name;

    /**
     * The surname of the client.
     */
    final String surname;

    /**
     * The age of the client.
     */
    final int age;

    /**
     * The balance available for the client to spend.
     */
    BigDecimal balance;

    /**
     * The list of preferences of the client, represented by category IDs.
     */
    final List<Long> preferences;

    /**
     * Retrieves a list of products that are available to the client based on their preferences and current balance.
     *
     * @param productGroupedByCategory A map where the key is a category name and the value is a list of products in that category.
     * @param preferencesRepository    A repository that provides category names based on preference IDs.
     * @return A list of products that the client can afford and that match their preferences.
     */
    public List<Product> getAvailableProducts(
            Map<String, List<Product>> productGroupedByCategory,
            PreferencesRepository preferencesRepository) {

        var listOfAvailableProducts = new ArrayList<Product>();

        // Iterate through the client's preferences.
        for (var preference : preferences) {

            // Get the category name based on the preference ID.
            var productsInCategory =
                    productGroupedByCategory.get(preferencesRepository.getById(preference));

            // Check each product in the category.
            for (var product : productsInCategory) {

                var totalPrice = product.getTotalPrice();

                // Check if the client can afford the product.
                if (totalPrice.compareTo(balance) <= 0) {
                    listOfAvailableProducts.add(product);
                    balance = balance.subtract(totalPrice);
                }

                // Stop if the client has used up their entire balance.
                if (balance.compareTo(BigDecimal.ZERO) == 0) {
                    break;
                }
            }
        }

        return listOfAvailableProducts;
    }
}
