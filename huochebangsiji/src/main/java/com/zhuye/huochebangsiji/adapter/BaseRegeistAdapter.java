package com.zhuye.huochebangsiji.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zhuye.huochebangsiji.fragment.BaseRegeistFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/23 0023.
 */

public class BaseRegeistAdapter extends FragmentPagerAdapter {

    private List<String> titles = new ArrayList<>(2);
    private List<BaseRegeistFragment> fragments = new ArrayList<>(2);

    public BaseRegeistAdapter(FragmentManager fm) {
        super(fm);
        titles.add("我是车主");
//        titles.add("我不是车主");

        fragments.add(new BaseRegeistFragment());
        fragments.add(new BaseRegeistFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return titles.size();
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);

    }
}
