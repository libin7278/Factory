package com.libin.factory.activity.custom_view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.libin.factory.R;
import com.libin.factory.widget.HuaWeiWeatherView;

public class CustomViewHWWActivity extends AppCompatActivity {
    private HuaWeiWeatherView hwwv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_hww);

        hwwv = (HuaWeiWeatherView) findViewById(R.id.hwwv);


    }
}
