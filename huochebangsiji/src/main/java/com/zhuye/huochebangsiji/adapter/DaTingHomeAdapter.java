package com.zhuye.huochebangsiji.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhuye.huochebangsiji.R;
import com.zhuye.huochebangsiji.base.BaseHolder;
import com.zhuye.huochebangsiji.base.BaseRecycleAdapter;
import com.zhuye.huochebangsiji.bean.HuoYuanBean;
import com.zhuye.huochebangsiji.contants.Contans;
import com.zhuye.huochebangsiji.widget.RoundedCornerImageView;

/**
 * Created by Administrator on 2018/1/16 0016.
 */

public class DaTingHomeAdapter extends BaseRecycleAdapter {

    public DaTingHomeAdapter(Context conn) {
        super(conn);
    }

    @Override
    protected int getResId() {
        return R.layout.dating_item;
    }

    @Override
    protected void conver(BaseHolder holder, int position) {

        TextView dizhi = holder.getView(R.id.dizhi);
        TextView time = holder.getView(R.id.time);
        TextView desc = holder.getView(R.id.desc);
        ImageView ads = holder.getView(R.id.ads);
        RoundedCornerImageView tou = holder.getView(R.id.tou);
        TextView name = holder.getView(R.id.name);
        //ImageView home_phone = holder.getView(R.id.home_phone);

        HuoYuanBean.DataBean bean =  (HuoYuanBean.DataBean)data.get(position);

        dizhi.setText(bean.getStart_addr()+"-"+bean.getEnd_addr());
        time.setText(bean.getCreate_time());
        desc.setText(bean.getLeng()+"   " + bean.getModel() + "   " + bean.getWeight()+ "   "+
           bean.getType_name() + "   " + bean.getPrice());
        Glide.with(conn).load(Contans.BASE_URL+bean.getFace()).into(tou);
        name.setText(bean.getName());
        if(bean.getType().equals("1")){
            ads.setVisibility(View.VISIBLE);
            Glide.with(conn).load(Contans.BASE_URL+bean.getUrl()).into(ads);
        }else {
            ads.setVisibility(View.GONE);
        }
    }
}
