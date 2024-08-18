package com.app.repsitory.impl;

import com.app.extension.repository.ClientRepositoryImplExtension;
import com.app.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Map;

import static com.app.data_provider.DataProvider.*;

@ExtendWith(ClientRepositoryImplExtension.class)
@RequiredArgsConstructor
public class ClientRepositoryImplGetAllTest {

    private final ClientRepository repository;

    @Test
    @DisplayName("When the client repository gets correct data")
    public void test1(){

        Assertions.assertThat(repository.getAll())
                .isEqualTo(Map.of(1L,CLIENT1, 2L, CLIENT2, 3L, CLIENT3));
    }

    @Test
    @DisplayName("When the client repository returns an immutable map")
    public void test2(){

        repository.getAll().put(4L, CLIENT1);

        Assertions.assertThat(repository.getAll())
                .isEqualTo(Map.of(1L,CLIENT1, 2L, CLIENT2, 3L, CLIENT3));
    }
}
