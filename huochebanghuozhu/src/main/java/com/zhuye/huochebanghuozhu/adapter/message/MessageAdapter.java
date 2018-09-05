package com.zhuye.huochebanghuozhu.adapter.message;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zhuye.huochebanghuozhu.fragment.dingdan.BaseDingDanview;
import com.zhuye.huochebanghuozhu.fragment.dingdan.DaiFuFragment;
import com.zhuye.huochebanghuozhu.fragment.dingdan.HistoryFragment;
import com.zhuye.huochebanghuozhu.fragment.dingdan.JinxingFragment;
import com.zhuye.huochebanghuozhu.fragment.dingdan.QueRenFragment;
import com.zhuye.huochebanghuozhu.fragment.dingdan.TotalFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/10 0010.
 */

public class MessageAdapter extends FragmentPagerAdapter {

    List<BaseDingDanview> fragments = new ArrayList<>();
    List<String> titles = new ArrayList<>();
    public MessageAdapter(FragmentManager fm) {
        super(fm);
        fragments.add(new TotalFragment());
        fragments.add(new DaiFuFragment());
        fragments.add(new JinxingFragment());
        fragments.add(new QueRenFragment());
        fragments.add(new HistoryFragment());
        titles.add("全部订单");
        titles.add("待付款");
        titles.add("进行中");
        titles.add("待确认");
        titles.add("历史订单");
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    public void setCurpos(int pos){
        fragments.get(pos).setCurpos(pos);

    }
}
