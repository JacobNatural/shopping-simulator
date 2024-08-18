package com.app.extension.repository;

import com.app.parser.impl.ProductLineParser;
import com.app.product.Product;
import com.app.product.ProductValidator;
import com.app.repository.ProductRepository;
import com.app.repository.impl.ProductRepositoryImpl;
import com.app.txt.load.impl.ProductLoad;
import com.app.txt.transfer.impl.TransferImpl;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.math.BigDecimal;

import static com.app.data_provider.DataProvider.*;

public class ProductRepositoryImplExtension implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(ProductRepositoryImpl.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        var transfer = new TransferImpl<Long, Product>();
        var productLoad = new ProductLoad(transfer);
        var productLineParser = new ProductLineParser(
                "([1-9]\\d*);[A-Z_]+;([1-9]\\d*);(\\d*)(.\\d*)?;[A-Z_]+",
                new ProductValidator(1, BigDecimal.valueOf(0.1)));

        return new ProductRepositoryImpl(getPath(FILENAME_PRODUCTS_LOAD),productLoad, productLineParser);
    }
}
