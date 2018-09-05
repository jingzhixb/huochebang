package com.zhuye.huochebanghuozhu.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.zhuye.huochebanghuozhu.R;
import com.zhuye.huochebanghuozhu.adapter.message.MessageAdapter;
import com.zhuye.huochebanghuozhu.fragment.dingdan.BaseDingDanview;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/1/9 0009.
 */

public class MessageFragment extends BaseFragment {

    @BindView(R.id.viewpagertab)
    SmartTabLayout viewpagertab;
    @BindView(R.id.message_viewpager)
    ViewPager messageViewpager;
    Unbinder unbinder;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.tou)
    RelativeLayout tou;
    Unbinder unbinder1;

    public void showBack() {
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }

    public void setCurrentPos(int pos) {
        if (messageViewpager != null) {
            messageViewpager.setCurrentItem(pos);
        }
        // viewpagertab.set
    }


    @Override
    protected void initView() {
        final MessageAdapter adapter = new MessageAdapter(getChildFragmentManager());
        messageViewpager.setAdapter(adapter);
        viewpagertab.setViewPager(messageViewpager);

        TextView view = (TextView) viewpagertab.getTabAt(0);
        view.setTextColor(getActivity().getResources().getColor(R.color.dindansel));

        adapter.setCurpos(0);

        viewpagertab.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                adapter.setCurpos(position);

                for (int i = 0; i < 5; i++) {
                    TextView view = (TextView) viewpagertab.getTabAt(i);
                    if (i == position) {
                        view.setTextColor(getActivity().getResources().getColor(R.color.dindansel));
                    } else {
                        view.setTextColor(getActivity().getResources().getColor(R.color.dindansnor));
                    }
                }
                ((BaseDingDanview) adapter.getItem(position)).clear();
                ((BaseDingDanview) adapter.getItem(position)).initData();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //viewpagertab.setCustomTabColorizer();
        // viewpagertab.setupWithViewPager(messageViewpager);
        //viewpagertab.setSelectedIndicatorColors(Color.BLUE);
    }

    @Override
    protected int getResId() {
        return R.layout.fragment_message;
    }


    @Override
    public void success(int requestcode, Object o) {

    }


}
