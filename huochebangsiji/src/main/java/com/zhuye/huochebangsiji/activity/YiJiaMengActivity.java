package com.zhuye.huochebangsiji.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.zhuye.huochebangsiji.R;
import com.zhuye.huochebangsiji.adapter.YiJiaMengAdapter;
import com.zhuye.huochebangsiji.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class YiJiaMengActivity extends BaseActivity {


    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.jiameng_yijiangmeng)
    RecyclerView jiamengYijiangmeng;

    @Override
    protected int getResId() {
        return R.layout.activity_yi_jia_meng;
    }


    @Override
    protected void initView() {
        super.initView();

        adapter = new YiJiaMengAdapter(this);
        jiamengYijiangmeng.setAdapter(adapter);
        jiamengYijiangmeng.setLayoutManager(new LinearLayoutManager(this));

    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
