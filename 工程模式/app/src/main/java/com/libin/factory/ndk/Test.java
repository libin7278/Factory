package com.libin.factory.ndk;

import android.widget.Toast;

import com.libin.factory.MainApplication;

/**
 * Created by doudou on 2017/3/30.
 */

public class Test {

    public int addInt(int a, int b){
        int c = a+ b ;
        Toast.makeText(MainApplication.getContext(),"计算结果：：："+c, Toast.LENGTH_SHORT).show();
        return a+b;
    }

    public void setStrings(String s){
        Toast.makeText(MainApplication.getContext(), s, Toast.LENGTH_SHORT).show();
    }

    public static int addIntS(int a, int b){
        int c = a+ b ;
        Toast.makeText(MainApplication.getContext(), "计算结果：：："+c, Toast.LENGTH_SHORT).show();
        return a+b;
    }

    public static void setStringsS(String s){
        Toast.makeText(MainApplication.getContext(), s, Toast.LENGTH_SHORT).show();
    }
}
