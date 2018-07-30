package com.test.moneytap.wikiapp;

import java.io.Serializable;
import java.util.List;

/**
 * Created by USER on 30-07-2018.
 */

public class Terms implements Serializable{
    private List<String> description = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public Terms() {
    }

    /**
     *
     * @param description
     */
    public Terms(List<String> description) {
        super();
        this.description = description;
    }

    public List<String> getDescription() {
        return description;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }

}
