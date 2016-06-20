package com.hust.jackeyang.weatherapp.mode;

import java.util.List;

/**
 * Created by jackeyang on 2016/6/20.
 */
public class AreaInfo {

    public AreaInfo(String cityName, String countryName, String ID, String provinceName) {
        this.cityName = cityName;
        this.countryName = countryName;
        this.ID = ID;
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
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

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    private String provinceName;
    private String cityName;
    private String countryName;
    private String ID;



}
