package com.app.txt.load.impl;

import com.app.product.Product;
import com.app.txt.load.Load;
import com.app.txt.load.generic.AbstractLoad;
import com.app.txt.transfer.Transfer;
import org.springframework.stereotype.Component;

/**
 * A concrete implementation of the {@link Load} interface for loading product data.
 * This class extends {@link AbstractLoad} and is used specifically to handle the loading
 * of product data from a file, using a specified {@link Transfer} mechanism.
 *
 * <p>This class is marked as a Spring {@link Component} to be automatically detected
 * and managed by the Spring container.</p>
 */
@Component
public class ProductLoad extends AbstractLoad<Long, Product> implements Load<Long, Product> {

    /**
     * Constructs a {@link ProductLoad} instance with the specified {@link Transfer} mechanism.
     *
     * @param transfer the {@link Transfer} mechanism used to read product data from a file
     */
    public ProductLoad(Transfer<Long, Product> transfer) {
        super(transfer);
    }
}