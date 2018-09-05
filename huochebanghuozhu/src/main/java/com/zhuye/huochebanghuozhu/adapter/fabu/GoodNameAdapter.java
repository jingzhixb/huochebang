package com.zhuye.huochebanghuozhu.adapter.fabu;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import com.zhuye.huochebanghuozhu.R;
import com.zhuye.huochebanghuozhu.activity.PeiSongBean;
import com.zhuye.huochebanghuozhu.base.BaseHolder;
import com.zhuye.huochebanghuozhu.base.BaseRecycleAdapter;

/**
 * Created by Administrator on 2018/1/11 0011.
 */

public class GoodNameAdapter extends BaseRecycleAdapter {

    public GoodNameAdapter(Context conn) {
        super(conn);
    }

    @Override
    protected int getResId() {
        return R.layout.name_tv;
    }

    @Override
    protected void conver(BaseHolder holder, int position) {
        TextView tv = holder.getView(R.id.fabu_name);
        tv.setText(((PeiSongBean.DataBean.TypeArrBean)data.get(position)).getChose_name());
        if(((PeiSongBean.DataBean.TypeArrBean)data.get(position)).getIsselect()){
            //tv.setVisibility(View.INVISIBLE);
            tv.setTextColor(Color.WHITE);
            tv.setBackgroundResource(R.drawable.agree_shape);
        }else {
            tv.setTextColor(conn.getResources().getColor(R.color.dindansel));
            tv.setBackgroundResource(R.drawable.delete_shape);
        }
    }


}
