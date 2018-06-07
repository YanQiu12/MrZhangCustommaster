package com.mrzhangcustom_library.util;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by 许佛 on 2018/6/7.
 */
public class DensityUtil {
    /**
     * 将px值转换为 dip或dp值，保证尺寸大小不变
     *
     * @param pxValue 像素值
     * @param context Context 对象
     * @return dp值
     */
    public static int px2dip(float pxValue, Context context) {
        float scale = getDensity(context);

        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue dip数值
     * @param context  Context 对象
     * @return 像素值
     */
    public static int dip2px(float dipValue, Context context) {
        float scale = getDensity(context);
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue 像素值
     * @param context Context 对象
     * @return 返回sp数值
     */
    public static int px2sp(float pxValue, Context context) {
        float scale = getDensity(context);

        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue sp数值
     * @param context Context 对象
     * @return 返回像素值
     */
    public static int sp2px(float spValue, Context context) {
        float scale = getDensity(context);
        return (int) (spValue * scale + 0.5f);
    }

    /**
     * 取得手机屏幕的密度
     *
     * @param context 上下文
     * @return 手机屏幕的密度
     */
    public static float getDensity(Context context) {
        float scale = context.getResources().getDisplayMetrics().density;
        return scale;
    }

    /**
     * 获取屏幕高
     * @param activity
     * @return 手机屏幕的高度
     */
    public static int getScreenHeight(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    /**
     * 获取屏幕宽
     * @param activity
     * @return 手机屏幕的宽度
     */
    public static int getScreenWidth(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }
}
