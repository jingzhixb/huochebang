package com.zhuye.huochebangsiji.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zhuye.huochebangsiji.fragment.BaseFragment;
import com.zhuye.huochebangsiji.fragment.XianXiaChongZhiFragment;
import com.zhuye.huochebangsiji.fragment.XiaoFeijilragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/10 0010.
 */

public class MyWaloatAdapter extends FragmentPagerAdapter {

    List<BaseFragment> fragments = new ArrayList<>();
    List<String> titles = new ArrayList<>();
    public MyWaloatAdapter(FragmentManager fm) {
        super(fm);
        fragments.add(new XiaoFeijilragment());
        fragments.add(new XianXiaChongZhiFragment());
        titles.add("消费记录");
        titles.add("线下充值");

    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
