package com.hust.jackeyang.weatherapp.mode;

/**
 * Created by jackeyang on 2016/6/20.
 */
public class Country {
    private String countryName;
    private String ID;

    public Country(String countryName, String ID) {
        this.countryName = countryName;
        this.ID = ID;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
