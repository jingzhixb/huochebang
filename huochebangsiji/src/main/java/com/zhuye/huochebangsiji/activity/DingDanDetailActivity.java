package com.zhuye.huochebangsiji.activity;

import android.view.View;
import android.widget.ImageView;
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
import com.zhuye.huochebangsiji.R;
import com.zhuye.huochebangsiji.base.BaseActivity;
import com.zhuye.huochebangsiji.bean.JinChengBean;
import com.zhuye.huochebangsiji.bean.OrderDetailBean;
import com.zhuye.huochebangsiji.data.GetData;
import com.zhuye.huochebangsiji.utils.SharedPreferencesUtil;
import com.zhuye.huochebangsiji.widget.DrivingRouteOverlay;
import com.zhuye.huochebangsiji.widget.MyDrivingRouteOverlay;

import java.util.ArrayList;
import java.util.List;

public class DingDanDetailActivity extends BaseActivity {

    private static final int JINCHENG = 20;
    private static final int DETAIL  = 21  ;
    MapView mapview;

    TextView fahuodi;
    TextView shouhuodi;
    TextView leixing;
    TextView xiehuoshijian;
    TextView peisongshuoming;
    TextView yunfei;
    TextView biaohao;
    TextView jiedanshijian;
    TextView querenjiage;
    TextView fukanshijian;
    TextView guobangdan;
    TextView xiehuo;
    TextView daodatime;
    TextView shouhuoqueren;
    ImageView iv_back;

    @Override
    protected int getResId() {
        return R.layout.activity_ding_dan_detail;
    }


    String order_id;
    @Override
    protected void initView() {
        super.initView();
        mapview = (MapView) findViewById(R.id.mapview);
        shouhuodi = (TextView) findViewById(R.id.shouhuodi);
        fahuodi = (TextView) findViewById(R.id.fahuodi);
        leixing = (TextView) findViewById(R.id.leixing);
        xiehuoshijian = (TextView) findViewById(R.id.xiehuoshijian);
        peisongshuoming = (TextView) findViewById(R.id.peisongshuoming);
        yunfei = (TextView) findViewById(R.id.yunfei);
        biaohao = (TextView) findViewById(R.id.biaohao);
        jiedanshijian = (TextView) findViewById(R.id.jiedanshijian);
        querenjiage = (TextView) findViewById(R.id.querenjiage);
        fukanshijian = (TextView) findViewById(R.id.fukanshijian);
        guobangdan = (TextView) findViewById(R.id.guobangdan);
        xiehuo = (TextView) findViewById(R.id.xiehuo);
        daodatime = (TextView) findViewById(R.id.daodatime);
        shouhuoqueren = (TextView) findViewById(R.id.shouhuoqueren);
        iv_back = (ImageView) findViewById(R.id.iv_back_order_detail);
        order_id = getIntent().getStringExtra("order_id");
        start = getIntent().getStringExtra("start");
        end =  getIntent().getStringExtra("end");

        mapview.showZoomControls(false);
// 初始化搜索模块，注册事件监听
        mSearch = RoutePlanSearch.newInstance();
        mBaiduMap = mapview.getMap();
        initBianma();
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    OnGetGeoCoderResultListener listene = new OnGetGeoCoderResultListener() {
        public void onGetGeoCodeResult(GeoCodeResult result) {

            if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                //没有检索到结果
            }

            //获取地理编码结果
           // Toast.makeText(DingDanDetailActivity.this, result.toString() ,Toast.LENGTH_SHORT).show();
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
        PlanNode stNode = PlanNode.withLocation(address.get(0));
        PlanNode enNode = PlanNode.withLocation(address.get(1));


        mSearch.drivingSearch((new DrivingRoutePlanOption())
                .from(stNode)
                .to(enNode));


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
                Toast.makeText(DingDanDetailActivity.this, "抱歉，未找到结果", Toast.LENGTH_SHORT).show();
            }
            if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
                //起终点或途经点地址有岐义，通过以下接口获取建议查询信息
                //result.getSuggestAddrInfo()
                Toast.makeText(DingDanDetailActivity.this, "抱歉，未找到结果", Toast.LENGTH_SHORT).show();
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

    GeoCoder coder;
    private void initBianma() {
        coder = GeoCoder.newInstance();
        coder.setOnGetGeoCodeResultListener(listene);

        // coder.set
        coder.geocode(new GeoCodeOption()
                .city(start).address(start));
        coder.geocode(new GeoCodeOption().city(end).address(end));
    }


    DrivingRouteOverlay routeOverlay;
    BaiduMap mBaiduMap = null;
    RoutePlanSearch mSearch;
    String start;
    String end;
    @Override
    protected void initData() {
        super.initData();

        GetData.order_process(SharedPreferencesUtil.getInstance().getString("token"), Integer.parseInt(order_id),this,JINCHENG);
        GetData.order_detail(SharedPreferencesUtil.getInstance().getString("token"), Integer.parseInt(order_id),this,DETAIL);
    }


    @Override
    public void success(int requestcode, Object o) {
        super.success(requestcode, o);
        switch (requestcode){
            case DETAIL:
                OrderDetailBean detail = (OrderDetailBean) o;
                shouhuodi.setText(detail.getData().getEnd_addr());
                fahuodi.setText(detail.getData().getStart_addr());
                leixing.setText(detail.getData().getLeng()+"  "+detail.getData().getModels()+"  "+detail.getData().getGoodtype()+"/"+detail.getData().getWeight());
                xiehuoshijian.setText("卸货时间:"+detail.getData().getTime());
                peisongshuoming.setText("配送说明:"+detail.getData().getPeihuo());
                yunfei.setText(detail.getData().getPrice());
                break;
            case JINCHENG:
                JinChengBean jin = (JinChengBean) o;
                biaohao.setText("订单编号:"+jin.getData().getOrder_sn());
                jiedanshijian.setText("司机接单:"+jin.getData().getPlace_order());
                querenjiage.setText("司机确认价格:"+jin.getData().getDriver_confirm());
                fukanshijian.setText("货主付款时间"+jin.getData().getOwner_pay());
                guobangdan.setText("上传装货过磅单:"+jin.getData().getDriver_waybill());
                xiehuo.setText("上传卸货过磅单:"+jin.getData().getDriver_landing());
                daodatime.setText("司机确认到达:"+jin.getData().getDriver_finish());
                shouhuoqueren.setText("货主确认收货:"+jin.getData().getOwner_finish());
                break;
        }
    }

    @Override
    protected void requestBefore() {
        super.requestBefore();
        SDKInitializer.initialize(getApplicationContext());
    }
}
