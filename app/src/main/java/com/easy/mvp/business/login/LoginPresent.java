package com.easy.mvp.business.login;

import com.easy.mvp.base.EVM;
import com.easy.mvp.bean.User;
import com.easy.mvp.net.RetrofitInitialization;
import com.easy.mvp.net.RetrofitPresent;

import retrofit2.Call;
import rx.Observable;

/**
 * description:
 * author: tianhonglong
 * new date: 2021/7/9
 * version: v 1.0
 */
public class LoginPresent extends RetrofitPresent implements LoginContract.Presenter {

    private LoginModel loginModel = RetrofitInitialization.get().create(LoginModel.class);

    @Override
    public void doLogin(String userName, String password) {
        EVM.getV(LoginContract.LoginView.class).showLoadingDialog();
        User user = loginModel.doLogin(userName, password);
        EVM.getV(LoginContract.LoginView.class).dismissDialog();
        if (user.isLoginState()) {
            EVM.getV(LoginContract.LoginView.class).loginSuccess(user);
        } else {
            EVM.getV(LoginContract.LoginView.class).loginFailed(user.getFailureStatus(), "用户密码不对");
        }
    }

    @Override
    public void register(String phoneNum, String pass) {

    }

    @Override
    public void verificationCode(String phoneNum) {

    }
}
