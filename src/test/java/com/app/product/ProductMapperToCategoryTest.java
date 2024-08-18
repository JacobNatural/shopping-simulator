package com.app.product;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.app.data_provider.DataProvider.PRODUCT1;


public class ProductMapperToCategoryTest {


    @Test
    @DisplayName("When the product mapper returns the intended value")
    public void test1(){
        Assertions.assertThat(ProductMapper.toCategory.apply(PRODUCT1))
                .isEqualTo("GROCERIES");
    }
}
