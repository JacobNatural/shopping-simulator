package com.app.parser.impl;


import com.app.extension.parser.ProductLineParserExtension;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static com.app.data_provider.DataProvider.PRODUCT1;

@ExtendWith(ProductLineParserExtension.class)
@RequiredArgsConstructor
public class ProductLineParserParseLineTest {
    private final ProductLineParser productLineParser;

    @Test
    @DisplayName("When the line is null")
    public void test1(){
        Assertions.assertThatThrownBy(() ->
                productLineParser.parseLine(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Line cannot be null");
    }

    @Test
    @DisplayName("When the line is empty")
    public void test2(){
        Assertions.assertThatThrownBy(() ->
                        productLineParser.parseLine(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Line cannot be empty");
    }

    static Stream<String> lines(){
        return Stream.of(
                "1;Pan_Tadeusz;1;23;BOOKS",
                "2;Bread;2;2.3;Groceries",
                "3:Trousers;1;23;CLOTHES"
        );
    }

    @ParameterizedTest
    @DisplayName("When the line is empty")
    @MethodSource("lines")
    public void test3(String line){
        Assertions.assertThatThrownBy(() ->
                        productLineParser.parseLine(line))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(STR."Line does not match regex: \{line}");
    }

    @Test
    @DisplayName("When the parser parses a line to a product")
    public void test4(){
        Assertions.assertThat(productLineParser.parseLine("1;BREAD;1;5.55;GROCERIES"))
                .isEqualTo(Map.of(1L,PRODUCT1));
    }
}
