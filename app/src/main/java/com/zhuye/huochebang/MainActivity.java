package com.zhuye.huochebang;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.ArraySet;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zhuye.huochebang.ui.CarManagerActivity;
import com.zhuye.huochebang.ui.MoneyMangerActivity;
import com.zhuye.huochebang.ui.SettingActivity;
import com.zhuye.huochebang.ui.SiJiShenqingActivity;
import com.zhuye.huochebang.utils.SharedPreferencesUtil;

import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

public class MainActivity extends BaseActivity {

    @BindView(R.id.shenqing)
    TextView shenqing;
    @BindView(R.id.cheliang)
    TextView cheliang;
    @BindView(R.id.caiwu)
    TextView caiwu;
    @BindView(R.id.setting)
    TextView setting;
    @BindView(R.id.daima)
    TextView daima;

    @Override
    protected void initData() {
        super.initData();

        addActivity(this);
        Set<String> dat = new ArraySet<>();
        dat.add("加盟商");
        JPushInterface.setTags(this, 10, dat);
    }

    @Override
    protected int getResId() {
        return R.layout.activity_main;
    }

    @OnClick({R.id.shenqing, R.id.cheliang, R.id.caiwu, R.id.setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shenqing:
                startActivity(new Intent(MainActivity.this, SiJiShenqingActivity.class));
                break;
            case R.id.cheliang:
                startActivity(new Intent(MainActivity.this, CarManagerActivity.class));
                break;
            case R.id.caiwu:
                startActivity(new Intent(MainActivity.this, MoneyMangerActivity.class));
                break;
            case R.id.setting:
                startActivity(new Intent(MainActivity.this, SettingActivity.class));
                break;
        }
    }

    @Override
    protected void initView() {
        super.initView();
        String encode = SharedPreferencesUtil.getInstance().getString("encode");
        daima.setText("加盟商代码:"+encode);
    }


    Long exitTime = 0l;

    @Override
    public void onBackPressed() {

        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
