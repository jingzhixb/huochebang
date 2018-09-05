package com.zhuye.huochebangsiji.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zhuye.huochebangsiji.R;
import com.zhuye.huochebangsiji.adapter.MewoloatAdapter;
import com.zhuye.huochebangsiji.bean.XiaoFeiBean;
import com.zhuye.huochebangsiji.data.GetData;
import com.zhuye.huochebangsiji.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

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
        List data = new ArrayList();
        data.add("asd");
        data.add("asd");
        data.add("asd");
        data.add("asd");
        data.add("asd");
         adapter = new MewoloatAdapter(getActivity());
        meWoloatHome.setAdapter(adapter);
        meWoloatHome.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
    MewoloatAdapter adapter;
    @Override
    protected int getResId() {
        return R.layout.fragment_me_woloat;
    }


    @Override
    protected void initData() {
        super.initData();
        GetData.consumption(SharedPreferencesUtil.getInstance().getString("token"),1,this,1000);
    }

    @Override
    public void success(int requestcode, Object o) {
        XiaoFeiBean xiaoFeiBean = (XiaoFeiBean) o;
        adapter.addData(xiaoFeiBean.getData());
    }
}
