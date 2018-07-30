package com.test.moneytap.wikiapp;

import java.io.Serializable;

/**
 * Created by USER on 30-07-2018.
 */

public class Page implements Serializable {

    private Integer pageid;
    private Integer ns;
    private String title;
    private Integer index;
    private Thumbnail thumbnail;
    private Terms terms;

    /**
     * No args constructor for use in serialization
     *
     */
    public Page() {
    }

    /**
     *
     * @param index
     * @param title
     * @param ns
     * @param thumbnail
     * @param terms
     * @param pageid
     */
    public Page(Integer pageid, Integer ns, String title, Integer index, Thumbnail thumbnail, Terms terms) {
        super();
        this.pageid = pageid;
        this.ns = ns;
        this.title = title;
        this.index = index;
        this.thumbnail = thumbnail;
        this.terms = terms;
    }

    public Integer getPageid() {
        return pageid;
    }

    public void setPageid(Integer pageid) {
        this.pageid = pageid;
    }

    public Integer getNs() {
        return ns;
    }

    public void setNs(Integer ns) {
        this.ns = ns;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Terms getTerms() {
        return terms;
    }

    public void setTerms(Terms terms) {
        this.terms = terms;
    }
}
