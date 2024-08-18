package com.app.collectors;

import com.app.product.Product;
import com.app.product.ProductMapper;
import com.app.statistic.Statistic;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * A {@link Collector} implementation that collects {@link Product} objects into a {@link Statistic}.
 * <p>
 * The resulting {@link Statistic} contains the minimum, maximum, and average prices of the products.
 * </p>
 */
public class ProductStatisticCollector implements Collector<Product, List<Product>, Statistic<BigDecimal, List<Product>>> {

    @Override
    public Supplier<List<Product>> supplier() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<Product>, Product> accumulator() {
        return List::add;
    }

    @Override
    public BinaryOperator<List<Product>> combiner() {
        return (l1, l2) -> {
            l1.addAll(l2);
            return l1;
        };
    }

    @Override
    public Function<List<Product>, Statistic<BigDecimal, List<Product>>> finisher() {
        return list -> {
            if (list.isEmpty()) {
                throw new IllegalArgumentException("Cannot compute statistics for an empty list");
            }

            var totalSum = list.stream()
                    .map(ProductMapper.toPrice)
                    .reduce(BigDecimal::add)
                    .orElse(BigDecimal.ZERO);

            var average = totalSum.divide(BigDecimal.valueOf(list.size()), 2, RoundingMode.CEILING);

            var grouped = list.stream()
                    .collect(Collectors.groupingBy(ProductMapper.toPrice));

            var min = grouped.entrySet().stream()
                    .min(Map.Entry.comparingByKey())
                    .map(Map.Entry::getValue)
                    .orElseThrow();

            var max = grouped.entrySet().stream()
                    .max(Map.Entry.comparingByKey())
                    .map(Map.Entry::getValue)
                    .orElseThrow();

            return new Statistic<>(min, max, average);
        };
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Set.of(Characteristics.CONCURRENT);
    }
}