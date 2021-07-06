package com.easy.mvp.business.main;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.easy.mvp.R;
import com.easy.mvp.base.EasyActivity;
import com.easy.mvp.business.login.LoginActivity;
import com.easy.mvp.business.set.SetActivity;

public class MainActivity extends EasyActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goSet(View view) {
        Intent intent = new Intent(this, SetActivity.class);
        startActivity(intent);
    }
}