package com.hust.jackeyang.weatherapp.mode;

/**
 * Created by jackeyang on 2016/6/20.
 */
//城市基本信息
public class Basic{
    public String city;
    public String id;
    public String cnty;
    public String lat;
    public String lon;
    public Update update;

    public Basic() {
    }

    public Basic(String city, String id, String cnty, String lat, String lon, Update update) {
        this.city = city;
        this.id = id;
        this.cnty = cnty;
        this.lat = lat;
        this.lon = lon;
        this.update = update;

    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCnty() {
        return cnty;
    }

    public void setCnty(String cnty) {
        this.cnty = cnty;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public Update getUpdate() {
        return update;
    }

    public void setUpdate(Update update) {
        this.update = update;
    }



    public static class Update{
        public String loc;
        public String utc;

        public Update(String loc, String utc) {
            this.loc = loc;
            this.utc = utc;
        }
    }
}
