package com.zhuye.huochebangsiji.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zhuye.huochebangsiji.fragment.BaseFragment;
import com.zhuye.huochebangsiji.fragment.DaiFuFragment;
import com.zhuye.huochebangsiji.fragment.HistoryFragment;
import com.zhuye.huochebangsiji.fragment.JinxingFragment;
import com.zhuye.huochebangsiji.fragment.QueRenFragment;
import com.zhuye.huochebangsiji.fragment.TotalFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/10 0010.
 */

public class MessageAdapter extends FragmentPagerAdapter {

    List<BaseFragment> fragments = new ArrayList<>();
    List<String> titles = new ArrayList<>();
    public MessageAdapter(FragmentManager fm) {
        super(fm);
        fragments.add(new TotalFragment());
        fragments.add(new DaiFuFragment());
        fragments.add(new JinxingFragment());
        fragments.add(new QueRenFragment());
        fragments.add(new HistoryFragment());
        titles.add("全部订单");
        titles.add("待成交");
        titles.add("进行中");
        titles.add("已完成");
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
}
