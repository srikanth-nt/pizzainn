package org.tui.presentation.panel;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * User defined label to display the price with currency symbol.
 * The price is passed in pence and this component shows it in pounds.
 *
 * @author: Srikanth NT
 */
public class MoneyLabel extends Label {

    private final Currency CURRENCY = Currency.getInstance("GBP");

    /**
     * Constructor.
     *
     * @param id    component id.
     * @param money pence in a model.
     */
    public MoneyLabel(final String id, final IModel<Integer> money) {
        super(id, money);
    }

    @Override
    public void onComponentTagBody(final MarkupStream markupStream, final ComponentTag openTag) {
        final Object modelObject = getDefaultModelObject();
        if (modelObject == null) {
            return;
        }

        BigDecimal decimalMoney = BigDecimal.valueOf((Integer) modelObject);
        final int currencyFractionDigits = CURRENCY.getDefaultFractionDigits();
        decimalMoney = decimalMoney.movePointLeft(currencyFractionDigits).setScale(currencyFractionDigits);

        final String display = CURRENCY.getSymbol() + decimalMoney.toPlainString();
        replaceComponentTagBody(markupStream, openTag, display);
    }
}
