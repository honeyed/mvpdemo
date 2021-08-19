package com.easy.mvp.net;

import com.easy.mvp.bean.User;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * description:
 * author: tianhonglong
 * new date: 2021/8/19
 * version: v 1.0
 */
public interface RetrofitService {

    @POST("login")
    Call<User> doLogin(@Query("username") String username, @Query("password") String password);
}
