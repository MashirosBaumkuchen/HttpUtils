package com.segway.httputils.okhttp;

import android.util.Log;
import com.google.gson.Gson;
import com.segway.httputils.okhttp.bean.BeanDemo;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;

/**
 * @author No.47 create at 2017/11/2.
 */
public class OKHttpUtils {
    public static final String TAG = OKHttpUtils.class.getSimpleName();


    public static void get() {
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .get()
                .url("http://api.juheapi.com/japi/toh?key=eff36bdaeeb868a6b8057a34f32d1326&v=1.0&month=11&day=1")
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.d(TAG, "fail");
            }

            @Override
            public void onResponse(Response response) throws IOException {
                Log.d(TAG, response.body().string());
            }
        });
    }

    public static void post() {
        OkHttpClient okHttpClient = new OkHttpClient();
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("key", "eff36bdaeeb868a6b8057a34f32d1326");
        builder.add("v", "1.0");
        builder.add("month", "11");
        builder.add("day", "1");
        final Request request = new Request.Builder()
                .post(builder.build())
                .url("http://api.juheapi.com/japi/toh")
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.d(TAG, "fail");
            }

            @Override
            public void onResponse(Response response) throws IOException {
                Gson gson = new Gson();
                BeanDemo beanDemo = gson.fromJson(response.body().string(),BeanDemo.class);
                Log.d(TAG, beanDemo.getResult().get(0).getDes());
            }
        });
    }
}
