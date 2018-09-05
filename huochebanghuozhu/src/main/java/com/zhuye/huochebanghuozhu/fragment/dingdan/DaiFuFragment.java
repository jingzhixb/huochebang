package com.zhuye.huochebanghuozhu.fragment.dingdan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhuye.huochebanghuozhu.R;
import com.zhuye.huochebanghuozhu.adapter.DingDanAdapter2;
import com.zhuye.huochebanghuozhu.bean.Code;
import com.zhuye.huochebanghuozhu.bean.MessageEvent;
import com.zhuye.huochebanghuozhu.bean.OrderBean;
import com.zhuye.huochebanghuozhu.data.GetData;
import com.zhuye.huochebanghuozhu.utils.SharedPreferencesUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/9 0009.
 */

public class DaiFuFragment extends BaseDingDanview {
    protected DingDanAdapter2 adapter;
    @Override
    public void initData() {
        super.initData();
        GetData.order(1, SharedPreferencesUtil.getInstance().getString("token"),2,this,DAIFU);
    }

    @Override
    public void onResume() {
        super.onResume();
//        GetData.order(1, SharedPreferencesUtil.getInstance().getString("token"),2,this,DAIFU);
    }

    @Override
    protected OrderBean getCurrentOrder() {
        return daifu;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected List<OrderBean.DataBean> getCurOrder() {
        return dataBeans;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        /* Do something */
    };

    @Override
    protected DingDanAdapter2 getAdapter() {
        adapter = new DingDanAdapter2(R.layout.fragment_dingdan_base);
        return adapter;
    }

    @Override
    protected void loadmore() {
        ++page;
        getData(page,2,LOADMORE);
    }

    @Override
    protected void refresh() {
        page = 1;
        getData(page,2,REFRESH);
    }

    @Override
    public void success(int requestcode, Object o) {
        switch (requestcode){
            case TOTAL:
                total = (OrderBean) o;
                adapter.addData(((OrderBean)o).getData());
                getCurorder();
                break;
            case QUEREN:
                quren = (OrderBean) o;
                adapter.addData(((OrderBean)o).getData());
                getCurorder();
                adapter.notifyDataSetChanged();
                break;
            case JINXING:
                jinxing = (OrderBean) o;
                adapter.addData(((OrderBean)o).getData());
                getCurorder();
                adapter.notifyDataSetChanged();
                break;
            case HISTORY:
                history = (OrderBean) o;
                adapter.addData(((OrderBean)o).getData());
                getCurorder();
                break;
            case DAIFU:
                daifu = (OrderBean) o;
                if(adapter!=null){
                    adapter.clear();
                    dataBeans.clear();
                    dataBeans.addAll(daifu.getData());
                    adapter.addData(dataBeans);
                    adapter.notifyDataSetChanged();
                    getCurorder();
                }
                break;
            case ORDERDELETE:
                Code message = (Code) o;
                toast(message.getMessage());
                adapter.remove(removepos);
                break;

            case REFRESH:
                dataBeans.clear();
                adapter.clear();
                daifu = (OrderBean) o;
                dataBeans.addAll(daifu.getData());
                adapter.addData(dataBeans);
                smartRefreshLayout.finishRefresh();
                dingdanRv.scrollToPosition(0);
                break;

            case LOADMORE:
                //Code message1 = (Code) o;
                OrderBean message1 = (OrderBean) o;
                adapter.clear();
                dataBeans.addAll(message1.getData());
                adapter.addData(dataBeans);
                toast(message1.getMessage());
                if(message1.getData().equals("")){
                }
                smartRefreshLayout.finishLoadmore();
                break;
            case QUERENORDER:
                Code messages = (Code) o;
                toast(messages.getMessage());
                adapter.notifyDataSetChanged();
                break;
        }

    }
    List<OrderBean.DataBean> dataBeans = new ArrayList<>();
}
