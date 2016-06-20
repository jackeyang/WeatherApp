package com.hust.jackeyang.weatherapp.util;

import com.hust.jackeyang.weatherapp.db.WeatherDB;
import com.hust.jackeyang.weatherapp.mode.AreaInfo;
import com.hust.jackeyang.weatherapp.mode.Basic;
import com.hust.jackeyang.weatherapp.mode.Now;
import com.hust.jackeyang.weatherapp.mode.Weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jackeyang on 2016/6/20.
 */
public class Utility {

    public static final String KEY = "e11db3d0deea48aeb0dc841c53393fd4";
    public static final String AREAINFO_ADDR = "https://api.heweather" +
            ".com/x3/citylist?search=allchina&key=" + KEY;

    private static final String SERVER_CITYINFO_KEY = "city_info";

    private static final String SERVER_WEATHER_KEY = "HeWeather data service 3.0";

    public static boolean handleAreaInfoResponse(WeatherDB db, String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            String status = jsonObject.getString("status");
            if (status.equals("ok")) {
                JSONArray jsonArray = jsonObject.getJSONArray(SERVER_CITYINFO_KEY);
                for (int i = 0, j = jsonArray.length(); i < j; i++) {
                    JSONObject object = (JSONObject) jsonArray.get(i);
                    String provinceName = object.getString("prov");
                    String city = object.getString("city");
                    String city_id = object.getString("id");
                    float longitude = Float.parseFloat(object.getString("lon"));
                    float latitude = Float.parseFloat(object.getString("lat"));
                    AreaInfo areaInfo = new AreaInfo(provinceName, city, city_id, new AreaInfo.Location
                            (latitude, longitude));
                    //数据存入数据库内
                    db.saveData(areaInfo);
                }
                return true;
            } else {
                //由status查看原因
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Weather parseWeather(String response) {
        Weather weather = new Weather();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray(SERVER_WEATHER_KEY);
            JSONObject object = (JSONObject) jsonArray.get(0);
            String status = object.getString("status");
            if (status.equals("ok")) {
                JSONObject basicObject = object.getJSONObject("basic");
                JSONObject updateObject = basicObject.getJSONObject("update");
                Basic basic = new Basic();
                basic.setCity(basicObject.getString("city"));
                basic.setId(basicObject.getString("id"));
                basic.setCnty(basicObject.getString("cnty"));
                basic.setLat(basicObject.getString("lat"));
                basic.setLon(basicObject.getString("lon"));
                Basic.Update update = new Basic.Update(updateObject.getString("loc"),
                        updateObject.getString("utc"));
                basic.setUpdate(update);

                JSONObject nowObject = object.getJSONObject("now");
                JSONObject condObject = nowObject.getJSONObject("cond");
                JSONObject windObject = nowObject.getJSONObject("wind");
                Now now = new Now();
                now.setTmp(nowObject.getString("tmp"));
                now.setFl(nowObject.getString("f1"));
                Now.Wind wind = new Now.Wind(windObject.getString("deg"), windObject.getString
                        ("dir"), windObject.getString("sc"), windObject.getString("spd"));
                now.setWind(wind);
                Now.Cond cond = new Now.Cond(condObject.getString("code"), condObject.getString
                        ("txt"));
                now.setCond(cond);
                now.setPcpn(nowObject.getString("pcpn"));
                now.setHum(nowObject.getString("hum"));
                now.setPres(nowObject.getString("pres"));
                now.setVis(nowObject.getString("vis"));

                //根据需要解析出数据
                weather.setBasic(basic);
                weather.setNow(now);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return weather;
    }

}
