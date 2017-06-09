package com.rongke.mifan.pulldownmenu;

import android.widget.PopupWindow;

import java.lang.reflect.Method;

public class UIHelper {
	/**
	 * @author lmz
	 * @date 2015-2-8
     * Set whether this window is touch modal or if outside touches will be sent
     * to
     * other windows behind it.
     * 点击外部区域,弹窗不消失,但是点击事件会向下面的activity传递
     */
    public static void setPopupWindowTouchModal(PopupWindow popupWindow,
            boolean touchModal) {
        if (null == popupWindow) {
            return;
        }
        Method method;
        try {

            method = PopupWindow.class.getDeclaredMethod("setTouchModal",
                    boolean.class);
            method.setAccessible(true);
            method.invoke(popupWindow, touchModal);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
