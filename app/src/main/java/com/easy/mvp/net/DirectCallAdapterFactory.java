package com.easy.mvp.net;

import com.easy.mvp.base.EasyData;
import com.easy.mvp.bean.User;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;

/**
 * 实现可以直接返回数据类的核心类
 */
public class DirectCallAdapterFactory extends CallAdapter.Factory {

    public static DirectCallAdapterFactory create() {
        return new DirectCallAdapterFactory();
    }

    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        final Type responseType = getResponseType(returnType);

        return new CallAdapter<Object, Object>() {
            public Type responseType() {
                return responseType;
            }

            public Object adapt(Call<Object> call) {
                // todo 可以在这里判断接口数据格式
                try {
                    Thread.sleep(30*1000);
                    return call.execute().body();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

//                call.enqueue(new Callback<Object>() {
//                    @Override
//                    public void onResponse(Call<Object> call, Response<Object> response) {
//                        EasyData<User> data = (EasyData<User>) response.body();
//                        System.out.println(data.getMsg()+data.getData().getClass().getSimpleName());
//                    }
//
//                    @Override
//                    public void onFailure(Call<Object> call, Throwable t) {
//
//                    }
//                });
                return null;
            }
        };
    }

    private Type getResponseType(Type type) {
        if (type instanceof WildcardType) {
            return ((WildcardType) type).getUpperBounds()[0];
        }
        return type;
    }
}
