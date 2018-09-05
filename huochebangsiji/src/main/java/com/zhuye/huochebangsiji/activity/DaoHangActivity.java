package com.zhuye.huochebangsiji.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
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
import com.bumptech.glide.Glide;
import com.zhuye.huochebangsiji.R;
import com.zhuye.huochebangsiji.base.BaseActivity;
import com.zhuye.huochebangsiji.bean.Code;
import com.zhuye.huochebangsiji.bean.GoodDetailBean;
import com.zhuye.huochebangsiji.contants.Contans;
import com.zhuye.huochebangsiji.data.GetData;
import com.zhuye.huochebangsiji.utils.DensityUtil;
import com.zhuye.huochebangsiji.utils.SharedPreferencesUtil;
import com.zhuye.huochebangsiji.widget.DrivingRouteOverlay;
import com.zhuye.huochebangsiji.widget.MyDrivingRouteOverlay;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DaoHangActivity extends BaseActivity {


    @BindView(R.id.map)
    MapView map;
    @BindView(R.id.back_pay_dao)
    ImageView backPayDao;

    @Override
    protected int getResId() {
        return R.layout.activity_dao_hang;
    }

    String start;
    String end;
    String good_id;

    @Override
    protected void initView() {
        super.initView();


        start = getIntent().getStringExtra("start");
        end = getIntent().getStringExtra("end");
        good_id = getIntent().getStringExtra("good_id");
        map.showZoomControls(false);
// 初始化搜索模块，注册事件监听
        mSearch = RoutePlanSearch.newInstance();
        mBaiduMap = map.getMap();

        initBianma();

        initBaiduMap();


    }

    private static final int GOODDETAIL = 10;
    private static final int JIEDAN = 11;

    PopupWindow window;

    TextView name;
    TextView mingcheng;
    TextView zhongliang;
    TextView shifa;
    TextView mudi;
    TextView cheliang;
    TextView yunfei;
    TextView lianxi;
    TextView shuoming;
    TextView xiehuo;
    ImageView touxing;
    Button jiedan;
    View search;

    private void initWindows() {
        window = new PopupWindow(this);
        window.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        window.setHeight(DensityUtil.dip2px(this, 380));
        search = View.inflate(this, R.layout.huoyuandetali_bottom, null);
        name = (TextView) search.findViewById(R.id.jinfen_name);
        mingcheng = (TextView) search.findViewById(R.id.mingcheng);
        zhongliang = (TextView) search.findViewById(R.id.zhongliang);
        shifa = (TextView) search.findViewById(R.id.shifa);
        mudi = (TextView) search.findViewById(R.id.mudi);
        cheliang = (TextView) search.findViewById(R.id.cheliang);
        yunfei = (TextView) search.findViewById(R.id.yunfei);
        lianxi = (TextView) search.findViewById(R.id.lianxi);
        shuoming = (TextView) search.findViewById(R.id.shuoming);
        xiehuo = (TextView) search.findViewById(R.id.xiehuo);
        touxing = (ImageView) search.findViewById(R.id.jifen_touxiang);
        jiedan = (Button) search.findViewById(R.id.jiedan);

        jiedan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetData.order(SharedPreferencesUtil.getInstance().getString("token"), Integer.parseInt(good_id), DaoHangActivity.this, JIEDAN);
            }
        });

        window.setContentView(search);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setOutsideTouchable(true);
        window.setFocusable(true);

    }

    GoodDetailBean gooddetailbean;

    Code message;

    @Override
    public void success(int requestcode, Object o) {
        super.success(requestcode, o);
        switch (requestcode) {
            case GOODDETAIL:

                gooddetailbean = (GoodDetailBean) o;
                name.setText(gooddetailbean.getData().getName());
                mingcheng.setText(gooddetailbean.getData().getType_name());
                zhongliang.setText(gooddetailbean.getData().getWeight()+ "吨");
                shifa.setText(gooddetailbean.getData().getStart_addr());

                mudi.setText(gooddetailbean.getData().getEnd_addr());

                cheliang.setText(gooddetailbean.getData().getCar_require());
                yunfei.setText(gooddetailbean.getData().getPrice()+"元/吨");
                lianxi.setText(gooddetailbean.getData().getMobile());
                shuoming.setText(gooddetailbean.getData().getIntro());
                xiehuo.setText(gooddetailbean.getData().getTime());
                //name.setText(gooddetailbean.getData().getName());
                Glide.with(DaoHangActivity.this).load(Contans.BASE_URL + gooddetailbean.getData().getFace()).into(touxing);
                break;

            case JIEDAN:
                message = (Code) o;
                toast(message.getMessage());
                jiedan.setText("已接单");
                jiedan.setClickable(false);
                break;
        }
    }

    @Override
    protected void initData() {
        super.initData();
        initWindows();
        GetData.good_detail(SharedPreferencesUtil.getInstance().getString("token"), Integer.parseInt(good_id), this, GOODDETAIL);
       // window.showAtLocation(search, Gravity.BOTTOM, 0, 0);
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    if (window != null) {
                        window.showAtLocation(search, Gravity.BOTTOM, 0, 0);
                    }
                    break;
            }

        }
    };
    @Override
    protected void onResume() {
        super.onResume();
        handler.sendEmptyMessageDelayed(0, 1000);
    }

    @Override
    protected void requestBefore() {
        super.requestBefore();
        SDKInitializer.initialize(getApplicationContext());
    }

    DrivingRouteOverlay routeOverlay;
    BaiduMap mBaiduMap = null;
    RoutePlanSearch mSearch;

    GeoCoder coder;

    private void initBianma() {

        coder = GeoCoder.newInstance();
        coder.setOnGetGeoCodeResultListener(listene);

        // coder.set

        coder.geocode(new GeoCodeOption().city(start).address(start));
        coder.geocode(new GeoCodeOption().city(end).address(end));
    }

    OnGetGeoCoderResultListener listene = new OnGetGeoCoderResultListener() {
        public void onGetGeoCodeResult(GeoCodeResult result) {

            if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                //没有检索到结果
            }

            //获取地理编码结果
           // Toast.makeText(DaoHangActivity.this, result.toString(), Toast.LENGTH_SHORT).show();
            address.add(result.getLocation());
            if (address.size() == 2) {
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
                if (window != null) {
                    window.showAtLocation(search, Gravity.BOTTOM, 0, 0);
                }
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

    private List<LatLng> address = new ArrayList<>();

    private void SearchButtonProcess() {

        mBaiduMap.clear();
        //DrivingRoutePlanOption op = new DrivingRoutePlanOption();

        mSearch.setOnGetRoutePlanResultListener(listener);
//        PlanNode stNode = PlanNode.withCityNameAndPlaceName(start, "郑州大学");
//        PlanNode enNode = PlanNode.withCityNameAndPlaceName("南阳", "方城");
        final PlanNode stNode = PlanNode.withLocation(address.get(0));
        final PlanNode enNode = PlanNode.withLocation(address.get(1));


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSearch.drivingSearch((new DrivingRoutePlanOption())
                        .from(stNode)
                        .to(enNode));
            }
        },200);

//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mSearch.drivingSearch((new DrivingRoutePlanOption())
//                        .from(stNode)
//                        .to(enNode));
//            }
//        },2000);


    }

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
                //Toast.makeText(DaoHangActivity.this, "抱歉，未找到结果", Toast.LENGTH_SHORT).show();
                address.clear();
                coder.geocode(new GeoCodeOption().city(start).address(start));
                coder.geocode(new GeoCodeOption().city(end).address(end));
              // initBianma();
//                PlanNode stNode = PlanNode.withLocation(address.get(0));
//                PlanNode enNode = PlanNode.withLocation(address.get(1));
//                mSearch.drivingSearch((new DrivingRoutePlanOption())
//                        .from(stNode)
//                        .to(enNode));
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.back_pay_dao)
    public void onViewClicked() {
        finish();
    }
}
