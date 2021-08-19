package com.easy.mvp.business.main;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.easy.mvp.R;

/**
 * Created by 田洪龙 on 2017/11/7.
 */

public class MainTabsFragment extends Fragment {

    public int discoverResourceNormal = R.mipmap.ic_launcher;
    public int discoverResourcePre = R.mipmap.ic_launcher;
    public int videoResourceNormal = R.mipmap.ic_launcher;
    public int videoResourcePre = R.mipmap.ic_launcher;
    public int musicResourceNormal = R.mipmap.ic_launcher;
    public int musicResourcePre = R.mipmap.ic_launcher;
    public int friendResourceNormal = R.mipmap.ic_launcher;
    public int friendResourcePre = R.mipmap.ic_launcher;
    public int accountResourceNormal = R.mipmap.ic_launcher;
    public int accountResourcePre = R.mipmap.ic_launcher;

    public String centerText = "";

    public static final String tab_one_key = "tab_one";
    public static final String tab_two_key = "tab_two";
    public static final String tab_three_key = "tab_three";
    public static final String tab_four_key = "tab_four";
    public static final String tab_five_key = "tab_five";

    //功能按钮
    ImageView tab_one;
    ImageView tab_two;
    ImageView tab_three;
    ImageView tab_four;
    ImageView tab_five;

    //功能按钮文字
    TextView tab_one_text;
    TextView tab_two_text;
    TextView tab_three_text;
    TextView tab_four_text;
    TextView tab_five_text;

    LinearLayout tab_linear_one;
    LinearLayout tab_linear_two;
    LinearLayout tab_linear_three;
    LinearLayout tab_linear_four;
    LinearLayout tab_linear_five;

    //用于显示某个信息的数量，比如未读邮件数，购物车货品数
    private static ImageView shopping_cart_num;
//    private static LinearLayout shopping_cart_num_bg;
//    private ProgressDialog progressDialog;
    private int checkedColor = Color.parseColor("#c3473a");
    private int normalColor = Color.GRAY;

//    //用于记录系统运行参数
//    private SharedPreferences shared;
//    private SharedPreferences.Editor editor;

    //各功能页主页Fragment
    Fragment oneFragment;
    Fragment fragmentTwo;
    Fragment fragmentThree;
    Fragment fragmentFour;
    Fragment fragmentFive;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_main_toolbar_subview, container, false);
        init(contentView);
        return contentView;
    }

    //初始化底部向导栏
    void init(View mainView) {

        this.shopping_cart_num = mainView.findViewById(R.id.shopping_cart_num);
//        this.shopping_cart_num_bg = mainView.findViewById(R.id.shopping_cart_num_bg);

        this.tab_one = mainView.findViewById(R.id.toolbar_tabone);
        this.tab_one_text = mainView.findViewById(R.id.toolbar_tabone_text);
        this.tab_linear_one = mainView.findViewById(R.id.tab_linear_one);
        this.tab_linear_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnTabSelected(tab_one_key);
            }
        });

        this.tab_two = mainView.findViewById(R.id.toolbar_tabtwo);
        this.tab_two_text = mainView.findViewById(R.id.toolbar_tabtwo_text);
        this.tab_linear_two = mainView.findViewById(R.id.tab_linear_two);
        this.tab_linear_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnTabSelected(tab_two_key);
            }
        });

        this.tab_three = mainView.findViewById(R.id.toolbar_tabthree);
        this.tab_three_text = mainView.findViewById(R.id.toolbar_tabthree_text);
        this.tab_linear_three = mainView.findViewById(R.id.tab_linear_three);
        this.tab_linear_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnTabSelected(tab_three_key);
            }
        });

        this.tab_four = mainView.findViewById(R.id.toolbar_tabfour);
        this.tab_four_text = mainView.findViewById(R.id.toolbar_tabfour_text);
        this.tab_linear_four = mainView.findViewById(R.id.tab_linear_four);
        this.tab_linear_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnTabSelected(tab_four_key);
            }
        });

        this.tab_five = (ImageView) mainView.findViewById(R.id.toolbar_tabfive);
        this.tab_five_text = (TextView) mainView.findViewById(R.id.toolbar_tabfive_text);
        this.tab_linear_five = (LinearLayout) mainView.findViewById(R.id.tab_linear_five);
        this.tab_linear_five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnTabSelected(tab_five_key);
            }
        });

        OnTabSelected(tab_one_key);
    }

    //功能页面跳转
    public void OnTabSelected(String tabName) {
        if (tabName == "tab_one") {
            //显示home页面
            if (null == oneFragment) {
                oneFragment = new DiscoverFragment();
            }

            FragmentTransaction localFragmentTransaction = getFragmentManager().beginTransaction();
            localFragmentTransaction.replace(R.id.fragment_container, oneFragment, "tab_one");
            localFragmentTransaction.commit();

            //视觉效果
            this.tab_one.setImageResource(discoverResourcePre);
            this.tab_two.setImageResource(videoResourceNormal);
            this.tab_three.setImageResource(musicResourceNormal);
            this.tab_four.setImageResource(friendResourceNormal);
            this.tab_five.setImageResource(accountResourceNormal);

            this.tab_one_text.setTextColor(checkedColor);
            this.tab_two_text.setTextColor(normalColor);
            this.tab_three_text.setTextColor(normalColor);
            this.tab_four_text.setTextColor(normalColor);
            this.tab_five_text.setTextColor(normalColor);

        } else if (tabName == "tab_two") {
            if (null == fragmentTwo) {
                fragmentTwo = new DiscoverFragment();
            }
            FragmentTransaction localFragmentTransaction = getFragmentManager().beginTransaction();
            localFragmentTransaction.replace(R.id.fragment_container, fragmentTwo, "tab_two");
            localFragmentTransaction.commit();

            //视觉效果
            this.tab_one.setImageResource(discoverResourceNormal);
            this.tab_two.setImageResource(videoResourcePre);
            this.tab_three.setImageResource(musicResourceNormal);
            this.tab_four.setImageResource(friendResourceNormal);
            this.tab_five.setImageResource(accountResourceNormal);

            this.tab_one_text.setTextColor(normalColor);
            this.tab_two_text.setTextColor(checkedColor);
            this.tab_three_text.setTextColor(normalColor);
            this.tab_four_text.setTextColor(normalColor);
            this.tab_five_text.setTextColor(normalColor);

        } else if (tabName == "tab_three") {
            if (null == fragmentThree) {
                fragmentThree = new DiscoverFragment();
            }
            FragmentTransaction localFragmentTransaction = getFragmentManager().beginTransaction();
            localFragmentTransaction.replace(R.id.fragment_container, fragmentThree, "tab_three");
            localFragmentTransaction.commit();


            //视觉效果
            this.tab_one.setImageResource(discoverResourceNormal);
            this.tab_two.setImageResource(videoResourceNormal);
            this.tab_three.setImageResource(musicResourcePre);
            this.tab_four.setImageResource(friendResourceNormal);
            this.tab_five.setImageResource(accountResourceNormal);

            this.tab_one_text.setTextColor(normalColor);
            this.tab_two_text.setTextColor(normalColor);
            this.tab_three_text.setTextColor(checkedColor);
            this.tab_four_text.setTextColor(normalColor);
            this.tab_five_text.setTextColor(normalColor);

        } else if (tabName == "tab_four") {
            if (null == fragmentFour) {
                fragmentFour = new DiscoverFragment();
            }
            FragmentTransaction localFragmentTransaction = getFragmentManager().beginTransaction();
            localFragmentTransaction.replace(R.id.fragment_container, fragmentFour, "tab_four");
            localFragmentTransaction.commit();


            //视觉效果
            this.tab_one.setImageResource(discoverResourceNormal);
            this.tab_two.setImageResource(videoResourceNormal);
            this.tab_three.setImageResource(musicResourceNormal);
            this.tab_four.setImageResource(friendResourcePre);
            this.tab_five.setImageResource(accountResourceNormal);

            this.tab_one_text.setTextColor(normalColor);
            this.tab_two_text.setTextColor(normalColor);
            this.tab_three_text.setTextColor(normalColor);
            this.tab_four_text.setTextColor(checkedColor);
            this.tab_five_text.setTextColor(normalColor);

        } else if (tabName == "tab_five") {
            if (null == fragmentFive) {
                fragmentFive = new DiscoverFragment();
            }
            FragmentTransaction localFragmentTransaction = getFragmentManager().beginTransaction();
            localFragmentTransaction.replace(R.id.fragment_container, fragmentFive, "tab_five");
            localFragmentTransaction.commit();

//            视觉效果
            this.tab_one.setImageResource(discoverResourceNormal);
            this.tab_two.setImageResource(videoResourceNormal);
            this.tab_three.setImageResource(musicResourceNormal);
            this.tab_four.setImageResource(friendResourceNormal);
            this.tab_five.setImageResource(accountResourcePre);

            this.tab_one_text.setTextColor(normalColor);
            this.tab_two_text.setTextColor(normalColor);
            this.tab_three_text.setTextColor(normalColor);
            this.tab_four_text.setTextColor(normalColor);
            this.tab_five_text.setTextColor(checkedColor);
        }

    }
    /**
     * 开启View闪烁效果
     *
     * */
    public static void startFlick() {
        Animation alphaAnimation = new AlphaAnimation(1, 0.4f);
        alphaAnimation.setDuration(300);
        alphaAnimation.setInterpolator(new LinearInterpolator());
        alphaAnimation.setRepeatCount(Animation.INFINITE);
        alphaAnimation.setRepeatMode(Animation.REVERSE);
        shopping_cart_num.setVisibility(View.VISIBLE);
        shopping_cart_num.startAnimation(alphaAnimation);
    }

    public static void stopFlick() {
        shopping_cart_num.clearAnimation();
        shopping_cart_num.setVisibility(View.INVISIBLE);
    }

}