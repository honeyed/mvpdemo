package com.easy.mvp.business.login;

import com.easy.mvp.bean.User;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * description:
 * author: tianhonglong
 * new date: 2021/7/9
 * version: v 1.0
 */
public interface LoginModel {

    @POST("login")
    User doLogin(@Query("username") String username, @Query("password") String password);
}
