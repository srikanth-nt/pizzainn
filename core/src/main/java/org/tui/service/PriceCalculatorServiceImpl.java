package org.tui.service;

import org.tui.model.Pizza;
import org.tui.model.Topping;

/**
 * Implementation class of the interface {@link PriceCalculatorService}
 *
 * @author: Srikanth NT
 */
public final class PriceCalculatorServiceImpl implements PriceCalculatorService {

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPrice(final Pizza pizza) {
        int total = 0;
        if (pizza != null) {
            if (pizza.getSize() != null) {
                total += pizza.getSize().getPrice();
            }

            if (pizza.getToppings() != null) {
                for (Topping topping : pizza.getToppings()) {
                    total += topping.getPrice();
                }
            }

            total *= pizza.getQuantity();
        }

        return total;
    }
}
