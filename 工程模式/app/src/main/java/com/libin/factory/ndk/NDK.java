package com.libin.factory.ndk;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.libin.factory.R;

/**
 * Created by doudou on 2017/3/17.
 */

public class NDK extends Activity {
    static {
        System.loadLibrary("MyJni"); //倒入生成的链接库文件
    }

    public native String getStringFromNative();//本地方法
    public native String getString_From_c();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ndk);
    }

    public void onClick(View view) {
        System.out.println(getString_From_c());
        Toast.makeText(this, getStringFromNative(), Toast.LENGTH_LONG).show();
    }
}
