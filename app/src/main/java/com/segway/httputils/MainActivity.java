package com.segway.httputils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.segway.httputils.httpurlconnection.HttpURLConnectionUtils;

public class MainActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HttpURLConnectionUtils.get();
        HttpURLConnectionUtils.post();
    }
}
