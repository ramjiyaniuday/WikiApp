package com.test.moneytap.wikiapp;

import java.io.Serializable;

/**
 * Created by USER on 30-07-2018.
 */

public class Thumbnail implements Serializable{
    private String source;
    private Integer width;
    private Integer height;

    /**
     * No args constructor for use in serialization
     *
     */
    public Thumbnail() {
    }

    /**
     *
     * @param height
     * @param source
     * @param width
     */
    public Thumbnail(String source, Integer width, Integer height) {
        super();
        this.source = source;
        this.width = width;
        this.height = height;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

}
