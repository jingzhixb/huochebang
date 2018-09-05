package com.zhuye.huochebangsiji.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhuye.huochebangsiji.R;
import com.zhuye.huochebangsiji.base.BaseHolder;
import com.zhuye.huochebangsiji.base.BaseRecycleAdapter;
import com.zhuye.huochebangsiji.bean.JiaMengBean;
import com.zhuye.huochebangsiji.contants.Contans;

/**
 * Created by Administrator on 2018/1/18 0018.
 */

public class JiaMengAdapter extends BaseRecycleAdapter {

    public JiaMengAdapter(Context conn) {
        super(conn);
    }

    @Override
    protected int getResId() {
        return R.layout.jiameng_item;
    }

    @Override
    protected void conver(BaseHolder holder, int position) {

        ImageView imageView = holder.getView(R.id.tou);
        TextView name = holder.getView(R.id.name);
        TextView daima = holder.getView(R.id.daima);

        JiaMengBean.DataBean bean = (JiaMengBean.DataBean) data.get(position);
        Glide.with(conn).load(Contans.BASE_URL+bean.getFace()).into(imageView);

        name.setText(bean.getName());
        daima.setText(bean.getEncode());
    }
}
