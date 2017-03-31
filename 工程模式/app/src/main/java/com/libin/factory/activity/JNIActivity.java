package com.libin.factory.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.libin.factory.R;
import com.libin.factory.ndk.NDK;
import com.orhanobut.logger.Logger;

public class JNIActivity extends AppCompatActivity {
    private Button btn_c_add;
    private Button btn_c_string;
    private Button btn_stu_info;
    private Button btn_update_std;
    private Button btn_p_s;
    private Button btn_c_secret;
    private Button btn_c_array;
    private Button btn_c_java_addint;
    private Button btn_c_java_getString;
    private Button button4;
    private Button button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jni);

        findView();

        setOnClick();

    }

    private void findView() {
        btn_c_add = (Button) findViewById(R.id.btn_c_add);
        btn_c_string = (Button) findViewById(R.id.btn_c_string);
        btn_stu_info = (Button) findViewById(R.id.btn_stu_info);
        btn_p_s = (Button) findViewById(R.id.btn_p_s);
        btn_update_std = (Button) findViewById(R.id.btn_update_std);
        btn_c_secret = (Button) findViewById(R.id.btn_c_secret);
        btn_c_array = (Button) findViewById(R.id.btn_c_array);
        btn_c_java_addint = (Button) findViewById(R.id.btn_c_java_addint);
        btn_c_java_getString = (Button) findViewById(R.id.btn_c_java_getString);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);

    }

    private void setOnClick() {
        btn_c_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(JNIActivity.this, "相加结果:" + NDK.addInt(10, 2), Toast.LENGTH_SHORT).show();
            }
        });

        btn_c_string.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(JNIActivity.this, "JNI结果 ：" + NDK.addString("I am From Java"), Toast.LENGTH_SHORT).show();
            }
        });

        btn_stu_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(JNIActivity.this, "Student信息 ：" + NDK.getStudentInfo().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        btn_update_std.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(JNIActivity.this, "Student信息 ：" + NDK.updateStudentInfo(NDK.getStudentInfo()), Toast.LENGTH_SHORT).show();
            }
        });

        btn_p_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(JNIActivity.this, "Student信息 ：" + NDK.updateStudentInfo(NDK.getStudentInfo()), Toast.LENGTH_SHORT).show();
            }
        });

        btn_c_secret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(JNIActivity.this, "密码校验" + NDK.checkPwd("123456"), Toast.LENGTH_SHORT).show();
            }
        });

        btn_c_array.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a[] = {1, 2, 3, 4, 5, 6, 7};
                int[] ints = NDK.increaseArrayEles(a);
                for (int i = 0; i < ints.length; i++) {
                    Logger.e(a[i] + "");
                }
            }
        });

        btn_c_java_addint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NDK.ccallBackAddInt();
            }
        });

        btn_c_java_getString.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NDK.ccallBackGetString();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NDK.ccallBackAddIntS();
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NDK.ccallBackGetStringS();
            }
        });


    }


}
