package com.hust.jackeyang.weatherapp.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hust.jackeyang.weatherapp.R;
import com.hust.jackeyang.weatherapp.db.WeatherDB;
import com.hust.jackeyang.weatherapp.util.HttpCallbackListener;
import com.hust.jackeyang.weatherapp.util.HttpUtil;
import com.hust.jackeyang.weatherapp.util.Utility;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jackeyang on 2016/6/20.
 */
public class ProvinceActivity extends Activity {

    private ProgressDialog progressDialog = null;
    private TextView title = null;
    private ListView listView = null;
    private ArrayAdapter<String> adapter = null;
    private WeatherDB weatherDB;
    private List<String> dataList = new ArrayList<>();
    private List<String> provinceList = null;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.area_layout);
        View empty_view = LayoutInflater.from(this).inflate(R.layout.listview_empty_view, null,
                false);
        listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(empty_view);
        title = (TextView) findViewById(R.id.title_text);
        weatherDB = WeatherDB.getInstance(getApplicationContext());

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String province = dataList.get(i);
                CityActivity.actionStart(ProvinceActivity.this,province);
            }
        });

        queryProvinces();
    }

    private void queryProvinces() {
        provinceList = weatherDB.loadProvinces();
        if (provinceList.size() > 0) {
            dataList.clear();
            for (String province : provinceList) {
                dataList.add(province);
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            title.setText(getString(R.string.main_title));
        }else{
            queryFromServer(Utility.AREAINFO_ADDR);
        }
    }

    private void queryFromServer(String address) {
        showProgressDialog();
        HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                boolean result = Utility.handleAreaInfoResponse(weatherDB,response);
                if (result) {
                    // 通过runOnUiThread()方法回到主线程处理逻辑
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            closeProgressDialog();
                            //重新查询省份信息
                            queryProvinces();
                        }
                    });
                }
            }

            @Override
            public void onError(Exception e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        closeProgressDialog();
                        Toast.makeText(ProvinceActivity.this,"加载失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
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
