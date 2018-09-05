package com.zhuye.huochebangsiji.adapter;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhuye.huochebangsiji.R;
import com.zhuye.huochebangsiji.base.BaseHolder;
import com.zhuye.huochebangsiji.base.BaseRecycleAdapter;
import com.zhuye.huochebangsiji.bean.VipListBean;

/**
 * Created by Administrator on 2018/1/17 0017.
 */

public class JinFenAdapter extends BaseRecycleAdapter {

    public JinFenAdapter(Context conn) {
        super(conn);
    }

    @Override
    protected int getResId() {
        return R.layout.jifen_item;
    }

    @Override
    protected void conver(BaseHolder holder, int position) {
          TextView jinemoney = holder.getView(R.id.jinemoney);
          LinearLayout jifen_root = holder.getView(R.id.jifen_root);
          TextView yueshu = holder.getView(R.id.yueshu);
          jinemoney.setText(((VipListBean.DataBean)data.get(position)).getMoney()+"元");
         yueshu.setText(((VipListBean.DataBean)data.get(position)).getMonth()+"个月");
        Boolean isselected = ((VipListBean.DataBean)data.get(position)).getSelected();
        if(isselected){
            jifen_root.setBackgroundResource(R.drawable.queren_shape);
        }else {
            jifen_root.setBackgroundResource(R.drawable.edit_shape);
        }
    }
}
