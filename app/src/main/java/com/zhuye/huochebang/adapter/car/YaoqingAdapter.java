package com.zhuye.huochebang.adapter.car;

import android.content.Context;

import com.zhuye.huochebang.R;
import com.zhuye.huochebang.base.BaseHolder;
import com.zhuye.huochebang.base.BaseRecycleAdapter;

/**
 * Created by Administrator on 2018/1/5 0005.
 */

public class YaoqingAdapter extends BaseRecycleAdapter {

    public YaoqingAdapter(Context conn) {
        super(conn);
    }

    @Override
    protected int getResId() {
        return R.layout.yaoting_item;
    }

    @Override
    protected void conver(BaseHolder holder, int position) {

    }
}
