package com.app.txt.save.impl;

import com.app.client.Client;
import com.app.extension.txt.save.ClientSaveExtension;
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

import static com.app.data_provider.DataProvider.*;


@ExtendWith(ClientSaveExtension.class)
@RequiredArgsConstructor
public class ClientSaveWriteTest {
    private final ClientSave clientSave;

    @Test
    @DisplayName("When saving a client to a file correctly")
    @SneakyThrows
    public void test1() {
        clientSave.write(FILENAME_SAVE, CLIENT1, Client::toString);

        try (var lines = Files.lines(Paths.get(FILENAME_SAVE))) {
            Assertions.assertThat(lines.collect(Collectors.joining("\n")))
                    .isEqualTo(CLIENT1.toString());

        }
    }

    @AfterAll
    @SneakyThrows
    public static void cleanData() {
        Files.deleteIfExists(Paths.get(FILENAME_SAVE));
    }
}
