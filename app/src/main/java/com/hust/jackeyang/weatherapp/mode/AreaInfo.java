package com.hust.jackeyang.weatherapp.mode;


/**
 * Created by jackeyang on 2016/6/20.
 */
public class AreaInfo {
    private String provinceName;
    private String cityName;
    //    private String countryName;
    private String city_id;
    private Location location;

    public AreaInfo(String provinceName,String cityName/*, String countryName*/, String city_id,Location location) {
        this.cityName = cityName;
//        this.countryName = countryName;
        this.city_id = city_id;
        this.provinceName = provinceName;
        this.location = location;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

  /*  public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }*/

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }




    public static class Location{
        private float latitude;
        private float longitude;

        public Location(float latitude, float longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public float getLatitude() {
            return latitude;
        }

        public void setLatitude(float latitude) {
            this.latitude = latitude;
        }

        public float getLongitude() {
            return longitude;
        }

        public void setLongitude(float longitude) {
            this.longitude = longitude;
        }
    }

}

