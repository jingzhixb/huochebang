package com.zhuye.huochebang.adapter.car;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhuye.huochebang.R;
import com.zhuye.huochebang.bean.HomeCarInfoBean;

/**
 * Created by Administrator on 2018/1/5 0005.
 */

public class CarHomeAdapter2 extends BaseQuickAdapter<HomeCarInfoBean.DataBean,BaseViewHolder> {



    public CarHomeAdapter2(int layoutResId) {
        super(layoutResId);
    }



//    @Override
//    protected void conver(BaseHolder holder, int position) {
//
//        ((TextView)holder.getView(R.id.chepai)).setText(((HomeCarInfoBean.DataBean)data.get(position)).getLicense());
//        if(((HomeCarInfoBean.DataBean)data.get(position)).getType().equals("0")){
//            ((TextView)holder.getView(R.id.state)).setText("空闲中");
//        }else if(((HomeCarInfoBean.DataBean)data.get(position)).getType().equals("1")){
//            ((TextView)holder.getView(R.id.state)).setText("出车中");
//        }
//
//        //司机处理
//    }


    public void clear(){
        int itemCount = mData.size();
        mData.clear();
        this.notifyItemRangeRemoved(0,itemCount);
    }


    @Override
    protected void convert(BaseViewHolder helper, HomeCarInfoBean.DataBean item) {

        helper.setText(R.id.chepai,item.getLicense())
                .addOnClickListener(R.id.go);
        String type = item.getType();

        if(type.equals("0")){
            helper.setText(R.id.state,"空闲中");
        }else if(type.equals("1")){
            helper.setText(R.id.state,"出车中");
        }
    }
}
