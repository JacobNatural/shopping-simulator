package com.app.product;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static com.app.data_provider.DataProvider.*;


public class ProductGetTotalPriceTest {

    static Stream<Arguments> products() {
        return Stream.of(
                Arguments.of(PRODUCT1, BigDecimal.valueOf(5.55)),
                Arguments.arguments(PRODUCT2,BigDecimal.valueOf(800)),
                Arguments.of(PRODUCT3, BigDecimal.valueOf(240))
        );
    }

    @ParameterizedTest
    @DisplayName("When the total price calculation is done correctly")
    @MethodSource("products")
    public void test1(Product product, BigDecimal expected) {
        Assertions.assertThat(product.getTotalPrice())
                .isEqualTo(expected);
    }

}
