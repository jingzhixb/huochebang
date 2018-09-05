package com.zhuye.huochebangsiji.activity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.zhuye.huochebangsiji.R;
import com.zhuye.huochebangsiji.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class VipDetailActivity extends BaseActivity {

    @BindView(R.id.vip_back)
    ImageView vipBack;
    @BindView(R.id.tou)
    RelativeLayout tou;
    @BindView(R.id.webview_vip)
    WebView webviewVip;
    private Intent intent;
    private String html= "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_vip_detail);

    }

    @Override
    protected void initView() {
        super.initView();
        intent = getIntent();
        html = intent.getStringExtra("html");
        webviewVip.getSettings().setJavaScriptEnabled(true);
        webviewVip.loadDataWithBaseURL("file://",html, "text/html","UTF-8","about:blank");
    }

    @Override
    protected int getResId() {
        return R.layout.activity_vip_detail;
    }

    @OnClick(R.id.vip_back)
    public void onViewClicked() {
        finish();
    }
}
