package org.tui.presentation.pages;

import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.tui.dao.PizzaSizeDao;
import org.tui.dao.ToppingDao;
import org.tui.model.PizzaSize;
import org.tui.model.Topping;
import org.tui.presentation.WicketTestApplication;
import org.tui.service.PriceCalculatorServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Test class for design page.
 *
 * @author: Srikanth NT
 */
public class DesignPageTest {

    private WicketTester tester;

    private List<PizzaSize> pizzaSizes;

    private List<Topping> toppings;

    @Before
    public void setUp() {
        pizzaSizes = new ArrayList<PizzaSize>();
        pizzaSizes.add(new PizzaSize(1, "Regular", 800));
        pizzaSizes.add(new PizzaSize(2, "Medium", 1200));
        pizzaSizes.add(new PizzaSize(3, "Large", 1500));

        toppings = new ArrayList<Topping>();
        toppings.add(new Topping(1, "Cheese", 200));
        toppings.add(new Topping(2, "Paneer", 150));
        toppings.add(new Topping(3, "Keema", 300));

        final WicketTestApplication application = new WicketTestApplication();
        application.getCtx().putBean(createPizzaSizeDaoMock());
        application.getCtx().putBean(createToppingDaoMock());
        application.getCtx().putBean("priceCalculatorService", new PriceCalculatorServiceImpl());
        tester = new WicketTester(application);
    }

    /**
     * Tests the page for default values.
     */
    @Test
    public void testDefaultPageRendering() {
        launchPage();
        tester.assertRenderedPage(DesignPage.class);

        tester.assertListView("form:sizeRadioGroup:sizeListView", pizzaSizes);
        tester.assertListView("form:toppingGroup:toppingListView", toppings);
        tester.assertModelValue("form:sizeRadioGroup", pizzaSizes.get(0));
        tester.assertModelValue("form:quantityDropDown", 1);
        tester.assertModelValue("form:totalPrice", 800);
    }

    /**
     * Test the price for change in pizza size.
     */
    @Test
    public void testPriceChangeForSizeChange() {
        launchPage();
        tester.assertRenderedPage(DesignPage.class);

        final FormTester formTester = tester.newFormTester("form", false);
        formTester.select("sizeRadioGroup", 2);
        formTester.submit();

        tester.assertModelValue("form:totalPrice", 1500);
    }

    /**
     * Test the price for change in pizza toppings.
     */
    @Test
    public void testPriceChangeForToppingChange() {
        launchPage();
        tester.assertRenderedPage(DesignPage.class);

        final FormTester formTester = tester.newFormTester("form", false);
        formTester.select("toppingGroup", 1);
        formTester.select("toppingGroup", 2);
        formTester.submit();
        tester.assertModelValue("form:totalPrice", 1250);
    }

    /**
     * Test the price for change in quantity.
     */
    @Test
    public void testPriceChangeForQuantityChange() {
        launchPage();
        tester.assertRenderedPage(DesignPage.class);

        final FormTester formTester = tester.newFormTester("form", false);
        formTester.select("quantityDropDown", 1);
        formTester.submit();
        tester.assertModelValue("form:totalPrice", 1600);
    }

    private void launchPage() {
        tester.startPage(DesignPage.class);
    }

    private PizzaSizeDao createPizzaSizeDaoMock() {
        final PizzaSizeDao mock = Mockito.mock(PizzaSizeDao.class);
        Mockito.when(mock.getPizzaSizes()).thenReturn(pizzaSizes);
        return mock;
    }

    private ToppingDao createToppingDaoMock() {
        final ToppingDao mock = Mockito.mock(ToppingDao.class);
        Mockito.when(mock.getToppings()).thenReturn(toppings);
        return mock;
    }
}
