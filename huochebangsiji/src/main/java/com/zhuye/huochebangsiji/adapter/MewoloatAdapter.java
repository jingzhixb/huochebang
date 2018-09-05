package com.zhuye.huochebangsiji.adapter;

import android.content.Context;

import com.zhuye.huochebangsiji.R;
import com.zhuye.huochebangsiji.base.BaseHolder;
import com.zhuye.huochebangsiji.base.BaseRecycleAdapter;

import java.util.List;

;

/**
 * Created by Administrator on 2018/1/11 0011.
 */

public class MewoloatAdapter extends BaseRecycleAdapter {
    public MewoloatAdapter(Context conn) {
        super(conn);
    }

    public MewoloatAdapter(Context conn, List data) {
        super(conn, data);
    }

    @Override
    protected int getResId() {
        return R.layout.me_woloat_item;
    }

    @Override
    protected void conver(BaseHolder holder, int position) {

    }


}
