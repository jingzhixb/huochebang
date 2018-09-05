package com.zhuye.huochebang.ui;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.zhuye.huochebang.BaseActivity;
import com.zhuye.huochebang.R;
import com.zhuye.huochebang.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class GuideActivity extends BaseActivity {

    @BindView(R.id.guide_Vp)
    ViewPager guideVp;
    @BindView(R.id.skip)
    ImageView skip;
    @BindView(R.id.btn)
    Button btn;

    @Override
    protected int getResId() {
        return R.layout.activity_guide;
    }

    private NavigatorPagerAdapter mPagerAdapter = new NavigatorPagerAdapter();
    @OnClick({R.id.guide_Vp, R.id.skip, R.id.btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.guide_Vp:
                break;
            case R.id.skip:
                skip(view);
                break;
            case R.id.btn:
                start(view);
                break;
        }
    }


    //开始体验按钮
    public void start(View view) {
//        SPUtils.getInstance().put("is_guide_show", true);
        SharedPreferencesUtil.getInstance().putBoolean("isfisr",true);
        // StorageUtil.putBoolean(this,"is_guide_show",true);
        //String token = StorageUtil.getTokenId(this);
//        String token = SPUtils.getInstance("userInfo").getString("token");
//        if (!token.equals("")){
//            startActivity(new Intent(this, MainActivity.class));
//        } else {
//            Intent intent = new Intent(this, LoginActivity.class);
//            startActivity(intent);
//        }
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }


    //跳过按钮
    public void skip(View view) {
//        SPUtils.getInstance().put("is_guide_show", true);
        SharedPreferencesUtil.getInstance().putBoolean("isfisr",true);
        //StorageUtil.putBoolean(this,"is_guide_show",true);
//        Intent intent = new Intent(this, BottomBarActivity.class);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void initView() {
        super.initView();
        TransformUtil.forward(guideVp, new DepthPageTransformer());
        guideVp.setAdapter(mPagerAdapter);

        mImageList = new ArrayList<>();   //初始化mImageList，防止空指针
        for (int i = 0; i < imagesArray.length; i++) {
            //做出页面
            ImageView imageView = new ImageView(GuideActivity.this);
            imageView.setImageResource(imagesArray[i]);
//            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            mImageList.add(imageView);
        }

        viewPagerScroll();
    }


    public void viewPagerScroll() {
        //在页面发生改变的时候回调
        guideVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            //控制按钮的隐藏和显示
            public void onPageSelected(int position) {
                // TODO Auto-generated method stub
                if (position == imagesArray.length - 1) {
                    //最后一页
                    btn.setVisibility(View.VISIBLE);
//                    skip_Iv.setVisibility(View.GONE);
                } else {
                    btn.setVisibility(View.GONE);
//                    skip_Iv.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrolled(int position, float arg1, int arg2) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub
            }
        });
    }

    private static final int[] imagesArray = new int[]{
            R.drawable.j_viewpaper_1,
            R.drawable.j_viewpaper_2,
            R.drawable.j_viewpaper_3,

    };

    private List<ImageView> mImageList;

    public class NavigatorPagerAdapter extends PagerAdapter {

        //返回ViewPager里面有几页
        public int getCount() {
            // TODO Auto-generated method stub
            return imagesArray.length;
        }

        @Override
        //视图是否由对象
        public boolean isViewFromObject(View view, Object obj) {
            // TODO Auto-generated method stub
            return view == obj;
        }

        //用来生成每一页的试图
        //container每一页的父容器
        //position当前显示的第几页，从0开始
        public Object instantiateItem(ViewGroup container, int position) {

            ImageView imageView = mImageList.get(position);
            container.addView(imageView);
            return imageView;
        }

        //在切换下一页的时候销毁上一页的试图
        public void destroyItem(ViewGroup container, int position, Object object) {

            container.removeView((View) object);

        }
    }
}
