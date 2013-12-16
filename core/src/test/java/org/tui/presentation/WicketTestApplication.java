package org.tui.presentation;

import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.spring.test.ApplicationContextMock;

/**
 * Wicket application for test classes.
 *
 * @author: Srikanth NT
 */
public class WicketTestApplication extends WicketApplication {

    private ApplicationContextMock ctx = new ApplicationContextMock();

    /**
     * Get the current application context.
     *
     * @return current application context.
     */
    public ApplicationContextMock getCtx() {
        return ctx;
    }

    @Override
    protected void onInitialize() {
        getComponentInstantiationListeners().add(new SpringComponentInjector(this, ctx));
    }
}

