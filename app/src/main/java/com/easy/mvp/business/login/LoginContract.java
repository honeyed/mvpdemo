package com.easy.mvp.business.login;

import com.easy.mvp.base.EasyPresent;
import com.easy.mvp.base.EasyView;
import com.easy.mvp.bean.User;

/**
 * description:
 * author: easy
 * new date: 2021/7/6
 * version: v 1.0
 */
public interface LoginContract {

    interface Presenter extends EasyPresent {
        void doLogin(String name,String pass);

        void register(String phoneNum, String pass);

        void verificationCode(String phoneNum);
    }

    interface LoginView extends EasyView {//定义登录页面两个结果业务逻辑

        void loginSuccess(User user);//登录成功

        void loginFailed(int state, String message);//登录失败
    }

    interface RegisterView extends EasyView {
        void registerSuccess(String userName, String passWord);
    }

    interface GetVerificationCode extends EasyView {
        void GetVerificationCodeSuccess(String verificationCode);
    }
}