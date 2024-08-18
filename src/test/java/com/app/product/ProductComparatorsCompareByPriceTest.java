package com.app.product;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.app.data_provider.DataProvider.*;

public class ProductComparatorsCompareByPriceTest {

    @Test
    @DisplayName("When the product comparator works correctly")
    public void test1(){
        var products = List.of(PRODUCT1, PRODUCT2, PRODUCT3);

        Assertions.assertThat(products
                        .stream()
                        .sorted(ProductComparators.compareByPrice)
                        .toList())
                .isEqualTo(List.of(PRODUCT1, PRODUCT3, PRODUCT2));
    }

}
