package com.app.repsitory.impl;

import com.app.extension.repository.ShopRepositoryImplExtension;
import com.app.repository.impl.ShopRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static com.app.data_provider.DataProvider.*;

@ExtendWith(ShopRepositoryImplExtension.class)
@RequiredArgsConstructor
public class ShopRepositoryImplUpdateProductListTest {

    private final ShopRepositoryImpl repository;

    @Test
    @DisplayName("When updating products with a client that does not exist")
    public void test1(){

        Assertions.assertThatThrownBy(() ->
                        repository.updateProductList(CLIENT4, List.of(PRODUCT1, PRODUCT2)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Client not found");
    }

    @Test
    @DisplayName("When updating products and return true")
    public void test2() {

        var products = List.of(PRODUCT1, PRODUCT2);
        Assertions.assertThat(
                        repository.updateProductList(CLIENT1, products))
                .isTrue();
    }

    @Test
    @DisplayName("When updating products and client contains products")
    public void test3() {

        var products = List.of(PRODUCT1, PRODUCT2);
        repository.updateProductList(CLIENT1, products);

        Assertions.assertThat(repository.getById(CLIENT1))
                .isEqualTo(products);
    }
}

