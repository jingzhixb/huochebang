package com.zhuye.huochebanghuozhu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.baidu.navisdk.adapter.BNOuterTTSPlayerCallback;
import com.baidu.navisdk.adapter.BNRoutePlanNode;
import com.baidu.navisdk.adapter.BaiduNaviManager;
import com.zhuye.huochebanghuozhu.R;
import com.zhuye.huochebanghuozhu.base.BaseActivity;
import com.zhuye.huochebanghuozhu.widget.DrivingRouteOverlay;
import com.zhuye.huochebanghuozhu.widget.MyDrivingRouteOverlay;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DaoHangActivity extends BaseActivity {


    @BindView(R.id.map)
    MapView map;
    BaiduMap mBaiduMap = null;

    // 导航相关
    public static final String TAG = "NaviSDkDemo";
    private static final String APP_FOLDER_NAME = "BNSDKDemo";
    public static final String ROUTE_PLAN_NODE = "routePlanNode";
    private String mSDCardPath = null;

    @Override
    protected int getResId() {
        return R.layout.activity_dao_hang;
    }

    @Override
    protected void initView() {
        super.initView();



        start = getIntent().getStringExtra("start");
        end =  getIntent().getStringExtra("end");


        //mBaiduMap = map.getMap();
        //mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        map.showZoomControls(false);
// 初始化搜索模块，注册事件监听
        mSearch = RoutePlanSearch.newInstance();
        mBaiduMap = map.getMap();

        initBianma();

        initBaiduMap();

        //initPio();

//        if (initDirs() ) {
//            initNavi();
//        }


    }
    GeoCoder coder;
    private void initBianma() {

        coder = GeoCoder.newInstance();
        coder.setOnGetGeoCodeResultListener(listene);

       // coder.set

        coder.geocode(new GeoCodeOption()
                .city(start).address(start));
        coder.geocode(new GeoCodeOption().city(end).address(end));
    }



    private List<LatLng> address = new ArrayList<>();

    private void initBaiduMap() {


        //地图点击事件处理
        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {

            @Override
            public boolean onMapPoiClick(MapPoi arg0) {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public void onMapClick(LatLng arg0) {
                // TODO Auto-generated method stub
                mBaiduMap.hideInfoWindow();
                toast("dianjile");
            }
        });

        //mSearch.setOnGetRoutePlanResultListener(this);
        mBaiduMap.setOnMapLoadedCallback(new BaiduMap.OnMapLoadedCallback() {

            @Override
            public void onMapLoaded() {
                // TODO Auto-generated method stub
                //地图加载完成

            }
        });
    }


    OnGetGeoCoderResultListener listene = new OnGetGeoCoderResultListener() {
        public void onGetGeoCodeResult(GeoCodeResult result) {

            if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                //没有检索到结果
            }

            //获取地理编码结果
            Toast.makeText(DaoHangActivity.this, result.toString() ,Toast.LENGTH_SHORT).show();
            address.add(result.getLocation());
            if(address.size()==2){
                SearchButtonProcess();//调用路径规划
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



    private void SearchButtonProcess() {

        mBaiduMap.clear();
        //DrivingRoutePlanOption op = new DrivingRoutePlanOption();
        mSearch.setOnGetRoutePlanResultListener(listener);

//        PlanNode stNode = PlanNode.withCityNameAndPlaceName(start, "郑州大学");
//        PlanNode enNode = PlanNode.withCityNameAndPlaceName("南阳", "方城");
        PlanNode stNode = PlanNode.withLocation(address.get(0));
        PlanNode enNode = PlanNode.withLocation(address.get(1));


        mSearch.drivingSearch((new DrivingRoutePlanOption())
                .from(stNode)
                .to(enNode));


    }

    String start;
    String end;



    OnGetRoutePlanResultListener listener = new OnGetRoutePlanResultListener() {

        @Override
        public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {

        }

        @Override
        public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {

        }

        @Override
        public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {

        }

        public void onGetDrivingRouteResult(DrivingRouteResult result) {
            //获取驾车线路规划结果
            //toast(result.getRouteLines().toString());
           // DrivingRouteOverlay over = new

            if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                Toast.makeText(DaoHangActivity.this, "抱歉，未找到结果", Toast.LENGTH_SHORT).show();
            }
            if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
                //起终点或途经点地址有岐义，通过以下接口获取建议查询信息
                //result.getSuggestAddrInfo()
                Toast.makeText(DaoHangActivity.this, "抱歉，未找到结果", Toast.LENGTH_SHORT).show();
                return;
            }
            if (result.error == SearchResult.ERRORNO.NO_ERROR) {

//            route = result.getRouteLines().get(0);
                DrivingRouteOverlay overlay = new MyDrivingRouteOverlay(mBaiduMap);//路线覆盖物，MyDrivingRouteOverlay代码下面给出
                routeOverlay = overlay;
//            mBaidumap.setOnMarkerClickListener(overlay);
                overlay.setData(result.getRouteLines().get(0));

                overlay.addToMap();

                overlay.zoomToSpan();

            }

        }

        @Override
        public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

        }

        @Override
        public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

        }

    };
    DrivingRouteOverlay routeOverlay;

    RoutePlanSearch mSearch;

    String authinfo = null;
    // 导航 初始化引擎
    private void initNavi() {
//      BaiduNaviManager.getInstance().setNativeLibraryPath(
//              mSDCardPath + "/BaiduNaviSDK_SO");
        BaiduNaviManager.getInstance().init(this, mSDCardPath, APP_FOLDER_NAME,
                new BaiduNaviManager.NaviInitListener() {
                    @Override
                    public void onAuthResult(int status, String msg) {
                        if (0 == status) {
                            authinfo = "key校验成功!";
                        } else {
                            authinfo = "key校验失败, " + msg;
                        }
                        DaoHangActivity.this.runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                Toast.makeText(DaoHangActivity.this, authinfo,
                                        Toast.LENGTH_LONG).show();
                            }
                        });
                    }

                    public void initSuccess() {
                        Toast.makeText(DaoHangActivity.this, "百度导航引擎初始化成功",
                                Toast.LENGTH_SHORT).show();
                        if ( BaiduNaviManager.isNaviInited() ) {
                            routeplanToNavi(BNRoutePlanNode.CoordinateType.WGS84);
                        }
                    }

                    public void initStart() {
                        Toast.makeText(DaoHangActivity.this, "百度导航引擎初始化开始",
                                Toast.LENGTH_SHORT).show();
                    }

                    public void initFailed() {
                        Toast.makeText(DaoHangActivity.this, "百度导航引擎初始化失败",
                                Toast.LENGTH_SHORT).show();
                    }
                },mTTSCallback);
    }

    private BNOuterTTSPlayerCallback mTTSCallback = new BNOuterTTSPlayerCallback() {

        @Override
        public void stopTTS() {
            // TODO Auto-generated method stub

        }

        @Override
        public void resumeTTS() {
            // TODO Auto-generated method stub

        }

        @Override
        public void releaseTTSPlayer() {
            // TODO Auto-generated method stub

        }

        @Override
        public int playTTSText(String speech, int bPreempt) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public void phoneHangUp() {
            // TODO Auto-generated method stub

        }

        @Override
        public void phoneCalling() {
            // TODO Auto-generated method stub

        }

        @Override
        public void pauseTTS() {
            // TODO Auto-generated method stub

        }

        @Override
        public void initTTSPlayer() {
            // TODO Auto-generated method stub

        }

        @Override
        public int getTTSState() {
            // TODO Auto-generated method stub
            return 0;
        }
    };


    @Override
    protected void requestBefore() {
        super.requestBefore();
        SDKInitializer.initialize(getApplicationContext());
    }

    private String getSdcardDir() {
        if (Environment.getExternalStorageState().equalsIgnoreCase(
                Environment.MEDIA_MOUNTED)) {
            return Environment.getExternalStorageDirectory().toString();
        }
        return null;
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

        public DemoRoutePlanListener(BNRoutePlanNode node) {
            mBNRoutePlanNode = node;
        }

        @Override
        public void onJumpToNavigator() {
            Intent intent = new Intent(DaoHangActivity.this,
                    BNDemoGuideActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(ROUTE_PLAN_NODE,
                    (BNRoutePlanNode) mBNRoutePlanNode);
            intent.putExtras(bundle);
            startActivity(intent);
        }

        @Override
        public void onRoutePlanFailed() {
            // TODO Auto-generated method stub

        }
    }

    private boolean initDirs() {
        mSDCardPath = getSdcardDir();
        if ( mSDCardPath == null ) {
            return false;
        }
        File f = new File(mSDCardPath, APP_FOLDER_NAME);
        if ( !f.exists() ) {
            try {
                f.mkdir();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSearch.destroy();
    }
}
