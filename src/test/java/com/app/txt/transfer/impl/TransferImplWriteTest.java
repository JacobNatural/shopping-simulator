package com.app.txt.transfer.impl;

import com.app.extension.txt.transfer.TransferImplExtension;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import static com.app.data_provider.DataProvider.FILENAME_SAVE;

@ExtendWith(TransferImplExtension.class)
@RequiredArgsConstructor
public class TransferImplWriteTest {
    private final TransferImpl<String, String> transferImpl;

    @Test
    @DisplayName("When the filename is null")
    public void test1() {
        Assertions.assertThatThrownBy(() ->
                        transferImpl.write(null, "some text", x -> x))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Filename cannot be null");
    }

    @Test
    @DisplayName("When the filename is empty")
    public void test2() {
        Assertions.assertThatThrownBy(() ->
                        transferImpl.write("", "some text", x -> x))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Filename cannot be empty");
    }

    @Test
    @DisplayName("When the object is null")
    public void test3() {
        Assertions.assertThatThrownBy(() ->
                        transferImpl.write(FILENAME_SAVE, null, x -> x))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("T cannot be null");
    }

    @Test
    @DisplayName("When the function is null")
    public void test4() {
        Assertions.assertThatThrownBy(() ->
                        transferImpl.write(FILENAME_SAVE, "simple text", null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Prepare cannot be null");
    }

    @Test
    @DisplayName("When the function is null")
    @SneakyThrows
    public void test5() {

        transferImpl.write(FILENAME_SAVE, "simple text", x -> x);

        try (var line = Files.lines(Paths.get(FILENAME_SAVE))) {
            Assertions.assertThat(line.collect(Collectors.joining("\n")))
                    .isEqualTo("simple text");
        }
    }

    @AfterAll
    @SneakyThrows
    public static void cleanData() {
        Files.deleteIfExists(Paths.get(FILENAME_SAVE));
    }

}
