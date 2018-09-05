package com.zhuye.huochebangsiji.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zhuye.huochebangsiji.R;
import com.zhuye.huochebangsiji.adapter.MewoloatXiaoAdapter;
import com.zhuye.huochebangsiji.bean.RechargeBean;
import com.zhuye.huochebangsiji.data.GetData;
import com.zhuye.huochebangsiji.utils.SharedPreferencesUtil;

import butterknife.BindView;
import butterknife.Unbinder;



/**
 * Created by Administrator on 2018/1/9 0009.
 */

public class XiaoFeijilragment extends BaseFragment  {

    @BindView(R.id.me_woloat_home)
    RecyclerView meWoloatHome;
    Unbinder unbinder;
    MewoloatXiaoAdapter adapter;
    @Override
    protected void initView() {
        adapter = new MewoloatXiaoAdapter(getActivity());
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
        GetData.getrecharge(SharedPreferencesUtil.getInstance().getString("token"),1,this,100);
    }



    @Override
    public void success(int requestcode, Object o) {
        RechargeBean data = (RechargeBean) o;
        adapter.addData(data.getData());
    }

}
