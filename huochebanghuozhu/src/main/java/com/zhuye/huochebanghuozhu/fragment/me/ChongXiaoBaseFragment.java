package com.zhuye.huochebanghuozhu.fragment.me;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zhuye.huochebanghuozhu.R;
import com.zhuye.huochebanghuozhu.adapter.me.MewoloatAdapter2;
import com.zhuye.huochebanghuozhu.bean.RechargeBean;
import com.zhuye.huochebanghuozhu.data.GetData;
import com.zhuye.huochebanghuozhu.fragment.BaseFragment;
import com.zhuye.huochebanghuozhu.utils.SharedPreferencesUtil;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/1/9 0009.
 */

public class ChongXiaoBaseFragment extends BaseFragment {
    @BindView(R.id.me_woloat_home)
    RecyclerView meWoloatHome;
    Unbinder unbinder;

    @Override
    protected void initView() {
        adapter = new MewoloatAdapter2(R.layout.me_woloat_item);
        meWoloatHome.setAdapter(adapter);
        meWoloatHome.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
    MewoloatAdapter2 adapter;
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
