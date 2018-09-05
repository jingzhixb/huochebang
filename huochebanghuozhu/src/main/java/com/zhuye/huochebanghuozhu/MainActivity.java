package com.zhuye.huochebanghuozhu;

import android.*;
import android.content.Intent;
import android.support.annotation.NonNull;
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
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;
import com.zhuye.huochebanghuozhu.activity.LoginActivity;
import com.zhuye.huochebanghuozhu.base.BaseActivity;
import com.zhuye.huochebanghuozhu.bean.NewsNumBean;
import com.zhuye.huochebanghuozhu.data.http.CommonApi;
import com.zhuye.huochebanghuozhu.fragment.FabuFragment;
import com.zhuye.huochebanghuozhu.fragment.MeFragment;
import com.zhuye.huochebanghuozhu.fragment.MessageFragment;
import com.zhuye.huochebanghuozhu.fragment.SijiDatingFragment;
import com.zhuye.huochebanghuozhu.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import cn.jpush.android.api.JPushInterface;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends BaseActivity implements PermissionListener {


    @BindView(R.id.home_framelayout)
    FrameLayout homeFramelayout;
    @BindView(android.R.id.content)
    FrameLayout content;
    @BindView(R.id.home_fragmenttabhost)
    FragmentTabHost homeFragmenttabhost;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;

    private List<String> tabnames = new ArrayList<>();
    private  List<Class> tabfragment = new ArrayList<>();
    private  List<Integer> tabimage = new ArrayList<>();

    @Override
    protected int getResId() {
        return R.layout.activity_main;
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (SharedPreferencesUtil.getInstance().getString("token") == null || TextUtils.isEmpty(SharedPreferencesUtil.getInstance().getString("token"))) {
            homeFragmenttabhost.setCurrentTab(0);
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
    protected void initView() {
        super.initView();
        if (!AndPermission.hasPermission(MainActivity.this
                ,android.Manifest.permission.CALL_PHONE, android.Manifest.permission.READ_PHONE_STATE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            AndPermission.with(MainActivity.this)
                    .requestCode(100)
                    .permission( android.Manifest.permission.CALL_PHONE,android.Manifest.permission.READ_PHONE_STATE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .rationale(new RationaleListener() {
                        @Override
                        public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
                            AndPermission.rationaleDialog(MainActivity.this, rationale).show();
                        }
                    })
                    .send();
        }
        String  mobile = getIntent().getStringExtra("mobile");//13578636507
        JPushInterface.setAlias(this,10,"货主"+mobile);

        addActivity(this);
        Set<String> dat = new ArraySet<>();
        dat.add("货主");

        JPushInterface.setTags(this,10,dat);
        tabnames.add("司机大厅");
        tabnames.add("发布需求");
        tabnames.add("我的订单");
        tabnames.add("我的");

        tabfragment.add(SijiDatingFragment.class);
        tabfragment.add( FabuFragment.class);
        tabfragment.add( MessageFragment.class);
        tabfragment.add( MeFragment.class);

        tabimage.add(R.drawable.dating_selecter);
        tabimage.add(R.drawable.fabu_selecter);
        tabimage.add(R.drawable.dingdan_selecter);
        tabimage.add(R.drawable.me_selecter);

        initTab();
        startLocation();
    }


    @Override
    protected void initListener() {
        super.initListener();

        homeFragmenttabhost.getTabWidget().getChildTabViewAt(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SharedPreferencesUtil.getInstance().getString("token") == null || TextUtils.isEmpty(SharedPreferencesUtil.getInstance().getString("token"))) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    //getActivity().finish();
                }else {
                    homeFragmenttabhost.setCurrentTab(1);
                }
            }
        });


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
//        homeFragmenttabhost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
//            @Override
//            public void onTabChanged(String s) {
//                if (SharedPreferencesUtil.getInstance().getString("token") == null || TextUtils.isEmpty(SharedPreferencesUtil.getInstance().getString("token"))) {
//                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
//                    //getActivity().finish();
//                }
//            }
//        });
    }


    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();
    private void startLocation() {
        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[]
            grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        /**
         * 转给AndPermission分析结果。
         *
         * @param requestCode  请求码。
         * @param permissions  权限数组，一个或者多个。
         * @param grantResults 请求结果。
         * @param listener PermissionListener 对象。
         */
        AndPermission.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
    @Override
    public void onSucceed(int requestCode, List<String> grantPermissions) {

    }

    @Override
    public void onFailed(int requestCode, List<String> deniedPermissions) {

    }




    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location){
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取地址相关的结果信息
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明
            String city = location.getCity();    //获取城市
            String city_code =  location.getCityCode();
            if(city != null){
                mLocationClient.stop();
            }
            //请求网络数据
            Log.i("as",city+city_code);
            //toast(city+city_code);
           // Sputils.setString(MainActivity.this,"code",city_code);
//            Sputils.setString(MainActivity.this,"city",city);
        }
    }



    List<TextView> aa = new ArrayList<>();

    private void initTab() {
        homeFragmenttabhost.setup(this,getSupportFragmentManager(),R.id.home_framelayout);
        for(int i = 0; i<4;i++){
            View v= View.inflate(this,R.layout.tabview,null);
            ImageView tabiv = v.findViewById(R.id.tab_view);
            TextView tv = v.findViewById(R.id.tab_tv);
            TextView messages = v.findViewById(R.id.messages);
            aa.add(messages);
           // ImageView iv = v.findViewById(R.id.tab_view);
            if(i==3){
                messages.setVisibility(View.VISIBLE);
            }else {
                messages.setVisibility(View.GONE);
            }
            tabiv.setImageResource(tabimage.get(i));
            tv.setText(tabnames.get(i));
            TabHost.TabSpec view  = homeFragmenttabhost.newTabSpec(tabnames.get(i)).setIndicator(v);
            homeFragmenttabhost.addTab(view,tabfragment.get(i),null);
        }
    }




    Long exitTime= 0l;
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
