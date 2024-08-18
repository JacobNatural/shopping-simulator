package com.app.service;

import com.app.repository.impl.ProductRepositoryImpl;
import com.app.service.impl.ProductServiceImpl;
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
public class ProductServiceImplGetAllProductsTest {

    @Mock
    ProductRepositoryImpl productRepository;

    @InjectMocks
    ProductServiceImpl productService;

    @Test
    @DisplayName("When the repository returns a single product, the service should return a list with one product")
    public void test1(){

        Mockito.when(productRepository.getAll())
                .thenReturn(Map.of(1L, PRODUCT1));

        Assertions.assertThat(productService.getAllProducts())
                .isEqualTo(List.of(PRODUCT1));

        Mockito.verify(productRepository, Mockito.times(1)).getAll();
    }
    @Test
    @DisplayName("When the list of products is returned by the service")
    public void test2(){

        Mockito.when(productRepository.getAll())
                .thenReturn(Map.of(1L, PRODUCT1, 2L, PRODUCT2, 3L, PRODUCT3));

        Assertions.assertThat(productService.getAllProducts())
                .containsExactlyInAnyOrder(PRODUCT1, PRODUCT2, PRODUCT3);

        Mockito.verify(productRepository, Mockito.times(1)).getAll();
    }

}
