package com.zhuye.huochebanghuozhu.adapter.me;

import android.content.Context;

import com.zhuye.huochebanghuozhu.R;
import com.zhuye.huochebanghuozhu.base.BaseHolder;
import com.zhuye.huochebanghuozhu.base.BaseRecycleAdapter;

/**
 * Created by Administrator on 2018/1/11 0011.
 */

public class MewoloatXiaoAdapter extends BaseRecycleAdapter {

    public MewoloatXiaoAdapter(Context conn) {
        super(conn);
    }

    @Override
    protected int getResId() {
        return R.layout.me_woloat_xiaofeiitem;
    }

    @Override
    protected void conver(BaseHolder holder, int position) {

    }


}
