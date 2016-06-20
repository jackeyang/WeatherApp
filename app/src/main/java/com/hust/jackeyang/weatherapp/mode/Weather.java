package com.hust.jackeyang.weatherapp.mode;

/**
 * Created by jackeyang on 2016/6/20.
 * 详细状态说明见网页http://www.heweather.com/documents/api
 */
public class Weather {
    //根据具体的需要增加数据
    public Basic basic;
    //public AQI aqi;
    //public Suggestion suggestion;
    //public Alarms alarms;
    public Now now;
    //public DailyForcast dailyForcast;
    //public HourlyForcast hourlyForcast;
    public Status status;

    public Weather() {
    }

    /**构造函数（更具需要增加内容）
     * @param basic
     * @param now
     */
    public Weather(Basic basic, Now now) {
        this.basic = basic;
        this.now = now;
    }

    public Basic getBasic() {
        return basic;
    }

    public void setBasic(Basic basic) {
        this.basic = basic;
    }

    public Now getNow() {
        return now;
    }

    public void setNow(Now now) {
        this.now = now;
    }

    //状态代码
    public class  Status{
        public static final String OK = "ok";
        public static final String INVAID_KEY = "invalid key";
        public static final String UNKNOWN_CITY = "unknown city";
        public static final String NO_MORE_REQUESTS = "no more requests";
        public static final String ANR = "anr";
        public static final String PERMISSION_DENIED = "permission denied";

    }
}


