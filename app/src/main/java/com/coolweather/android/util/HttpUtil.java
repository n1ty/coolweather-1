package com.coolweather.android.util;

import android.app.DownloadManager;
import android.app.VoiceInteractor;

import com.bumptech.glide.request.Request;

import okhttp3.OkHttpClient;




/**
 * Created by admin on 2018/5/31.
 */

public class HttpUtil {
    public static void sendOKHttpRequest(String address,okhttp3.Callback callback){
        OkHttpClient client=new OkHttpClient();
      
       okhttp3.Request request = new okhttp3.Request.Builder()
                .url(address)
                .build();

        client.newCall(request).enqueue(callback);


    }
}
