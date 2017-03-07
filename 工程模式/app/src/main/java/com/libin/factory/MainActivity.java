package com.libin.factory;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.libin.factory.handler.HandlerUtil;
import com.libin.factory.reflex_factory.JILiDiHao;
import com.libin.factory.reflex_factory.JiLIBoYue;
import com.libin.factory.reflex_factory.JiliCarFactory;

public class MainActivity extends AppCompatActivity {
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHandler = HandlerUtil.getInstance(this);
        Message message = new Message();
        message.what = 1;
        message.obj = "lala";
        mHandler.sendMessage(message) ;

        JiLIBoYue jiLIBoYue = JiliCarFactory.getInstance().createJiliCar(JiLIBoYue.class);
        jiLIBoYue.driver();
        jiLIBoYue.selgVavigator();

        JILiDiHao jiLiDiHao = JiliCarFactory.getInstance().createJiliCar(JILiDiHao.class);
        jiLiDiHao.driver();
        jiLiDiHao.selgVavigator();
    }
}
