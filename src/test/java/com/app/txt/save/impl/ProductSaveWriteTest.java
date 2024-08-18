package com.app.txt.save.impl;

import com.app.extension.txt.save.ProductSaveExtension;
import com.app.product.Product;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import static com.app.data_provider.DataProvider.*;

@ExtendWith(ProductSaveExtension.class)
@RequiredArgsConstructor
public class ProductSaveWriteTest {
    private final ProductSave productSave;

    @Test
    @DisplayName("When saving a product to a file correctly")
    @SneakyThrows
    public void test1() {
        productSave.write(FILENAME_SAVE, PRODUCT1, Product::toString);

        try (var lines = Files.lines(Paths.get(FILENAME_SAVE))) {
            Assertions.assertThat(lines.collect(Collectors.joining("\n")))
                    .isEqualTo(PRODUCT1.toString());

        }
    }

    @AfterAll
    @SneakyThrows
    public static void cleanData() {
        Files.deleteIfExists(Paths.get(FILENAME_SAVE));
    }

}
