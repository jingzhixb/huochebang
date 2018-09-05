package com.zhuye.huochebangsiji.adapter;

import android.content.Context;
import android.widget.TextView;

import com.zhuye.huochebangsiji.R;
import com.zhuye.huochebangsiji.base.BaseHolder;
import com.zhuye.huochebangsiji.base.BaseRecycleAdapter;
import com.zhuye.huochebangsiji.bean.HelpBean;

/**
 * Created by Administrator on 2018/1/10 0010.
 */

public class ShengShiAdaper extends BaseRecycleAdapter {
    public ShengShiAdaper(Context conn) {
        super(conn);
    }

    @Override
    protected int getResId() {
        return R.layout.tv;
    }

    @Override
    protected void conver(BaseHolder holder, int position) {
        TextView tv =  holder.getView(R.id.shengshi);
        tv.setText(((HelpBean)data.get(position)).getName());
    }


}
