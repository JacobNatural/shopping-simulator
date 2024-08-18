package com.app.client;

import com.app.extension.client.ClientValidatorExtension;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.math.BigDecimal;
import java.util.List;

@ExtendWith(ClientValidatorExtension.class)
@RequiredArgsConstructor

public class ClientValidatorValidateTest {

    private final ClientValidator clientValidator;

    @Test
    @DisplayName("When ID of the client is not correct")
    public void test1() {
        var client = new Client(
                0L, "A", "B", 15, BigDecimal.valueOf(100), List.of(1L));

        Assertions.assertThat(clientValidator.validate(client))
                .isEqualTo(List.of("ID cannot be negative."));
    }


    @Test
    @DisplayName("When the age is lower than the minimum")
    public void test2() {
        var client = new Client(
                1L, "A", "B", 8, BigDecimal.valueOf(100), List.of(1L));

        Assertions.assertThat(clientValidator.validate(client))
                .isEqualTo(List.of("Age cannot be less than 10."));
    }

    @Test
    @DisplayName("When the balance is lower than the minimum")
    public void test3() {
        var client = new Client(
                1L, "A", "B", 18, BigDecimal.valueOf(-1), List.of(1L));

        Assertions.assertThat(clientValidator.validate(client))
                .isEqualTo(List.of("Balance cannot be less than 0."));
    }

    @Test
    @DisplayName("When the preference ID is incorrect.")
    public void test4() {
        var client = new Client(
                1L, "A", "B", 18, BigDecimal.valueOf(2000), List.of(0L));

        Assertions.assertThat(clientValidator.validate(client))
                .isEqualTo(List.of("Preference ID not available 0."));
    }
}
