package com.app.statistic;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Represents statistical data for a given set of items.
 * This class holds statistical values such as minimum, maximum, and average.
 *
 * @param <T> the type of the average value
 * @param <U> the type of the minimum and maximum values
 */
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class Statistic<T, U> {
    /**
     * The minimum value in the set.
     */
    private final U min;

    /**
     * The maximum value in the set.
     */
    private final U max;

    /**
     * The average value in the set.
     */
    private final T avg;
}