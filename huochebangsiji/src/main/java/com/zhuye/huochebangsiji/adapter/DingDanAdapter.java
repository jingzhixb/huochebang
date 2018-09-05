package com.zhuye.huochebangsiji.adapter;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhuye.huochebangsiji.R;
import com.zhuye.huochebangsiji.base.BaseHolder;
import com.zhuye.huochebangsiji.base.BaseRecycleAdapter;
import com.zhuye.huochebangsiji.bean.OrdersBean;
import com.zhuye.huochebangsiji.contants.Contans;
import com.zhuye.huochebangsiji.widget.RoundedCornerImageView;


/**
 * Created by Administrator on 2018/1/16 0016.
 */

public class DingDanAdapter extends BaseRecycleAdapter {

    public DingDanAdapter(Context conn) {
        super(conn);
    }

    @Override
    protected int getResId() {
        return R.layout.fragment_dingdan_base;
    }

    @Override
    protected void conver(BaseHolder holder, int position) {

        RoundedCornerImageView face = holder.getView(R.id.face);
        TextView lianxifangshi = holder.getView(R.id.lianxifangshi);
        TextView name = holder.getView(R.id.name);
        TextView state = holder.getView(R.id.state);

        TextView start = holder.getView(R.id.start);

        TextView end = holder.getView(R.id.end);

        TextView chechang = holder.getView(R.id.chechang);
        TextView chexing = holder.getView(R.id.chexing);
        TextView zhonglei = holder.getView(R.id.zhonglei);

        TextView jine = holder.getView(R.id.jine);

        RelativeLayout typedengdai = holder.getView(R.id.typedengdai);
        RelativeLayout typewaitqueren = holder.getView(R.id.typewaitqueren);
        RelativeLayout typeyifukuan = holder.getView(R.id.typeyifukuan);
        Button quxiao2 = holder.getView(R.id.quxiao2);
        Button fahuodan = holder.getView(R.id.fahuodan);
        Button qiehoudan = holder.getView(R.id.qiehoudan);
        Button querensongda = holder.getView(R.id.querensongda);

        OrdersBean.DataBean bean = (OrdersBean.DataBean) data.get(position);

        Glide.with(conn).load(Contans.BASE_URL+bean.getFace()).into(face);
        lianxifangshi.setText(bean.getMobile());
        name.setText(bean.getName());

        start.setText(bean.getStart_city());
        end.setText(bean.getEnd_city());
        chechang.setText(bean.getLeng());
        chexing.setText(bean.getModels());
        zhonglei.setText(bean.getGoodtype());
        jine.setText(bean.getPrice());

        int type = Integer.parseInt(bean.getType());
        switch (type){
            case 0:
                state.setText("待确认");
                typewaitqueren.setVisibility(View.VISIBLE);
                typedengdai.setVisibility(View.GONE);
                quxiao2.setVisibility(View.GONE);
                typeyifukuan.setVisibility(View.GONE);
                break;
            case 1:
                state.setText("进行中");
                typeyifukuan.setVisibility(View.VISIBLE);
                typewaitqueren.setVisibility(View.GONE);
                typedengdai.setVisibility(View.GONE);
                quxiao2.setVisibility(View.GONE);
                break;
            case 2:
                state.setText("已上传装榜单");
                typeyifukuan.setVisibility(View.VISIBLE);
                typewaitqueren.setVisibility(View.GONE);
                typedengdai.setVisibility(View.GONE);
                quxiao2.setVisibility(View.GONE);
                fahuodan.setText("已上传发货单");
                break;
            case 3:
                state.setText("已上传卸货单");
                typeyifukuan.setVisibility(View.VISIBLE);
                typewaitqueren.setVisibility(View.GONE);
                typedengdai.setVisibility(View.GONE);
                quxiao2.setVisibility(View.GONE);
                fahuodan.setText("已上传发货单");
                qiehoudan.setText("已上传卸货单");
                break;
            case 4:
                state.setText("完成确认");
                typeyifukuan.setVisibility(View.VISIBLE);
                typewaitqueren.setVisibility(View.GONE);
                typedengdai.setVisibility(View.GONE);
                quxiao2.setVisibility(View.GONE);
                querensongda.setText("已确认");
                break;
            case 5:
                state.setText("完成");
                break;
            case 6:
                state.setText("已确认待货主支付");
                typewaitqueren.setVisibility(View.GONE);
                typedengdai.setVisibility(View.GONE);
                quxiao2.setVisibility(View.VISIBLE);
                typeyifukuan.setVisibility(View.GONE);
                //querena.setVisibility(View.GONE);
                break;
        }

    }
}
