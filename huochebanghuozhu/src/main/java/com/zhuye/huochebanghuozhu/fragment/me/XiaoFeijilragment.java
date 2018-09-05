package com.zhuye.huochebanghuozhu.fragment.me;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zhuye.huochebanghuozhu.R;
import com.zhuye.huochebanghuozhu.adapter.me.MewoloatXiaoAdapter2;
import com.zhuye.huochebanghuozhu.bean.XiaoFeiBean;
import com.zhuye.huochebanghuozhu.data.GetData;
import com.zhuye.huochebanghuozhu.fragment.BaseFragment;
import com.zhuye.huochebanghuozhu.utils.SharedPreferencesUtil;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/1/9 0009.
 */

public class XiaoFeijilragment extends BaseFragment  {

    @BindView(R.id.me_woloat_home)
    RecyclerView meWoloatHome;
    Unbinder unbinder;
    MewoloatXiaoAdapter2 adapter;
    @Override
    protected void initView() {
        adapter = new MewoloatXiaoAdapter2(R.layout.me_woloat_xiaofeiitem);
        meWoloatHome.setAdapter(adapter);
        meWoloatHome.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected int getResId() {
        return R.layout.fragment_me_woloat;
    }

    @Override
    protected void initData() {
        super.initData();
        GetData.consumption(SharedPreferencesUtil.getInstance().getString("token"),1,this,1000);
        //GetData.getrecharge(SharedPreferencesUtil.getInstance().getString("token"),1,this,100);
//         http://whale.zyeo.net/index.php/home/ownercenter/consumption/token/5807d17557765c370ab4b168dda8c6a3/page/1
    }



//    @Override
//    public void success(int requestcode, Object o) {
//        RechargeBean data = (RechargeBean) o;
//        adapter.addData(data.getData());
//    }

    @Override
    public void success(int requestcode, Object o) {
        XiaoFeiBean xiaoFeiBean = (XiaoFeiBean) o;
        adapter.addData(xiaoFeiBean.getData());
    }

}