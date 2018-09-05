package com.zhuye.huochebanghuozhu.fragment.dingdan;

import android.view.View;

import com.zhuye.huochebanghuozhu.R;
import com.zhuye.huochebanghuozhu.adapter.DingDanAdapter2;
import com.zhuye.huochebanghuozhu.bean.Code;
import com.zhuye.huochebanghuozhu.bean.OrderBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/9 0009.
 */

public class TotalFragment extends BaseDingDanview {

    protected DingDanAdapter2 adapter;

    @Override
    public void initData() {
        super.initData();
        getData(1,1,TOTAL);
//        OkGo.<String>post(Contans.BASE_URL+"/index.php/home/ownerorder/order")
//                .params("token",SharedPreferencesUtil.getInstance().getString("token"))
//                .params("page",1)
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(Response<String> response) {
//                        Log.i("as",response.body());
//                    }
//
//                    @Override
//                    public void onError(Response<String> response) {
//                        super.onError(response);
//                        Log.i("as","");
//                    }
//                });
    }



    @Override
    protected DingDanAdapter2 getAdapter() {
        adapter = new DingDanAdapter2(R.layout.fragment_dingdan_base);
        return adapter;
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

    @Override
    protected OrderBean getCurrentOrder() {
        return total;
    }

    @Override
    protected List<OrderBean.DataBean> getCurOrder() {
        return dataBeans;
    }


    List<OrderBean.DataBean> dataBeans = new ArrayList<>();
    @Override
    public void success(int requestcode, Object o) {
        switch (requestcode){
            case TOTAL:
                total = (OrderBean) o;
                if (adapter != null) {
                    adapter.clear();
                    dataBeans.clear();
                    dataBeans.addAll(total.getData());
                    adapter.addData(dataBeans);
                    getCurorder();
                }
                break;
            case QUEREN:
                quren = (OrderBean) o;
                adapter.addData(((OrderBean)o).getData());
                getCurorder();
                break;
            case JINXING:
                jinxing = (OrderBean) o;
                adapter.addData(((OrderBean)o).getData());
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
                total = (OrderBean) o;
                dataBeans.clear();
                adapter.clear();
                dataBeans.addAll(total.getData());
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


}
