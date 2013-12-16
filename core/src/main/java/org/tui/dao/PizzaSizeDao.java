package org.tui.dao;

import org.tui.model.PizzaSize;

import java.util.List;

/**
 * Data access class for Pizza Size entity.
 *
 * @author: Srikanth NT
 */
public interface PizzaSizeDao {

    /**
     * Get all the pizza sizes available.
     *
     * @return list of pizza sizes if available, otherwise empty list
     */
    List<PizzaSize> getPizzaSizes();

}
