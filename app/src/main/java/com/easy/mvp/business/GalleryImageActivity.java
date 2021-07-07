package com.easy.mvp.business;

/*
 *	Copyright (c) 2013, Zks
 *
 *  修改纪录
 *	2021年7月6日 版本：1.0 田洪龙 新建。
 */

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;

import androidx.viewpager.widget.ViewPager;

import com.easy.mvp.R;
import com.easy.mvp.base.EasyActivity;
import com.easy.mvp.business.login.LoginActivity;
import com.easy.mvp.common.EasySP;
import com.easy.mvp.easing.Cubic;
import com.easy.mvp.easing.Sine;


public class GalleryImageActivity extends EasyActivity implements OnGestureListener, OnTouchListener {

    public static final String IsHandleShow = "IsHandleShow";//手工显示向导
    private ViewPager imagePager;
    private GalleryImageAdapter galleryImageAdapter;

    private int pager_num;
    int total_page;
    FrameLayout backgroundLayout;
    HorizontalScrollView background_srcollview;
    HorizontalScrollView layer_srcollview;
    int backgoundWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        Intent intent = getIntent();
        boolean isHandleShow = intent.getBooleanExtra(IsHandleShow, false);
        //不是首次运行直接跳转
        boolean isFirstRun = EasySP.getInstance().getBoolean("isFirstRun", false);
//        if (!isFirstRun &&  !isHandleShow) {
//            Intent it = new Intent(this, LoginActivity.class);
//            startActivity(it);
//            overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
//            finish();
//        }

        initLayout();
        backgroundLayout = (FrameLayout) findViewById(R.id.backgroundLayout);
        background_srcollview = (HorizontalScrollView) findViewById(R.id.background_srcollview);
        background_srcollview.setHorizontalScrollBarEnabled(false);

        layer_srcollview = (HorizontalScrollView) findViewById(R.id.layer_srcollview);
        layer_srcollview.setHorizontalScrollBarEnabled(false);

        imagePager = (ViewPager) findViewById(R.id.image_pager);
        //imagePager.setAlpha((float) 0.5);

        galleryImageAdapter = new GalleryImageAdapter(this);
        imagePager.setAdapter(galleryImageAdapter);
        imagePager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                pager_num = position + 1;
            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
                float realOffset = Cubic.easeIn(positionOffset, 0, 1, 1);

                total_page = galleryImageAdapter.getCount();
                float offset = (float) ((float) (position + realOffset) * 1.0 / total_page);
                int offsetPositon = (int) (backgoundWidth * offset);
                //  background_srcollview.scrollTo(offsetPositon,0);

                float layerRealOffset = Sine.easeIn(positionOffset, 0, 1, 1);
                float layerOffset = (float) ((float) (position + layerRealOffset) * 1.0 / total_page);
                int layerOffsetPositon = (int) (backgoundWidth * layerOffset);
                layer_srcollview.scrollTo(layerOffsetPositon, 0);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        imagePager.setOnTouchListener(this);
    }

    GestureDetector mygesture = new GestureDetector(this);

    public boolean onTouch(View v, MotionEvent event) {
        return mygesture.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {
        if (e1.getX() - e2.getX() > 120) {
            if (pager_num == 5) { //到最后一页 跳转到主页
                Intent intent = new Intent(GalleryImageActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                EasySP.getInstance().putBoolean("isFirstRun", false).apply();//测试时总是显示向导
            }
        }
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    void initLayout() {
        DisplayMetrics dm = new DisplayMetrics();
        //取得窗口属性
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        backgoundWidth = dm.widthPixels * 5;
        ViewGroup.LayoutParams layoutParams;

        ImageView back_image_one = (ImageView) findViewById(R.id.back_image_one);
        layoutParams = back_image_one.getLayoutParams();
        layoutParams.height = dm.heightPixels;
        layoutParams.width = dm.widthPixels;
        back_image_one.setLayoutParams(layoutParams);

        ImageView back_image_two = (ImageView) findViewById(R.id.back_image_two);
        layoutParams = back_image_two.getLayoutParams();
        layoutParams.height = dm.heightPixels;
        layoutParams.width = dm.widthPixels;
        back_image_two.setLayoutParams(layoutParams);

        ImageView back_image_three = (ImageView) findViewById(R.id.back_image_three);
        layoutParams = back_image_three.getLayoutParams();
        layoutParams.height = dm.heightPixels;
        layoutParams.width = dm.widthPixels;
        back_image_three.setLayoutParams(layoutParams);

        ImageView back_image_four = (ImageView) findViewById(R.id.back_image_four);
        layoutParams = back_image_four.getLayoutParams();
        layoutParams.height = dm.heightPixels;
        layoutParams.width = dm.widthPixels;
        back_image_four.setLayoutParams(layoutParams);

        ImageView back_image_five = (ImageView) findViewById(R.id.back_image_five);
        layoutParams = back_image_five.getLayoutParams();
        layoutParams.height = dm.heightPixels;
        layoutParams.width = dm.widthPixels;
        back_image_five.setLayoutParams(layoutParams);


        FrameLayout.LayoutParams frameLayoutParams;
        ImageView layer_image_one = (ImageView) findViewById(R.id.layer_image_one);
        frameLayoutParams = (FrameLayout.LayoutParams) layer_image_one.getLayoutParams();
        frameLayoutParams.height = dm.heightPixels;
        frameLayoutParams.width = dm.widthPixels;
        layer_image_one.setLayoutParams(frameLayoutParams);

        ImageView layer_image_two = (ImageView) findViewById(R.id.layer_image_two);
        frameLayoutParams = (FrameLayout.LayoutParams) layer_image_two.getLayoutParams();
        frameLayoutParams.height = dm.heightPixels;
        frameLayoutParams.width = dm.widthPixels;
        layer_image_two.setLayoutParams(frameLayoutParams);

        ImageView layer_image_three = (ImageView) findViewById(R.id.layer_image_three);
        frameLayoutParams = (FrameLayout.LayoutParams) layer_image_three.getLayoutParams();
        frameLayoutParams.height = dm.heightPixels;
        frameLayoutParams.width = dm.widthPixels;
        layer_image_three.setLayoutParams(frameLayoutParams);

        ImageView layer_image_four = (ImageView) findViewById(R.id.layer_image_four);
        frameLayoutParams = (FrameLayout.LayoutParams) layer_image_four.getLayoutParams();
        frameLayoutParams.height = dm.heightPixels;
        frameLayoutParams.width = dm.widthPixels;
        layer_image_four.setLayoutParams(frameLayoutParams);

        ImageView layer_image_five = (ImageView) findViewById(R.id.layer_image_five);
        frameLayoutParams = (FrameLayout.LayoutParams) layer_image_five.getLayoutParams();
        frameLayoutParams.height = dm.heightPixels;
        frameLayoutParams.width = dm.widthPixels;
        layer_image_five.setLayoutParams(frameLayoutParams);
    }


}
