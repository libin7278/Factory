package com.libin.factory.activity.custom_view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.libin.factory.R;

public class CustomViewActivity extends AppCompatActivity {
    private Button btn_civ;
    private Button btn_qq_weather;
    private Button btn_hew;
    private Button btn_path;
    private Button btn_paint;
    private Button btn_shader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);

        findView();

        onClick();

    }

    private void findView() {
        btn_civ = (Button) findViewById(R.id.btn_civ);
        btn_hew = (Button) findViewById(R.id.btn_hew);
        btn_qq_weather = (Button) findViewById(R.id.btn_qq_weather);
        btn_paint = (Button) findViewById(R.id.btn_paint);
        btn_path = (Button) findViewById(R.id.btn_path);
        btn_shader = (Button) findViewById(R.id.btn_shader);
    }

    private void onClick() {
        btn_civ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomViewActivity.this,CustomViewSActivity.class));
            }
        });

        btn_hew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomViewActivity.this,CustomViewHWWActivity.class));
            }
        });

        btn_qq_weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomViewActivity.this,CustomQQWActivity.class));
            }
        });

        btn_path.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomViewActivity.this,PathActivity.class));
            }
        });

        btn_paint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomViewActivity.this,PaintActivity.class));
            }
        });

        btn_shader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomViewActivity.this,ShaderActivity.class));
            }
        });
    }
}
