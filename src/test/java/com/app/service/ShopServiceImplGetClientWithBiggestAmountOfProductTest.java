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
public class ShopServiceImplGetClientWithBiggestAmountOfProductTest{

    @Mock
    private ShopRepositoryImpl repository;

    @InjectMocks
    private ShopServiceImpl service;

    @Test
    @DisplayName("When we have only one client with biggest amount of product")
    public void test1(){

        Mockito.when(repository.getAll())
                        .thenReturn(Map.of(
                                CLIENT1, List.of(PRODUCT1, PRODUCT2, PRODUCT3),
                                CLIENT2, List.of(PRODUCT3, PRODUCT4),
                                CLIENT3, List.of(PRODUCT1)));

        Assertions.assertThat(service.getClientWithBiggestAmountOfProduct())
                .isEqualTo(List.of(CLIENT1));
    }

    @Test
    @DisplayName("When we have only two clients with biggest amount of product")
    public void test2(){

        Mockito.when(repository.getAll())
                .thenReturn(Map.of(
                        CLIENT1, List.of(PRODUCT1, PRODUCT2),
                        CLIENT2, List.of(PRODUCT3, PRODUCT4),
                        CLIENT3, List.of(PRODUCT1)
                        ));

        Assertions.assertThat(service.getClientWithBiggestAmountOfProduct())
                .containsExactlyInAnyOrder(CLIENT1, CLIENT2);
    }

    @Test
    @DisplayName("When we have only one client")
    public void test3(){

        Mockito.when(repository.getAll())
                .thenReturn(Map.of(
                        CLIENT1, List.of(PRODUCT1, PRODUCT2)
                        )
                );

        Assertions.assertThat(service.getClientWithBiggestAmountOfProduct())
                .containsExactlyInAnyOrder(CLIENT1);
    }

    @Test
    @DisplayName("When multiple clients have the same number of products")
    public void test4(){

        Mockito.when(repository.getAll())
                .thenReturn(Map.of(
                                CLIENT1, List.of(PRODUCT1, PRODUCT2),
                        CLIENT2, List.of(PRODUCT2, PRODUCT4),
                        CLIENT3, List.of(PRODUCT3, PRODUCT1)
                        )
                );

        Assertions.assertThat(service.getClientWithBiggestAmountOfProduct())
                .containsExactlyInAnyOrder(CLIENT1, CLIENT2, CLIENT3);
    }

    @Test
    @DisplayName("When we have clients without products")
    public void test5(){

        Mockito.when(repository.getAll())
                .thenReturn(Map.of(
                                CLIENT1, List.of(),
                        CLIENT2, List.of()
                        )
                );

        Assertions.assertThatThrownBy(() -> service.getClientWithBiggestAmountOfProduct())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("The map is empty, no value to return");
    }
}
