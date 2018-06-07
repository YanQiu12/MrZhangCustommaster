package com.mrzhangcustom_library.util;

import android.app.ProgressDialog;
import android.content.Context;

public class ProgressDialogUtil {

    private static ProgressDialog progressDlg = null;

    private ProgressDialogUtil() {}
    /**
     * 启动进度条
     * @param ctx 当前的activity
     * @param content 进度条显示的信息
     */
    public static void show(Context ctx, String content) {
        if (null == progressDlg) {
            progressDlg = new ProgressDialog(ctx);
            progressDlg.setProgressStyle(ProgressDialog.STYLE_SPINNER);// 设置进度条的形式为圆形转动的进度条
            //progressDlg.setTitle("提示");//设置进度条标题
            progressDlg.setMessage(content);//提示的消息
            progressDlg.setIndeterminate(false);
            progressDlg.setCancelable(false);
            //progressDlg.setIcon(R.drawable.ic_launcher_scale);
            progressDlg.show();
        }
    }

    /**
     * 结束进度条
     */
    public static void dismiss() {
        if (null != progressDlg) {
            progressDlg.dismiss();
            progressDlg = null;
        }
    }
}
