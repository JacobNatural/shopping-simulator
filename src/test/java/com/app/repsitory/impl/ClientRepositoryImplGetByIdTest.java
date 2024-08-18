package com.app.repsitory.impl;

import com.app.client.Client;
import com.app.extension.repository.ClientRepositoryImplExtension;
import com.app.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.app.data_provider.DataProvider.CLIENT1;

@ExtendWith(ClientRepositoryImplExtension.class)
@RequiredArgsConstructor
public class ClientRepositoryImplGetByIdTest {
    private final ClientRepository repository;

    @Test
    @DisplayName("When the ID is not available")
    public void test1(){
        Assertions.assertThatThrownBy(() ->
                        repository.getById(4L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Client with ID: 4 not found");
    }

    @Test
    @DisplayName("When we find a client by ID")
    public void test2(){
        Assertions.assertThat(repository.getById(1L))
                .isEqualTo(CLIENT1);
    }
}
