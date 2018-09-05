package com.zhuye.huochebang.adapter.money;

import android.content.Context;

import com.zhuye.huochebang.R;
import com.zhuye.huochebang.base.BaseHolder;
import com.zhuye.huochebang.base.BaseRecycleAdapter;

/**
 * Created by Administrator on 2018/1/9 0009.
 */

public class AhouRuadapter2 extends BaseRecycleAdapter {

    public AhouRuadapter2(Context conn) {
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
