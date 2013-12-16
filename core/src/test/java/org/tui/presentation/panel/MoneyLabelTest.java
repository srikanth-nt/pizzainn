package org.tui.presentation.panel;

import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.tui.presentation.WicketTestApplication;

/**
 * Test class for Money label component {@link MoneyLabel}
 *
 * @author : Srikanth NT
 */
public class MoneyLabelTest {

    private WicketTester tester;

    @Before
    public void setUp() {
        final WicketTestApplication application = new WicketTestApplication();
        tester = new WicketTester(application);
    }

    @Test
    public void testNull() {
        tester.startComponentInPage(new MoneyLabel("labelId", null));
        tester.assertModelValue("labelId", null);
    }

    @Test
    public void testZero() {
        tester.startComponentInPage(new MoneyLabel("labelId", new Model<Integer>(0)));
        tester.assertModelValue("labelId", 0);
        tester.assertContains("£0.00");
    }

    @Test
    public void testDecimalNumber() {
        tester.startComponentInPage(new MoneyLabel("labelId", new Model<Integer>(1000)));
        tester.assertModelValue("labelId", 1000);
        tester.assertContains("£10");
    }

    @Test
    public void testWholeNumber() {
        tester.startComponentInPage(new MoneyLabel("labelId", new Model<Integer>(10)));
        tester.assertModelValue("labelId", 10);
        tester.assertContains("£0.10");
    }
}
