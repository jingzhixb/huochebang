package com.zhuye.huochebanghuozhu.adapter.me;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhuye.huochebanghuozhu.R;
import com.zhuye.huochebanghuozhu.base.BaseHolder;
import com.zhuye.huochebanghuozhu.base.BaseRecycleAdapter;
import com.zhuye.huochebanghuozhu.bean.LiuYanBean;
import com.zhuye.huochebanghuozhu.contants.Contans;

/**
 * Created by Administrator on 2018/1/11 0011.
 */

public class LiuYanAdapter extends BaseRecycleAdapter {
    public LiuYanAdapter(Context conn) {
        super(conn);
    }

    @Override
    protected int getResId() {
        return R.layout.liuyan_item;
    }

    @Override
    protected void conver(BaseHolder holder, int position) {
        TextView time =  holder.getView(R.id.tv_time);
        time.setText(((LiuYanBean.DataBean)data.get(position)).getCreate_time());
        TextView name =  holder.getView(R.id.tv_user_name);
        name.setText(((LiuYanBean.DataBean)data.get(position)).getName());
        TextView content =  holder.getView(R.id.tv_content);
        content.setText(((LiuYanBean.DataBean)data.get(position)).getIntro());
        ImageView imageView = holder.getView(R.id.liuyan_tou);
        Glide.with(conn)
                .load(Contans.BASE_URL+((LiuYanBean.DataBean) data.get(position)).getFace())
                .into(imageView);
    }

}
