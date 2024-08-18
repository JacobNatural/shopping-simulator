package com.app.repsitory.impl;

import com.app.extension.repository.PreferencesRepositoryImplExtension;
import com.app.repository.impl.PreferencesRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Map;


@ExtendWith(PreferencesRepositoryImplExtension.class)
@RequiredArgsConstructor
public class PreferencesRepositoryImplGetAllTest {
    private final PreferencesRepositoryImpl repository;

    @Test
    @DisplayName("When the preferences repository gets correct data")
    public void test1(){

        Assertions.assertThat(repository.getAll())
                .isEqualTo(Map.of(1L,"GROCERIES", 2L, "HOME_APPLIANCES", 3L, "CLOTHES"));
    }

    @Test
    @DisplayName("When the preferences repository returns an immutable map")
    public void test2(){

        repository.getAll().put(4L, "TOYS");
        Assertions.assertThat(repository.getAll())
                .isEqualTo(Map.of(1L,"GROCERIES", 2L, "HOME_APPLIANCES", 3L, "CLOTHES"));
    }
}
