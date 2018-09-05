package com.zhuye.huochebang.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhuye.huochebang.BaseActivity;
import com.zhuye.huochebang.R;
import com.zhuye.huochebang.data.http.GetData;
import com.zhuye.huochebang.utils.CacheUtils;
import com.zhuye.huochebang.utils.SharedPreferencesUtil;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity {


    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.mima)
    ImageView mima;
    @BindView(R.id.huancun)
    TextView huancun;
    @BindView(R.id.logout)
    Button logout;
    @BindView(R.id.pingjia)
    RelativeLayout pingjia;
    @BindView(R.id.xuzhi)
    RelativeLayout xuzhi;
    @BindView(R.id.tiaokuan)
    RelativeLayout tiaokuan;
    @BindView(R.id.guanyu)
    RelativeLayout guanyu;
    @BindView(R.id.llpass)
    RelativeLayout llpass;

    @Override
    protected int getResId() {
        return R.layout.activity_setting;
    }


    private Handler handler = new Handler();

    @OnClick({R.id.llpass,R.id.back, R.id.mima, R.id.huancun, R.id.logout, R.id.pingjia, R.id.xuzhi, R.id.tiaokuan, R.id.guanyu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tiaokuan:
                Intent inte1 = new Intent(SettingActivity.this, CommonActivity.class);
                inte1.putExtra("type", 7);
                startActivity(inte1);

                break;
            case R.id.guanyu:
                Intent inte = new Intent(SettingActivity.this, CommonActivity.class);
                inte.putExtra("type", 6);
                startActivity(inte);

                break;
            case R.id.pingjia:
                Intent inten = new Intent(SettingActivity.this, CommonActivity.class);
                inten.putExtra("type", 4);
                startActivity(inten);

                break;
            case R.id.xuzhi:
//               Intent intent = new Intent(SettingActivity.this,CommonActivity.class);
//               intent.putExtra("type",5);
//                startActivity(intent);
                break;
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
                            huancun.setText(CacheUtils.getCacheSize(file));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },1000);
                break;
            case R.id.logout:
//                SharedPreferencesUtil.getInstance().putString("token","");
//                startActivity(new Intent(SettingActivity.this,LoginActivity.class));
//                //// TODO: 2018/1/10 0010
//                finish();
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
            exitActivitys();
            //// TODO: 2018/1/10 0010
        }
    }

    File file;
    @Override
    protected void initData() {
        super.initData();
       file =new File(this.getCacheDir().getPath());
        try {
            huancun.setText(CacheUtils.getCacheSize(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
        addActivity(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
