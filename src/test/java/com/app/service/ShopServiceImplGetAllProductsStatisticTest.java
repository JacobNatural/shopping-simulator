package com.app.service;

import com.app.collectors.ProductStatisticCollector;
import com.app.service.impl.ShopServiceImpl;
import com.app.statistic.Statistic;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import static com.app.data_provider.DataProvider.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings()
public class ShopServiceImplGetAllProductsStatisticTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ShopServiceImpl shopServiceImpl;


    @Test
    @DisplayName("When we obtain the product statistics")
    public void test1(){

        Mockito.when(productService.getProductsStatistic())
                .thenReturn(Stream.of(PRODUCT1,PRODUCT2, PRODUCT3, PRODUCT4)
                        .collect(new ProductStatisticCollector()));

        Assertions.assertThat(shopServiceImpl.getAllProductsStatistic())
                .isEqualTo(new Statistic<>(List.of(PRODUCT1), List.of(PRODUCT2), BigDecimal.valueOf(436.39)));

        Mockito.verify(productService, Mockito.times(1)).getProductsStatistic();

    }
}
