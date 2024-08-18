package com.app.repsitory.impl;

import com.app.extension.repository.ShopRepositoryImplExtension;
import com.app.repository.impl.ShopRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import java.util.List;
import java.util.Map;

import static com.app.data_provider.DataProvider.*;

@ExtendWith(ShopRepositoryImplExtension.class)
@RequiredArgsConstructor
public class ShopRepositoryImplGetAllTest {

    private final ShopRepositoryImpl repository;

    @Test
    @DisplayName("When the shop repository gets correct data")
    public void test1(){
        Assertions.assertThat(repository.getAll())
                .isEqualTo(Map.of(CLIENT1, List.of(),  CLIENT2,List.of(),  CLIENT3, List.of()));
    }

    @Test
    @DisplayName("When the shop repository returns an immutable map")
    public void test2(){

        repository.getAll().put(CLIENT4, List.of());

        Assertions.assertThat(repository.getAll())
                .isEqualTo(Map.of(CLIENT1, List.of(),  CLIENT2,List.of(),  CLIENT3, List.of()));
    }

}
