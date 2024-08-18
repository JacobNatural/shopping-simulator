package com.app.repsitory.impl;

import com.app.extension.repository.ProductRepositoryImplExtension;
import com.app.repository.impl.ProductRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Map;
import static com.app.data_provider.DataProvider.*;

@ExtendWith(ProductRepositoryImplExtension.class)
@RequiredArgsConstructor
public class ProductRepositoryImplGetAllTest {
    private final ProductRepositoryImpl repository;

    @Test
    @DisplayName("When the client repository gets correct data")
    public void test1(){

        Assertions.assertThat(repository.getAll())
                .isEqualTo(Map.of(1L,PRODUCT1, 2L, PRODUCT2, 3L, PRODUCT3));
    }

    @Test
    @DisplayName("When the client repository returns an immutable map")
    public void test2(){

        repository.getAll().put(4L, PRODUCT1);

        Assertions.assertThat(repository.getAll())
                .isEqualTo(Map.of(1L,PRODUCT1, 2L, PRODUCT2, 3L, PRODUCT3));
    }
}
