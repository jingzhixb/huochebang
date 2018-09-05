package com.zhuye.huochebanghuozhu.adapter.dading;

import android.support.annotation.LayoutRes;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhuye.huochebanghuozhu.R;
import com.zhuye.huochebanghuozhu.bean.DaTingBean;
import com.zhuye.huochebanghuozhu.contants.Contans;

/**
 * Created by Administrator on 2018/1/29 0029.
 */

public class DaTingHomeAdapter2 extends BaseQuickAdapter<DaTingBean.DataBean,BaseViewHolder> {

    public DaTingHomeAdapter2(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, DaTingBean.DataBean item) {

        Glide.with(mContext).load(Contans.BASE_URL+item.getFace()).into((ImageView) helper.getView(R.id.touxing));

        helper.setText(R.id.name,item.getName())
                .setText(R.id.chepai,item.getLicense())
                .setText(R.id.chexing,item.getLeng()==null?"":item.getLeng()+"/"+item.getModels()==null?"":item.getModels())
                .addOnClickListener(R.id.phone);
        int type = Integer.parseInt(item.getType());
        if(type==0){
            helper.setText(R.id.state,"空闲");
        }else {
            helper.setText(R.id.state,"出车中");
        }

    }

    public void clear(){
        int itemCount = mData.size();
        mData.clear();
        this.notifyItemRangeRemoved(0,itemCount);
    }
}
