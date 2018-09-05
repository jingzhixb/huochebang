package com.zhuye.huochebang.adapter.siji;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhuye.huochebang.R;
import com.zhuye.huochebang.base.BaseHolder;
import com.zhuye.huochebang.base.BaseRecycleAdapter;
import com.zhuye.huochebang.bean.CarListBean;

/**
 * Created by Administrator on 2018/1/5 0005.
 */

public class SelectCarAdapter extends BaseRecycleAdapter {

    public SelectCarAdapter(Context conn) {
        super(conn);
    }

    @Override
    protected int getResId() {
        return R.layout.selectcar_item;
    }

    @Override
    protected void conver(BaseHolder holder, int position) {
        TextView vie = holder.getView(R.id.chehao);
        vie.setText(((CarListBean.DataBean)data.get(position)).getLicense());
        ImageView im = holder.getView(R.id.xianshi);
        if(((CarListBean.DataBean)data.get(position)).getIsselect()){
            im.setVisibility(View.VISIBLE);
        }else {
            im.setVisibility(View.INVISIBLE);
        }
    }
}
