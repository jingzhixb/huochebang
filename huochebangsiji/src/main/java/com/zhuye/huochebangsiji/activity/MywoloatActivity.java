package com.zhuye.huochebangsiji.activity;

import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.zhuye.huochebangsiji.R;
import com.zhuye.huochebangsiji.adapter.MyWaloatAdapter;
import com.zhuye.huochebangsiji.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;



public class MywoloatActivity extends BaseActivity {


    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.viewpagertab)
    SmartTabLayout viewpagertab;
    @BindView(R.id.message_viewpager)
    ViewPager messageViewpager;

    @Override
    protected int getResId() {
        return R.layout.activity_mywoloat;
    }


    @Override
    protected void initView() {
        super.initView();
        MyWaloatAdapter adapter = new MyWaloatAdapter(getSupportFragmentManager());
        messageViewpager.setAdapter(adapter);
        viewpagertab.setViewPager(messageViewpager);
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
