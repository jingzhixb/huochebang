package com.zhuye.huochebangsiji.adapter;

import android.support.annotation.LayoutRes;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhuye.huochebangsiji.R;
import com.zhuye.huochebangsiji.bean.OrdersBean;
import com.zhuye.huochebangsiji.contants.Contans;


/**
 * Created by Administrator on 2018/1/16 0016.
 */

public class DingDanAdapter2 extends BaseQuickAdapter<OrdersBean.DataBean, BaseViewHolder> {

    int type= 0;
    public DingDanAdapter2(@LayoutRes int layoutResId) {
        super(layoutResId);
    }


    public void clear() {
        int end = mData.size();
        mData.clear();
        notifyItemRangeRemoved(0, end);
    }

    public void updata(int po, OrdersBean.DataBean data) {
        notifyItemChanged(po, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrdersBean.DataBean item) {
      String valid = item.getValid();
        Glide.with(mContext).load(Contans.BASE_URL + item.getFace()).into((ImageView) helper.getView(R.id.face));
        helper.setText(R.id.name, item.getName())
                .setText(R.id.lianxifangshi, item.getMobile())
                .setText(R.id.start, item.getStart_city())
                .setText(R.id.end, item.getEnd_city())
                .setText(R.id.chechang, item.getLeng())
                .setText(R.id.chexing, item.getModels())
                .setText(R.id.zhonglei, item.getGoodtype())
                .setText(R.id.jine, item.getPrice())
                .setText(R.id.name, item.getName())
                .addOnClickListener(R.id.querena)
                // 上传过磅单
                .addOnClickListener(R.id.quxiao2)
                .addOnClickListener(R.id.qiehoudan)
                .addOnClickListener(R.id.fahuodan)
                .addOnClickListener(R.id.querensongda)
                .addOnClickListener(R.id.shanchu)
                .addOnClickListener(R.id.quxiao);
        if (valid.equals("1")) {
            type = 5;
        }else {
             type = Integer.parseInt(item.getType());
        }



        helper.setVisible(R.id.shanchu,false);

        switch (type) {
//            "type": "0表示待确认 1表示已支付-进行中 2表示已上传装榜单 3表示已上传卸榜单4表示司机完成确认 5表示完成 6司机已确认待货主支付"

            case 0:
                helper.setText(R.id.state, "待确认");
                helper.getView(R.id.typedengdai).setVisibility(View.INVISIBLE);
                helper.getView(R.id.quxiao2).setVisibility(View.INVISIBLE);
                helper.getView(R.id.typeyifukuan).setVisibility(View.INVISIBLE);
                helper.getView(R.id.typewaitqueren).setVisibility(View.VISIBLE);
                break;
            case 1:
//                helper.setText(R.id.state, "进行中");
                helper.setText(R.id.state, "待上传卸货单");
                helper.getView(R.id.typedengdai).setVisibility(View.INVISIBLE);
                helper.getView(R.id.quxiao2).setVisibility(View.INVISIBLE);
                helper.getView(R.id.typewaitqueren).setVisibility(View.INVISIBLE);
                helper.getView(R.id.typeyifukuan).setVisibility(View.VISIBLE);
                helper.getView(R.id.fahuodan).setClickable(true);
                helper.getView(R.id.qiehoudan).setClickable(true);
                helper.getView(R.id.querensongda).setClickable(false);

                helper.setText(R.id.fahuodan, "已上传装货单");
                break;
            case 2:
//                helper.setText(R.id.state, "已上传装货单");
                helper.setText(R.id.state, "待货主支付");
                helper.getView(R.id.typedengdai).setVisibility(View.INVISIBLE);
                helper.getView(R.id.quxiao2).setVisibility(View.INVISIBLE);
                helper.getView(R.id.typewaitqueren).setVisibility(View.INVISIBLE);
                helper.getView(R.id.typeyifukuan).setVisibility(View.VISIBLE);
                helper.setText(R.id.fahuodan, "已上传装货单");
                helper.getView(R.id.fahuodan).setClickable(true);
                helper.getView(R.id.qiehoudan).setClickable(false);
                helper.getView(R.id.querensongda).setClickable(false);
                break;
            case 3:
//                helper.setText(R.id.state, "已上传卸货单");
                helper.setText(R.id.state, "待后台确认");
                helper.getView(R.id.typedengdai).setVisibility(View.INVISIBLE);
                helper.getView(R.id.quxiao2).setVisibility(View.INVISIBLE);
                helper.getView(R.id.typewaitqueren).setVisibility(View.INVISIBLE);
                helper.getView(R.id.typeyifukuan).setVisibility(View.VISIBLE);
                helper.setText(R.id.fahuodan, "已上传装货单");
                helper.setText(R.id.qiehoudan, "已上传卸货单");
                helper.getView(R.id.fahuodan).setClickable(true);
                helper.getView(R.id.qiehoudan).setClickable(true);
                helper.getView(R.id.querensongda).setClickable(true);
                break;
            case 4:
//                helper.setText(R.id.state, "完成确认");
                helper.setText(R.id.state, "待货主确认");
                helper.getView(R.id.typedengdai).setVisibility(View.INVISIBLE);
                helper.getView(R.id.quxiao2).setVisibility(View.INVISIBLE);
                helper.getView(R.id.typewaitqueren).setVisibility(View.INVISIBLE);
                helper.getView(R.id.typeyifukuan).setVisibility(View.VISIBLE);
                helper.setText(R.id.querensongda, "已确认");
                helper.setText(R.id.fahuodan, "已上传装货单");
                helper.setText(R.id.qiehoudan, "已上传卸货单");
                helper.getView(R.id.fahuodan).setClickable(true);
                helper.getView(R.id.qiehoudan).setClickable(true);
                helper.getView(R.id.querensongda).setClickable(false);
                break;
            case 5:
                if (valid.equals("1")){
                    helper.setText(R.id.state, "已取消");
//                    helper.setText(R.id.fahuodan, "查看装货单");
//                    helper.setText(R.id.qiehoudan, "查看卸货单");
                    helper.setVisible(R.id.fahuodan,false)
                            .setVisible(R.id.qiehoudan,false);

                    helper.setVisible(R.id.shanchu,false);
                }else {
                    helper.setText(R.id.state, "完成");
                    helper.setText(R.id.fahuodan, "查看装货单");
                    helper.setText(R.id.qiehoudan, "查看卸货单");
                }
                helper.getView(R.id.typedengdai).setVisibility(View.INVISIBLE);
                helper.getView(R.id.quxiao2).setVisibility(View.INVISIBLE);
                helper.getView(R.id.typewaitqueren).setVisibility(View.INVISIBLE);
                helper.getView(R.id.typeyifukuan).setVisibility(View.INVISIBLE);
                helper.getView(R.id.typeyifukuan).setVisibility(View.VISIBLE);

//                helper.setText(R.id.querensongda, "已确认");
//                helper.getView(R.id.fahuodan).setClickable(true);
//                helper.getView(R.id.qiehoudan).setClickable(true);
//                helper.getView(R.id.querensongda).setClickable(false);
                break;
            case 6:
                helper.setText(R.id.state, "待上传装货单");
//                helper.setText(R.id.state, "已确认待货主支付");
                helper.getView(R.id.typedengdai).setVisibility(View.INVISIBLE);
                helper.getView(R.id.typeyifukuan).setVisibility(View.INVISIBLE);
                helper.getView(R.id.typewaitqueren).setVisibility(View.INVISIBLE);
                helper.getView(R.id.quxiao2).setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }
}
