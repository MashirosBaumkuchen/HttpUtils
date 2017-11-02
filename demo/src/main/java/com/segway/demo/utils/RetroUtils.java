package com.segway.demo.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.segway.demo.bean.Event;
import com.segway.demo.bean.Result;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author No.47 create at 2017/11/2.
 */
public class RetroUtils {
    private final String TAG = getClass().getSimpleName();
    private Retrofit retrofit;

    public void get(String month, String day) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.juheapi.com/japi/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        HoTService service = retrofit.create(HoTService.class);
        Call<Result<Event>> call = service.getEvent("eff36bdaeeb868a6b8057a34f32d1326",
                "1.0", month, day);
        call.enqueue(new Callback<Result<Event>>() {
            @Override
            public void onResponse(Call<Result<Event>> call, Response<Result<Event>> response) {
                Log.d(TAG, "onResponse");
                Log.d(TAG, String.valueOf(response.code()));
                Log.d(TAG, response.body().getResult().get(0).getDes());
            }

            @Override
            public void onFailure(Call<Result<Event>> call, Throwable t) {
                Log.d(TAG, "onResponse");
            }
        });
    }

    public interface HoTService {
        @GET("toh")
        Call<Result<Event>> getEvent(@Query("key") String key,
                                     @Query("version") String version,
                                     @Query("month") String month,
                                     @Query("day") String day);
    }
}
