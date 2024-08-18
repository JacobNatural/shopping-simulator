package com.app.extension.client;


import com.app.client.ClientValidator;
import com.app.data_provider.DataProvider;
import com.app.parser.impl.PreferencesLineParser;
import com.app.repository.impl.PreferencesRepositoryImpl;
import com.app.txt.load.impl.PreferencesLoad;
import com.app.txt.transfer.impl.TransferImpl;
import com.app.validators.PreferencesValidator;
import lombok.SneakyThrows;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;

import static com.app.data_provider.DataProvider.*;

public class ClientValidatorExtension implements ParameterResolver {

    @Mock
    private PreferencesValidator preferencesValidator;

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(ClientValidator.class);
    }

    @Override
    @SneakyThrows
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        try(var closeable = MockitoAnnotations.openMocks(this)){

            Mockito.when(preferencesValidator.validate(ArgumentMatchers.any()))
                    .thenReturn(List.of());

            var transfer = new TransferImpl<Long, String>();
            var preferencesLoad = new PreferencesLoad(transfer);
            var preferenceLineParser = new PreferencesLineParser("[1-9][0-9]*;[A-Z_]+");

            var preferencesRepository = new PreferencesRepositoryImpl(
                    getPath(FILENAME_PREFERENCES_LOAD), preferencesLoad,
                    preferenceLineParser, preferencesValidator);

            return new ClientValidator(10, BigDecimal.ZERO,preferencesRepository);
        }

    }
}
