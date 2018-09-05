package com.zhuye.huochebang;

import android.app.Application;
import android.content.Context;

import com.zhuye.huochebang.utils.SharedPreferencesUtil;

/**
 * Created by Administrator on 2018/1/3 0003.
 */

public class HuoCheApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferencesUtil.init(this,"config", Context.MODE_PRIVATE);
    }
}
