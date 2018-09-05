package com.zhuye.huochebangsiji;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.util.ArraySet;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.umeng.socialize.UMShareAPI;
import com.zhuye.huochebangsiji.activity.LoginActivity;
import com.zhuye.huochebangsiji.base.BaseActivity;
import com.zhuye.huochebangsiji.bean.MessageEvent;
import com.zhuye.huochebangsiji.bean.NewsNumBean;
import com.zhuye.huochebangsiji.data.GetData;
import com.zhuye.huochebangsiji.data.http.CommonApi;
import com.zhuye.huochebangsiji.fragment.FabuFragment;
import com.zhuye.huochebangsiji.fragment.MeFragment;
import com.zhuye.huochebangsiji.fragment.MessageFragment;
import com.zhuye.huochebangsiji.fragment.SijiDatingFragment;
import com.zhuye.huochebangsiji.utils.SharedPreferencesUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import cn.jpush.android.api.JPushInterface;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity implements BDLocationListener{
//    private static final int NEWSNUM = 10;
    @BindView(R.id.home_framelayout)
    FrameLayout homeFramelayout;
    @BindView(android.R.id.content)
    FrameLayout content;
    @BindView(R.id.home_fragmenttabhost)
    FragmentTabHost homeFragmenttabhost;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;

    private List<String> tabnames = new ArrayList<>();
    private List<Class> tabfragment = new ArrayList<>();
    private List<Integer> tabimage = new ArrayList<>();

    @Override
    protected int getResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();
        String mobile = getIntent().getStringExtra("mobile");//13578636507
        JPushInterface.setAlias(this, 10, "司机" + mobile);

        addActivity(this);
        Set<String> dat = new ArraySet<>();
        dat.add("司机");
        JPushInterface.setTags(this, 10, dat);

        tabnames.add("服务大厅");
        tabnames.add("货源");
        tabnames.add("我的订单");
        tabnames.add("我的");

        tabfragment.add(SijiDatingFragment.class);
        tabfragment.add(FabuFragment.class);
        tabfragment.add(MessageFragment.class);
        tabfragment.add(MeFragment.class);

        tabimage.add(R.drawable.dating_selecter);
        tabimage.add(R.drawable.huoyuan_selecter);
        tabimage.add(R.drawable.dingdan_selecter);
        tabimage.add(R.drawable.me_selecter);
        initTab();
        startLocation();
        homeFragmenttabhost.setCurrentTab(1);
//        homeFragmenttabhost.get


    }




    @Override
    protected void initData() {
        super.initData();
        EventBus.getDefault().register(this);
//        getnewsNumber();


    }


    @Override
    protected void initListener() {
        super.initListener();
//        homeFragmenttabhost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
//            @Override
//            public void onTabChanged(String s) {
//                if (s.equals("我的") || s.equals("我的订单")) {
//                    if (SharedPreferencesUtil.getInstance().getString("token") == null || TextUtils.isEmpty(SharedPreferencesUtil.getInstance().getString("token"))) {
//                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
//                        //getActivity().finish();
//                    }
//                }
//            }
//        });



        homeFragmenttabhost.getTabWidget().getChildTabViewAt(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SharedPreferencesUtil.getInstance().getString("token") == null || TextUtils.isEmpty(SharedPreferencesUtil.getInstance().getString("token"))) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    //getActivity().finish();
                }else {
                    homeFragmenttabhost.setCurrentTab(2);
                }
            }
        });

        homeFragmenttabhost.getTabWidget().getChildTabViewAt(3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SharedPreferencesUtil.getInstance().getString("token") == null || TextUtils.isEmpty(SharedPreferencesUtil.getInstance().getString("token"))) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    //getActivity().finish();
                }else {
                    homeFragmenttabhost.setCurrentTab(3);
                }
            }
        });

    }

    private void getnewsNumber() {
//        GetData.news_num(SharedPreferencesUtil.getInstance().getString("token"), this, NEWSNUM);
    }

    public LocationClient mLocationClient = null;

    //    private MyLocationListener myListener = new MyLocationListener();
    private void startLocation() {
        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(this);
        //注册监听函数

        LocationClientOption option = new LocationClientOption();

        option.setIsNeedAddress(true);
        //可选，是否需要地址信息，默认为不需要，即参数为false
        //如果开发者需要获得当前点的地址信息，此处必须为true

        mLocationClient.setLocOption(option);
        //mLocationClient为第二步初始化过的LocationClient对象
        //需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
        //更多LocationClientOption的配置，请参照类参考中LocationClientOption类的详细说明
        mLocationClient.start();
    }

    @Override
    public void success(int requestcode, Object o) {
        super.success(requestcode, o);
        switch (requestcode) {
//            case NEWSNUM:
//                NewsNumBean bean = (NewsNumBean) o;
//                tab_num.setText(Integer.parseInt(bean.getData().getSystem_num()) + Integer.parseInt(bean.getData().getNews_num()) + "");
//                break;
        }
    }

    NewsNumBean bean;

    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        String city = bdLocation.getCity();    //获取城市
        String city_code = bdLocation.getCityCode();
        if (city != null) {
            SharedPreferencesUtil.getInstance().putString("city", city);
            mLocationClient.stop();
            List<Fragment> fragments =   getSupportFragmentManager().getFragments();
            for (Fragment fragment : fragments) {
                if(fragment instanceof FabuFragment){
                    ((FabuFragment) fragment).initView();
                    ((FabuFragment) fragment).initData();
                    ((FabuFragment) fragment).initListener();
                }
            }
        } else {
        }
    }

//    public class MyLocationListener implements BDLocationListener {
//        @Override
//        public void onReceiveLocation(BDLocation location){
//            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
//            //以下只列举部分获取地址相关的结果信息
//            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明
//            String city = location.getCity();    //获取城市
//            String city_code =  location.getCityCode();
//            if (city != null) {
//                SharedPreferencesUtil.getInstance().putString("city",city);
//                mLocationClient.stop();
//            }
//            //请求网络数据
//           // Log.i("as",city+city_code);
//            //toast(city+city_code);
//
//            // Sputils.setString(MainActivity.this,"code",city_code);
//            //Sputils.setString(MainActivity.this,"city",city);
//        }
//    }

    List<TextView> aa = new ArrayList<>();

    private void initTab() {

        homeFragmenttabhost.setup(this, getSupportFragmentManager(), R.id.home_framelayout);
        for (int i = 0; i < 4; i++) {
            View v = View.inflate(this, R.layout.tabview, null);
            TextView tv = v.findViewById(R.id.tab_tv);
//            tab_num = v.findViewById(R.id.tab_num);
            ImageView iv = v.findViewById(R.id.tab_view);
            iv.setImageResource(tabimage.get(i));
            tv.setText(tabnames.get(i));

            TextView messages = v.findViewById(R.id.messages);
            aa.add(messages);
            // ImageView iv = v.findViewById(R.id.tab_view);
            if(i==3){
                messages.setVisibility(View.VISIBLE);
            }else {
                messages.setVisibility(View.GONE);
            }
            TabHost.TabSpec view = homeFragmenttabhost.newTabSpec(tabnames.get(i)).setIndicator(v);
            homeFragmenttabhost.addTab(view, tabfragment.get(i), null);
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        mLocationClient.start();

    }

    @Override
    public void onResume() {
        super.onResume();
        GetData.bindHandler(mHandler);
        if (SharedPreferencesUtil.getInstance().getString("token") == null || TextUtils.isEmpty(SharedPreferencesUtil.getInstance().getString("token"))) {
            homeFragmenttabhost.setCurrentTab(1);
            aa.get(3).setVisibility(View.GONE);
        }else {
            CommonApi.getInstance().news_num(SharedPreferencesUtil.getInstance().getString("token")).subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<NewsNumBean>(){

                        @Override
                        public void onSubscribe(Disposable d) {

                        }
                        @Override
                        public void onNext(NewsNumBean newsNumBean) {
                            int num =Integer.parseInt(newsNumBean.getData().getNews_num())+Integer.parseInt(newsNumBean.getData().getSystem_num());
                            if(num==0){
                                aa.get(3).setVisibility(View.GONE);
                            }else {
                                aa.get(3).setText(num+"");
                            }

//                            aa.get(3).setText("sd");
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }

    @Override
    public void onStop() {
        super.onStop();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {/* Do something */
//         toast("我也阿斯顿发生手袋了" +event.getNum());
        Log.i("as", "我也阿斯顿发生手袋了");
        if (bean == null) {
            return;
        }
        if (event.getType() == 1) {
            //tab_num.setText(Integer.parseInt(beanss.getData().getNews_num())+"");
//            tab_num.setText(event.getNum() + Integer.parseInt(bean.getData().getNews_num()) + "");

        } else if (event.getType() == 2) {
            // tab_num.setText(Integer.parseInt(beanss.getData().getSystem_num())+"");
//            tab_num.setText(Integer.parseInt(bean.getData().getSystem_num()) + event.getNum() + "");
        }
    }

    ;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }

    }


}
