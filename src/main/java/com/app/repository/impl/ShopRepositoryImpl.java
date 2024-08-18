package com.app.repository.impl;

import com.app.client.Client;
import com.app.product.Product;
import com.app.repository.ShopRepository;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Implementation of the {@link ShopRepository} interface that manages the association
 * between {@link Client} objects and their corresponding {@link Product} lists.
 */
@ToString
@Component
public class ShopRepositoryImpl implements ShopRepository {

    private final Map<Client, List<Product>> shops;

    /**
     * Constructs a {@link ShopRepositoryImpl} and initializes the shop data using
     * the provided {@link ClientRepositoryImpl}.
     *
     * @param clientRepositoryImpl the repository that provides the client data
     */
    public ShopRepositoryImpl(ClientRepositoryImpl clientRepositoryImpl) {
        this.shops = clientRepositoryImpl
                .getAll()
                .values()
                .stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        client -> new ArrayList<>(List.of())));
    }

    /**
     * Updates the product list for a specified client.
     *
     * @param client   the {@link Client} whose product list is to be updated
     * @param products the list of {@link Product} objects to add to the client's list
     * @return {@code true} if the products were successfully added; otherwise, throws an exception
     * @throws IllegalArgumentException if the specified client is not found
     */
    public boolean updateProductList(Client client, List<Product> products) {
        if (shops.containsKey(client)) {
            return shops.get(client).addAll(products);
        }
        throw new IllegalArgumentException("Client not found");
    }

    /**
     * Retrieves all client-product associations.
     *
     * @return a map containing all clients and their corresponding product lists
     */
    @Override
    public Map<Client, List<Product>> getAll() {
        return new HashMap<>(shops);
    }

    /**
     * Retrieves the list of products associated with a specific client.
     *
     * @param client the {@link Client} whose product list is to be retrieved
     * @return the list of {@link Product} objects associated with the specified client
     * @throws IllegalArgumentException if the specified client is not found
     */
    @Override
    public List<Product> getById(Client client) {
        if (shops.containsKey(client)) {
            return shops.get(client);
        }
        throw new IllegalArgumentException("Client not found");
    }
}