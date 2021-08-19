package com.easy.mvp.business.main;

import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.easy.mvp.R;
import com.easy.mvp.base.EasyActivity;
import com.easy.mvp.base.EasyFragment;
import com.easy.mvp.base.IBackHandledInterface;

import retrofit2.Retrofit;

public class MainActivity extends EasyActivity implements IBackHandledInterface {

    private EasyFragment mBackHandedFragment;
    private long clickTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //创建Retrofit实例
    }

    @Override
    public void setSelectedFragment(EasyFragment fragment) {
        mBackHandedFragment = fragment;
    }

    @Override
    public void onBackPressed() {
        if (mBackHandedFragment == null || !mBackHandedFragment.onBackPressed()) {
            if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
                if ((System.currentTimeMillis() - clickTime) > 2000) {
                    Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                    clickTime = System.currentTimeMillis();
                } else {
                    super.onBackPressed();
                }
            } else {
                getSupportFragmentManager().popBackStack();
            }
        }
    }
}