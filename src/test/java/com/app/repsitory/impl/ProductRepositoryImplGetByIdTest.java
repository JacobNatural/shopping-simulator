package com.app.repsitory.impl;

import com.app.extension.repository.ProductRepositoryImplExtension;
import com.app.repository.impl.ProductRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.app.data_provider.DataProvider.PRODUCT1;

@ExtendWith(ProductRepositoryImplExtension.class)
@RequiredArgsConstructor
public class ProductRepositoryImplGetByIdTest {
    private final ProductRepositoryImpl repository;

    @Test
    @DisplayName("When the ID is not available")
    public void test1(){
        Assertions.assertThatThrownBy(() ->
                        repository.getById(4L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Product with ID: 4 not found");
    }

    @Test
    @DisplayName("When we find a product by ID")
    public void test2(){
        Assertions.assertThat(repository.getById(1L))
                .isEqualTo(PRODUCT1);
    }
}
