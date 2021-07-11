package com.easy.mvp.base;

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

    private Map<String, WeakReference<EasyView>> views = new HashMap<>();

    public static void register(EasyView easyView) {
        EVM.ins().managerView(easyView, true);
    }

    private <T extends EasyView> T getView(Class<T> clazz) {
        EasyView easyView = views.get(clazz.getSimpleName()).get();
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

    public static <T extends EasyView> T get(Class<T> clazz) {
        return EVM.ins().getView(clazz);
    }

    public static void unregister(EasyView easyView) {
        EVM.ins().managerView(easyView, false);
    }

    private void managerView(EasyView easyView, boolean registerOrNot) {
        Class[] classes = easyView.getClass().getInterfaces();
        for (Class clazz : classes) {
            if (clazz.isAssignableFrom(EasyView.class)) {
                if (registerOrNot) {
                    views.put(clazz.getSimpleName(), new WeakReference<>(easyView));
                } else {
                    views.remove(clazz.getSimpleName());
                }
            }
        }
    }

    private static class InnerClass {
        private static EVM easyPresent = new EVM();
    }

    private static EVM ins() {
        return InnerClass.easyPresent;
    }
}
