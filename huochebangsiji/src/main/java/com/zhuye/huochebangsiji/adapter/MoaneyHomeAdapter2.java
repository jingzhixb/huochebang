package com.zhuye.huochebangsiji.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhuye.huochebangsiji.R;
import com.zhuye.huochebangsiji.bean.MoneyManBean;

/**
 * Created by Administrator on 2018/1/8 0008.
 */

public class MoaneyHomeAdapter2 extends BaseQuickAdapter<MoneyManBean.DataBean.Data,BaseViewHolder> {


    public MoaneyHomeAdapter2(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, MoneyManBean.DataBean.Data item) {
        helper.setText(R.id.week,item.getWeek())
                .setText(R.id.data,item.getCreate_time())
                .setText(R.id.bank,item.getIntro())
                .setText(R.id.type,Integer.parseInt(item.getMoney())<0 ?"提现":"转账")
                .setText(R.id.money,item.getMoney());
    }

    //    @Override
//    protected int getResId() {
//        return R.layout.money_home_item;
//    }



}
