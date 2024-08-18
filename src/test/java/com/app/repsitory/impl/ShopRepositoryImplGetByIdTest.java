package com.app.repsitory.impl;

import com.app.client.Client;
import com.app.extension.repository.ShopRepositoryImplExtension;
import com.app.repository.impl.ShopRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static com.app.data_provider.DataProvider.*;

@ExtendWith(ShopRepositoryImplExtension.class)
@RequiredArgsConstructor

public class ShopRepositoryImplGetByIdTest {
    private final ShopRepositoryImpl shopRepository;

    @Test
    @DisplayName("When the Client is not available")
    public void test1(){
        Assertions.assertThatThrownBy(() ->
                        shopRepository.getById(CLIENT4))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Client not found");
    }

    @Test
    @DisplayName("When we find products by Client")
    public void test2(){

        shopRepository.updateProductList(CLIENT1, List.of(PRODUCT1));

        Assertions.assertThat(shopRepository.getById(CLIENT1))
                .isEqualTo(List.of(PRODUCT1));
    }
}
