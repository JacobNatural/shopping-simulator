package com.app.repsitory.impl;

import com.app.extension.repository.PreferencesRepositoryImplExtension;
import com.app.repository.impl.PreferencesRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


@ExtendWith(PreferencesRepositoryImplExtension.class)
@RequiredArgsConstructor
public class PreferencesRepositoryImplGetByIdTest {

    private final PreferencesRepositoryImpl repository;

    @Test
    @DisplayName("When the ID is not available")
    public void test1(){
        Assertions.assertThatThrownBy(() ->
                        repository.getById(4L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Preference with ID: 4 not found");
    }

    @Test
    @DisplayName("When we find a preference by ID")
    public void test2(){
        Assertions.assertThat(repository.getById(1L))
                .isEqualTo("GROCERIES");
    }
}
