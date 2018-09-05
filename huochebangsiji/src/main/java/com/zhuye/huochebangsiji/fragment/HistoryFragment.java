package com.zhuye.huochebangsiji.fragment;

import com.zhuye.huochebangsiji.R;
import com.zhuye.huochebangsiji.adapter.DingDanAdapter2;
import com.zhuye.huochebangsiji.bean.Code;
import com.zhuye.huochebangsiji.bean.OrdersBean;
import com.zhuye.huochebangsiji.data.GetData;
import com.zhuye.huochebangsiji.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/9 0009.
 */

public class HistoryFragment extends BaseDingDanview {



    @Override
    protected void initData() {
        super.initData();
        GetData.order_list(SharedPreferencesUtil.getInstance().getString("token"),1,5,this,HISTORY);
    }

    @Override
    protected void loadmore() {
        ++page;
        getData(page,5,LOADMORE);
    }

    @Override
    protected void refresh() {
        page = 1;
        getData(page,5,REFRESH);
    }

    DingDanAdapter2 adapter;
    @Override
    protected DingDanAdapter2 getAdapter() {
        adapter = new DingDanAdapter2(R.layout.fragment_dingdan_base);
        return adapter;
    }

    @Override
    protected List<OrdersBean.DataBean> getCurOrder() {
        return dataBeans;
    }

    @Override
    public void success(int requestcode, Object o) {
        switch (requestcode){
            case TOTAL:
                totaldata = (OrdersBean) o;
                adapter.addData(((OrdersBean)o).getData());
                break;
            case QUEREN:
                adapter.addData(((OrdersBean)o).getData());
                break;
            case JINXING:
                adapter.addData(((OrdersBean)o).getData());
                break;
            case HISTORY:
                lishidata = (OrdersBean) o;
                dataBeans.addAll(lishidata.getData());
                if (lishidata.getData().size()==0){
                    //getActivity().addContentView();
                }
                adapter.addData(((OrdersBean)o).getData());
                break;
            case DAIFU:
                adapter.addData(((OrdersBean)o).getData());
                break;
            case QUERENDINGDAN:
                toast(((Code)o).getMessage());
                refresh();
                break;

            case QUXIAOUDINGDAN:
                toast(((Code)o).getMessage());
                refresh();
                break;

            case QUERENSONGDA:
                toast(((Code)o).getMessage());
                refresh();
                break;

            case REFRESH:
                lishidata= (OrdersBean) o;
                dataBeans.clear();
                adapter.clear();
                dataBeans.addAll(lishidata.getData());
                adapter.addData(dataBeans);
                dingdanRv.scrollToPosition(0);
                smartRefreshLayout.finishRefresh();
                break;

            case LOADMORE:
                //Code message1 = (Code) o;
                adapter.clear();
                OrdersBean message1 = (OrdersBean) o;
                dataBeans.addAll(message1.getData());
                adapter.addData(dataBeans);
                if(message1.getData().equals("")){

                }
                smartRefreshLayout.finishLoadmore();
                break;
        }
    }
    List<OrdersBean.DataBean> dataBeans = new ArrayList<>();
}
