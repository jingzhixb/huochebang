package com.zhuye.huochebanghuozhu.adapter.fabu;

import android.content.Context;
import android.widget.TextView;

import com.zhuye.huochebanghuozhu.R;
import com.zhuye.huochebanghuozhu.base.BaseHolder;
import com.zhuye.huochebanghuozhu.base.BaseRecycleAdapter;
import com.zhuye.huochebanghuozhu.bean.GoodsDeailBean;

/**
 * Created by Administrator on 2018/1/19 0019.
 */

public class DetailItemAdapter extends BaseRecycleAdapter {

    public DetailItemAdapter(Context conn) {
        super(conn);
    }

    @Override
    protected int getResId() {
        return R.layout.detail_ittem;
    }

    @Override
    protected void conver(BaseHolder holder, int position) {

       TextView dizhi =  holder.getView(R.id.dizhi);
       TextView jiee =  holder.getView(R.id.jiee);
       TextView desc =  holder.getView(R.id.desc);
        GoodsDeailBean.DataBean bean = (GoodsDeailBean.DataBean) data.get(position);

        dizhi.setText(bean.getStart_city());
        jiee.setText(bean.getPrice()+"元/吨");
        desc.setText(bean.getLeng() + "   "+bean.getModels()+"    "+bean.getGood_type()+"/"+bean.getWeight());
    }
}
