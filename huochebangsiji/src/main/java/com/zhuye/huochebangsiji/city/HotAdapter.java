package com.zhuye.huochebangsiji.city;

import android.content.Context;
import android.widget.TextView;

import com.zhuye.huochebangsiji.R;
import com.zhuye.huochebangsiji.base.BaseHolder;
import com.zhuye.huochebangsiji.base.BaseRecycleAdapter;


/**
 * Created by Administrator on 2017/12/12 0012.
 */

public class HotAdapter extends BaseRecycleAdapter {


    public HotAdapter(Context conn) {
        super(conn);
    }

    @Override
    protected int getResId() {
        return R.layout.tv;
    }

    @Override
    protected void conver(BaseHolder holder, int position) {
        TextView tv = holder.getView(R.id.name);
        tv.setText(data.get(position)+"");
    }
}
