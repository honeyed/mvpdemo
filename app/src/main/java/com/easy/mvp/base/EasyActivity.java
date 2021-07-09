package com.easy.mvp.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * description:
 * author: tianhonglong
 * new date: 2021/7/6
 * version: v 1.0
 */
public class EasyActivity extends AppCompatActivity implements EasyView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EVM.getInstance().register(this);
    }

    public void onBackListener(View view) {
        finish();
    }

    @Override
    public void showMessage() {

    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void showLoadingDialog(String msg) {

    }

    @Override
    public void dismissDialog() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EVM.getInstance().unregister(this);
    }
}
