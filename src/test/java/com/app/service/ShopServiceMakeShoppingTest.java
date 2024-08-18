package com.app.service;

import com.app.client.Client;
import com.app.product.Product;
import com.app.product.ProductComparators;
import com.app.product.ProductMapper;
import com.app.repository.PreferencesRepository;
import com.app.repository.impl.ShopRepositoryImpl;
import com.app.service.impl.ProductServiceImpl;
import com.app.service.impl.ShopServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.app.data_provider.DataProvider.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings()
public class ShopServiceMakeShoppingTest {

    @Mock
    private ShopRepositoryImpl shopRepository;

    @Mock
    private ProductServiceImpl productService;

    @Mock
    private PreferencesRepository preferencesRepository;

    @InjectMocks
    private ShopServiceImpl shopService;

    @Test
    public void test1(){

        Mockito.when(shopRepository.getAll())
                .thenReturn(new HashMap<>(Map.of(
                        CLIENT1, new ArrayList<>(List.of()),
                        CLIENT2, new ArrayList<>(List.of()),
                        CLIENT3, new ArrayList<>(List.of())
                        )));

        Mockito.when(shopRepository
                .updateProductList(ArgumentMatchers.any(Client.class), ArgumentMatchers.any()))
                        .thenReturn(true);

        Map<String, List<Product>> m = Map.of(
                "GROCERIES", List.of(PRODUCT1),
                "CLOTHES", List.of(PRODUCT3, PRODUCT4),
                "HOME_APPLIANCES", List.of(PRODUCT2));

        Mockito.when(productService.groupAndSortProducts(
                ProductMapper.toCategory, ProductComparators.compareByPrice))
                        .thenReturn(m);

        Mockito.when(preferencesRepository.getById(1L)).thenReturn("GROCERIES");
        Mockito.when(preferencesRepository.getById(2L)).thenReturn("CLOTHES");
        Mockito.when(preferencesRepository.getById(3L)).thenReturn("HOME_APPLIANCES");

        shopService.postConstructAction();

        var order = Mockito.inOrder(shopRepository, productService, preferencesRepository);
        order.verify(productService, Mockito.times(1))
                .groupAndSortProducts(ProductMapper.toCategory, ProductComparators.compareByPrice);
        order.verify(shopRepository,Mockito.times(1)).getAll();

        order.verify(preferencesRepository, Mockito.times(1)).getById(2L);
        order.verify(preferencesRepository, Mockito.times(1)).getById(1L);
        order.verify(shopRepository,Mockito.times(1))
                .updateProductList(CLIENT1,List.of(PRODUCT3, PRODUCT4, PRODUCT1));

        order.verify(preferencesRepository, Mockito.times(1)).getById(2L);
        order.verify(preferencesRepository, Mockito.times(1)).getById(3L);
        order.verify(shopRepository,Mockito.times(1))
                .updateProductList(CLIENT2,List.of());

        order.verify(preferencesRepository, Mockito.times(1)).getById(1L);
        order.verify(shopRepository,Mockito.times(1))
                .updateProductList(CLIENT3,List.of(PRODUCT1));
    }
}
