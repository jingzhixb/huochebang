package com.zhuye.huochebanghuozhu.adapter.me;

import android.content.Context;
import android.widget.TextView;

import com.zhuye.huochebanghuozhu.R;
import com.zhuye.huochebanghuozhu.base.BaseHolder;
import com.zhuye.huochebanghuozhu.base.BaseRecycleAdapter;
import com.zhuye.huochebanghuozhu.bean.HelpBean;

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
