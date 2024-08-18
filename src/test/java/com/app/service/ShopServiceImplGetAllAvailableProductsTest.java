package com.app.service;

import com.app.repository.impl.ProductRepositoryImpl;
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

import static com.app.data_provider.DataProvider.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings()
public class ShopServiceImplGetAllAvailableProductsTest {

    @Mock
    ProductService productService;

    @InjectMocks
    ShopServiceImpl shopServiceImpl;

    @Test
    @DisplayName("When we obtain all the products")
    public void test1(){

        Mockito.when(productService.getAllProducts())
                .thenReturn(List.of(PRODUCT1, PRODUCT2, PRODUCT3));

        Assertions.assertThat(shopServiceImpl.getAllAvailableProducts())
                .isEqualTo(List.of(PRODUCT1, PRODUCT2, PRODUCT3));

        Mockito.verify(productService, Mockito.times(1)).getAllProducts();
    }
}
