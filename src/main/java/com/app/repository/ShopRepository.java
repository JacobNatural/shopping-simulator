package com.app.repository;

import com.app.client.Client;
import com.app.product.Product;

import java.util.List;

/**
 * A repository interface for managing the relationship between {@link Client} entities and their associated {@link Product} lists.
 * This interface extends the generic {@link Repository} interface, using {@link Client} as the key type and a {@link List} of {@link Product} as the value type.
 */
public interface ShopRepository extends Repository<Client, List<Product>> {

    /**
     * Updates the list of products associated with a given client by adding the provided list of products.
     *
     * @param client   the {@link Client} whose product list is to be updated
     * @param products the list of {@link Product} objects to be added to the client's product list
     * @return {@code true} if the product list was successfully updated, {@code false} otherwise
     * @throws IllegalArgumentException if the specified client is not found in the repository
     */
    boolean updateProductList(Client client, List<Product> products);
}
