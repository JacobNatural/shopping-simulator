package com.app.txt.load.impl;

import com.app.parser.impl.ProductLineParser;
import com.app.extension.parser.ProductLineParserExtension;
import com.app.extension.txt.load.ProductLoadExtension;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Map;

import static com.app.data_provider.DataProvider.*;

@ExtendWith({ProductLoadExtension.class, ProductLineParserExtension.class})
@RequiredArgsConstructor
public class ProductLoadReadTest {
    private final ProductLoad productLoad;
    private final ProductLineParser productLineParser;

    @Test
    @DisplayName("When reading a product file correctly")
    public void test1() {
        Assertions.assertThat(productLoad.read(getPath(FILENAME_PRODUCTS_LOAD), productLineParser))
                .isEqualTo(Map.of(1L, PRODUCT1, 2L, PRODUCT2, 3L, PRODUCT3));
    }
}
