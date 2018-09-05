package com.zhuye.huochebangsiji.fragment;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;

import com.zhuye.huochebangsiji.HuoCheApp;
import com.zhuye.huochebangsiji.R;
import com.zhuye.huochebangsiji.adapter.DingDanAdapter2;
import com.zhuye.huochebangsiji.base.BaseView;
import com.zhuye.huochebangsiji.bean.Code;
import com.zhuye.huochebangsiji.bean.OrdersBean;
import com.zhuye.huochebangsiji.data.GetData;
import com.zhuye.huochebangsiji.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

import static com.umeng.socialize.utils.DeviceConfig.context;

/**
 * Created by Administrator on 2018/1/9 0009.
 */

public class JinxingFragment extends BaseDingDanview {
//    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            if (msg.arg1 == 1) {
//                GetData.order_list(SharedPreferencesUtil.getInstance().getString("token"),1,3, (BaseView) getActivity(),JINXING);
//            } else if (msg.arg1 == 2) {
//                GetData.order_list(SharedPreferencesUtil.getInstance().getString("token"),1,3, (BaseView) getActivity(),JINXING);
//            }
//        }
//    };
    @Override
    protected void initData() {
        super.initData();
//        GetData.bindHandler(handler);
       // GetData.order(1, SharedPreferencesUtil.getInstance().getString("token"),3,this,JINXING);
        GetData.order_list(SharedPreferencesUtil.getInstance().getString("token"),1,3,this,JINXING);
    }

    @Override
    protected void loadmore() {
        ++page;
        getData(page,3,LOADMORE);
    }

    @Override
    protected void refresh() {
        page = 1;
        getData(page,3,REFRESH);
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
                jinxingdata = (OrdersBean) o;
                dataBeans.addAll(jinxingdata.getData());
                adapter.addData(((OrdersBean)o).getData());
                getCurOrder();
                break;
            case HISTORY:
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
                Message msg = new Message();
                msg.arg1 = 3;
                GetData.getHandler().sendMessage(msg);
                refresh();
                break;
            case REFRESH:
                jinxingdata = (OrdersBean) o;
                dataBeans.clear();
                adapter.clear();
                dataBeans.addAll(jinxingdata.getData());
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

    @Override
    public void onResume() {
        super.onResume();
//        GetData.bindHandler(handler);
    }
}
