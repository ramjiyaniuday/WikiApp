package com.test.moneytap.wikiapp;

/**
 * Created by USER on 30-07-2018.
 */

public class Continue {

    private Integer gpsoffset;
    private String _continue;

    /**
     * No args constructor for use in serialization
     *
     */
    public Continue() {
    }

    /**
     *
     * @param gpsoffset
     * @param _continue
     */
    public Continue(Integer gpsoffset, String _continue) {
        super();
        this.gpsoffset = gpsoffset;
        this._continue = _continue;
    }

    public Integer getGpsoffset() {
        return gpsoffset;
    }

    public void setGpsoffset(Integer gpsoffset) {
        this.gpsoffset = gpsoffset;
    }

    public String getContinue() {
        return _continue;
    }

    public void setContinue(String _continue) {
        this._continue = _continue;
    }

}
