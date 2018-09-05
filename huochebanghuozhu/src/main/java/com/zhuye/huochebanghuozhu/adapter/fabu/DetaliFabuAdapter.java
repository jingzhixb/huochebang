package com.zhuye.huochebanghuozhu.adapter.fabu;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zhuye.huochebanghuozhu.fragment.BaseFragment;
import com.zhuye.huochebanghuozhu.fragment.fabu.WeiJieFragment;
import com.zhuye.huochebanghuozhu.fragment.fabu.YiJieFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/10 0010.
 */

public class DetaliFabuAdapter extends FragmentPagerAdapter {

    List<BaseFragment> fragments = new ArrayList<>();
    List<String> titles = new ArrayList<>();
    public DetaliFabuAdapter(FragmentManager fm) {
        super(fm);
        fragments.add(new WeiJieFragment());
        fragments.add(new YiJieFragment());
        titles.add("未接单");
        titles.add("已接单");

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
