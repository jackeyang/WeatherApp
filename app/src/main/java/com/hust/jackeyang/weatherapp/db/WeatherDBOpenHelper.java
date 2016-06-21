package com.hust.jackeyang.weatherapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jackeyang on 2016/6/20.
 */
public class WeatherDBOpenHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "AreaInfo";
    public static final String TABLE_NAME_WEATHER_CODE = "weather_code";

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "province text,"
            + "city text,"
            + "latitude real,"
            + "longitude real,"
            + "city_id text)";

    private static final String CREATE_TABLE_WEATHER_CODE = "CREATE TABLE " + TABLE_NAME_WEATHER_CODE + "("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "code text,"
            + "txt text,"
            + "txt_en text,"
            + "icon text)";


    public WeatherDBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int
            version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        //数据库版本2中新增表
        db.execSQL(CREATE_TABLE_WEATHER_CODE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion) {
            case 1:
                db.execSQL(CREATE_TABLE_WEATHER_CODE);
                default:
        }
    }
}
