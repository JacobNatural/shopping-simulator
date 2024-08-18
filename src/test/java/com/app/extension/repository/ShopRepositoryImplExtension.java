package com.app.extension.repository;

import com.app.repository.impl.ClientRepositoryImpl;
import com.app.repository.impl.ShopRepositoryImpl;
import lombok.SneakyThrows;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Map;

import static com.app.data_provider.DataProvider.*;


public class ShopRepositoryImplExtension implements ParameterResolver {

    @Mock
    private ClientRepositoryImpl clientRepository;

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(ShopRepositoryImpl.class);
    }

    @Override
    @SneakyThrows
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        try(var closeable = MockitoAnnotations.openMocks(this)){

            Mockito.when(clientRepository.getAll())
                    .thenReturn(Map.of(1L, CLIENT1, 2L, CLIENT2, 3L, CLIENT3));

            return new ShopRepositoryImpl(clientRepository);
        }

    }
}
