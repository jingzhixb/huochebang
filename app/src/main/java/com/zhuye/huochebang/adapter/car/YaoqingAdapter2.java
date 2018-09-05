package com.zhuye.huochebang.adapter.car;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhuye.huochebang.R;
import com.zhuye.huochebang.bean.DriverListBean;
import com.zhuye.huochebang.contants.Contans;

/**
 * Created by Administrator on 2018/1/5 0005.
 */

public class YaoqingAdapter2 extends BaseQuickAdapter<DriverListBean.DataBean,BaseViewHolder> {


    public YaoqingAdapter2(int layoutResId) {
        super(layoutResId);
    }

//    @Override
//    protected int getResId() {
//        return R.layout.yaoting_item;
//    }
//
//    @Override
//    protected void conver(BaseHolder holder, int position) {
//
//    }


    public void clear(){
        int itemCount = mData.size();
        mData.clear();
        this.notifyItemRangeRemoved(0,itemCount);
    }


    @Override
    protected void convert(BaseViewHolder helper, DriverListBean.DataBean item) {
        helper.setText(R.id.blacknumber_name,item.getName())
                .addOnClickListener(R.id.blacknumber_yichu);
        Glide.with(mContext).load(Contans.BASE+item.getFace()).into((ImageView) helper.getView(R.id.blacknumber_iv));
    }
}
