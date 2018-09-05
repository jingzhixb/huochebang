package com.zhuye.huochebangsiji.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhuye.huochebangsiji.R;
import com.zhuye.huochebangsiji.bean.OilCardListBean;

/**
 * Created by Administrator on 2018/1/22 0022.
 */

public class OilCardHomeAdapter2 extends BaseQuickAdapter<OilCardListBean.DataBean.OilcardLogBean,BaseViewHolder> {


    public OilCardHomeAdapter2(int layoutResId) {
        super(layoutResId);
    }


    protected int getResId() {
        return R.layout.oilcard_item;
    }


    @Override
    protected void convert(BaseViewHolder helper, OilCardListBean.DataBean.OilcardLogBean item) {
        helper.setText(R.id.week,item.getWeek())
                .setText(R.id.dizhi,item.getCreate_time())
                .setText(R.id.name,item.getIntro())
                .setText(R.id.type,item.getCard())
                .setText(R.id.money,item.getMoney());

    }}
