package com.app.parser.impl;

import com.app.extension.parser.ClientLineParserExtension;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static com.app.data_provider.DataProvider.CLIENT1;

@ExtendWith(ClientLineParserExtension.class)
@RequiredArgsConstructor
public class ClientLineParserParseLineTest {

    private final ClientLineParser clientLineParser;

    @Test
    @DisplayName("When the line is null")
    public void test1() {

        Assertions.assertThatThrownBy(() ->
                        clientLineParser.parseLine(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Line cannot be null");
    }

    @Test
    @DisplayName("When the line is empty")
    public void test2() {
        Assertions.assertThatThrownBy(() ->
                        clientLineParser.parseLine(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Line cannot be empty");
    }

    static Stream<String> lines() {
        return Stream.of(
                "1:KUBA;MECH;30;2000;1:3;2",
                "2;Kuba;MECH;10;1500;1;2;3",
                "3;KUBA;Mech;12;100;1:2:3");
    }

    @ParameterizedTest
    @DisplayName("When the line does not match regex")
    @MethodSource("lines")
    public void test3(String line) {
        Assertions.assertThatThrownBy(() ->
                        clientLineParser.parseLine(line))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(STR."Line does not match regex: \{line}");
    }

    @Test
    @DisplayName("When the parser parses a line to a client")
    public void test4() {
        Assertions.assertThat(clientLineParser.parseLine("1;JAN;KOWAL;20;1000;2:1:4"))
                .isEqualTo(Map.of(1L, CLIENT1));
    }

}
