package com.zhuye.huochebangsiji.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhuye.huochebangsiji.R;
import com.zhuye.huochebangsiji.base.BaseActivity;
import com.zhuye.huochebangsiji.data.GetData;
import com.zhuye.huochebangsiji.utils.CacheUtils;
import com.zhuye.huochebangsiji.utils.SharedPreferencesUtil;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity {


    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.mima)
    ImageView mima;

    @BindView(R.id.logout)
    Button logout;
    @BindView(R.id.llpass)
    RelativeLayout llpass;
    @BindView(R.id.huancun)
    TextView huancun;

    @Override
    protected int getResId() {
        return R.layout.activity_setting;
    }


    File file;
    @Override
    protected void initData() {
        super.initData();
        addActivity(this);
        file =new File(this.getCacheDir().getPath());
        try {
            huancun.setText(CacheUtils.getCacheSize(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private Handler handler = new Handler();

    @OnClick({R.id.llpass,R.id.back, R.id.mima, R.id.huancun, R.id.logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.llpass:
            case R.id.mima:
                startActivity(new Intent(SettingActivity.this, Editpass1Activity.class));
                break;
            case R.id.huancun:
                //清楚缓存
                CacheUtils.cleanInternalCache(getApplicationContext());
                huancun.setText("0.00KB");
                try {
                    if(CacheUtils.getCacheSize(file).equals("0.00KB")){
                        toast("没有缓存");
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            huancun.setText("0.00KB");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },1000);
                break;
            case R.id.logout:
                GetData.logout(0, this, 100);
                break;
        }
    }

    @Override
    public void success(int requestcode, Object o) {
        super.success(requestcode, o);
        if (requestcode == 100) {
            SharedPreferencesUtil.getInstance().putString("token", "");
            startActivity(new Intent(SettingActivity.this, LoginActivity.class));
            //exitActivitys();
            //// TODO: 2018/1/10 0010
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
