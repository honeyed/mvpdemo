package com.easy.mvp.net;

import com.easy.mvp.business.login.LoginModel;

/**
 * description:
 * author: easy
 * new date: 2021/8/19
 * version: v 1.0
 */
public class RetrofitPresent {

    public <T> T getModel(Class<T> t) {
        return RetrofitInitialization.get().create(t);
    }
}
