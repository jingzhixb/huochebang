package com.zhuye.huochebangsiji.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.zhuye.huochebangsiji.MainActivity;
import com.zhuye.huochebangsiji.R;
import com.zhuye.huochebangsiji.base.BaseActivity;
import com.zhuye.huochebangsiji.utils.SharedPreferencesUtil;

public class SplashActivity extends BaseActivity {

    private Handler mhandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };



    private Boolean isfisr;

    @Override
    protected void requestBefore() {
        super.requestBefore();
        //getWindow().getWindowManager()
    }

    @Override
    protected int getResId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initData() {
        super.initData();

        //startActivity(new Intent(SplashActivity.this, LoginActivity.class));

        isfisr =  SharedPreferencesUtil.getInstance().getBoolean("isfisr");
        if(!isfisr){
            startActivity(new Intent(SplashActivity.this, GuideActivity.class));
            finish();
        }else {
            mhandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                }
            },2000);
        }
    }
}
