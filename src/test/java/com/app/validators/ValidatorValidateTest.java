package com.app.validators;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ValidatorValidateTest {

    @Test
    @DisplayName("When the object is null")
    public void test1(){
        Assertions.assertThatThrownBy(() ->
                Validator.validate(null, x -> List.of()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Object to be validated cannot be null");
    }

    @Test
    @DisplayName("When the validator is null")
    public void test2(){
        Assertions.assertThatThrownBy(() ->
                        Validator.validate("text", null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Validator cannot be null");
    }


    @Test
    @DisplayName("When the validator throws exceptions")
    public void test3(){
        Assertions.assertThatThrownBy(() ->
                        Validator.validate("text", x -> List.of(
                                "The text should start with a capital letter",
                                "The text should contain a number"
                                )))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(
                        """
                        The text should start with a capital letter
                        The text should contain a number"""
                );
    }
}
