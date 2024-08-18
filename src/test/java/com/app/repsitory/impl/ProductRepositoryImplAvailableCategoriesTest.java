package com.app.repsitory.impl;

import com.app.extension.repository.ProductRepositoryImplExtension;
import com.app.repository.impl.ProductRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import java.util.List;

@ExtendWith(ProductRepositoryImplExtension.class)
@RequiredArgsConstructor
public class ProductRepositoryImplAvailableCategoriesTest {
    private final ProductRepositoryImpl repository;

    @Test
    @DisplayName("When getting all available categories without duplicates")
    public void test1(){

        Assertions.assertThat(repository.availableCategories())
                .isEqualTo(List.of("GROCERIES", "HOME_APPLIANCES","CLOTHES"));
    }
}
