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
public class PreferenceRepositoryImplContainIdTest {
    private final PreferencesRepositoryImpl repository;

    @Test
    @DisplayName("When the repository contains an ID")
    public void test1(){
        Assertions.assertThat(repository.containId(2L))
                .isTrue();
    }

    @Test
    @DisplayName("When the repository does not contain an ID")
    public void test2(){
        Assertions.assertThat(repository.containId(4L))
                .isFalse();
    }
}
