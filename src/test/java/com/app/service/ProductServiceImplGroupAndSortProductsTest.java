package com.app.service;


import com.app.product.ProductComparators;
import com.app.product.ProductMapper;
import com.app.repository.impl.ProductRepositoryImpl;
import com.app.service.impl.ProductServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Map;

import static com.app.data_provider.DataProvider.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplGroupAndSortProductsTest {

    @Mock
    ProductRepositoryImpl productRepository;

    @InjectMocks
    ProductServiceImpl productService;

    @BeforeEach
    public void setUp(TestInfo testInfo) {
        var methodName = testInfo.getTestMethod().get().getName();
        if(methodName.equals("test1") || methodName.equals("test2")) {
            return;
        }

        Mockito.when(productRepository.getAll())
                .thenReturn(Map.of(1L ,PRODUCT1, 2L, PRODUCT2, 3L, PRODUCT3,4L, PRODUCT4));

    }

    @Test
    @DisplayName("When the product mapper is null")
    public void test1() {

        Assertions.assertThatThrownBy(() ->
                        productService.groupAndSortProducts(null, ProductComparators.compareByPrice))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Product mapper cannot be null");
    }

    @Test
    @DisplayName("When the comparator is null")
    public void test2() {

        Assertions.assertThatThrownBy(() ->
                        productService.groupAndSortProducts(ProductMapper.toCategory, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Comparator cannot be null");
    }

    @Test
    @DisplayName("When grouping by category and sorting by price in ascending order")
    public void test3() {

        Assertions.assertThat(
                        productService.groupAndSortProducts(ProductMapper.toCategory, ProductComparators.compareByPrice))
                .isEqualTo(Map.of(
                        "GROCERIES", List.of(PRODUCT1),
                        "HOME_APPLIANCES", List.of(PRODUCT2),
                        "CLOTHES", List.of(PRODUCT3,PRODUCT4 )));

        Mockito.verify(productRepository, Mockito.times(1)).getAll();
    }

    @Test
    @DisplayName("When group by category and sorting by price in descending order")
    public void test4() {

        Assertions.assertThat(
                        productService.groupAndSortProducts(
                                ProductMapper.toCategory,
                                ProductComparators.compareByPrice.reversed()))
                .isEqualTo(Map.of(
                        "GROCERIES", List.of(PRODUCT1),
                        "HOME_APPLIANCES", List.of(PRODUCT2),
                        "CLOTHES", List.of(PRODUCT4, PRODUCT3 )));

        Mockito.verify(productRepository, Mockito.times(1)).getAll();
    }

    @Test
    @DisplayName("When group by price and sorting by price in ascending order")
    public void test5() {

        Assertions.assertThat(productService.groupAndSortProducts(
                        ProductMapper.toAmount::applyAsInt, ProductComparators.compareByPrice))
                .isEqualTo(Map.of(1, List.of(PRODUCT1, PRODUCT3, PRODUCT4, PRODUCT2)));

        Mockito.verify(productRepository, Mockito.times(1)).getAll();
    }

    @Test
    @DisplayName("When group by price and sorting by price in ascending order")
    public void test6() {

        Assertions.assertThat(productService.groupAndSortProducts(
                        ProductMapper.toAmount::applyAsInt,
                        ProductComparators.compareByPrice.reversed()))
                .isEqualTo(Map.of(1, List.of(PRODUCT2, PRODUCT4, PRODUCT3, PRODUCT1)));

        Mockito.verify(productRepository, Mockito.times(1)).getAll();
    }
}
