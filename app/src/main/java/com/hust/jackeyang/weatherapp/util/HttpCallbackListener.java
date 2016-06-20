package com.hust.jackeyang.weatherapp.util;

/**
 * Created by jackeyang on 2016/6/20.
 */
public interface HttpCallbackListener {
    public void onFinish(String response);

    public void onError(Exception e);
}
