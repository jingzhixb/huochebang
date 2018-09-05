package com.zhuye.huochebanghuozhu.adapter.message;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhuye.huochebanghuozhu.R;
import com.zhuye.huochebanghuozhu.base.BaseHolder;
import com.zhuye.huochebanghuozhu.base.BaseRecycleAdapter;
import com.zhuye.huochebanghuozhu.bean.OrderBean;
import com.zhuye.huochebanghuozhu.contants.Contans;
import com.zhuye.huochebanghuozhu.widget.RoundedCornerImageView;

/**
 * Created by Administrator on 2018/1/16 0016.
 */

public class DingDanAdapter extends BaseRecycleAdapter{

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


        RelativeLayout typewaitqueren = holder.getView(R.id.typewaitqueren);
        RelativeLayout typechakan = holder.getView(R.id.typechakan);
        RelativeLayout typewancheng = holder.getView(R.id.typewancheng);
        RelativeLayout typequxiao = holder.getView(R.id.typequxiao);

        Button zhifu = holder.getView(R.id.zhifu);
        Button qiehoudan = holder.getView(R.id.qiehoudan);
        Button fahuodan = holder.getView(R.id.fahuodan);
        Button querenzhifu = holder.getView(R.id.querenzhifu);
        Button kanqiehoudan = holder.getView(R.id.kanqiehoudan);
        Button kanfahuodan = holder.getView(R.id.kanfahuodan);



        OrderBean.DataBean bean = (OrderBean.DataBean) data.get(position);
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
                state.setText("待司机确认");
                typewaitqueren.setVisibility(View.GONE);
                zhifu.setVisibility(View.GONE);
                typechakan.setVisibility(View.GONE);
                typewancheng.setVisibility(View.GONE);
                typequxiao.setVisibility(View.VISIBLE);
//                quxiao2.setVisibility(View.GONE);
//                typeyifukuan.setVisibility(View.GONE);
                break;
            case 1:
                state.setText("已付款");
                typechakan.setVisibility(View.VISIBLE);
                typewaitqueren.setVisibility(View.GONE);
                typewancheng.setVisibility(View.GONE);
                typequxiao.setVisibility(View.GONE);
                fahuodan.setText("等待传发货单");
                qiehoudan.setText("等待传卸货单");
//                quxiao2.setVisibility(View.GONE);
                break;
            case 2:
                state.setText("已上传装榜单");
                typechakan.setVisibility(View.VISIBLE);
                typewaitqueren.setVisibility(View.GONE);
                typewancheng.setVisibility(View.GONE);
//                typeyifukuan.setVisibility(View.VISIBLE);
//                typewaitqueren.setVisibility(View.GONE);
//                typedengdai.setVisibility(View.GONE);
//                quxiao2.setVisibility(View.GONE);
//                fahuodan.setText("已上传发货单");
                fahuodan.setText("已上传发货单");
                qiehoudan.setText("等待传卸货单");
                typequxiao.setVisibility(View.GONE);
                break;
            case 3:
                state.setText("已上传卸货单");
                typechakan.setVisibility(View.VISIBLE);
                typewaitqueren.setVisibility(View.GONE);
                typewancheng.setVisibility(View.GONE);
                typequxiao.setVisibility(View.GONE);
//                typeyifukuan.setVisibility(View.VISIBLE);
//                typewaitqueren.setVisibility(View.GONE);
//                typedengdai.setVisibility(View.GONE);
//                quxiao2.setVisibility(View.GONE);
//                fahuodan.setText("已上传发货单");
//                qiehoudan.setText("已上传卸货单");
                fahuodan.setText("已上传发货单");
                qiehoudan.setText("已上传卸货单");

                break;
            case 4:
                state.setText("司机已送达");
                typechakan.setVisibility(View.GONE);
                typewaitqueren.setVisibility(View.GONE);
                typewancheng.setVisibility(View.VISIBLE);
                typequxiao.setVisibility(View.GONE);
//                typeyifukuan.setVisibility(View.VISIBLE);
//                typewaitqueren.setVisibility(View.GONE);
//                typedengdai.setVisibility(View.GONE);
//                quxiao2.setVisibility(View.GONE);
//                querensongda.setText("已确认");
                querenzhifu.setText("确认支付");
                kanqiehoudan.setVisibility(View.VISIBLE);
                kanfahuodan.setVisibility(View.VISIBLE);
                break;
            case 5:
                state.setText("已完成");
                typechakan.setVisibility(View.GONE);
                typewaitqueren.setVisibility(View.GONE);
                typewancheng.setVisibility(View.VISIBLE);
                typequxiao.setVisibility(View.GONE);
                querenzhifu.setText("已完成");
                kanqiehoudan.setVisibility(View.GONE);
                kanfahuodan.setVisibility(View.GONE);
                break;
            case 6:
                state.setText("待支付");
                typewaitqueren.setVisibility(View.VISIBLE);
                zhifu.setVisibility(View.VISIBLE);
                typechakan.setVisibility(View.GONE);
                typewancheng.setVisibility(View.GONE);
                typequxiao.setVisibility(View.GONE);
//                typewaitqueren.setVisibility(View.GONE);
//                typedengdai.setVisibility(View.GONE);
//                quxiao2.setVisibility(View.VISIBLE);
//                typeyifukuan.setVisibility(View.GONE);
                //querena.setVisibility(View.GONE);
                break;
        }
    }
}
