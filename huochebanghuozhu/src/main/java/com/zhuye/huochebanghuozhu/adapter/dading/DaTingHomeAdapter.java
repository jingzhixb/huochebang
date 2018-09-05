package com.zhuye.huochebanghuozhu.adapter.dading;

import android.content.Context;

import com.zhuye.huochebanghuozhu.R;
import com.zhuye.huochebanghuozhu.base.BaseHolder;
import com.zhuye.huochebanghuozhu.base.BaseRecycleAdapter;

/**
 * Created by Administrator on 2018/1/16 0016.
 */

public class DaTingHomeAdapter extends BaseRecycleAdapter {

    public DaTingHomeAdapter(Context conn) {
        super(conn);
    }

    @Override
    protected int getResId() {
        return R.layout.dating_home_item;
    }

    @Override
    protected void conver(BaseHolder holder, int position) {


    }


}
