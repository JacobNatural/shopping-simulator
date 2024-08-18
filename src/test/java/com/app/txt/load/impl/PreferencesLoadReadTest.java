package com.app.txt.load.impl;

import com.app.parser.impl.PreferencesLineParser;
import com.app.extension.parser.PreferencesLineParserExtension;
import com.app.extension.txt.load.PreferencesLoadExtension;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Map;

import static com.app.data_provider.DataProvider.*;

@ExtendWith({PreferencesLoadExtension.class, PreferencesLineParserExtension.class})
@RequiredArgsConstructor
public class PreferencesLoadReadTest {
    private final PreferencesLoad preferencesLoad;
    private final PreferencesLineParser preferencesLineParser;

    @Test
    @DisplayName("When reading a preferences file correctly")
    public void test1() {
        Assertions.assertThat(preferencesLoad.read(getPath(FILENAME_PREFERENCES_LOAD), preferencesLineParser))
                .isEqualTo(Map.of(1L, "GROCERIES", 2L, "HOME_APPLIANCES", 3L, "CLOTHES"));
    }
}
