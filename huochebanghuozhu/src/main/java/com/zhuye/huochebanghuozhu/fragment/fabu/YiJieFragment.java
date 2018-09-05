package com.zhuye.huochebanghuozhu.fragment.fabu;

import com.zhuye.huochebanghuozhu.bean.GoodsDeailBean;
import com.zhuye.huochebanghuozhu.data.GetData;
import com.zhuye.huochebanghuozhu.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/19 0019.
 */

public class YiJieFragment extends BaseYiWeiFragment {


    @Override
    protected void initData() {
        super.initData();
        GetData.goods(1,1, SharedPreferencesUtil.getInstance().getString("token"),this,YIJIE);
    }

    @Override
    protected void loadmore() {
        ++page;
        GetData.goods(page,1, SharedPreferencesUtil.getInstance().getString("token"),this,LOADMOEW);

    }

    @Override
    protected void refresh() {
        GetData.goods(1,1, SharedPreferencesUtil.getInstance().getString("token"),this,REFRESH);

    }

    List<GoodsDeailBean.DataBean> mdata= new ArrayList<>();
    @Override
    public void success(int requestcode, Object o) {
        switch (requestcode) {
            case WEIJIE:
                bean = (GoodsDeailBean) o;
                mdata.addAll(bean.getData());
                adapter.addData(mdata);
                break;

            case YIJIE:
                bean = (GoodsDeailBean) o;
                mdata.addAll(bean.getData());
                adapter.addData(mdata);
                break;

            case REFRESH:
                bean = (GoodsDeailBean) o;
                mdata.clear();
                adapter.clear();
                mdata.addAll(bean.getData());
                adapter.addData(mdata);
                goddsDetailRv.scrollToPosition(0);
                break;

            case LOADMOEW:

                break;
        }
    }
}
