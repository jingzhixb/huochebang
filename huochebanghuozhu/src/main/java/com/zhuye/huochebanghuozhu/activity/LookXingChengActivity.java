package com.zhuye.huochebanghuozhu.activity;

import android.util.Log;
import android.view.Window;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.navisdk.adapter.BNRoutePlanNode;
import com.baidu.navisdk.adapter.BaiduNaviManager;
import com.zhuye.huochebanghuozhu.R;
import com.zhuye.huochebanghuozhu.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class LookXingChengActivity extends BaseActivity {


    @BindView(R.id.bai_mapView)
    TextureMapView mapview;
    private MyLocationConfiguration.LocationMode mCurrentMode;
    @Override
    protected void requestBefore() {
        super.requestBefore();
        //// TODO: 2018/1/12 0012 初始化地图 
        SDKInitializer.initialize(getApplicationContext());
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);// 设置标题栏不可用
        //BaiduNaviManager.getInstance().init();


        mCurrentMode = MyLocationConfiguration.LocationMode.COMPASS;

    }

    @Override
    protected int getResId() {
        return R.layout.activity_look_xing_cheng;
    }

    // 定位相关
    LocationClient mLocClient;
    BaiduMap mBaidumap;
    RoutePlanSearch mSearch;
    @Override
    protected void initView() {
        super.initView();
        //routeplanToNavi();
        // 不显示缩放比例尺
        mapview.showZoomControls(false);
        // 不显示百度地图Logo
        mapview.removeViewAt(1);
        mBaidumap = mapview.getMap();

        // 开启定位图层
        mBaidumap.setMyLocationEnabled(true);




        //地图点击事件处理
        mBaidumap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {

            @Override
            public boolean onMapPoiClick(MapPoi arg0) {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public void onMapClick(LatLng arg0) {
                // TODO Auto-generated method stub
                mBaidumap.hideInfoWindow();
            }
        });

// 初始化搜索模块，注册事件监听
        mSearch = RoutePlanSearch.newInstance();
        //mSearch.setOnGetRoutePlanResultListener(this);
        mBaidumap.setOnMapLoadedCallback(new BaiduMap.OnMapLoadedCallback() {

            @Override
            public void onMapLoaded() {
                // TODO Auto-generated method stub
                //地图加载完成
               // SearchButtonProcess();//调用路径规划
               // Toast.makeText(LookXingChengActivity.this,"sdfa",Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected void initData() {
        super.initData();

        startLocation();

    }


    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();
    private void startLocation() {
        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        //注册监听函数

        LocationClientOption option = new LocationClientOption();

        //可选，默认gcj02，设置返回的定位结果坐标系
        //option.setCoorType("bd09ll");
        option.setIsNeedAddress(true);
        //可选，是否需要地址信息，默认为不需要，即参数为false
        //如果开发者需要获得当前点的地址信息，此处必须为true
        //option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
       // option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.            getLocationDescribe里得到，结果类似于“在北京天安门附近”
       // option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiLi            st里得到
       // option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是            否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
       // option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//设置高精度定位定位模式


        mLocationClient.setLocOption(option);
        //mLocationClient为第二步初始化过的LocationClient对象
        //需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
        //更多LocationClientOption的配置，请参照类参考中LocationClientOption类的详细说明
        mLocationClient.start();
    }


    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location){
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取地址相关的结果信息
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明
            String city = location.getCity();    //获取城市
            String city_code =  location.getCityCode();
            mLocationClient.stop();
            //请求网络数据
            Log.i("as",city+city_code);
            toast(city+city_code);
            // Sputils.setString(MainActivity.this,"code",city_code);
            //Sputils.setString(MainActivity.this,"city",city);

            latitude  = location.getLatitude();  // 获取精度纬度
            longitude = location.getLongitude();

            LatLng ll = new LatLng(latitude, longitude);
            //MapStatus.Builder builder = new MapStatus.Builder();
            //builder.target(ll).zoom(18.0f);
            //builder.target(ll).zoom(10.0f);
            //mBaidumap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));//地图移动到当前经纬                  度
            checkAddPosition();
        }
    }
    Double latitude;
    Double longitude;
    GeoCoder mearch;
    //通过地址获取经纬度
    public  void  checkAddPosition( )
    {
        if(mearch==null)
        {
            mearch = GeoCoder.newInstance();
            mearch.setOnGetGeoCodeResultListener(listener);
        }
        mearch.geocode(new GeoCodeOption()
                .city("南阳市").address("方城县"));
                //.address("xx村"));

    }


    //计算地理的 编码与反编码
    OnGetGeoCoderResultListener listener = new OnGetGeoCoderResultListener() {
        public void onGetGeoCodeResult(GeoCodeResult result) {
            if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                //没有检索到结果
               // PromptManager.showToast(BDNavigationActivity.this,"当前城市无法进行定位！");
                toast("当前城市无法进行定位！");
            }
            else {

                //获取地理编码结果
                LatLng location = result.getLocation();
                latitude1 = location.latitude;
                longitude1 = location.longitude;
                getLine(latitude, longitude, latitude1, longitude1);
                Log.i("TAG", "latitude1:" + latitude1 + "  longitude1" + longitude1);
            }

        }
        @Override
        public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
            if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                //没有找到检索结果
            }
            //获取反向地理编码结果
        }
    };

    double latitude1;
    double longitude1;
    public void  getLine(double latitude,double longitude,double latitude1,double longitude1)
    {
        //------------添加覆盖物--d--------
        BitmapDescriptor bdA = BitmapDescriptorFactory
                .fromResource(R.drawable.on);
        BitmapDescriptor bdB = BitmapDescriptorFactory
                .fromResource(R.drawable.off);
        LatLng llA = new LatLng(latitude, longitude);
        LatLng llB = new LatLng(latitude1, longitude1);
        MarkerOptions ooA = new MarkerOptions().position(llA).icon(bdA)
                .zIndex(9).draggable(true);
        Marker mMarkerA = (Marker) (mBaidumap.addOverlay(ooA));
        MarkerOptions ooB = new MarkerOptions().position(llB).icon(bdB)
                .zIndex(5);
        Marker mMarkerB = (Marker) (mBaidumap.addOverlay(ooB));
        //--------------------处理连线------------------
        Log.i("TAG", "latitude="+latitude+"  longitude"+longitude);
        List<LatLng> points = new ArrayList<LatLng>();
        points.add(llA);
        points.add(llB);
        OverlayOptions ooPolyline = new PolylineOptions().width(5)
                .color(0xAAFF0000).points(points);
        mBaidumap.addOverlay(ooPolyline);

        //----------------
        MapStatus.Builder builder = new MapStatus.Builder();
        LatLng ll = new LatLng((latitude+latitude1)/2, (longitude+longitude1)/2);
        builder.target(ll).zoom(9.5f);
        mBaidumap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));//地图移动到当前经纬
    }

    /**
     * 发起路线规划搜索示例
     *
     * @param
     */
    private void SearchButtonProcess() {
        //重置浏览节点的路线数据
        //route = null;
        mBaidumap.clear();
        ArrayList<PlanNode> arg0 =new ArrayList<PlanNode>();
        //设置起终点、途经点信息，对于tranist search 来说，城市名无意义
        PlanNode stNode = PlanNode.withLocation(new LatLng(100 ,101));
        PlanNode enNode = PlanNode.withLocation(new LatLng(103 ,104));
//        for (int i = 1; i < StationsList.getStationsList().size()-1; i++) {
//            PlanNode node = PlanNode.withLocation(new LatLng(StationsList.getStationsList().get(i).getLatitude(), StationsList.getStationsList().get(i).getLongitude()));
//            arg0.add(node);
//        }

        // 实际使用中请对起点终点城市进行正确的设定
        mSearch.drivingSearch((new DrivingRoutePlanOption())
                .from(stNode)//起点.passBy(arg0)//途经点
                .to(enNode));//终点
    }
    private void routeplanToNavi(BNRoutePlanNode.CoordinateType coType) {
        BNRoutePlanNode sNode = null;
        BNRoutePlanNode eNode = null;
        switch(coType) {
            case GCJ02: {
                sNode = new BNRoutePlanNode(116.30142, 40.05087,
                        "百度大厦", null, coType);
                eNode = new BNRoutePlanNode(116.39750, 39.90882,
                        "北京天安门", null, coType);
                break;
            }
            case WGS84: {
                sNode = new BNRoutePlanNode(116.300821,40.050969,
                        "百度大厦", null, coType);
                eNode = new BNRoutePlanNode(116.397491,39.908749,
                        "北京天安门", null, coType);
                break;
            }
            case BD09_MC: {
                sNode = new BNRoutePlanNode(12947471,4846474,
                        "百度大厦", null, coType);
                eNode = new BNRoutePlanNode(12958160,4825947,
                        "北京天安门", null, coType);
                break;
            }
            case BD09LL: {
                sNode = new BNRoutePlanNode(116.30784537597782,
                        40.057009624099436, "百度大厦", null, coType);
                eNode = new BNRoutePlanNode(116.40386525193937,
                        39.915160800132085, "北京天安门", null, coType);
                break;
            }
            default : ;
        }
        if (sNode != null && eNode != null) {
            List<BNRoutePlanNode> list = new ArrayList<BNRoutePlanNode>();
            list.add(sNode);
            list.add(eNode);
            BaiduNaviManager.getInstance().launchNavigator(this, list, 1, true, new DemoRoutePlanListener(sNode));
        }
    }

    public class DemoRoutePlanListener implements BaiduNaviManager.RoutePlanListener {

        private BNRoutePlanNode mBNRoutePlanNode = null;
        public DemoRoutePlanListener(BNRoutePlanNode node){
            mBNRoutePlanNode = node;
        }

        @Override
        public void onJumpToNavigator() {
//            Intent intent = new Intent(BNDemoMainActivity.this, BNDemoGuideActivity.class);
//            Bundle bundle = new Bundle();
//            bundle.putSerializable(ROUTE_PLAN_NODE, (BNRoutePlanNode) mBNRoutePlanNode);
//            intent.putExtras(bundle);
//            startActivity(intent);
        }
        @Override
        public void onRoutePlanFailed() {
            // TODO Auto-generated method stub

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // activity 暂停时同时暂停地图控件
        mapview.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // activity 恢复时同时恢复地图控件
        mapview.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // activity 销毁时同时销毁地图控件
        mapview.onDestroy();
        if (mSearch != null) {
            mSearch.destroy();

        }

    }
}
