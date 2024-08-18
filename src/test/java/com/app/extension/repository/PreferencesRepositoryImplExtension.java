package com.app.extension.repository;

import com.app.data_provider.DataProvider;
import com.app.parser.impl.PreferencesLineParser;
import com.app.repository.ProductRepository;
import com.app.repository.impl.PreferencesRepositoryImpl;
import com.app.txt.load.impl.PreferencesLoad;
import com.app.txt.transfer.impl.TransferImpl;
import com.app.validators.PreferencesValidator;
import lombok.SneakyThrows;
import org.junit.jupiter.api.extension.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

public class PreferencesRepositoryImplExtension implements ParameterResolver {

    @Mock
    private ProductRepository productRepository;

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(PreferencesRepositoryImpl.class);
    }

    @Override
    @SneakyThrows
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        try(var _ = MockitoAnnotations.openMocks(this)){

            Mockito.when(productRepository.availableCategories())
                    .thenReturn(List.of("GROCERIES", "HOME_APPLIANCES", "CLOTHES"));
            var transfer = new TransferImpl<Long, String>();

            var preferenceLoad = new PreferencesLoad(transfer);
            var preferencesLineParser = new PreferencesLineParser("[1-9][0-9]*;[A-Z_]+");
            var preferenceValidator = new PreferencesValidator(productRepository);

            return new PreferencesRepositoryImpl(DataProvider.getPath(DataProvider.FILENAME_PREFERENCES_LOAD),
                    preferenceLoad,preferencesLineParser,preferenceValidator);
        }

    }
}
