package com.zhuye.huochebangsiji.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhuye.huochebangsiji.R;
import com.zhuye.huochebangsiji.bean.SystemMessage;


/**
 * Created by Administrator on 2018/1/11 0011.
 */

public class SystemMessageAdapter2 extends BaseQuickAdapter<SystemMessage.DataBean,BaseViewHolder> {

    public SystemMessageAdapter2(int layoutResId) {
        super(layoutResId);
    }



    @Override
    protected void convert(BaseViewHolder helper, SystemMessage.DataBean item) {

        helper.setText(R.id.time,item.getCreate_time())
                .setText(R.id.title,item.getTitle())
                .setText(R.id.content,item.getIntro());
    }
}
