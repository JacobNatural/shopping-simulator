package com.app.service;

import com.app.repository.impl.ProductRepositoryImpl;
import com.app.service.impl.ProductServiceImpl;
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
import java.util.Map;

import static com.app.data_provider.DataProvider.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings()
public class ProductServiceImplGetProductStatisticTest {

    @Mock
    ProductRepositoryImpl productRepository;

    @InjectMocks
    ProductServiceImpl productService;

    @Test
    @DisplayName("When we have obtained the statistics")
    public void test1() {

        Mockito.when(productRepository.getAll())
                .thenReturn(Map.of(
                        1L, PRODUCT1, 2L, PRODUCT2,
                        3L, PRODUCT3, 4L, PRODUCT4));

        Assertions.assertThat(productService.getProductsStatistic())
                .isEqualTo(new Statistic<>(List.of(PRODUCT1), List.of(PRODUCT2), BigDecimal.valueOf(436.39)));
        Mockito.verify(productRepository, Mockito.times(1)).getAll();

    }
}


