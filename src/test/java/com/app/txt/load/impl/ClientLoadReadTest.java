package com.app.txt.load.impl;

import com.app.parser.impl.ClientLineParser;
import com.app.extension.parser.ClientLineParserExtension;
import com.app.extension.txt.load.ClientLoadExtension;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Map;

import static com.app.data_provider.DataProvider.*;

@ExtendWith({ClientLoadExtension.class, ClientLineParserExtension.class})
@RequiredArgsConstructor
public class ClientLoadReadTest {
    private final ClientLoad clientLoad;
    private final ClientLineParser clientLineParser;

    @Test
    @DisplayName("When reading a clients file correctly")
    public void test1() {
        Assertions.assertThat(clientLoad.read(getPath(FILENAME_CLIENT_LOAD), clientLineParser))
                .isEqualTo(Map.of(1L, CLIENT1, 2L, CLIENT2, 3L, CLIENT3));
    }
}
