package com.zhuye.huochebangsiji.widget;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.zhuye.huochebangsiji.R;

import java.lang.ref.WeakReference;

/**
 * Created by hpc on 2017/12/21.
 */

public class CustomProgressDialog extends Dialog implements DialogInterface.OnCancelListener{
    private WeakReference<Context> mContext = new WeakReference<>(null);
    private volatile static CustomProgressDialog sDialog;

    public CustomProgressDialog(Context context, String message) {
        super(context, R.style.CustomProgressDialog);

        mContext = new WeakReference<>(context);

        @SuppressLint("InflateParams")
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_custom_progress, null);
        TextView tvMessage = (TextView) view.findViewById(R.id.tv_message);
        if (!TextUtils.isEmpty(message)) {
            tvMessage.setText(message);
        }
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addContentView(view, lp);

        setOnCancelListener(this);
    }



    @Override
    public void onCancel(DialogInterface dialog) {
        // 点手机返回键等触发Dialog消失，应该取消正在进行的网络请求等
        Context context = mContext.get();
        if (context != null) {
            Toast.makeText(context, "cancel", Toast.LENGTH_SHORT).show();
//            MyHttpClient.cancelRequests(context);
        }
    }

    public static synchronized void showLoading(Context context) {
        showLoading(context, "loading...");
    }

    public static synchronized void showLoading(Context context, String message) {
        showLoading(context, message, true);
    }

    public static synchronized void showLoading(Context context, String message, boolean cancelable) {
        if (sDialog != null && sDialog.isShowing()) {
            sDialog.dismiss();
        }

        if (context == null || !(context instanceof Activity)) {
            return;
        }
        sDialog = new CustomProgressDialog(context, message);
        sDialog.setCancelable(cancelable);

        if (sDialog != null && !sDialog.isShowing() && !((Activity) context).isFinishing()) {
            sDialog.show();
        }
    }

    public static synchronized void stopLoading() {
        if (sDialog != null && sDialog.isShowing()) {
            sDialog.dismiss();
        }
        sDialog = null;
    }

}
