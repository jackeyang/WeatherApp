package com.hust.jackeyang.weatherapp.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hust.jackeyang.weatherapp.R;
import com.hust.jackeyang.weatherapp.db.WeatherDB;
import com.hust.jackeyang.weatherapp.mode.Now;
import com.hust.jackeyang.weatherapp.mode.Weather;
import com.hust.jackeyang.weatherapp.util.HttpCallbackListener;
import com.hust.jackeyang.weatherapp.util.HttpUtil;
import com.hust.jackeyang.weatherapp.util.Utility;

/**
 * Created by jackeyang on 2016/6/21.
 */
public class WeatherActivity extends Activity {

    private String city = null;
    private String code = null;
    WeatherDB weatherDB = null;
    Weather weatherInfo = null;
    ImageView imageView = null;
    TextView txt_desc = null;
    TextView txt_publish_time = null;
    TextView txt_city_title = null;
    TextView txt_temperature = null;
    TextView txt_wind = null;
    Button btn_refresh = null;
    private ProgressDialog progressDialog = null;

    public static void actionStart(Context context, String city) {
        Intent intent = new Intent(context, WeatherActivity.class);
        intent.putExtra("city", city);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_weather);
        this.city = getIntent().getStringExtra("city");
        init();
        queryWeather(city);
    }

    private void init() {
        this.weatherDB = WeatherDB.getInstance(getApplicationContext());
        this.imageView = (ImageView) findViewById(R.id.imageView);
        this.txt_desc = (TextView) findViewById(R.id.txt_desc);
        this.txt_publish_time = (TextView) findViewById(R.id.publish_time);
        this.txt_city_title = (TextView) findViewById(R.id.txt_title);
        this.txt_temperature = (TextView) findViewById(R.id.temperature);
        this.txt_wind = (TextView) findViewById(R.id.wind);
        this.btn_refresh = (Button) findViewById(R.id.refresh);
        txt_city_title.setText(city);
    }

    private void showWeatherInfo(Weather weatherInfo) {
        String updateTime = weatherInfo.getBasic().getUpdate().loc;
        txt_publish_time.setText(updateTime);
        String temperature = weatherInfo.getNow().getTmp();
        txt_temperature.setText("现在气温：" + temperature + "度");
        String windInfo = getWindInfo(weatherInfo.getNow().getWind());
        txt_wind.setText(windInfo);
        String desc = weatherInfo.getNow().getCond().txt;
        txt_desc.setText(desc);
    }

    /**
     * 请求天气数据
     * @param city
     */
    private void queryWeather(String city) {
        if (!TextUtils.isEmpty(city)) {
            showProgressDialog();
            HttpUtil.sendHttpRequest(assembleAdder(city), new HttpCallbackListener() {
                @Override
                public void onFinish(String response) {
                    weatherInfo = Utility.handleWeatherInfoResponse(response);
                    //获取天气代码
                    code = weatherInfo.getNow().getCond().code;
                    //根据天气代码加载天气图标
                    loadWeatherIcon(imageView);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            closeProgressDialog();
                            showWeatherInfo(weatherInfo);
                        }
                    });

                }

                @Override
                public void onError(Exception e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            closeProgressDialog();
                            Toast.makeText(WeatherActivity.this, "加载失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });


        }

    }

    private void loadWeatherIcon(ImageView imageView) {
        String icon_url = weatherDB.loadIconURL(code);
        if (!TextUtils.isEmpty(icon_url)) {
            HttpUtil.downLoadIcon(icon_url,imageView);
        } else {
            queryWeatherIconFromServer();
        }
    }

    /**
     * 完成天气代码的下载和保存
     */
    private void queryWeatherIconFromServer() {
        HttpUtil.sendHttpRequest(Utility.WEATHER_CODE_ADDR, new HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                boolean result = Utility.handleWeatherCodeResponse(weatherDB,response);
                if (result) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            loadWeatherIcon(imageView);
                        }
                    });
                }
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });
    }

    private String assembleAdder(String city) {
        String address;
        String city_ID = weatherDB.loadID(city);
        address = Utility.WEATHER_ADDR + city_ID + "&key=" + Utility.KEY;
        return address;
    }

    private String getWindInfo(Now.Wind wind) {
        StringBuilder sb = new StringBuilder("");
        sb.append(wind.dir).append(" ").append(wind.sc).append("级").append("\n").append("风速:")
                .append(wind.spd);
        return sb.toString();
    }

    private void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage(getString(R.string.loading));
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.show();
    }

    private void closeProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.cancel();
        }
    }

}
