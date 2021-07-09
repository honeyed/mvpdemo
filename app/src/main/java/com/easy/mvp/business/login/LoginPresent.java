package com.easy.mvp.business.login;

import com.easy.mvp.base.EVM;
import com.easy.mvp.bean.User;

/**
 * description:
 * author: tianhonglong
 * new date: 2021/7/9
 * version: v 1.0
 */
public class LoginPresent implements LoginContract.Presenter {

    private LoginModel loginModel = new LoginModel();

    @Override
    public void doLogin(String userName, String password) {
        LoginContract.LoginView loginView = EVM.ins().get(LoginContract.LoginView.class);
        EVM.ins().get(LoginContract.LoginView.class).showLoadingDialog();
        User user = loginModel.doUser(userName, password);
        loginView.dismissDialog();
        if(user.isLoginState()) {
            EVM.ins().get(LoginContract.LoginView.class).loginSuccess(user);
        } else {
            loginView.loginFailed(user.getFailureStatus(),"用户密码不对");
        }
    }

    @Override
    public void register(String phoneNum, String pass) {

    }

    @Override
    public void verificationCode(String phoneNum) {

    }
}