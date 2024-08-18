package com.app.collector;

import com.app.collectors.ProductStatisticCollector;
import com.app.product.Product;
import com.app.statistic.Statistic;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static com.app.data_provider.DataProvider.*;

public class ProductStatisticCollectorTest {

    @Test
    @DisplayName("When the collector gives the correct statistics")
    public void test1(){

        Assertions.assertThat(Stream
                .of(PRODUCT1,PRODUCT2, PRODUCT3)
                .collect(new ProductStatisticCollector()))
                .isEqualTo(
                        new Statistic<>(
                                List.of(PRODUCT1), List.of(PRODUCT2), BigDecimal.valueOf(348.52)));
    }

    @Test
    @DisplayName("When there are two products with the maximum price")
    public void test2(){
       var product = new Product(5L, "HAT",1, BigDecimal.valueOf(800), "CLOTHES");

       Assertions.assertThat(Stream.of(PRODUCT1, PRODUCT2, PRODUCT3, product)
               .collect(new ProductStatisticCollector()))
               .isEqualTo(new Statistic<>(
                       List.of(PRODUCT1),
                       List.of(PRODUCT2, product),
                       BigDecimal.valueOf(461.39)));
    }

    @Test
    @DisplayName("When there are two products with the minimum price")
    public void test3(){
        var product = new Product(5L, "HAT",1, BigDecimal.valueOf(5.55), "CLOTHES");

        Assertions.assertThat(Stream.of(PRODUCT1, PRODUCT2, PRODUCT3, product)
                        .collect(new ProductStatisticCollector()))
                .isEqualTo(new Statistic<>(
                        List.of(PRODUCT1, product),
                        List.of(PRODUCT2),
                        BigDecimal.valueOf(262.78)));
    }

    @Test
    @DisplayName("When there are two products with the minimum and maximum prices")
    public void test4(){
        var product1 = new Product(5L, "HAT",1, BigDecimal.valueOf(5.55), "CLOTHES");
        var product2 = new Product(5L, "BOOTS",1, BigDecimal.valueOf(800), "CLOTHES");

        Assertions.assertThat(Stream.of(PRODUCT1, PRODUCT2, PRODUCT3, product1, product2)
                        .collect(new ProductStatisticCollector()))
                .isEqualTo(new Statistic<>(
                        List.of(PRODUCT1, product1),
                        List.of(PRODUCT2, product2),
                        BigDecimal.valueOf(370.22)));
    }

    @Test
    @DisplayName("When we have one product and obtain the correct statistics")
    public void test5() {

        Assertions.assertThat(Stream.of(PRODUCT1)
                .collect(new ProductStatisticCollector()))
                .isEqualTo(new Statistic<>(List.of(PRODUCT1), List.of(PRODUCT1), BigDecimal.valueOf(5.55)));

    }

    @Test
    @DisplayName("When we have two products and obtain the correct statistics")
    public void test6() {

        Assertions.assertThat(Stream.of(PRODUCT1, PRODUCT2)
                        .collect(new ProductStatisticCollector()))
                .isEqualTo(new Statistic<>(List.of(PRODUCT1), List.of(PRODUCT2), BigDecimal.valueOf(402.78)));
    }

    @Test
    @DisplayName("When we have an empty list")
    public void test7() {

        var products = new ArrayList<Product>();

        Assertions
                .assertThatThrownBy(() -> products
                        .stream()
                        .collect(new ProductStatisticCollector()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Cannot compute statistics for an empty list");

    }
}
