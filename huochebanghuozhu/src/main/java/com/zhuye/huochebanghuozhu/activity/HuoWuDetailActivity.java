package com.zhuye.huochebanghuozhu.activity;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.zhuye.huochebanghuozhu.R;
import com.zhuye.huochebanghuozhu.adapter.fabu.DetaliFabuAdapter;
import com.zhuye.huochebanghuozhu.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class HuoWuDetailActivity extends BaseActivity {


    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.viewpagertab)
    SmartTabLayout viewpagertab;
    @BindView(R.id.message_viewpager)
    ViewPager messageViewpager;
    @BindView(R.id.xinzeng)
    TextView xinzeng;

    @Override
    protected int getResId() {
        return R.layout.activity_huo_wu_detail;
    }

    int type;

    @Override
    protected void initView() {
        super.initView();

        type =  getIntent().getIntExtra("type",0);

        DetaliFabuAdapter adapter = new DetaliFabuAdapter(getSupportFragmentManager());
        messageViewpager.setAdapter(adapter);
        viewpagertab.setViewPager(messageViewpager);
        TextView view = (TextView) viewpagertab.getTabAt(0);
        view.setTextColor(getResources().getColor(R.color.dindansel));
        viewpagertab.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0 ; i <2 ;i++){
                    TextView view = (TextView) viewpagertab.getTabAt(i);
                    if(i ==position){
                        view.setTextColor(getResources().getColor(R.color.dindansel));
                    }else {
                        view.setTextColor(getResources().getColor(R.color.dindansnor));
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        if(type == 0){
            xinzeng.setVisibility(View.VISIBLE);
        }else if(type == 1){
            xinzeng.setVisibility(View.INVISIBLE);
        }
    }

    @OnClick({R.id.back,R.id.xinzeng})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.xinzeng:

                finish();
                break;
            case R.id.back:
                finish();
                break;
        }

    }


}
