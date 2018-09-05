package com.zhuye.huochebang.adapter.car;

import android.content.Context;
import android.widget.TextView;

import com.zhuye.huochebang.R;
import com.zhuye.huochebang.base.BaseHolder;
import com.zhuye.huochebang.base.BaseRecycleAdapter;
import com.zhuye.huochebang.bean.HomeCarInfoBean;

/**
 * Created by Administrator on 2018/1/5 0005.
 */

public class CarHomeAdapter extends BaseRecycleAdapter {
    public CarHomeAdapter(Context conn) {
        super(conn);
    }

    @Override
    protected int getResId() {
        return R.layout.carhome_item;
    }

    @Override
    protected void conver(BaseHolder holder, int position) {

        ((TextView)holder.getView(R.id.chepai)).setText(((HomeCarInfoBean.DataBean)data.get(position)).getLicense());
        if(((HomeCarInfoBean.DataBean)data.get(position)).getType().equals("0")){
            ((TextView)holder.getView(R.id.state)).setText("空闲中");
        }else if(((HomeCarInfoBean.DataBean)data.get(position)).getType().equals("1")){
            ((TextView)holder.getView(R.id.state)).setText("出车中");
        }

        //司机处理
    }
}
