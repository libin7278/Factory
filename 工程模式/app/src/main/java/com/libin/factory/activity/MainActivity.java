package com.libin.factory.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.libin.factory.R;
import com.libin.factory.handler.HandlerUtil;
import com.libin.factory.reflex_factory.JILiDiHao;
import com.libin.factory.reflex_factory.JiLIBoYue;
import com.libin.factory.reflex_factory.JiliCarFactory;

public class MainActivity extends AppCompatActivity {
    private Button btn_rx_rt;

    private Handler mHandler;
    String TAG = "AndroidTest";

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
    }

    private void findview() {
        btn_rx_rt = (Button) findViewById(R.id.btn_rx_rt);
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
