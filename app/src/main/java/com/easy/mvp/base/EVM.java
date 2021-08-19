package com.easy.mvp.base;

import com.easy.mvp.business.login.LoginContract;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * description:
 * author: tianhonglong
 * new date: 2021/7/9
 * version: v 1.0
 */
public class EVM {

    private EVM() {
    }

    private Map<String, EasyView> views = new HashMap<>();
    private Map<String, EasyPresent> presents = new HashMap<>();

    private <T extends EasyView> T getView(Class<T> clazz) {
        EasyView easyView = views.get(clazz.getSimpleName());
        if (easyView == null) {
            try {
                return clazz.newInstance();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        return (T) easyView;
    }

    private <T extends EasyPresent> T getPre(Class<T> clazz) {
        EasyPresent present = presents.get(clazz.getSimpleName());
        if (present == null) {
            try {
                T t = clazz.newInstance();
                presents.put(clazz.getSimpleName(), t);
                return t;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        return (T) present;
    }

    private void managerView(EasyView easyView, boolean registerOrNot) {
        Class[] classes = easyView.getClass().getInterfaces();
        for (Class clazz : classes) {
            if (EasyView.class.isAssignableFrom(clazz)) {
                if (registerOrNot) {
                    views.put(clazz.getSimpleName(), easyView);
                } else {
                    views.remove(clazz.getSimpleName());
                }
            }
        }
    }

    public static void register(EasyView easyView) {
        EVM.ins().managerView(easyView, true);
    }

    public static void unregister(EasyView easyView) {
        EVM.ins().managerView(easyView, false);
    }

    public static <T extends EasyView> T getV(Class<T> clazz) {
        return EVM.ins().getView(clazz);
    }

    public static <T extends EasyPresent> T getP(Class<T> clazz) {
        return EVM.ins().getPre(clazz);
    }

    private static class InnerClass {
        private static EVM easyPresent = new EVM();
    }

    private static EVM ins() {
        return InnerClass.easyPresent;
    }
}
