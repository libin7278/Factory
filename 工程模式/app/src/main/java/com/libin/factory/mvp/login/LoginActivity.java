package com.libin.factory.mvp.login;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.libin.factory.R;
import com.libin.factory.mvp.base.MVPBaseActivity;


/**
 * MVPPlugin
 * ＊ 邮箱 784787081@qq.com
 */

public class LoginActivity extends MVPBaseActivity<LoginContract.View, LoginPresenter> implements LoginContract.View {

    private LoginPresenter loginPresenter ;
    private TextView tv_login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login_activity);
        tv_login =(TextView) findViewById(R.id.tv_login);

        Log.e("TAG", "LoginActivityss");

        loginPresenter = mPresenter;

        loginPresenter.handleRequest();

    }

    @Override
    public void initview() {

    }

    @Override
    public void showLoading() {
        Toast.makeText(this, "准备登陆", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void disLoading() {
        Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
    }

}
