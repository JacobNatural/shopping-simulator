package com.app.parser.impl;

import com.app.extension.parser.PreferencesLineParserExtension;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

@ExtendWith(PreferencesLineParserExtension.class)
@RequiredArgsConstructor
public class PreferencesLineParserParseLineTest {
    private final PreferencesLineParser preferencesLineParser;

    @Test
    @DisplayName("When the line is null")
    public void test1() {
        Assertions.assertThatThrownBy(() ->
                        preferencesLineParser.parseLine(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Line cannot be null");
    }

    @Test
    @DisplayName("When the filename is empty")
    public void test2() {
        Assertions.assertThatThrownBy(() ->
                        preferencesLineParser.parseLine(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Line cannot be empty");
    }

    static Stream<String> lines() {
        return Stream.of(
                "1:BOOKS",
                "A;BOOKS",
                "2;Toys"
        );
    }

    @ParameterizedTest
    @DisplayName("When the line does not match regex")
    @MethodSource("lines")
    public void test3(String line) {

        Assertions.assertThatThrownBy(() ->
                        preferencesLineParser.parseLine(line))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(STR."Line does not match regex: \{line}");
    }


    @Test
    @DisplayName("When the parser parses a line to a preferences")
    public void test5(){

        Assertions.assertThat(preferencesLineParser.parseLine("1;BOOKS"))
                .isEqualTo(Map.of(1L, "BOOKS"));
    }
}
