package com.zhuye.huochebanghuozhu.adapter.me;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhuye.huochebanghuozhu.R;
import com.zhuye.huochebanghuozhu.bean.RechargeBean;

/**
 * Created by Administrator on 2018/1/11 0011.
 */

public class MewoloatAdapter2 extends BaseQuickAdapter<RechargeBean.DataBean,BaseViewHolder> {

    public MewoloatAdapter2(int layoutResId) {
        super(layoutResId);
    }

//    @Override
//    protected int getResId() {
//        return R.layout.me_woloat_item;
//    }



    @Override
    protected void convert(BaseViewHolder helper, RechargeBean.DataBean item) {
        helper.setText(R.id.me_mywoloat_name,item.getShopname())
                .setText(R.id.dizhi,item.getAddress())
                .setText(R.id.phone,item.getMobile());
    }
}
