package com.zhuye.huochebanghuozhu.fragment.dingdan;

import android.view.View;

import com.zhuye.huochebanghuozhu.R;
import com.zhuye.huochebanghuozhu.adapter.DingDanAdapter2;
import com.zhuye.huochebanghuozhu.bean.Code;
import com.zhuye.huochebanghuozhu.bean.OrderBean;
import com.zhuye.huochebanghuozhu.data.GetData;
import com.zhuye.huochebanghuozhu.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2018/1/9 0009.
 */

public class QueRenFragment extends BaseDingDanview {
    protected DingDanAdapter2 adapter;
    @Override
    public void initData() {
        super.initData();
        GetData.order(1, SharedPreferencesUtil.getInstance().getString("token"),4,this,QUEREN);
    }

    @Override
    protected OrderBean getCurrentOrder() {
        return quren;
    }

    @Override
    protected List<OrderBean.DataBean> getCurOrder() {
        return dataBeans;
    }

    @Override
    protected DingDanAdapter2 getAdapter() {
        adapter = new DingDanAdapter2(R.layout.fragment_dingdan_base);
        return adapter;
    }

    @Override
    protected void loadmore() {
        ++page;
        getData(page,4,LOADMORE);
    }

    @Override
    protected void refresh() {
        page = 1;
        getData(page,4,REFRESH);
    }

    List<OrderBean.DataBean> dataBeans = new ArrayList<>();
    @Override
    public void success(int requestcode, Object o) {
        switch (requestcode){
            case TOTAL:
                total = (OrderBean) o;
                getCurorder();
                break;
            case QUEREN:
                quren = (OrderBean) o;
//                if (quren.getData().size() >0){
//                    empty.setVisibility(View.GONE);
//                }else{
//                    empty.setVisibility(View.VISIBLE);
//                }
                if (adapter != null) {
                    adapter.clear();
                    dataBeans.clear();
                    dataBeans.addAll(quren.getData());
                    adapter.addData(dataBeans);
                    adapter.notifyDataSetChanged();
                    getCurorder();
                }
                break;
            case JINXING:
                jinxing = (OrderBean) o;
                adapter.addData(((OrderBean)o).getData());
                adapter.notifyDataSetChanged();
                getCurorder();
                break;
            case HISTORY:
                history = (OrderBean) o;
                adapter.addData(((OrderBean)o).getData());
                getCurorder();
                break;
            case DAIFU:
                daifu = (OrderBean) o;
                adapter.addData(((OrderBean)o).getData());
                getCurorder();
                break;
            case ORDERDELETE:
                Code message = (Code) o;
                toast(message.getMessage());
                adapter.remove(removepos);
                break;
            case REFRESH:

                quren = (OrderBean) o;
                dataBeans.clear();
                adapter.clear();
                dataBeans.addAll(quren.getData());
                adapter.addData(dataBeans);
                smartRefreshLayout.finishRefresh();
                dingdanRv.scrollToPosition(0);
                break;

            case LOADMORE:
                //Code message1 = (Code) o;
                adapter.clear();
                OrderBean message1 = (OrderBean) o;
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
}