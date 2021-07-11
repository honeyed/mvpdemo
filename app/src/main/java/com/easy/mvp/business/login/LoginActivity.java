package com.easy.mvp.business.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.easy.mvp.R;
import com.easy.mvp.base.EasyActivity;
import com.easy.mvp.bean.User;
import com.easy.mvp.business.main.MainActivity;

/**
 * description:
 * author: tianhonglong
 * new date: 2021/7/6
 * version: v 1.0
 */
public class LoginActivity extends EasyActivity implements LoginContract.LoginView {

    private EditText userName, passWord;
    private LoginPresent loginPresent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginPresent = new LoginPresent();//创建LoginPresent
        userName = findViewById(R.id.userName);
        passWord = findViewById(R.id.passWord);
    }

    public void doLogin(View view) {
        String password = passWord.getText().toString();
        String username = userName.getText().toString();
        loginPresent.doLogin(username, password);
    }

    @Override
    public void loginSuccess(User user) {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void loginFailed(int state, String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
