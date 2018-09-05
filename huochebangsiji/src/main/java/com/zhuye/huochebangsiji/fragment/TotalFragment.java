package com.zhuye.huochebangsiji.fragment;

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

/**
 * Created by Administrator on 2018/1/9 0009.
 */

public class TotalFragment extends BaseDingDanview {
//    private  Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            if (msg.arg1 == 1) {
//                getData(1,1,TOTAL);
//            } else if (msg.arg1 == 2) {
//                getData(1,1,TOTAL);
//            }
//        }
//    };
    @Override
    protected void initData() {
        super.initData();
        getData(1,1,TOTAL);
//        GetData.bindHandler(handler);
//        OkGo.<String>post(Contans.BASE_URL+"/index.php/home/driver/order_list")
//                .params("token", SharedPreferencesUtil.getInstance().getString("token"))
//                .params("page",1)
//                .params("state",1)
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(Response<String> response) {
//                        Log.i("as",response.body());
//                    }
//
//                    @Override
//                    public void onError(Response<String> response) {
//                        super.onError(response);
//                        Log.i("as","sdaf");
//                    }
//                });
   }

    @Override
    public void onResume() {
        super.onResume();
//        GetData.bindHandler(handler);

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
    protected void loadmore() {
        ++page;
        getData(page,1,LOADMORE);
    }

    @Override
    protected void refresh() {
        page = 1;
        getData(page,1,REFRESH);
    }

    List<OrdersBean.DataBean> dataBeans = new ArrayList<>();
    @Override
    public void success(int requestcode, Object o) {
        switch (requestcode){
            case TOTAL:
                dataBeans.clear();
                totaldata = (OrdersBean) o;
                dataBeans.addAll(totaldata.getData());
                adapter.addData(dataBeans);
                break;
            case QUEREN:
                adapter.addData(((OrdersBean)o).getData());
                break;
            case JINXING:
                adapter.addData(((OrdersBean)o).getData());
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
                refresh();
                break;

            case REFRESH:
                totaldata = (OrdersBean) o;
                dataBeans.clear();
                adapter.clear();
                dataBeans.addAll(totaldata.getData());
                adapter.addData(dataBeans);
//                dingdanRv.scrollToPosition(0);
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
}
