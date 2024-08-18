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

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static com.app.data_provider.DataProvider.*;
import static com.app.data_provider.DataProvider.CLIENT1;

@ExtendWith(MockitoExtension.class)
@MockitoSettings()
public class ShopServiceImplGetCategoriesSortedByPopularityTest {

    @Mock
    private ShopRepositoryImpl repository;

    @InjectMocks
    private ShopServiceImpl service;

    @Test
    @DisplayName("When comparator is null")
    public void test1() {

        Assertions.assertThatThrownBy(() ->
                        service.getCategoriesSortedByPopularity(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Comparator cannot be null");
    }

    @Test
    @DisplayName("When we get categories sorted by the most frequently purchased products")
    public void test2() {

        Mockito.when(repository.getAll())
                .thenReturn(Map.of(
                        CLIENT1, List.of(PRODUCT1, PRODUCT2, PRODUCT3),
                        CLIENT2, List.of(PRODUCT3, PRODUCT4),
                        CLIENT3, List.of(PRODUCT1)));

        Assertions.assertThat(service.getCategoriesSortedByPopularity(Comparator.naturalOrder()))
                .isEqualTo(List.of("CLOTHES",  "GROCERIES","HOME_APPLIANCES"));
    }

    @Test
    @DisplayName("\"When categories are sorted by the least frequently purchased products")
    public void test3() {

        Mockito.when(repository.getAll())
                .thenReturn(Map.of(
                        CLIENT1, List.of(PRODUCT1, PRODUCT2, PRODUCT3),
                        CLIENT2, List.of(PRODUCT3, PRODUCT4),
                        CLIENT3, List.of(PRODUCT1)));

        Assertions.assertThat(service.getCategoriesSortedByPopularity(Comparator.naturalOrder()))
                .isEqualTo(List.of("CLOTHES",  "GROCERIES","HOME_APPLIANCES"));
    }

    @Test
    @DisplayName("When clients do not have any products")
    public void test4() {

        Mockito.when(repository.getAll())
                .thenReturn(Map.of(
                        CLIENT1, List.of(),
                        CLIENT2, List.of(),
                        CLIENT3, List.of()));

        Assertions.assertThat(service.getCategoriesSortedByPopularity(Comparator.naturalOrder()))
                .isEqualTo(List.of());
    }
}
