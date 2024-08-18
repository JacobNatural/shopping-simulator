package com.app.extension.parser;

import com.app.parser.impl.ClientLineParser;
import com.app.client.ClientValidator;
import lombok.SneakyThrows;
import org.junit.jupiter.api.extension.*;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.List;


public class ClientLineParserExtension implements ParameterResolver {
    @Mock
    private ClientValidator clientValidator;

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(ClientLineParser.class);
    }

    @Override
    @SneakyThrows
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {

        try (var closeable = MockitoAnnotations.openMocks(this)) {

            Mockito.when(clientValidator.validate(ArgumentMatchers.any())).thenReturn(List.of());

            return new ClientLineParser(
                    "[1-9][0-9]*;[A-Z]+;[A-Z]+;([1-9][0-9]*;){2}([1-9][0-9]*:)*[0-9][1-9]*", clientValidator);
        }

    }
}
