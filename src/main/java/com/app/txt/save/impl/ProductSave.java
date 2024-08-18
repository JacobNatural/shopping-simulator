package com.app.txt.save.impl;

import com.app.product.Product;
import com.app.txt.transfer.Transfer;
import com.app.txt.save.Save;
import com.app.txt.save.generic.AbstractSave;
import org.springframework.stereotype.Component;

/**
 * Implementation of the {@link Save} interface for {@link Product} objects.
 *
 * <p>This class provides functionality to save {@link Product} instances to a file. It extends
 * the generic {@link AbstractSave} class and uses a {@link Transfer} instance to handle the
 * actual saving process.</p>
 */
@Component
public class ProductSave extends AbstractSave<Product> implements Save<Product> {

    /**
     * Constructs a {@link ProductSave} with the specified {@link Transfer}.
     *
     * @param transfer the {@link Transfer} instance used for saving {@link Product} objects
     */
    public ProductSave(Transfer<Product, Product> transfer) {
        super(transfer);
    }
}