package com.example.ncrsoft.food.utils;

import android.util.Log;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

/**
 * Created by NcrSoft on 01-Mar-17.
 */

public class ExecuteHttpRequest {

    public static String executeHttp(String url, String data) {

        String resp = "";

        try {

            OkHttpClient client = new OkHttpClient();
            client.getRetryOnConnectionFailure();

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();

            resp = response.body().string();

            Log.e("response", resp);

        } catch (Exception e) {
            Log.e("execute http", e.getMessage());
            resp = e.getMessage();
        }

        return resp;
    }


    public static String executeHttpPost(String url, RequestBody requestBody) {

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        String resp = "";

        try {

            OkHttpClient client = new OkHttpClient();
            client.getRetryOnConnectionFailure();

            // RequestBody body = RequestBody.create(JSON, data);

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();

            resp = response.body().string();

            Log.e("response", resp);

        } catch (Exception e) {
            Log.e("execute http", e.getMessage());
            resp = e.getMessage();
        }

        return resp;
    }

}
