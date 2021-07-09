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

    private EVM() {}
    private Map<String, WeakReference<EasyView>> views = new HashMap<>();

    public void register(EasyView easyView) {
        managerView(easyView, true);
    }

    public <T extends EasyView> T get(Class<T> clazz) {
        EasyView easyView = views.get(clazz.getSimpleName()).get();
        if(easyView == null) {
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

    public void unregister(EasyView easyView) {
        managerView(easyView, false);
    }

    private void managerView(EasyView easyView, boolean b) {
        Class[] classes = easyView.getClass().getInterfaces();
        for (Class clazz : classes) {
            if (clazz.isAssignableFrom(EasyView.class)) {
                if(b) {
                    views.put(clazz.getSimpleName(),new WeakReference<EasyView>(easyView));
                } else {
                    views.remove(clazz.getSimpleName());
                }
            }
        }
    }

    private static class InnerClass {
        private static EVM easyPresent = new EVM();
    }

    public static EVM ins() {
        return InnerClass.easyPresent;
    }
}
