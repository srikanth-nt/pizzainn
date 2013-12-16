package org.tui.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Entity class defined to hold the details of a pizza size.
 *
 * @author: Srikanth NT
 */
@Entity(name = "pizza_size")
public final class PizzaSize implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    /**
     * Price is defined in pence
     */
    @Column(name = "price")
    private int price;

    public PizzaSize() {
    }

    public PizzaSize(final int id, final String name, final int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(final int price) {
        this.price = price;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final PizzaSize pizzaSize = (PizzaSize) o;

        if (id != pizzaSize.id) return false;
        if (price != pizzaSize.price) return false;
        if (name != null ? !name.equals(pizzaSize.name) : pizzaSize.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + price;
        return result;
    }
}
