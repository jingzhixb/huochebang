package com.zhuye.huochebangsiji.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.zhuye.huochebangsiji.R;
import com.zhuye.huochebangsiji.adapter.MessageAdapter;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        unbinder1 = ButterKnife.bind(this, super.onCreateView(inflater, container, savedInstanceState));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void setCurrentPos(int pos) {
        if (messageViewpager != null) {
            messageViewpager.setCurrentItem(pos);
        }
        // viewpagertab.set
    }

    @Override
    protected void initView() {

        MessageAdapter adapter = new MessageAdapter(getChildFragmentManager());
        messageViewpager.setAdapter(adapter);
        viewpagertab.setViewPager(messageViewpager);

        TextView view = (TextView) viewpagertab.getTabAt(0);
        view.setTextColor(getActivity().getResources().getColor(R.color.dindansel));

        viewpagertab.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < 5; i++) {
                    TextView view = (TextView) viewpagertab.getTabAt(i);
                    if (i == position) {
                        view.setTextColor(getActivity().getResources().getColor(R.color.dindansel));
                    } else {
                        view.setTextColor(getActivity().getResources().getColor(R.color.dindansnor));
                    }
                }


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
