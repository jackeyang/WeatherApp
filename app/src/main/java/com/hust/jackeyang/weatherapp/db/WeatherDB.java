package com.hust.jackeyang.weatherapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.hust.jackeyang.weatherapp.mode.AreaInfo;
import com.hust.jackeyang.weatherapp.mode.Code;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jackeyang on 2016/6/20.
 */
public class WeatherDB {
    private static final String DB_NAME = "weather.db";

    private static final int DB_VERSION = 2;

    /**
     * 地区信息表
     */
    private static final String TABLE_AREAINFO = WeatherDBOpenHelper.TABLE_NAME;

    /**
     * 天气代码表
     */
    private static final String TABLE_WEATHER_CODE = WeatherDBOpenHelper.TABLE_NAME_WEATHER_CODE;

    private static WeatherDB weatherDB = null;

    private SQLiteDatabase db;

    private WeatherDB(Context context) {
        WeatherDBOpenHelper openHelper = new WeatherDBOpenHelper(context,DB_NAME,null,DB_VERSION);
        db = openHelper.getWritableDatabase();
    }

    public static WeatherDB getInstance(Context context) {
        if (weatherDB == null) {
            synchronized (WeatherDB.class) {
                if (weatherDB == null) {
                    weatherDB = new WeatherDB(context);
                }
            }
        }
        return weatherDB;
    }

    public void saveData(AreaInfo areaInfo) {
        if (areaInfo != null) {
            ContentValues cv = new ContentValues();
            cv.put("province", areaInfo.getProvinceName());
            cv.put("city", areaInfo.getCityName());
            cv.put("longitude", areaInfo.getLocation().getLongitude());
            cv.put("latitude",areaInfo.getLocation().getLatitude());
            cv.put("city_id", areaInfo.getCity_id());
            db.insert(TABLE_AREAINFO, null, cv);
        }
    }

    public void savaCodeData(Code code) {
        if (code != null) {
            ContentValues cv = new ContentValues();
            cv.put("code", code.getCode());
            cv.put("txt", code.getTxt());
            cv.put("txt_en", code.getTxt_en());
            cv.put("icon", code.getIcon());
            db.insert(TABLE_WEATHER_CODE, null, cv);
        }
    }



    public List<String> loadProvinces() {
        ArrayList<String> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("select distinct province from " + TABLE_AREAINFO, null);
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        return list;
    }

    public List<String> loadCities(String province) {
        ArrayList<String> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("select distinct city from " + TABLE_AREAINFO + " where province = " +
                "?", new String[]{province});
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        return list;
    }


    /**
     * 加载城市的ID
     * @param city
     * @return
     */
    public String loadID(String city) {
        String city_id = "";
        Cursor cursor = db.rawQuery("select city_id from " + TABLE_AREAINFO + " where city = ?", new
                String[]{city});
        if (cursor.moveToFirst()) {
            city_id = cursor.getString(0);
        }
        return city_id;
    }


    public String loadIconURL(String code) {
        String url = "";
        Cursor cursor = db.rawQuery("select icon from " + TABLE_WEATHER_CODE + " where code = ?",
                new String[]{code});
        if (cursor.moveToFirst()) {
            url = cursor.getString(0);
        }
        return url;
    }

    //改进为异步方式访问数据库
    class LoadProvince extends AsyncTask<Void, Void, List<String>>{

        @Override
        protected List<String> doInBackground(Void... voids) {
            return null;
        }

        @Override
        protected void onPostExecute(List<String> strings) {
            super.onPostExecute(strings);
        }
    }

}
