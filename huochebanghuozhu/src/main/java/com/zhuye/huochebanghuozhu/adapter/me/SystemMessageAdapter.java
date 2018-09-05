package com.zhuye.huochebanghuozhu.adapter.me;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhuye.huochebanghuozhu.R;
import com.zhuye.huochebanghuozhu.base.BaseHolder;
import com.zhuye.huochebanghuozhu.base.BaseRecycleAdapter;
import com.zhuye.huochebanghuozhu.bean.SystemMessage;

import java.util.List;

/**
 * Created by Administrator on 2018/1/11 0011.
 */

public class SystemMessageAdapter  extends BaseQuickAdapter<SystemMessage.DataBean,BaseViewHolder> {


    public SystemMessageAdapter(int layoutResId, @Nullable List<SystemMessage.DataBean> data) {
        super(layoutResId,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SystemMessage.DataBean item) {
        helper.setText(R.id.time,item.getCreate_time())
                .setText(R.id.title,item.getTitle())
                .setText(R.id.content,item.getIntro());
    }
}
