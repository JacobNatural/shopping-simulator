package com.app.client;

import com.app.extension.client.ClientExtension;
import com.app.extension.repository.PreferencesRepositoryImplExtension;
import com.app.product.Product;
import com.app.repository.impl.PreferencesRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@ExtendWith({ClientExtension.class, PreferencesRepositoryImplExtension.class})
@RequiredArgsConstructor
public class ClientGetAvailableProductsTest {
    private final Client client;
    private final PreferencesRepositoryImpl preferencesRepository;

    @Test
    @DisplayName("When we have money for all products")
    public void test1(){
        var product1 = new Product(1L, "TOASTER", 1, BigDecimal.valueOf(50),"HOME_APPLIANCES");
        var product2 = new Product(2L, "PILLOW", 1, BigDecimal.valueOf(20),"HOME_APPLIANCES");
        var product3 = new Product(3L, "TROUSER", 1, BigDecimal.valueOf(40),"CLOTHES");
        var map = Map.of(
                "HOME_APPLIANCES", List.of(product1, product2),
                "CLOTHES", List.of(product3));

        Assertions.assertThat(client.getAvailableProducts(map, preferencesRepository))
                .isEqualTo(List.of(product1, product2, product3));
    }

    @Test
    @DisplayName("When we do not have enough money for all products")
    public void test2(){
        System.out.println(client);
        var product1 = new Product(1L, "OVEN", 1, BigDecimal.valueOf(200),"HOME_APPLIANCES");
        var product2 = new Product(2L, "PILLOW", 1, BigDecimal.valueOf(20),"HOME_APPLIANCES");
        var product3 = new Product(3L, "TROUSER", 1, BigDecimal.valueOf(40),"CLOTHES");
        var map = Map.of(
                "HOME_APPLIANCES", List.of(product1, product2),
                "CLOTHES", List.of(product3));

        Assertions.assertThat(client.getAvailableProducts(map, preferencesRepository))
                .isEqualTo(List.of(product1));
    }

    @Test
    @DisplayName("When we do not have enough money for all products in the first category but have enough for the second")
    public void test3(){
        var product1 = new Product(1L, "REFRIGERATOR", 1, BigDecimal.valueOf(250),"HOME_APPLIANCES");
        var product2 = new Product(2L, "PILLOW", 1, BigDecimal.valueOf(30),"HOME_APPLIANCES");
        var product3 = new Product(3L, "TROUSER", 1, BigDecimal.valueOf(40),"CLOTHES");
        var map = Map.of(
                "HOME_APPLIANCES", List.of(product1, product2),
                "CLOTHES", List.of(product3));

        Assertions.assertThat(client.getAvailableProducts(map, preferencesRepository))
                .isEqualTo(List.of(product2, product3));
    }

    @Test
    @DisplayName("When we do not have enough money for any product")
    public void test4(){
        var product1 = new Product(1L, "REFRIGERATOR", 1, BigDecimal.valueOf(250),"HOME_APPLIANCES");
        var product2 = new Product(2L, "OVEN", 1, BigDecimal.valueOf(500),"HOME_APPLIANCES");
        var product3 = new Product(3L, "BOOTS", 1, BigDecimal.valueOf(240),"CLOTHES");
        var map = Map.of(
                "HOME_APPLIANCES", List.of(product1, product2),
                "CLOTHES", List.of(product3));

        Assertions.assertThat(client.getAvailableProducts(map, preferencesRepository))
                .isEqualTo(List.of());
    }
}
