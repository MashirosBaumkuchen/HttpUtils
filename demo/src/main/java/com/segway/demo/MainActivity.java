package com.segway.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.segway.demo.utils.RetroUtils;

import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new RetroUtils().get("11","2");
    }
}
