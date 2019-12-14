package com.amazing.eye;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;


/**
 * Create by: xiaoguoqing
 * Date: 2018/12/29 0029
 * description:
 */
public class ApplicationContext {
    private static Context context;
    private static Handler uiHandler = null;

    public static void initContext(Context context) {
        ApplicationContext.context = context;
        if (null == uiHandler) {
            uiHandler = new Handler();
        }
    }

    public static Context getContext() {
        return context.getApplicationContext();
    }

    public static Handler getUiHandler() {
        if (null == uiHandler) {
            throw new RuntimeException("必须先在MyApplication中初始化ApplicationContext.initContext(application)");
        }
        return uiHandler;
    }

    public static String getString(@StringRes int resId) {
        return context.getString(resId);
    }

    public static String getString(@StringRes int resId, Object... formatArgs) {
        return context.getString(resId, formatArgs);
    }

    public static int getColor(@ColorRes int resId) {
        return ContextCompat.getColor(context, resId);
    }

    public static int getDimensionPixelOffset(@DimenRes int resId) {
        return context.getResources().getDimensionPixelOffset(resId);
    }

    public static Drawable getDrawable(@DrawableRes int resId) {
        return ContextCompat.getDrawable(context, resId);
    }

    public static int getIdentifier(String name, String defType, String defPackage) {
        return context.getResources().getIdentifier(name, defType, defPackage);
    }

    public static int getSystemId(String name) {
        return getIdentifier(name, "id", "android");
    }


}
