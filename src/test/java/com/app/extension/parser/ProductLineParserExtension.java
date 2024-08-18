package com.app.extension.parser;

import com.app.parser.impl.ProductLineParser;
import com.app.product.ProductValidator;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.math.BigDecimal;

public class ProductLineParserExtension implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(ProductLineParser.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return new ProductLineParser(
                "([1-9]\\d*);[A-Z_]+;([1-9]\\d*);(\\d*)(.\\d*)?;[A-Z_]+",
                new ProductValidator(1, BigDecimal.valueOf(0.1)));
    }
}
