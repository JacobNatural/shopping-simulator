package com.app.validators;

import com.app.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
@MockitoSettings()
public class PreferencesValidatorValidateTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private PreferencesValidator preferencesValidator;

    @BeforeEach
    public void setUp(TestInfo testInfo){

        var name = testInfo.getTestMethod().get().getName();

        if(name.equals("test4")){
            return;
        }

        Mockito.when(productRepository.availableCategories())
                .thenReturn(List.of("BOOKS", "TOYS", "GROCERIES","CLOTHES"));
    }


    @Test
    @DisplayName("When preferences categories are duplicated")
    public void test1(){

        Assertions.assertThat(
                preferencesValidator.validate(
                        Map.of(1L, "TOYS", 2L,"BOOKS", 3L, "BOOKS")))
                .isEqualTo(List.of("Categories in preferences cannot be duplicated"));
    }

    @Test
    @DisplayName("When the preferences categories are not available")
    public void test2(){

        Assertions.assertThat(
                        preferencesValidator.validate(
                                Map.of(1L, "TOYS", 2L,"BOOKS", 3L, "HOME", 4L, "SPORTS")))
                .contains(
                        "Category not available: SPORTS",
                        "Category not available: HOME");
    }

    @Test
    @DisplayName("When using the Validator interface with the PreferencesValidator implementation, and encountering exceptions")
    public void test3(){

        Assertions.assertThatThrownBy(() ->
                Validator.validate(Map.of(
                        1L, "TOYS",
                        2L,"BOOKS",
                        3L, "BOOKS",
                        4L, "HOME"), preferencesValidator))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("""
                        Categories in preferences cannot be duplicated
                        Category not available: HOME""");
    }

    @Test
    @DisplayName("When preferences are null")
    public void test4(){

        Assertions.assertThatThrownBy(() -> preferencesValidator.validate(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Preferences cannot be null");
    }

}
