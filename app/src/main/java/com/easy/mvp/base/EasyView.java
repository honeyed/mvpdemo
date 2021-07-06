package com.easy.mvp.base;

/**
 * description:定义通用的view方法
 * author: tianhonglong
 * new date: 2021/7/6
 * version: v 1.0
 */
public interface EasyView {

    void showMessage();

    void showMessage(String msg);

    void showLoadingDialog();

    void showLoadingDialog(String msg);

    void dismissDialog();
}
