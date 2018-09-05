package com.zhuye.huochebangsiji.adapter;

import android.content.Context;

import com.zhuye.huochebangsiji.R;
import com.zhuye.huochebangsiji.base.BaseHolder;
import com.zhuye.huochebangsiji.base.BaseRecycleAdapter;

/**
 * Created by Administrator on 2018/1/22 0022.
 */

public class OilCardHomeAdapter extends BaseRecycleAdapter {

    public OilCardHomeAdapter(Context conn) {
        super(conn);
    }

    @Override
    protected int getResId() {
        return R.layout.oilcard_item;
    }

    @Override
    protected void conver(BaseHolder holder, int position) {

    }
}
