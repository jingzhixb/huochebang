package com.zhuye.huochebangsiji.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhuye.huochebangsiji.R;
import com.zhuye.huochebangsiji.bean.JiaMengBean;
import com.zhuye.huochebangsiji.contants.Contans;

/**
 * Created by Administrator on 2018/1/18 0018.
 */

public class JiaMengAdapter2 extends BaseQuickAdapter<JiaMengBean.DataBean,BaseViewHolder> {


    public JiaMengAdapter2(int layoutResId) {
        super(layoutResId);
    }

//    @Override
    protected int getResId() {
        return R.layout.jiameng_item;
    }

//    @Override
//    protected void conver(BaseHolder holder, int position) {
//
//        ImageView imageView = holder.getView(R.id.tou);
//        TextView name = holder.getView(R.id.name);
//        TextView daima = holder.getView(R.id.daima);
//
//        JiaMengBean.DataBean bean = (JiaMengBean.DataBean) data.get(position);
//        Glide.with(conn).load(Contans.BASE_URL+bean.getFace()).into(imageView);
//
//        name.setText(bean.getName());
//        daima.setText(bean.getEncode());
//    }

    @Override
    protected void convert(BaseViewHolder helper, JiaMengBean.DataBean item) {
        helper.setText(R.id.name,item.getName())
                .setText(R.id.daima,item.getEncode())
                .addOnClickListener(R.id.refuse);
        Glide.with(mContext).load(Contans.BASE_URL+item.getFace()).into((ImageView) helper.getView(R.id.tou));


    }
}
