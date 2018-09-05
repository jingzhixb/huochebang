package com.zhuye.huochebanghuozhu.adapter.fabu;

import android.content.Context;

import com.zhuye.huochebanghuozhu.R;
import com.zhuye.huochebanghuozhu.base.BaseHolder;
import com.zhuye.huochebanghuozhu.base.BaseRecycleAdapter;

import java.util.List;

/**
 * Created by Administrator on 2018/1/11 0011.
 */

public class FaBuDetailAdapter extends BaseRecycleAdapter {
    public FaBuDetailAdapter(Context conn) {
        super(conn);
    }

    public FaBuDetailAdapter(Context conn, List data) {
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
