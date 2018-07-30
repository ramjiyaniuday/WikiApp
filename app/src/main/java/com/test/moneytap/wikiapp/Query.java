package com.test.moneytap.wikiapp;

import java.io.Serializable;
import java.util.List;

/**
 * Created by USER on 30-07-2018.
 */

public class Query implements Serializable{
    private List<Redirect> redirects = null;
    private List<Page> pages = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public Query() {
    }

    /**
     *
     * @param pages
     * @param redirects
     */
    public Query(List<Redirect> redirects, List<Page> pages) {
        super();
        this.redirects = redirects;
        this.pages = pages;
    }

    public List<Redirect> getRedirects() {
        return redirects;
    }

    public void setRedirects(List<Redirect> redirects) {
        this.redirects = redirects;
    }

    public List<Page> getPages() {
        return pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }

}
