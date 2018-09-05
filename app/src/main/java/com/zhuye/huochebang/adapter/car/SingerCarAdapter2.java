package com.zhuye.huochebang.adapter.car;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhuye.huochebang.R;
import com.zhuye.huochebang.bean.HomeCarInfoBean;
import com.zhuye.huochebang.contants.Contans;

/**
 * Created by Administrator on 2018/1/8 0008.
 */

public class SingerCarAdapter2 extends BaseQuickAdapter<HomeCarInfoBean.DataBean.Data,BaseViewHolder> {


    public SingerCarAdapter2(int layoutResId) {
        super(layoutResId);
    }


    public void clear(){
        int itemCount = mData.size();
        mData.clear();
        this.notifyItemRangeRemoved(0,itemCount);
    }

//    protected int getResId() {
//        return R.layout.singer_man_item;
//    }

//    @Override
//    protected void conver(BaseHolder holder, int position) {
//        DriverListBean.DataBean bean = (DriverListBean.DataBean)data.get(position);
//        ImageView iv = holder.getView(R.id.tou);
//        Glide.with(conn).load(Contans.BASE_URL+bean.getFace()).into(iv);
//
//        TextView shifu = holder.getView(R.id.shifu);
//        shifu.setText(bean.getName());
//
//        //// TODO: 2018/1/9 0009 无联系方式
//        TextView state = holder.getView(R.id.state);
//        shifu.setText(bean.getName());
//
//
//    }

    @Override
    protected void convert(BaseViewHolder helper, HomeCarInfoBean.DataBean.Data item) {
        helper.setText(R.id.shifu,item.getName())
                .setText(R.id.state,item.getCar_driverid())
                .addOnClickListener(R.id.delete);
        Glide.with(mContext).load(Contans.BASE_URL+item.getFace()).into((ImageView) helper.getView(R.id.tou));
    }
}
