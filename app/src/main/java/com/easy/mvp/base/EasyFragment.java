package com.easy.mvp.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;

/**
 * description:
 * author: tianhonglong
 * new date: 2021/7/12
 * version: v 1.0
 */
public abstract class EasyFragment extends Fragment {

    protected IBackHandledInterface mBackHandledInterface;
    protected View contentView;
    /**
     * 所有继承BackHandledFragment的子类都将在这个方法中实现物理Back键按下后的逻辑
     * FragmentActivity捕捉到物理返回键点击事件后会首先询问Fragment是否消费该事件
     * 如果没有Fragment消息时FragmentActivity自己才会消费该事件
     */
    public abstract boolean onBackPressed();// 返回true是执行fragment中的返回，返回false执行mainActivity中的返回

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!(getActivity() instanceof IBackHandledInterface)) {
            throw new ClassCastException(
                    "Hosting Activity must implement IBackHandledInterface");
        } else {
            this.mBackHandledInterface = (IBackHandledInterface) getActivity();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        if (null != contentView) {
            ViewGroup view = (ViewGroup) contentView.getParent();
            if (view != null)
                view.removeAllViews();
        } else {
            if (getLayoutId() != 0) {
                contentView = inflater.inflate(getLayoutId(), container, false);
                bindView();
            } else
                throw new RuntimeException(
                        "getViewById() can't return 0,please set id in getViewById();");
        }
        return contentView;
    }

    protected abstract int getLayoutId();

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onPause() {
        super.onPause();
    }


    /**
     * 绑定控件
     */
    protected abstract void bindView();

    /**
     * @param id the id of the view to be found
     * @return the view of the specified id, null if cannot be found
     * @hide
     */
    protected <T extends View> T findViewById(@IdRes int id) {
        if (id == id) {
            return (T) contentView.findViewById(id);
        }
        return null;
    }

    @Override
    public void onStart() {
        super.onStart();
        // 告诉FragmentActivity，当前Fragment在栈顶
        mBackHandledInterface.setSelectedFragment(this);
    }
}
