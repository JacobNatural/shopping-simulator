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
public class ProductServiceImplGetMostOftenProductTest {

    @Mock
    ShopRepositoryImpl repository;

    @InjectMocks
    ShopServiceImpl service;

    @Test
    @DisplayName("When we have one most frequent product")
    public void test1(){

        Mockito.when(repository.getAll())
                .thenReturn(Map.of(
                        CLIENT1, List.of(PRODUCT1, PRODUCT2, PRODUCT3),
                        CLIENT2, List.of(PRODUCT3, PRODUCT4, PRODUCT1),
                        CLIENT3, List.of(PRODUCT1)));

        Assertions.assertThat(service.getMostOftenProduct())
                .isEqualTo(List.of(PRODUCT1));
    }

    @Test
    @DisplayName("When we have two most often product")
    public void test2(){

        Mockito.when(repository.getAll())
                .thenReturn(Map.of(
                        CLIENT1, List.of(PRODUCT1, PRODUCT2, PRODUCT3),
                        CLIENT2, List.of(PRODUCT3, PRODUCT4),
                        CLIENT3, List.of(PRODUCT1)));

        Assertions.assertThat(service.getMostOftenProduct())
                .containsExactlyInAnyOrder(PRODUCT1, PRODUCT3);
    }

    @Test
    @DisplayName("When we have one client and one product")
    public void test3(){

        Mockito.when(repository.getAll())
                .thenReturn(Map.of(
                        CLIENT1, List.of(PRODUCT1)));

        Assertions.assertThat(service.getMostOftenProduct())
                .isEqualTo(List.of(PRODUCT1));
    }

    @Test
    @DisplayName("When we don't have any products")
    public void test4(){

        Mockito.when(repository.getAll())
                .thenReturn(Map.of(
                        CLIENT1, List.of(),
                        CLIENT2, List.of()));

        Assertions.assertThatThrownBy(() ->
                        service.getMostOftenProduct())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("The list is empty, no value to return");
    }
}
