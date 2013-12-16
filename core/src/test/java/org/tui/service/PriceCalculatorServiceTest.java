package org.tui.service;

import org.junit.BeforeClass;
import org.junit.Test;
import org.tui.model.Pizza;
import org.tui.model.PizzaSize;
import org.tui.model.Topping;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Test class for price calculator service {@link PriceCalculatorService}.
 *
 * @author: Srikanth NT
 */
public class PriceCalculatorServiceTest {

    private static PriceCalculatorService priceCalculatorService;

    @BeforeClass
    public static void initialize() {
        priceCalculatorService = new PriceCalculatorServiceImpl();
    }

    /**
     * Test for null initialization of priceCalculatorService.
     */
    @Test
    public void testNullInitialization() {
        assertNotNull("priceCalculatorService cannot be null ", priceCalculatorService);
    }

    /**
     * Test by passing null parameter to the getPrice method.
     */
    @Test
    public void testGetPriceForNullParam() {
        final int price = priceCalculatorService.getPrice(null);
        assertTrue(price == 0);
    }

    /**
     * Test by passing valid parameter to the getPrice method.
     */
    @Test
    public void testGetPriceForValidParam() {
        final Pizza pizza = new Pizza();
        pizza.setQuantity(1);
        pizza.setSize(new PizzaSize(1, "Regular", 1000));

        pizza.getToppings().add(new Topping(1, "Cheese", 150));
        pizza.getToppings().add(new Topping(2, "Chicken", 300));

        final int price = priceCalculatorService.getPrice(pizza);
        assertTrue(price == 1450);
    }

}
