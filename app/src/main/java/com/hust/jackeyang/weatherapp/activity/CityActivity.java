package com.hust.jackeyang.weatherapp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.hust.jackeyang.weatherapp.R;
import com.hust.jackeyang.weatherapp.db.WeatherDB;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jackeyang on 2016/6/20.
 */
public class CityActivity extends Activity {

    public static void actionStart(Context context, String province) {
        Intent intent = new Intent(context, CityActivity.class);
        intent.putExtra("province", province);
        context.startActivity(intent);
    }

    String province = null;
    TextView title = null;
    ListView list = null;
    List<String> city_list_data = new ArrayList<>();
    List<String> cityList = null;
    ArrayAdapter<String> adapter = null;
    WeatherDB weatherDB = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.area_layout);
        this.weatherDB = WeatherDB.getInstance(getApplicationContext());
        this.title = (TextView) findViewById(R.id.txt_title);
        this.list = (ListView) findViewById(R.id.list_view);
        province = getIntent().getStringExtra("province");
        this.title.setText(province);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, city_list_data);
        this.list.setAdapter(adapter);
        this.list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                WeatherActivity.actionStart(CityActivity.this,city_list_data.get(i));
            }
        });
        queryCities();
    }

    private void queryCities() {
        cityList = weatherDB.loadCities(province);
        if (cityList.size() > 0) {
            city_list_data.clear();
            for (String city : cityList) {
                city_list_data.add(city);
            }
            adapter.notifyDataSetChanged();
        }
    }

}
