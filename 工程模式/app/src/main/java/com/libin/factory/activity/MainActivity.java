package com.libin.factory.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.libin.factory.R;
import com.libin.factory.handler.HandlerUtil;
import com.libin.factory.mvp.login.LoginActivity;
import com.libin.factory.reflex_factory.JILiDiHao;
import com.libin.factory.reflex_factory.JiLIBoYue;
import com.libin.factory.reflex_factory.JiliCarFactory;

public class MainActivity extends AppCompatActivity {
    private Button btn_rx_rt;
    private Button btn_mvp;
    private Button btn_greendao;

    private Handler mHandler;
    String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findview();

        onClickListener();

        handlerTest();

        factoryTest();

    }

    private void onClickListener() {
        btn_rx_rt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_mvp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));

            }
        });

        btn_greendao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GreenDaoAvtivity.class));
            }
        });
    }

    private void findview() {
        btn_rx_rt = (Button) findViewById(R.id.btn_rx_rt);
        btn_mvp =(Button) findViewById(R.id.btn_mvp);
        btn_greendao =(Button) findViewById(R.id.btn_greendao);
    }

    private void factoryTest() {
        JiLIBoYue jiLIBoYue = JiliCarFactory.getInstance().createJiliCar(JiLIBoYue.class);
        jiLIBoYue.driver();
        jiLIBoYue.selgVavigator();

        JILiDiHao jiLiDiHao = JiliCarFactory.getInstance().createJiliCar(JILiDiHao.class);
        jiLiDiHao.driver();
        jiLiDiHao.selgVavigator();
    }

    void handlerTest(){
        mHandler = HandlerUtil.getInstance(this);
        Message message = new Message();
        message.what = 1;
        message.obj = "lala";
        mHandler.sendMessage(message) ;
    }


}
