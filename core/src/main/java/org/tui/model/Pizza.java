package org.tui.model;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Class defined to hold the details of a pizza.
 *
 * @author: Srikanth NT
 */
public final class Pizza implements Serializable {

    private PizzaSize size;

    private Set<Topping> toppings;

    private int quantity = 1;

    public Pizza() {
    }

    public PizzaSize getSize() {
        return size;
    }

    public void setSize(final PizzaSize size) {
        this.size = size;
    }

    public Set<Topping> getToppings() {
        if (toppings == null) {
            toppings = new LinkedHashSet<Topping>();
        }
        return toppings;
    }

    public void setToppings(final Set<Topping> toppings) {
        this.toppings = toppings;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(final int quantity) {
        this.quantity = quantity;
    }
}
