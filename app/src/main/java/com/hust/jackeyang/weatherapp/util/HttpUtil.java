package com.hust.jackeyang.weatherapp.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by jackeyang on 2016/6/20.
 */
public class HttpUtil {
    public static void sendHttpRequest(final String address, final HttpCallbackListener lister) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader br = null;
                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5 * 1000);
                    connection.setReadTimeout(5 * 1000);
                    br = new BufferedReader(new InputStreamReader(connection.getInputStream(),
                            "utf-8"));
                    String line = null;
                    StringBuilder response = new StringBuilder("");
                    while ((line = br.readLine()) != null) {
                        response.append(line);
                    }
                    if (lister != null) {
                        //回调onFinish()方法
                        lister.onFinish(response.toString());
                    }
                } catch (MalformedURLException e) {
                    if (lister != null) {
                        //回调onError方法
                        lister.onError(e);
                    }
                } catch (IOException e) {
                    //回调onError方法
                    if (lister != null) {
                        lister.onError(e);
                    }
                }finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                    try {
                        br.close();
                    } catch (IOException e) {

                    }
                }
            }
        }).start();
    }

    public static void downLoadIcon(final String icon_url, final ImageView imageView) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Bitmap bitmap = downLoadIcon(icon_url);
                if (bitmap != null) {
                    imageView.post(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setImageBitmap(bitmap);
                        }
                    });
                }
            }
        }).start();
    }

    private static Bitmap downLoadIcon(String icon_url) {
        HttpURLConnection connection = null;
        Bitmap bitmap = null;
        try {
            URL url = new URL(icon_url);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5 * 1000);
            connection.setReadTimeout(5 * 1000);
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                bitmap = BitmapFactory.decodeStream(connection.getInputStream());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return bitmap;
    }
}
