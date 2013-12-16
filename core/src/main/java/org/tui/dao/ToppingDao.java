package org.tui.dao;

import org.tui.model.Topping;

import java.util.List;

/**
 * Data access class for Pizza Toppings entity.
 *
 * @author: Srikanth NT
 */
public interface ToppingDao {

    /**
     * Get all the pizza toppings available.
     *
     * @return list of pizza toppings if available, otherwise empty list
     */
    List<Topping> getToppings();

}
