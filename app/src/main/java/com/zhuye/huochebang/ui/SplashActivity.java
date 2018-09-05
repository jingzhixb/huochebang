package com.zhuye.huochebang.ui;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.zhuye.huochebang.BaseActivity;
import com.zhuye.huochebang.R;
import com.zhuye.huochebang.utils.SharedPreferencesUtil;

public class SplashActivity extends BaseActivity {


    private Handler mhandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };



    private Boolean isfisr;


    @Override
    protected int getResId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initData() {
        super.initData();

        isfisr =  SharedPreferencesUtil.getInstance().getBoolean("isfisr");
        if(!isfisr){
            startActivity(new Intent(SplashActivity.this, GuideActivity.class));
            finish();
        }else {
            mhandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                }
            },2000);
        }
    }

}
