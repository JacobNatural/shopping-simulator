package com.app.service;

import com.app.repository.impl.ShopRepositoryImpl;
import com.app.service.impl.ShopServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.List;
import java.util.Map;

import static com.app.data_provider.DataProvider.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings()
public class ProductServiceImplGroupByProductAndAmountOfClientTest {

    @Mock
    ShopRepositoryImpl repository;

    @InjectMocks
    ShopServiceImpl service;

    @Test
    @DisplayName("When products have different quantities")
    public void test1() {

        Mockito.when(repository.getAll())
                .thenReturn(Map.of(
                        CLIENT1, List.of(PRODUCT1, PRODUCT2, PRODUCT3),
                        CLIENT2, List.of(PRODUCT1),
                        CLIENT3, List.of(PRODUCT1, PRODUCT2)));

        Assertions.assertThat(service.groupByProductAndAmountOfClient())
                .isEqualTo(Map.of(
                        PRODUCT1, 3,
                        PRODUCT2, 2,
                        PRODUCT3, 1));
    }

    @Test
    @DisplayName("When products have the same number of clients")
    public void test2() {

        Mockito.when(repository.getAll())
                .thenReturn(Map.of(
                        CLIENT1, List.of(PRODUCT1, PRODUCT2, PRODUCT3),
                        CLIENT2, List.of(PRODUCT3, PRODUCT1, PRODUCT2),
                        CLIENT3, List.of(PRODUCT1, PRODUCT2, PRODUCT3)));

        Assertions.assertThat(service.groupByProductAndAmountOfClient())
                .isEqualTo(Map.of(
                        PRODUCT1, 3,
                        PRODUCT2, 3,
                        PRODUCT3, 3));
    }

    @Test
    @DisplayName("When products have the same number of clients")
    public void test3() {

        Mockito.when(repository.getAll())
                .thenReturn(Map.of(
                        CLIENT1, List.of(),
                        CLIENT2, List.of(),
                        CLIENT3, List.of()));

        Assertions.assertThat(service.groupByProductAndAmountOfClient())
                .isEqualTo(Map.of());
    }
}

