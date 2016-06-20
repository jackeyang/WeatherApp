package com.hust.jackeyang.weatherapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jackeyang on 2016/6/20.
 */
public class WeatherDBOpenHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "AreaInfo";

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "province text,"
            + "city text,"
            + "latitude real,"
            + "longitude real,"
            + "city_id text)";


    public WeatherDBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int
            version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
