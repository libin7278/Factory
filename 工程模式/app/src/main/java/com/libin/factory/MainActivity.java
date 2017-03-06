package com.libin.factory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.libin.factory.reflex_factory.JILiDiHao;
import com.libin.factory.reflex_factory.JiLIBoYue;
import com.libin.factory.reflex_factory.JiLiFactory;
import com.libin.factory.reflex_factory.JiliCarFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JiLiFactory jiLiFactory = new JiliCarFactory();

        JiLIBoYue jiLIBoYue = jiLiFactory.createJiliCar(JiLIBoYue.class);
        jiLIBoYue.driver();
        jiLIBoYue.selgVavigator();

        JILiDiHao jiLiDiHao = jiLiFactory.createJiliCar(JILiDiHao.class);
        jiLiDiHao.driver();
        jiLiDiHao.selgVavigator();
    }
}
