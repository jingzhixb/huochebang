package com.zhuye.huochebanghuozhu.adapter;

import android.support.annotation.LayoutRes;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhuye.huochebanghuozhu.R;
import com.zhuye.huochebanghuozhu.bean.OrderBean;
import com.zhuye.huochebanghuozhu.contants.Contans;

/**
 * Created by Administrator on 2018/1/16 0016.
 */

public class DingDanAdapter2 extends BaseQuickAdapter<OrderBean.DataBean,BaseViewHolder>{

private int type =0;
    public DingDanAdapter2(@LayoutRes int layoutResId) {
        super(layoutResId);
    }


    public void clear(){
        int end = mData.size();
        mData.clear();
        notifyItemRangeRemoved(0,end);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderBean.DataBean item) {
        String valid = item.getValid();
        Glide.with(mContext).load(Contans.BASE_URL+item.getFace()).into((ImageView) helper.getView(R.id.face));
        helper.setText(R.id.name,item.getName())
                .setText(R.id.lianxifangshi,item.getMobile())
                .setText(R.id.start,item.getStart_city())
                .setText(R.id.end,item.getEnd_city())
                .setText(R.id.chechang,item.getLeng())
                .setText(R.id.chexing,item.getModels())
                .setText(R.id.zhonglei,item.getGoodtype())
                .setText(R.id.jine,item.getPrice())
                .setText(R.id.name,item.getName())


                .addOnClickListener(R.id.queixao)
                .addOnClickListener(R.id.shanchu)

                .addOnClickListener(R.id.zhifu)
                .addOnClickListener(R.id.qiehoudan)
                .addOnClickListener(R.id.fahuodan)
                .addOnClickListener(R.id.kanqiehoudan)
                .addOnClickListener(R.id.kanfahuodan)
                .addOnClickListener(R.id.querenzhifu)
                .addOnClickListener(R.id.xiugaijiage)
                .addOnClickListener(R.id.queixao1);


        if (valid.equals("1")) {
            type = 5;
        }else {
            type = Integer.parseInt(item.getType());
        }
        switch (type){
            case 0:
                helper.setText(R.id.state,"待司机确认");
                helper.getView(R.id.typewaitqueren).setVisibility(View.INVISIBLE);
                helper.getView(R.id.zhifu).setVisibility(View.INVISIBLE);
                helper.getView(R.id.typechakan).setVisibility(View.INVISIBLE);
                helper.getView(R.id.typewancheng).setVisibility(View.INVISIBLE);
                helper.getView(R.id.typequxiao).setVisibility(View.VISIBLE);
//                quxiao2.setVisibility(View.GONE);
//                typeyifukuan.setVisibility(View.GONE);
                break;
            case 1:
                helper.setText(R.id.state,"等待传卸货单");
                helper.getView(R.id.typequxiao).setVisibility(View.INVISIBLE);
                helper.getView(R.id.typewancheng).setVisibility(View.INVISIBLE);
                helper.getView(R.id.typewaitqueren).setVisibility(View.INVISIBLE);
                helper.getView(R.id.typechakan).setVisibility(View.VISIBLE);
                helper.setText(R.id.fahuodan,"查看发货单");
                helper.setText(R.id.qiehoudan,"等待传卸货单");
//                typechakan.setVisibility(View.VISIBLE);
//                typewaitqueren.setVisibility(View.GONE);
//                typewancheng.setVisibility(View.GONE);
//                typequxiao.setVisibility(View.GONE);
//                fahuodan.setText("等待传发货单");
//                qiehoudan.setText("等待传卸货单");
//                quxiao2.setVisibility(View.GONE);
                break;
            case 2:
//                helper.setText(R.id.state,"已上传装榜单");
                helper.setText(R.id.state,"待支付");
                helper.getView(R.id.typequxiao).setVisibility(View.INVISIBLE);
                helper.getView(R.id.typewancheng).setVisibility(View.INVISIBLE);

                helper.getView(R.id.typewaitqueren).setVisibility(View.VISIBLE);
                helper.getView(R.id.typechakan).setVisibility(View.INVISIBLE);
                helper.setText(R.id.fahuodan,"已上传发货单");
                helper.setText(R.id.qiehoudan,"等待传卸货单");

               // state.setText("已上传装榜单");
//                typechakan.setVisibility(View.VISIBLE);
//                typewaitqueren.setVisibility(View.GONE);
//                typewancheng.setVisibility(View.GONE);
////                typeyifukuan.setVisibility(View.VISIBLE);
////                typewaitqueren.setVisibility(View.GONE);
////                typedengdai.setVisibility(View.GONE);
////                quxiao2.setVisibility(View.GONE);
////                fahuodan.setText("已上传发货单");
//                fahuodan.setText("已上传发货单");
//                qiehoudan.setText("等待传卸货单");
//                typequxiao.setVisibility(View.GONE);
                break;
            case 3:

//                helper.setText(R.id.state,"已上传卸货单");
                helper.setText(R.id.state,"待后台确认");
                helper.getView(R.id.typequxiao).setVisibility(View.INVISIBLE);
                helper.getView(R.id.typewancheng).setVisibility(View.INVISIBLE);
                helper.getView(R.id.typewaitqueren).setVisibility(View.INVISIBLE);
                helper.getView(R.id.typechakan).setVisibility(View.VISIBLE);
                helper.setText(R.id.fahuodan,"已上传发货单");
                helper.setText(R.id.qiehoudan,"已上传卸货单");

//                state.setText("已上传卸货单");
//                typechakan.setVisibility(View.VISIBLE);
//                typewaitqueren.setVisibility(View.GONE);
//                typewancheng.setVisibility(View.GONE);
//                typequxiao.setVisibility(View.GONE);
////                typeyifukuan.setVisibility(View.VISIBLE);
////                typewaitqueren.setVisibility(View.GONE);
////                typedengdai.setVisibility(View.GONE);
////                quxiao2.setVisibility(View.GONE);
////                fahuodan.setText("已上传发货单");
////                qiehoudan.setText("已上传卸货单");
//                fahuodan.setText("已上传发货单");
//                qiehoudan.setText("已上传卸货单");

                break;
            case 4:
//                helper.setText(R.id.state,"司机已送达");
//                helper.setText(R.id.state,"确认价格");
                helper.setText(R.id.state,"确认送达");
                helper.getView(R.id.typequxiao).setVisibility(View.INVISIBLE);
                helper.getView(R.id.typewancheng).setVisibility(View.VISIBLE);
                helper.getView(R.id.typewaitqueren).setVisibility(View.INVISIBLE);
                helper.getView(R.id.typechakan).setVisibility(View.INVISIBLE);
                helper.getView(R.id.kanqiehoudan).setVisibility(View.VISIBLE);
                helper.getView(R.id.kanfahuodan).setVisibility(View.VISIBLE);
                helper.setText(R.id.querenzhifu,"确认");
                break;
            case 5:
                if (valid.equals("1")){
                    helper.setText(R.id.state, "已取消");
                    helper.setVisible(R.id.fahuodan,false)
                            .setVisible(R.id.qiehoudan,false);

                    helper.setVisible(R.id.shanchu,false);
                }else {
                    helper.setText(R.id.state, "已完成");
                    helper.setText(R.id.fahuodan,"查看发货单");
                    helper.setText(R.id.qiehoudan,"查看卸货单");
                }
                helper.getView(R.id.typequxiao).setVisibility(View.INVISIBLE);
                helper.getView(R.id.typewancheng).setVisibility(View.INVISIBLE);
                helper.getView(R.id.typewaitqueren).setVisibility(View.INVISIBLE);
//                helper.getView(R.id.typechakan).setVisibility(View.GONE);
                helper.getView(R.id.kanqiehoudan).setVisibility(View.INVISIBLE);
                helper.getView(R.id.kanfahuodan).setVisibility(View.INVISIBLE);
                helper.getView(R.id.querenzhifu).setVisibility(View.INVISIBLE);

                helper.getView(R.id.typechakan).setVisibility(View.VISIBLE);



                break;
            case 6:
               // helper.setText(R.id.state,"待支付");
                helper.setText(R.id.state,"等待传发货单");
                helper.getView(R.id.typequxiao).setVisibility(View.INVISIBLE);
                helper.getView(R.id.typewancheng).setVisibility(View.INVISIBLE);

                helper.getView(R.id.typewaitqueren).setVisibility(View.INVISIBLE);

                helper.getView(R.id.typechakan).setVisibility(View.INVISIBLE);
                helper.getView(R.id.zhifu).setVisibility(View.INVISIBLE);




//                state.setText("待支付");
//                typewaitqueren.setVisibility(View.VISIBLE);
//                zhifu.setVisibility(View.VISIBLE);
//                typechakan.setVisibility(View.GONE);
//                typewancheng.setVisibility(View.GONE);
//                typequxiao.setVisibility(View.GONE);
//                typewaitqueren.setVisibility(View.GONE);
//                typedengdai.setVisibility(View.GONE);
//                quxiao2.setVisibility(View.VISIBLE);
//                typeyifukuan.setVisibility(View.GONE);
                //querena.setVisibility(View.GONE);
                break;
        }

    }
}
