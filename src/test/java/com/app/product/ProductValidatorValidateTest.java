package com.app.product;

import com.app.extension.product.ProductValidatorExtension;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.math.BigDecimal;

@ExtendWith(ProductValidatorExtension.class)
@RequiredArgsConstructor
public class ProductValidatorValidateTest {
    private final ProductValidator productValidator;

    @Test
    @DisplayName("When ID of the product is not correct")
    public void test1(){
        var product = new Product(0L, "BREAD",1, BigDecimal.ONE, "GROCERIES");

        Assertions.assertThat(productValidator.validate(product))
                .contains("Product id cannot be less than 1");
    }

    @Test
    @DisplayName("When the amount of the product is lower than the minimum amount")
    public void test2(){
        var product = new Product(1L, "BREAD",0, BigDecimal.ONE, "GROCERIES");

        Assertions.assertThat(productValidator.validate(product))
                .contains("Product amount cannot be less than 1");
    }

    @Test
    @DisplayName("When the price is lower than the minimum price")
    public void test3(){
        var product = new Product(1L, "BREAD",1, BigDecimal.valueOf(0.05), "GROCERIES");

        Assertions.assertThat(productValidator.validate(product))
                .contains("Product price cannot be less than 0.1");
    }
}
