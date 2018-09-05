package com.zhuye.huochebangsiji.adapter;

import android.content.Context;

import com.zhuye.huochebangsiji.R;
import com.zhuye.huochebangsiji.base.BaseHolder;
import com.zhuye.huochebangsiji.base.BaseRecycleAdapter;


/**
 * Created by Administrator on 2018/1/8 0008.
 */

public class MoaneyHomeAdapter extends BaseRecycleAdapter {

    public MoaneyHomeAdapter(Context conn) {
        super(conn);
    }

    @Override
    protected int getResId() {
        return R.layout.money_home_item;
    }

    @Override
    protected void conver(BaseHolder holder, int position) {

    }
}
