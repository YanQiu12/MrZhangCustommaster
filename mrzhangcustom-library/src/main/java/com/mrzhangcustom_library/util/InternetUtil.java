package com.mrzhangcustom_library.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by 许佛 on 2018/5/24.
 */

public class InternetUtil {
    /**
     * 判断手机是否有网络连接
     * @param context 上下文
     * @return 有网返回ture，没网返回false
     */
    public static boolean checkInternet(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return isConnected(connectivityManager,context)||isWifi(connectivityManager,context);
    }
    /**
     * 判断手机是否有数据连接
     * @param context 上下文
     * @return 有数据连接返回ture，没数据连接返回false
     */
    public static boolean isConnected(ConnectivityManager connectivityManager, Context context) {
        if ( connectivityManager != null) {
            NetworkInfo info = connectivityManager.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * 判断手机是否有Wifi连接
     * @param context 上下文
     * @return 有Wifi连接返回ture，没Wifi连接返回false
     */
    public static boolean isWifi(ConnectivityManager connectivityManager, Context context) {
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;

    }
}
