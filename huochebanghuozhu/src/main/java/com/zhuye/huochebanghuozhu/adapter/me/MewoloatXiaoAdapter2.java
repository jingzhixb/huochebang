package com.zhuye.huochebanghuozhu.adapter.me;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhuye.huochebanghuozhu.R;
import com.zhuye.huochebanghuozhu.bean.XiaoFeiBean;

/**
 * Created by Administrator on 2018/1/11 0011.
 */

public class MewoloatXiaoAdapter2 extends BaseQuickAdapter<XiaoFeiBean.DataBean,BaseViewHolder> {


    public MewoloatXiaoAdapter2(int layoutResId) {
        super(layoutResId);
    }

//    @Override
//    protected int getResId() {
//        return R.layout.me_woloat_xiaofeiitem;
//    }

//    @Override
//    protected void conver(BaseHolder holder, int position) {
//
//    }


    @Override
    protected void convert(BaseViewHolder helper, XiaoFeiBean.DataBean item) {

        helper.setText(R.id.week,item.getWeek())
                .setText(R.id.dizhi,item.getCreate_time())
                .setText(R.id.type,item.getCode())
                .setText(R.id.name,item.getIntro())
                .setText(R.id.money,item.getMoney());
    }
}
