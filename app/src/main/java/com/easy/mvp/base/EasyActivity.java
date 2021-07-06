package com.easy.mvp.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.easy.mvp.business.login.LoginActivity;
import com.easy.mvp.hook.NeedLoginUtils;

/**
 * description:
 * author: tianhonglong
 * new date: 2021/7/6
 * version: v 1.0
 */
public class EasyActivity extends AppCompatActivity implements EasyView {

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
}
