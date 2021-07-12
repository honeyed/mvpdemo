package com.easy.mvp.business.main;

import com.easy.mvp.R;
import com.easy.mvp.base.EasyFragment;

/**
 * description:
 * author: tianhonglong
 * new date: 2021/7/12
 * version: v 1.0
 */
public class DiscoverFragment extends EasyFragment {
    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void bindView() {

    }
}
