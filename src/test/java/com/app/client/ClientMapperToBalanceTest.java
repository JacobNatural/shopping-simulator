package com.app.client;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import static com.app.data_provider.DataProvider.CLIENT1;

public class ClientMapperToBalanceTest {

    @Test
    @DisplayName("When the product mapper returns the intended value")
    public void test1(){
        Assertions.assertThat(ClientMapper.toBalance.apply(CLIENT1))
                .isEqualTo(BigDecimal.valueOf(1000));
    }
}
