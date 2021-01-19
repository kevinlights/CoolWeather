package com.gjh.learn.android.coolweather.util;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpUtil {
    public static void sendOkHttpRequest(String address, Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request req = new Request.Builder().url(address).build();
        client.newCall(req).enqueue(callback);
    }
}
