package com.libin.factory.activity.custom_view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.libin.factory.R;

public class CustomViewActivity extends AppCompatActivity {
    private Button btn_civ;
    private Button btn_hew;

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
    }
}
