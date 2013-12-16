package org.tui.presentation.pages;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormChoiceComponentUpdatingBehavior;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Check;
import org.apache.wicket.markup.html.form.CheckGroup;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.tui.dao.PizzaSizeDao;
import org.tui.dao.ToppingDao;
import org.tui.model.Pizza;
import org.tui.model.PizzaSize;
import org.tui.model.Topping;
import org.tui.presentation.panel.MoneyLabel;
import org.tui.service.PriceCalculatorService;

import java.util.Arrays;
import java.util.List;

/**
 * Custom pizza designing page.
 *
 * @author: Srikanth NT
 */
public final class DesignPage extends BaseWebPage {

    @SpringBean
    private PizzaSizeDao pizzaSizeDao;

    @SpringBean
    private ToppingDao toppingDao;

    @SpringBean
    private PriceCalculatorService priceCalculatorService;

    /**
     * Only up to 5 pizzas are allowed for ordering.
     */
    private static final List<Integer> QUANTITIES = Arrays.asList(1, 2, 3, 4, 5);

    public DesignPage() {
        super(new Model<Pizza>(new Pizza()));

        final Form form = new Form("form");
        add(form);

        displayPizzaSize(form);
        displayToppings(form);
        displayQuantity(form);
        displayTotalPrice(form);
    }

    /**
     * Displays the quantity dropdown control.
     *
     * @param form parent component
     */
    private void displayQuantity(final Form form) {
        final DropDownChoice<Integer> quantityDropDown = new DropDownChoice<Integer>("quantityDropDown",
                new PropertyModel<Integer>(DesignPage.this.getDefaultModel(), "quantity"),
                QUANTITIES);
        form.add(quantityDropDown);

        quantityDropDown.add(new AjaxFormComponentUpdatingBehavior("onchange") {
            @Override
            protected void onUpdate(final AjaxRequestTarget target) {
                if (target != null) {
                    target.add(DesignPage.this.get("form:totalPrice"));
                }
            }
        });
    }

    /**
     * Displays the total price control. The price changes based on the values present in the page's default model.
     *
     * @param form parent component
     */
    private void displayTotalPrice(final Form form) {
        final LoadableDetachableModel<Integer> priceModel = new LoadableDetachableModel<Integer>() {
            @Override
            protected Integer load() {
                final Pizza pizza = (Pizza) DesignPage.this.getDefaultModelObject();
                return priceCalculatorService.getPrice(pizza);
            }
        };

        final MoneyLabel totalPrice = new MoneyLabel("totalPrice", priceModel);
        totalPrice.setOutputMarkupPlaceholderTag(true);
        form.add(totalPrice);
    }

    /**
     * Displays the pizza toppings control.
     *
     * @param form parent component
     */
    private void displayToppings(final Form form) {
        final LoadableDetachableModel<List<Topping>> toppingsModel = new LoadableDetachableModel<List<Topping>>() {
            @Override
            protected List<Topping> load() {
                return toppingDao.getToppings();
            }
        };

        final CheckGroup<Topping> toppingGroup = new CheckGroup<Topping>("toppingGroup",
                new PropertyModel<List<Topping>>(getDefaultModel(), "toppings"));
        form.add(toppingGroup);

        toppingGroup.add(new AjaxFormChoiceComponentUpdatingBehavior() {
            @Override
            protected void onUpdate(final AjaxRequestTarget target) {
                if (target != null) {
                    target.add(DesignPage.this.get("form:totalPrice"));
                }
            }
        });

        final ListView<Topping> toppingListView = new ListView<Topping>("toppingListView", toppingsModel) {
            @Override
            protected void populateItem(final ListItem<Topping> item) {
                item.add(new Check<Topping>("toppingOption", item.getModel()));
                item.add(new Label("name", new PropertyModel(item.getModel(), "name")));
                item.add(new MoneyLabel("price", new PropertyModel<Integer>(item.getModel(), "price")));
            }
        };

        toppingGroup.add(toppingListView);
    }

    /**
     * Displays the pizza size control. By default the first item is selected.
     *
     * @param form parent component
     */
    private void displayPizzaSize(final Form form) {
        final LoadableDetachableModel<List<PizzaSize>> pizzaSizeModel = new LoadableDetachableModel<List<PizzaSize>>() {
            @Override
            protected List<PizzaSize> load() {
                return pizzaSizeDao.getPizzaSizes();
            }
        };

        final ListView<PizzaSize> sizeListView = new ListView<PizzaSize>("sizeListView", pizzaSizeModel) {
            @Override
            protected void populateItem(final ListItem<PizzaSize> item) {
                final Radio<PizzaSize> sizeOption = new Radio<PizzaSize>("sizeOption", item.getModel());
                item.add(sizeOption);

                item.add(new Label("sizeName", new PropertyModel<String>(item.getModel(), "name")));
            }
        };

        final RadioGroup<PizzaSize> sizeRadioGroup = new RadioGroup<PizzaSize>("sizeRadioGroup",
                new PropertyModel<PizzaSize>(getDefaultModel(), "size")) {
            @Override
            protected void onConfigure() {
                sizeListView.configure();

                // pre selecting the first item as default value.
                if (getModelObject() == null && pizzaSizeModel.getObject() != null) {
                    final List<PizzaSize> sizes = pizzaSizeModel.getObject();
                    if (!sizes.isEmpty()) {
                        setModelObject(sizes.get(0));
                    }
                }
            }
        };
        sizeRadioGroup.add(sizeListView);
        form.add(sizeRadioGroup);

        sizeRadioGroup.add(new AjaxFormChoiceComponentUpdatingBehavior() {
            @Override
            protected void onUpdate(final AjaxRequestTarget target) {
                if (target != null) {
                    target.add(DesignPage.this.get("form:totalPrice"));
                }
            }
        });
    }

}
