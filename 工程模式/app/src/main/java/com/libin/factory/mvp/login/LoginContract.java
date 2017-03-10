package com.libin.factory.mvp.login;

import com.libin.factory.mvp.base.BasePresenter;
import com.libin.factory.mvp.base.BaseView;

/**
 * MVPPlugin
 * ＊ 邮箱 784787081@qq.com
 */

public class LoginContract {
    interface View extends BaseView {
        void initview();

        void showLoading();

        void disLoading();
    }

    interface  Presenter extends BasePresenter<View> {
        void handleClickLogin();

        void handleRequest();
    }
}
