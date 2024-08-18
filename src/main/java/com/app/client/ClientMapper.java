package com.app.client;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;

/**
 * A utility interface for mapping {@link Client} attributes to various types.
 * Provides functions to extract client preferences and balance.
 */
public interface ClientMapper {

    /**
     * A function to get the list of preferences from a {@link Client}.
     *
     * @see Client#preferences
     */
    Function<Client, List<Long>> toPreferences = client -> client.preferences;

    /**
     * A function to get the balance of a {@link Client}.
     *
     * @see Client#balance
     */
    Function<Client, BigDecimal> toBalance = client -> client.balance;
}