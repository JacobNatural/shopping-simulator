package com.app.txt.transfer.impl;

import com.app.data_provider.DataProvider;
import com.app.extension.txt.transfer.TransferImplExtension;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.util.Map;

import static com.app.data_provider.DataProvider.FILENAME_LOAD;

@ExtendWith(TransferImplExtension.class)
@RequiredArgsConstructor
public class TransferImplReadTest {
    private final TransferImpl<Long, String> transferImpl;

    @Test
    @DisplayName("When the filename is null")
    public void test1() {
        Assertions.assertThatThrownBy(() ->
                        transferImpl.read(null, x -> Map.of(1L, x)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Filename cannot be null");
    }

    @Test
    @DisplayName("When the filename is empty")
    public void test2() {
        Assertions.assertThatThrownBy(() ->
                        transferImpl.read("", x -> Map.of(1L, x)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Filename cannot be empty");
    }

    @Test
    @DisplayName("When the line parser is null")
    public void test3() {
        Assertions.assertThatThrownBy(() ->
                        transferImpl.read("test.txt", null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Line parser cannot be null");
    }

    @Test
    @DisplayName("When the filename is incorrect")
    public void test4() {
        Assertions.assertThatThrownBy(() ->
                        transferImpl.read("test.txt", x -> Map.of(1L, x)))
                .isInstanceOf(IOException.class);
    }


    @Test
    @DisplayName("When read file correctly")
    public void test5() {
        Assertions.assertThat(
                        transferImpl.read(DataProvider.getPath(FILENAME_LOAD), x -> Map.of(1L, x)))
                .isEqualTo(Map.of(1L, "simple text"));
    }


}
