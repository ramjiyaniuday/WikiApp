package com.test.moneytap.wikiapp;

import java.io.Serializable;

/**
 * Created by USER on 30-07-2018.
 */


public class WikiResponse implements Serializable {

    private Boolean batchcomplete;
    private Continue _continue;
    private Query query;

    /**
     * No args constructor for use in serialization
     *
     */
    public WikiResponse() {
    }

    /**
     *
     * @param query
     * @param batchcomplete
     * @param _continue
     */
    public WikiResponse(Boolean batchcomplete, Continue _continue, Query query) {
        super();
        this.batchcomplete = batchcomplete;
        this._continue = _continue;
        this.query = query;
    }

    public Boolean getBatchcomplete() {
        return batchcomplete;
    }

    public void setBatchcomplete(Boolean batchcomplete) {
        this.batchcomplete = batchcomplete;
    }

    public Continue getContinue() {
        return _continue;
    }

    public void setContinue(Continue _continue) {
        this._continue = _continue;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

}