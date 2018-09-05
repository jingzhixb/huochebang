package com.zhuye.huochebang.adapter.car;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhuye.huochebang.R;
import com.zhuye.huochebang.base.BaseHolder;
import com.zhuye.huochebang.base.BaseRecycleAdapter;
import com.zhuye.huochebang.bean.DriverListBean;
import com.zhuye.huochebang.contants.Contans;

/**
 * Created by Administrator on 2018/1/8 0008.
 */

public class SingerCarAdapter extends BaseRecycleAdapter {
    public SingerCarAdapter(Context conn) {
        super(conn);
    }

    @Override
    protected int getResId() {
        return R.layout.singer_man_item;
    }

    @Override
    protected void conver(BaseHolder holder, int position) {
        DriverListBean.DataBean bean = (DriverListBean.DataBean)data.get(position);
        ImageView iv = holder.getView(R.id.tou);
        Glide.with(conn).load(Contans.BASE_URL+bean.getFace()).into(iv);

        TextView shifu = holder.getView(R.id.shifu);
        shifu.setText(bean.getName());

        //// TODO: 2018/1/9 0009 无联系方式
        TextView state = holder.getView(R.id.state);
        shifu.setText(bean.getName());


    }
}
