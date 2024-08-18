package com.app.client;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.app.data_provider.DataProvider.CLIENT1;

public class ClientMapperToPreferencesTest {

    @Test
    @DisplayName("When the product mapper returns the intended value")
    public void test1(){
        Assertions.assertThat(ClientMapper.toPreferences.apply(CLIENT1))
                .isEqualTo(List.of(2L,1L));
    }
}
