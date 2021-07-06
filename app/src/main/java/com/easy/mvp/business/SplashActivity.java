package com.easy.mvp.business;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.easy.mvp.R;
import com.easy.mvp.base.EasyActivity;

/**
 * description:
 * author: tianhonglong
 * new date: 2021/7/6
 * version: v 1.0
 */
public class SplashActivity extends EasyActivity {

    private Context context;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View startView = View.inflate(this, R.layout.activity_splash, null);
        setContentView(startView);
        context = this;
        //渐变
        AlphaAnimation aa = new AlphaAnimation(0.3f, 1.0f);
        aa.setDuration(2000);
        startView.setAnimation(aa);
        aa.setAnimationListener(new Animation.AnimationListener() {

                                    @Override
                                    public void onAnimationStart(Animation animation) {
                                        // TODO Auto-generated method stub

                                    }

                                    @Override
                                    public void onAnimationRepeat(Animation animation) {
                                        // TODO Auto-generated method stub

                                    }

                                    @Override
                                    // TODO Auto-generated method stub
                                    public void onAnimationEnd(Animation animation) {
                                        redirectto();
                                    }
                                }
        );
    }

    private void redirectto() {
        Intent intent = new Intent(this, GalleryImageActivity.class);
        startActivity(intent);
        finish();
    }
}
