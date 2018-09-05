package com.zhuye.huochebang.adapter.money;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhuye.huochebang.R;
import com.zhuye.huochebang.bean.MoneyLog;

/**
 * Created by Administrator on 2018/1/9 0009.
 */

public class AhouRuadapter extends BaseQuickAdapter<MoneyLog.DataBean,BaseViewHolder> {

    public void clear(){
        int itemCount = mData.size();
        mData.clear();
        this.notifyItemRangeRemoved(0,itemCount);
    }


    public AhouRuadapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, MoneyLog.DataBean item) {
        helper.setText(R.id.week,item.getWeek())
                .setText(R.id.data,item.getCreate_time())
                .setText(R.id.bank,item.getIntro())
                .setText(R.id.type,Integer.parseInt(item.getMoney())<0 ?"提现":"转账")
                .setText(R.id.money,item.getMoney());
    }
}
