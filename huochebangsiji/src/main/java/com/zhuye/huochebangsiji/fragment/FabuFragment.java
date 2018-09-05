package com.zhuye.huochebangsiji.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zhuye.huochebangsiji.R;
import com.zhuye.huochebangsiji.activity.DaoHangActivity;
import com.zhuye.huochebangsiji.activity.LoginActivity;
import com.zhuye.huochebangsiji.activity.PayResult;
import com.zhuye.huochebangsiji.adapter.DaTingHomeAdapter2;
import com.zhuye.huochebangsiji.adapter.SearchItemAdapter;
import com.zhuye.huochebangsiji.bean.AliPayBean;
import com.zhuye.huochebangsiji.bean.Base;
import com.zhuye.huochebangsiji.bean.ChaKanBean;
import com.zhuye.huochebangsiji.bean.HuoYuanBean;
import com.zhuye.huochebangsiji.bean.PeiSongBean;
import com.zhuye.huochebangsiji.bean.WeixinBean;
import com.zhuye.huochebangsiji.city.ChooseAddressActivity;
import com.zhuye.huochebangsiji.contants.Contans;
import com.zhuye.huochebangsiji.data.GetData;
import com.zhuye.huochebangsiji.utils.SharedPreferencesUtil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/1/9 0009.
 */

public class FabuFragment extends BaseFragment implements View.OnClickListener {

    private static final int FABULOADMORE = 14;
    private static final int FUBUREFRESH = 15;
    private static final int GOOD_VIEW = 16;
    private static final int YUEPAY = 17;
    private static final int JIFENPAY = 18;
    private static final int ZHIFUBAOPAY = 19;
    private static final int WEIXINPAY = 20;
    @BindView(R.id.haha)
    NiceSpinner haha;
    @BindView(R.id.haha1)
    NiceSpinner haha1;

    @BindView(R.id.dading_rv)
    RecyclerView dadingRv;
    Unbinder unbinder;
    List<String> dataset;
    List<String> todataset;
    List<String> tyepdataset;
    List<String> tyepdataset2;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.arrow)
    ImageView arrow;
    Unbinder unbinder1;
    @BindView(R.id.root)
    RelativeLayout root;
    Unbinder unbinder2;
    private static final int CHEXING = 10;
    private static final int CHECHANG = 11;
    private static final int CHAIXUAN = 13;
    @BindView(R.id.three_ll)
    LinearLayout threeLl;
    Unbinder unbinder3;
    @BindView(R.id.location)
    TextView location;
    @BindView(R.id.iv_location)
    ImageView ivLocation;
    protected SmartRefreshLayout smartRefreshLayout;
    protected ClassicsHeader classicsHeader;
    protected ClassicsFooter classicsFooter;
    private  Button queding;
    private IWXAPI iwxapi; //微信支付api
    Handler payHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == Contans.SDK_PAY_FLAG) {

                PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                /**
                 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为结束的通知。
                 */
                String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                String resultStatus = payResult.getResultStatus();
                // 判断resultStatus 为9000则代表支付成功
                if (TextUtils.equals(resultStatus, "9000")) {
                    // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
//                    toast("支付成功");
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("！提示:");
                    builder.setMessage("支付成功");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            queding.setClickable(false);
                            getActivity().finish();
                        }
                    });
                    builder.create().show();

                } else {
                    // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                    queding.setClickable(true);
                    toast("支付失败");
                    getActivity().finish();
                }
            }else if (msg.arg1 == 102 ){
                getActivity().finish();
                queding.setClickable(false);
            }

        }
    };

    @Override
    public void initView() {
        if (SharedPreferencesUtil.getInstance().getString("city") != null) {
            location.setText(SharedPreferencesUtil.getInstance().getString("city"));
            ivLocation.setVisibility(View.INVISIBLE);
        }
        smartRefreshLayout = rootView.findViewById(R.id.smartRefreshLayout);
        classicsHeader = rootView.findViewById(R.id.header);
        classicsFooter = rootView.findViewById(R.id.footer);


        haha.setBackgroundResource(R.drawable.xuanze_shape);
        haha1.setBackgroundResource(R.drawable.xuanze_shape);
        // haha2.setBackgroundResource(R.drawable.xuanze_shape);

        dataset = new LinkedList<>(Arrays.asList("车长（米）"));
        //haha.setTextSize(DensityUtil.dip2px(getActivity(),12));
        todataset = new LinkedList<>(Arrays.asList("车型"));
        tyepdataset = new LinkedList<>(Arrays.asList("货物类型"));
        haha.attachDataSource(dataset);
        haha1.attachDataSource(todataset);
        //haha2.attachDataSource(tyepdataset);

        haha.setPadding(40, 0, 0, 0);
        haha.setTextColor(Color.WHITE);

        haha1.setPadding(40, 0, 0, 0);
        haha1.setTextColor(Color.WHITE);

        // haha2.setPadding(40, 0, 0, 0);
        //  haha2.setTextColor(Color.WHITE);
    }


    private Boolean ISTAGSELECTED = false;
    private Integer selectleixing;
    private Integer selectchechang;
    private Integer selectchexing;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    private int page = 1;

    @Override
    public void initListener() {
        super.initListener();
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        smartRefreshLayout.finishRefresh();
                    }
                }, 2000);
                refresh();
            }
        });

        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        smartRefreshLayout.finishLoadmore();
                    }
                }, 2500);
                loadMore();
            }
        });

        haha.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectchechang = i + 1;
                String xingcan = null;
                String xingcanleixing = null;

                home.clear();


                if (selectchexing != null && selectchexing != 0) {
                    xingcan = shaixuan.getData().get(1).getType_arr().get(selectchexing - 1 - 1).getChose_id();
                }

                if (selectleixing != null) {
                    xingcanleixing = huoleixing.get(selectleixing).getChose_id();
                }

                page = 1;
                GetData.good(SharedPreferencesUtil.getInstance().getString("token"), searchcity, page,
                        shaixuan.getData().get(0).getType_arr().get(i - 1).getChose_id(), xingcan, xingcanleixing, FabuFragment.this, 12);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        haha1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectchexing = i + 1;
                String xingcan = null;
                String xingcanleixing = null;
                if (selectleixing != null) {
                    xingcanleixing = huoleixing.get(selectleixing).getChose_id();
                }

                home.clear();
                if (selectchechang != null && selectchechang != 0) {
                    xingcan = shaixuan.getData().get(0).getType_arr().get(selectchechang - 1 - 1).getChose_id();
                }

                page = 1;
                GetData.good(SharedPreferencesUtil.getInstance().getString("token"), searchcity, page,
                        xingcan, shaixuan.getData().get(1).getType_arr().get(i - 1).getChose_id(), xingcanleixing, FabuFragment.this, 12);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

//        haha2.setOnDragListener(new View.OnDragListener() {
//            @Override
//            public boolean onDrag(View view, DragEvent dragEvent) {
//                Log.i("as", view.toString());
//                return true;
//            }
//        });

        home.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (SharedPreferencesUtil.getInstance().getString("token") == null || TextUtils.isEmpty(SharedPreferencesUtil.getInstance().getString("token"))) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                    //getActivity().finish();
                } else {
                    good_id = data.get(position).getGood_id();
                    start = data.get(position).getStart_addr();
                    end = data.get(position).getEnd_addr();
                    GetData.good_view(SharedPreferencesUtil.getInstance().getString("token"), Integer.parseInt(data.get(position).getGood_id()), FabuFragment.this, GOOD_VIEW);
                    temp = view;
                }
            }
        });

        home.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.home_phone:
                        call(position);
                        break;

                    case R.id.ads:

                        break;
                }
                alertBootom();
            }
        });

//        home.setOnItemClickListener(new BaseHolder.OnItemClickListener() {
//            @Override
//            public void OnItemClick(View view, int position) {
//                //Intent intent = new Intent(getActivity(), HuoYuanDetailActivity.class);
//                Intent intent = new Intent(getActivity(), DaoHangActivity.class);
//                intent.putExtra("good_id",data.get(position).getGood_id());
//                intent.putExtra("start",data.get(position).getStart_addr());
//                intent.putExtra("end",data.get(position).getEnd_addr());
//                startActivity(intent);
//            }
//        });


        leixing.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(final View view, int position, FlowLayout parent) {
                for (int i = 0; i < parent.getChildCount(); i++) {
                    if (i == position) {
//                        ISTAGSELECTED = !ISTAGSELECTED;
//                        if(ISTAGSELECTED){
//                            ((TextView)parent.getChildAt(i).findViewById(R.id.name)).setTextColor(Color.RED);
//                        }else {
//                            ((TextView)parent.getChildAt(i).findViewById(R.id.name)).setTextColor(Color.RED);
//                        }
                        ((TextView) parent.getChildAt(i).findViewById(R.id.name)).setTextColor(Color.RED);
                    } else {
                        ((TextView) parent.getChildAt(i).findViewById(R.id.name)).setTextColor(getResources().getColor(R.color.dindansnor));
                    }
                    selectleixing = position;

                }
                return true;
            }
        });
    }

    String good_id;
    String start;
    String end;


    private void call(int position) {
        //Uri uri = Uri.parse("tel:"+1008611);
        Intent intent = new Intent(Intent.ACTION_CALL, Uri
                .parse("tel:" + data.get(position).getMobile()));
        // 如果需要将内容传过去增加如下代码
        //intent.putExtra("sms_body", "");
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
//        GetData.good(SharedPreferencesUtil.getInstance().getString("token"), SharedPreferencesUtil.getInstance().getString("city"), 1, null, null, null, this, 12);


    }

    private void alertBootom() {


    }

    private void loadMore() {
        ++page;

        String leixing_id = null;//huoleixing.get(selectleixing).getChose_id();
        String xingcan = null;
        String chemodel = null;
        if (selectleixing != null) {
            leixing_id = huoleixing.get(selectleixing).getChose_id();
        }
        if (selectchechang != null && selectchechang != 0) {
            xingcan = shaixuan.getData().get(0).getType_arr().get(selectchechang - 1 - 1).getChose_id();

        }
        if (selectchexing != null && selectchexing != 0) {
            chemodel = shaixuan.getData().get(1).getType_arr().get(selectchexing - 1 - 1).getChose_id();
        }
        GetData.good(SharedPreferencesUtil.getInstance().getString("token"), searchcity, page,
                xingcan, chemodel, leixing_id, FabuFragment.this, FABULOADMORE);
    }

    private void refresh() {
        page = 1;

        String leixing_id = null;//huoleixing.get(selectleixing).getChose_id();
        String xingcan = null;
        String chemodel = null;
        if (selectleixing != null) {
            leixing_id = huoleixing.get(selectleixing).getChose_id();
        }
        if (selectchechang != null && selectchechang != 0) {
            xingcan = shaixuan.getData().get(0).getType_arr().get(selectchechang - 1 - 1).getChose_id();

        }
        if (selectchexing != null && selectchexing != 0) {
            chemodel = shaixuan.getData().get(1).getType_arr().get(selectchexing - 1 - 1).getChose_id();
        }
        GetData.good(SharedPreferencesUtil.getInstance().getString("token"), searchcity, page,
                xingcan, chemodel, leixing_id, FabuFragment.this, FUBUREFRESH);
    }


    @Override
    protected int getResId() {
        return R.layout.fragment_fabu;
    }


    PeiSongBean chexing;
    PeiSongBean chechang;
    PeiSongBean shaixuan;
    HuoYuanBean huoyuanbean;
    List<HuoYuanBean.DataBean> data = new ArrayList<>();
    public Map<Integer, Boolean> dat;
    List<PeiSongBean.DataBean.TypeArrBean> huoleixing;
    private View temp;

    @Override
    public void success(int requestcode, Object o) {
        switch (requestcode) {
            case CHEXING:
                chexing = (PeiSongBean) o;
                chexingadapter.addData(chexing.getData());
                break;
            case CHECHANG:
                chechang = (PeiSongBean) o;
                chechangadapter.addData(chechang.getData());
                break;
            case 12:
                data.clear();
                huoyuanbean = (HuoYuanBean) o;
                data.addAll(huoyuanbean.getData());
                home.addData(data);
                break;

            case FUBUREFRESH:
                HuoYuanBean huoyuanbea = (HuoYuanBean) o;
                home.clear();
                data.clear();
                data.addAll(huoyuanbea.getData());
                home.addData(data);
                smartRefreshLayout.finishRefresh();
                break;
            case FABULOADMORE:
                home.clear();
                HuoYuanBean huo = (HuoYuanBean) o;
                data.addAll(huo.getData());
                smartRefreshLayout.finishLoadmore();
                home.addData(data);
                break;
            case CHAIXUAN:
                shaixuan = (PeiSongBean) o;
                List<PeiSongBean.DataBean.TypeArrBean> chechang = shaixuan.getData().get(0).getType_arr();
                List<PeiSongBean.DataBean.TypeArrBean> chexing = shaixuan.getData().get(1).getType_arr();
                huoleixing = shaixuan.getData().get(2).getType_arr();
                for (int i = 0; i < chechang.size(); i++) {
                    dataset.add(chechang.get(i).getChose_name());
                }
                for (int i = 0; i < chexing.size(); i++) {
                    todataset.add(chexing.get(i).getChose_name());
                }
                tyepdataset2 = new ArrayList<>();
                for (int i = 0; i < huoleixing.size(); i++) {
                    tyepdataset2.add(huoleixing.get(i).getChose_name());
                }
                //leixinggadapter.addData(tyepdataset);

                leixing.setAdapter(new TagAdapter<String>(tyepdataset2) {
                    @Override
                    public View getView(FlowLayout parent, int position, String s) {
                        View vie = View.inflate(getActivity(), R.layout.search_item, null);
                        TextView tv = vie.findViewById(R.id.name);
                        tv.setText(s);
                        return vie;
                    }
                });
                break;
            case GOOD_VIEW:
                ChaKanBean bean = (ChaKanBean) o;
//                bean.getData().setType("0");
                if (bean.getData().getType().equals("0")) {
                    //免费看
                    Intent intent = new Intent(getActivity(), DaoHangActivity.class);
                    intent.putExtra("good_id", good_id);
                    intent.putExtra("start", start);
                    intent.putExtra("end", end);
                    startActivity(intent);
                } else if (bean.getData().getType().equals("1")) {
                    //付费
                    WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                    lp.alpha = 0.4f;
                    getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                    getActivity().getWindow().setAttributes(lp);
                    window2.showAtLocation(temp, Gravity.BOTTOM, 0, 0);
                }
                jiege.setText(bean.getData().getPrice() + "元/" + bean.getData().getScode());
                yueaaa.setText(bean.getData().getBalance_price());
                jifen22.setText(bean.getData().getBalance_scode());
                payorder_id = Integer.parseInt(bean.getData().getGood_id());
                break;

            case YUEPAY:
                Base mess = (Base) o;
                toast(mess.getMessage());
                break;

            case JIFENPAY:
                Base ji = (Base) o;
                toast(ji.getMessage());
                break;
            case ZHIFUBAOPAY:
                AliPayBean aliPayBean = (AliPayBean) o;
                String url = aliPayBean.getData();
                aliPay(url);
                break;
            case WEIXINPAY://微信支付回调
                WeixinBean weixinBean = (WeixinBean) o;
                String appid = weixinBean.getData().getAppid();
                String partnerid = weixinBean.getData().getPartnerid();
                String prepayid= weixinBean.getData().getPrepayid();
    //            String packages =  weixinBean.getData().getPackages();
                String  noncestr= weixinBean.getData().getNoncestr();
                String timestamp = weixinBean.getData().getTimestamp();
                String sign = weixinBean.getData().getSign();
                WXPay(appid,partnerid,prepayid,"Sign=WXPay",noncestr,timestamp,sign);
                break;
        }
    }

    PopupWindow window;
    SearchItemAdapter chexingadapter;
    SearchItemAdapter chechangadapter;
    SearchItemAdapter leixinggadapter;
    DaTingHomeAdapter2 home;
    TagFlowLayout leixing;

    @Override
    public void initData() {
        super.initData();
        home = new DaTingHomeAdapter2(R.layout.dating_item);
        dadingRv.setAdapter(home);
        dadingRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        window = new PopupWindow(getActivity());
        window.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        window.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        View search = View.inflate(getActivity(), R.layout.sousuo_boottom1, null);
        window.setContentView(search);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setOutsideTouchable(true);
        window.setFocusable(true);

        inittWindow2();
        GetData.good(SharedPreferencesUtil.getInstance().getString("token"), SharedPreferencesUtil.getInstance().getString("city"), 1, null, null, null, this, 12);
        //GetData.models(1,this,CHEXING);
        //GetData.commander(1,this,CHECHANG);
        GetData.shaixuan("", this, CHAIXUAN);


        // GetData.good_view(SharedPreferencesUtil.getInstance().getString("token"),12,FabuFragment.this,GOOD_VIEW);
        searchcity = SharedPreferencesUtil.getInstance().getString("city");
        leixing = search.findViewById(R.id.id_flowlayout);
        Button queding = search.findViewById(R.id.queding);

        queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
                //请求数据  参数处理
                if (selectleixing == null) {
                    //没有选择  不处理
                } else {
                    String leixing_id = huoleixing.get(selectleixing).getChose_id();

                    String xingcan = null;
                    String chemodel = null;
                    String xingcanname = null;
                    String chemodelname = null;

                    home.clear();

                    if (selectchechang != null && selectchechang != 0) {
                        xingcan = shaixuan.getData().get(0).getType_arr().get(selectchechang - 1 - 1).getChose_id();
                        xingcanname = shaixuan.getData().get(0).getType_arr().get(selectchechang - 1 - 1).getChose_name();
                    }

                    if (selectchexing != null && selectchexing != 0) {
                        chemodel = shaixuan.getData().get(1).getType_arr().get(selectchexing - 1 - 1).getChose_id();
                        chemodelname = shaixuan.getData().get(1).getType_arr().get(selectchexing - 1 - 1).getChose_name();
                    }

//                    Log.i("as",xingcanname
//                        +chemodelname
//                        +huoleixing.get(selectleixing).getChose_name()+searchcity);


                    page = 1;
                    GetData.good(SharedPreferencesUtil.getInstance().getString("token"), searchcity, page,
                            xingcan, chemodel, leixing_id, FabuFragment.this, 12);
                }


                Log.i("as", selectleixing + " ");
            }
        });


        dat = new HashMap(30);
        for (int i = 0; i < 30; i++) {
            dat.put(i, false);
        }


//        leixinggadapter = new SearchItemAdapter(getActivity());
//        leixing.setAdapter(leixinggadapter);
//        leixing.setLayoutManager(new GridLayoutManager(getActivity(), 3));
    }

    ImageView xuanyue;
    ImageView xuanalipay;
    ImageView xuanweixin;
    //    ImageView xuanyinlian;
    ImageView xuanjifen;
    PopupWindow window2;
    TextView jiege;
    TextView yueaaa;
    TextView jifen22;

    private void inittWindow2() {

        window2 = new PopupWindow(getActivity());
        window2.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        window2.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        View search = View.inflate(getActivity(), R.layout.bottom_zhifu, null);

        jiege = search.findViewById(R.id.jiege);
        yueaaa = search.findViewById(R.id.yueaaa);
        yueaaa.setVisibility(View.VISIBLE);
        jifen22 = search.findViewById(R.id.jifen22);
        jifen22.setVisibility(View.VISIBLE);
        ImageView close = search.findViewById(R.id.close);

        RelativeLayout yuea = search.findViewById(R.id.yuea);
        xuanyue = search.findViewById(R.id.xuanyue);
        yuea.setOnClickListener(FabuFragment.this);
        RelativeLayout jifen = search.findViewById(R.id.jifen);
        xuanjifen = search.findViewById(R.id.xuanjifen);
        jifen.setOnClickListener(FabuFragment.this);
        RelativeLayout alipay = search.findViewById(R.id.alipay);
        xuanalipay = search.findViewById(R.id.xuanalipay);
        alipay.setOnClickListener(FabuFragment.this);
        final RelativeLayout weixin = search.findViewById(R.id.weixin);
        xuanweixin = search.findViewById(R.id.xuanweixin);
        weixin.setOnClickListener(FabuFragment.this);
//        RelativeLayout yinlian = search.findViewById(R.id.yinlian);
//        xuanyinlian = search.findViewById(R.id.xuanyinlian);
//        yinlian.setOnClickListener(FabuFragment.this);
        queding = search.findViewById(R.id.queding);
        queding.setOnClickListener(FabuFragment.this);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (window2 != null && window2.isShowing()) {
                    window2.dismiss();
                }
            }
        });

        window2.setContentView(search);
        window2.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window2.setOutsideTouchable(true);
        window2.setFocusable(true);
        window2.update();

        window2.setOnDismissListener(new PopupWindow.OnDismissListener() {
            // 在dismiss中恢复透明度
            public void onDismiss() {
                WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                lp.alpha = 1f;
                getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                getActivity().getWindow().setAttributes(lp);
            }
        });
    }

    private Boolean showdown = true;

    private String searchcity;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String before = location.getText().toString().trim();
        location.setText(data.getStringExtra("city"));

        if (before.equals(data.getStringExtra("city"))) {
            return;
        }
        searchcity = data.getStringExtra("city");
        home.clear();
        String chechang_id = null;
        String chexing_id = null;

        String xingcanleixing = null;

        if (selectleixing != null) {
            xingcanleixing = huoleixing.get(selectleixing).getChose_id();
        }

        if (selectchechang == null || selectchechang == 0) {
            selectchechang = null;
        } else {
            selectchechang = selectchechang - 2;
            chechang_id = shaixuan.getData().get(0).getType_arr().get(selectchexing).getChose_id();
        }

        if (selectchexing == null || selectchexing == 0) {
            selectchexing = null;
        } else {
            selectchexing = selectchexing - 2;
            chexing_id = shaixuan.getData().get(1).getType_arr().get(selectchechang).getChose_id();
        }

        page = 1;

        GetData.good(SharedPreferencesUtil.getInstance().getString("token"), data.getStringExtra("city"), page, chechang_id
                , chexing_id, xingcanleixing, FabuFragment.this, 12);
    }

    @OnClick({R.id.root, R.id.location})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.root:
                showWindow(view);
                break;

            case R.id.location:
                startActivityForResult(new Intent(getActivity(), ChooseAddressActivity.class), 10);
                break;

        }

//        fanzhuan(view);
//        if(window!=null && !window.isShowing()){
//
//            showdown = true;
//        }
    }

    private void startAnim() {
        // RotateAnimation anim = new RotateAnimation(getActivity(),null,0,)
    }

    private void fanzhuan(View view) {
        if (showdown) {
            showWindow(view);
        } else {
            closeWindow(view);
        }
    }

    private void showWindow(View view) {
        if (window != null) {
            startAnim();
            window.showAsDropDown(root);
            showdown = false;
        }
    }

    private void closeWindow(View view) {
        if (window != null) {
            endAnim();
            window.dismiss();
            showdown = true;
        }
    }

    private void endAnim() {

    }

    int position;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.yuea:
                position = 1;
                //  toast(position+"");
                selectBackground(position);
                break;
            case R.id.alipay:
                position = 2;
                //  toast(position+"");
                selectBackground(position);
                break;
            case R.id.weixin:
                position = 3;
                //  toast(position+"");
                selectBackground(position);
                break;
//            case R.id.yinlian:
//                position = 4;
//                // toast(position+"");
//                selectBackground(position);
//                break;

            case R.id.jifen:
                position = 5;
                selectBackground(position);
                break;
            case R.id.queding:
                goPay();
                break;
        }
    }

    int payorder_id;

    private void goPay() {

        switch (position) {
            case 1://余额
                GetData.pay_balance(SharedPreferencesUtil.getInstance().getString("token"), payorder_id, FabuFragment.this, YUEPAY);
                break;
            case 5://积分
                GetData.balance_scode(SharedPreferencesUtil.getInstance().getString("token"), payorder_id, FabuFragment.this, JIFENPAY);
                break;
            case 2://支付宝
                GetData.pay_aliay(SharedPreferencesUtil.getInstance().getString("token"), payorder_id, FabuFragment.this, ZHIFUBAOPAY);
                break;
            case 3://微信
                GetData.pay_weixin(SharedPreferencesUtil.getInstance().getString("token"), payorder_id, FabuFragment.this, WEIXINPAY);
                break;
            case 4://银联

                break;

        }
    }

    /**
     * 支付寶支付
     *
     * @param requestUrl
     */
    private void aliPay(final String requestUrl) {
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(getActivity());
                Map<String, String> result = alipay.payV2(requestUrl, true);
                Log.i("msp", result.toString());
                Message msg = new Message();
                msg.what = Contans.SDK_PAY_FLAG;
                msg.obj = result;
                payHandler.sendMessage(msg);
            }
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    private void WXPay(final String appid, final String partnerId,final String prepayId, final String packageValue,
                       final String nonceStr, final String timeStamp, final String sign) {
        GetData.WX_FLAG = 1;
        iwxapi = WXAPIFactory.createWXAPI(getActivity(), Contans.WEIXIN_APP_ID); //初始化微信api
        iwxapi.registerApp(Contans.WEIXIN_APP_ID); //注册appid  appid可以在开发平台获取

        Runnable payRunnable = new Runnable() {  //这里注意要放在子线程
            @Override
            public void run() {
                PayReq request = new PayReq(); //调起微信APP的对象
//                下面是设置必要的参数，也就是前面说的参数,这几个参数从何而来请看上面说明
                request.appId = appid;
                request.partnerId = partnerId;
                request.prepayId = prepayId;
                request.packageValue ="Sign=WXPay";
                request.nonceStr = nonceStr;
                request.timeStamp = timeStamp;
                request.sign = sign;
                iwxapi.sendReq(request);//发送调起微信的请求
            }
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();

    }

    private void selectBackground(int position) {
        xuanyue.setImageResource(position == 1 ? R.drawable.gouxuan_on : R.drawable.gouxuan_off);
        xuanalipay.setImageResource(position == 2 ? R.drawable.gouxuan_on : R.drawable.gouxuan_off);
        xuanweixin.setImageResource(position == 3 ? R.drawable.gouxuan_on : R.drawable.gouxuan_off);
//        xuanyinlian.setImageResource(position==4 ?R.drawable.gouxuan_on:R.drawable.gouxuan_off);
        xuanjifen.setImageResource(position == 5 ? R.drawable.gouxuan_on : R.drawable.gouxuan_off);
    }
}
