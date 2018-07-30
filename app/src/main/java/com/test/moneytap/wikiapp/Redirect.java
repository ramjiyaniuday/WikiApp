package com.test.moneytap.wikiapp;

/**
 * Created by USER on 30-07-2018.
 */

public class Redirect {

    private Integer index;
    private String from;
    private String to;

    /**
     * No args constructor for use in serialization
     *
     */
    public Redirect() {
    }

    /**
     *
     * @param to
     * @param index
     * @param from
     */
    public Redirect(Integer index, String from, String to) {
        super();
        this.index = index;
        this.from = from;
        this.to = to;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
