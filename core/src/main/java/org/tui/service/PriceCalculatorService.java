package org.tui.service;

import org.tui.model.Pizza;

/**
 * Interface for all price calculation of the pizza.
 *
 * @author: Srikanth NT
 */
public interface PriceCalculatorService {

    /**
     * Get the price of a single pizza in pence.
     *
     * @param pizza pizza details.
     * @return price in pence.
     */
    int getPrice(final Pizza pizza);

}
