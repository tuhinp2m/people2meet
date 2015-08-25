package com.people2meet;

/**
 * Created by tuhinsengupta on 25/08/15.
 */
public class CountryDetail {

    private String iso;
    private String isd;
    private String name;

    public CountryDetail(String iso, String isd, String name) {
        this.iso = iso;
        this.name = name;
        this.isd = isd;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getIso() {
        return iso;
    }

    public String getIsd() {
        return isd;
    }

    public String getName() {
        return name;
    }
}
