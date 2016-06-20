package com.hust.jackeyang.weatherapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.hust.jackeyang.weatherapp.mode.AreaInfo;
import com.hust.jackeyang.weatherapp.mode.Country;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jackeyang on 2016/6/20.
 */
public class WeatherDB {
    private static final String DB_NAME = "weather.db";

    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = WeatherDBOpenHelper.TABLE_NAME;

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
            cv.put("country", areaInfo.getCountryName());
            cv.put("ID", areaInfo.getID());
            db.insert(TABLE_NAME, null, cv);
        }
    }


    //开启数据库事务进行提交
    public void saveDataByTransaction(List<AreaInfo> datas) {
        for(int i=0,j=datas.size();i<j;i++) {

            db.beginTransaction();
        }
    }

    public List<String> loadProvinces() {
        ArrayList<String> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("select distinct province from " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        return list;
    }

    public List<String> loadCities(String province) {
        ArrayList<String> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("select distinct city from " + TABLE_NAME + " where province = " +
                "?", new String[]{province});
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        return list;
    }

    public List<String> loadCountries(String city) {
        ArrayList<String> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("select distinct country from " + TABLE_NAME + " where city = " +
                "?", new String[]{city});
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        return list;
    }

    public List<Country> loadCountries2(String city) {
        ArrayList<Country> countries = new ArrayList<>();
        Cursor cursor = db.rawQuery("select country ID from " + TABLE_NAME + " where city = ?",
                new String[]{city});
        if (cursor.moveToFirst()) {
            do {
                String countryName = cursor.getString(cursor.getColumnIndex("country"));
                String ID = cursor.getString(cursor.getColumnIndex("ID"));
                Country country = new Country(countryName, ID);
                countries.add(country);
            } while (cursor.moveToNext());
        }
        return countries;
    }

    public String loadID(String country) {
        String ID = "";
        Cursor cursor = db.rawQuery("select ID from " + TABLE_NAME + " where country = ?", new
                String[]{country});
        if (cursor.moveToFirst()) {
            ID = cursor.getString(0);
        }
        return ID;
    }

    public void loadProvinces(List<String> list) {

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
