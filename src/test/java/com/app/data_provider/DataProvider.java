package com.app.data_provider;

import com.app.client.Client;
import com.app.product.Product;
import lombok.SneakyThrows;

import java.math.BigDecimal;
import java.nio.file.Paths;
import java.util.List;

public interface DataProvider {
    String FILENAME_LOAD = "txt/transfer/transfer_test.txt";
    String FILENAME_SAVE = "test.txt";
    String FILENAME_CLIENT_LOAD = "txt/load/clients_test.txt";
    String FILENAME_PREFERENCES_LOAD = "txt/load/preferences_test.txt";
    String FILENAME_PRODUCTS_LOAD = "txt/load/products_test.txt";

    Client CLIENT1 = new Client(
            1L, "JAN", "KOWAL", 20,
            BigDecimal.valueOf(1000), List.of(2L,1L));

    Client CLIENT2 = new Client(
            2L, "SEBASTIAN", "ZIOBRO", 25,
            BigDecimal.valueOf(200), List.of(2L, 3L));

    Client CLIENT3 = new Client(
            3L, "MATEUSZ", "KOWALSKI", 34,
            BigDecimal.valueOf(2300), List.of(1L));

    Client CLIENT4 = new Client(
            4L, "TADEUSZ", "FILAK",24,
            BigDecimal.valueOf(1500), List.of(1L));

    Product PRODUCT1 = new Product(1L, "BREAD", 1,
            BigDecimal.valueOf(5.55), "GROCERIES");

    Product PRODUCT2 = new Product(2L, "OVEN", 1,
                BigDecimal.valueOf(800), "HOME_APPLIANCES");

    Product PRODUCT3 = new Product(3L, "TROUSERS", 1,
            BigDecimal.valueOf(240), "CLOTHES");

    Product PRODUCT4 = new Product(4L, "SWEATER", 1,
            BigDecimal.valueOf(700), "CLOTHES");

    @SneakyThrows
    static String getPath(String filename) {
        return Paths.get(DataProvider.class.getClassLoader().getResource(filename).toURI()).toString();
    }
}
