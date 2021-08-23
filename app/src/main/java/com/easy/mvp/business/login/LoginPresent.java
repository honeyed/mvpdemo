package com.easy.mvp.business.login;

import com.easy.mvp.base.EVM;
import com.easy.mvp.base.EasyData;
import com.easy.mvp.bean.User;
import com.easy.mvp.net.Any;
import com.easy.mvp.net.RetrofitInitialization;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import retrofit2.Call;
import rx.schedulers.Schedulers;

/**
 * description:
 * author: tianhonglong
 * new date: 2021/7/9
 * version: v 1.0
 */
public class LoginPresent implements LoginContract.Presenter {

    public static void main(String[] args) {
        LoginPresent loginPresent = new LoginPresent();
        loginPresent.doLogin("1","12");
    }

    private LoginModel loginModel = RetrofitInitialization.get().create(LoginModel.class);

    @Override
    public void doLogin(String userName, String password) {
        EVM.getV(LoginContract.LoginView.class).showLoadingDialog();
        Any.asyncTask(new Any.AsyncTask<EasyData<User>>() {
            @Override
            public EasyData<User> execute() {
                return loginModel.doLogin(userName, password);
            }

            @Override
            public void OnMessageResponse(EasyData<User> user) {
                if (user.getData().isLoginState()) {
                    EVM.getV(LoginContract.LoginView.class).loginSuccess(user.getData());//加点测试
                } else {
                    EVM.getV(LoginContract.LoginView.class).loginFailed(user.getData().getFailureStatus(), "用户密码不对");
                }
            }
        });
    }

    @Override
    public void register(String phoneNum, String pass) {

    }

    @Override
    public void verificationCode(String phoneNum) {
        Observable.class.getSimpleName();
    }
}
