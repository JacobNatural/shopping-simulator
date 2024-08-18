package com.app;

import com.app.config.AppConfig;
import com.app.service.impl.ShopServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Comparator;

/**
 * Main application class for the shop management system.
 * It uses Spring's {@link AnnotationConfigApplicationContext} to configure the application context
 * and retrieve the {@link ShopServiceImpl} bean for performing operations related to the shop.
 */
public class App {
    /**
     * The main method of the application that runs the shop service.
     * It prints the results of various operations provided by the {@link ShopServiceImpl} class.
     */
    public static void main(String[] args) {

        // SPRING

        AnnotationConfigApplicationContext context;
        context = new AnnotationConfigApplicationContext(AppConfig.class);

        var shopService = context.getBean("shopServiceImpl", ShopServiceImpl.class);

        // SHOP SERVICE METHODS

        System.out.println("Products available");
        shopService.getAllAvailableProducts().forEach(System.out::println);
        System.out.println();

        System.out.println("Products statistic");
        System.out.println(shopService.getAllProductsStatistic() + "\n");

        System.out.println("Clients with bigger payment");
        System.out.println(shopService.getClientWithBiggerPayment() + "\n");

        System.out.println("Clients with biggest amount of products");
        System.out.println(shopService.getClientWithBiggestAmountOfProduct() + "\n");

        System.out.println("Products and amount of clients");
        shopService.groupByProductAndAmountOfClient().forEach((k, v) -> System.out.println(STR."\{k}: \{v}"));
        System.out.println();

        System.out.println("Most often product");
        System.out.println(shopService.getMostOftenProduct() + "\n");

        System.out.println("Rather product");
        System.out.println(shopService.getRatherProduct() + "\n");

        System.out.println("Categories of product sorted by popularity");
        shopService.getCategoriesSortedByPopularity(Comparator.naturalOrder()).forEach(System.out::println);
    }
}
