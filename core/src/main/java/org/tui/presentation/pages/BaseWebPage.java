package org.tui.presentation.pages;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;

/**
 * Abstract class for all pizza inn wicket pages.
 *
 * @author: Srikanth NT
 */
public abstract class BaseWebPage extends WebPage {

    public BaseWebPage(final IModel<?> model) {
        super(model);
    }

    /**
     * Do things here that might need to be modified by child pages.
     * Give the child pages a chance to set themselves up.
     *
     * @see org.apache.wicket.Page#onBeforeRender()
     */
    @Override
    protected void onBeforeRender() {
        setPageTitle();
        super.onBeforeRender();
    }

    /**
     * Set the page title.
     */
    protected void setPageTitle() {
        addOrReplace(new Label("pageTitle", getPageTitle()).setEscapeModelStrings(false));
    }

    /**
     * Get the current page title. Based on the value of 'page.title' in properties file, the page's title is displayed.
     *
     * @return page title of this page.
     */
    protected String getPageTitle() {
        return getLocalizer().getString("page.title", this);
    }

}
