
package org.tui.presentation;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.tui.presentation.pages.DesignPage;

/**
 * Definition of the pizza inn web application.
 *
 * @author: Srikanth NT
 */
public class WicketApplication extends WebApplication {

    /**
     * Default Entry page for the web application.
     *
     * @return the index page of the website.
     */
    @Override
    public Class<? extends Page> getHomePage() {
        return DesignPage.class;
    }

    /**
     * Any wicket servlet/filter initialization goes here.
     */
    @Override
    public void init() {
        super.init();
        onInitialize();
    }

    /**
     * Initialize the spring components injector. Use @SpringBean to access any bean from wicket pages.
     */
    protected void onInitialize() {
        getComponentInstantiationListeners().add(new SpringComponentInjector(this));
    }
}
