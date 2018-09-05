package com.zhuye.huochebangsiji.adapter;

import android.support.annotation.LayoutRes;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhuye.huochebangsiji.R;
import com.zhuye.huochebangsiji.bean.HuoYuanBean;
import com.zhuye.huochebangsiji.contants.Contans;
import com.zhuye.huochebangsiji.widget.RoundedCornerImageView;

/**
 * Created by Administrator on 2018/1/16 0016.
 */

public class DaTingHomeAdapter2 extends BaseQuickAdapter< HuoYuanBean.DataBean,BaseViewHolder> {


    public DaTingHomeAdapter2(@LayoutRes int layoutResId) {
        super(layoutResId);
    }


//    @Override
//    protected void conver(BaseHolder holder, int position) {
//
//        TextView dizhi = holder.getView(R.id.dizhi);
//        TextView time = holder.getView(R.id.time);
//        TextView desc = holder.getView(R.id.desc);
//        ImageView ads = holder.getView(ads);
//        RoundedCornerImageView tou = holder.getView(R.id.tou);
//        TextView name = holder.getView(R.id.name);
//        //ImageView home_phone = holder.getView(R.id.home_phone);
//
//        HuoYuanBean.DataBean bean =  (HuoYuanBean.DataBean)data.get(position);
//
//       // dizhi.setText(bean.getStart_addr()+"-"+bean.getEnd_addr());
//        //time.setText(bean.getCreate_time());
////        desc.setText(bean.getLeng()+"   " + bean.getModel() + "   " + bean.getWeight()+ "   "+
////           bean.getType_name() + "   " + bean.getPrice());
//        Glide.with(conn).load(Contans.BASE_URL+bean.getFace()).into(tou);
//        //name.setText(bean.getName());
//        if(bean.getType().equals("1")){
//            ads.setVisibility(View.VISIBLE);
//            Glide.with(conn).load(Contans.BASE_URL+bean.getUrl()).into(ads);
//        }else {
//            ads.setVisibility(View.GONE);
//        }
//    }


    public void clear(){
        int itemCount = mData.size();
        mData.clear();
        this.notifyItemRangeRemoved(0,itemCount);
    }

    @Override
    protected void convert(BaseViewHolder helper, HuoYuanBean.DataBean item) {
        helper.setText(R.id.dizhi,item.getStart_addr()+"-"+item.getEnd_addr())
                .setText(R.id.time,item.getCreate_time())
                .setText(R.id.name,item.getName())
                .setText(R.id.desc,item.getLeng()+"   " + item.getModel() + "   " + item.getWeight()+ "吨   "+
                        item.getType_name() + "   " + item.getPrice()+"元/吨")
                .addOnClickListener(R.id.ads)
                .addOnClickListener(R.id.home_phone);

        Glide.with(mContext).load(Contans.BASE_URL+item.getFace()).into((RoundedCornerImageView) helper.getView(R.id.tou));

        ImageView ads = helper.getView(R.id.ads);

        if(item.getType().equals("1")){
            ads.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(Contans.BASE_URL+item.getUrl()).into(ads);
        }else {
            ads.setVisibility(View.GONE);
        }
    }
}
