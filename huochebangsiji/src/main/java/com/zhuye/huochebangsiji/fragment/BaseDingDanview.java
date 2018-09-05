package com.zhuye.huochebangsiji.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhuye.huochebangsiji.HuoCheApp;
import com.zhuye.huochebangsiji.R;
import com.zhuye.huochebangsiji.activity.DingDanDetailActivity;
import com.zhuye.huochebangsiji.activity.UpLoadFaActivity;
import com.zhuye.huochebangsiji.activity.UpLoadXiehuoActivity;
import com.zhuye.huochebangsiji.adapter.DingDanAdapter2;
import com.zhuye.huochebangsiji.base.BaseView;
import com.zhuye.huochebangsiji.bean.Base;
import com.zhuye.huochebangsiji.bean.OrdersBean;
import com.zhuye.huochebangsiji.data.GetData;
import com.zhuye.huochebangsiji.utils.SharedPreferencesUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/1/11 0011.
 */

public abstract class BaseDingDanview extends BaseFragment {


    protected static final int QUERENDINGDAN = 15;
    protected static final int QUXIAOUDINGDAN = 16;
    protected static final int QUERENSONGDA = 17;
    private static final int CANCLE = 8742;
    protected int page = 1;
    protected static final int REFRESH = 18;
    protected static final int LOADMORE = 19;
    @BindView(R.id.dingdan_rv)
    RecyclerView dingdanRv;
    Unbinder unbinder;
    DingDanAdapter2 adapter;
    protected static final int TOTAL = 10;
    protected static final int QUEREN = 11;
    protected static final int JINXING = 12;
    protected static final int HISTORY = 13;
    protected static final int DAIFU = 14;
    protected SmartRefreshLayout smartRefreshLayout;
    protected ClassicsHeader classicsHeader;
    protected ClassicsFooter classicsFooter;
    protected View emptyview;
    private int clickPosition = 0;
    private int flag = 0;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            try {
                if (msg.arg1 == 1) {
                    OrdersBean.DataBean beans = getCurOrder().get(clickPosition);
                    beans.setType("2");
                    adapter.updata(clickPosition, beans);
    //                getData(1,1,TOTAL);
    //                getData(1,3,JINXING);
                } else if (msg.arg1 == 2) {
                    OrdersBean.DataBean beans = getCurOrder().get(clickPosition);
                    beans.setType("3");
    //                getData(1,1,TOTAL);
    //                getData(1,3,JINXING);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };






    @Override
    protected void initView() {
        smartRefreshLayout = rootView.findViewById(R.id.smartRefreshLayout);
        classicsHeader = rootView.findViewById(R.id.header);
        classicsFooter = rootView.findViewById(R.id.footer);
        emptyview = LayoutInflater.from(getActivity()).inflate(R.layout.empty, null);
        GetData.bindHandler(handler);
        adapter = getAdapter();
        dingdanRv.setAdapter(adapter);
        dingdanRv.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void success(int requestcode, Object o) {
        switch (requestcode) {
            case TOTAL:
                totaldata = (OrdersBean) o;
                adapter.addData(((OrdersBean) o).getData());
//                OrdersBean.DataBean beans = getCurOrder().getData().get(clickPosition);
//                beans.setType("4");
//                adapter.updata(clickPosition, beans);
                break;
            case JINXING:
                jinxingdata = (OrdersBean) o;
                adapter.addData(((OrdersBean) o).getData());
                break;
            case  12345:
                daifudata = (OrdersBean) o;
//                dataBeans.addAll(daifudata.getData());
                adapter.addData(((OrdersBean)o).getData());
                break;
            case CANCLE:
                Base base = (Base) o;
                toast(((Base) o).getMessage());
                refresh();
                break;
        }
    }

    protected abstract DingDanAdapter2 getAdapter();

    @Override
    protected int getResId() {
        return R.layout.fragment_dingdan_rv;
    }

    protected Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };

    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

    }

    protected void getData(int page, int type, int shoutype) {
        GetData.order_list(SharedPreferencesUtil.getInstance().getString("token"), page, type, this, shoutype);
    }

    @Override
    protected void initListener() {
        super.initListener();


        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refresh();
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        smartRefreshLayout.finishRefresh();
                    }
                }, 2500);
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
                loadmore();
            }
        });

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), DingDanDetailActivity.class);
                intent.putExtra("order_id", getCurOrder().get(position).getOrder_id());
                intent.putExtra("start", getCurOrder().get(position).getStart_city());
                intent.putExtra("end", getCurOrder().get(position).getEnd_city());
                getCurOrder().get(position).getType();
                startActivity(intent);
            }
        });

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapt, View view, int position) {
                clickPosition = position;
                String type = getCurOrder().get(position).getType();
                switch (view.getId()) {
                    case R.id.shanchu:
                        GetData.cancel_order(SharedPreferencesUtil.getInstance().getString("token"),
                                    getCurOrder().get(position).getOrder_id()
                                ,BaseDingDanview.this, CANCLE);
                        break;

                    case R.id.querena:
                        GetData.confirm_order(SharedPreferencesUtil.getInstance().getString("token"), Integer.parseInt(getCurOrder().get(position).getOrder_id())
                                ,BaseDingDanview.this, QUERENDINGDAN);
                        break;
                    case R.id.quxiao2:
                        // 上传过磅单


                        Intent intents = new Intent(getActivity(), UpLoadFaActivity.class);
                        intents.putExtra("order_id", getCurOrder().get(position).getOrder_id());
                        intents.putExtra("flag", 1);
                        startActivity(intents);

//                        GetData.cancel_order(SharedPreferencesUtil.getInstance().getString("token"), Integer.parseInt(getCurOrder().get(position).getOrder_id())
//                                , BaseDingDanview.this, QUXIAOUDINGDAN);

                        // adapter.remove(position);
                        break;

                    case R.id.quxiao:
                        GetData.cancel_order(SharedPreferencesUtil.getInstance().getString("token"), Integer.parseInt(getCurOrder().get(position).getOrder_id())
                                , BaseDingDanview.this, QUXIAOUDINGDAN);
                        break;
                    case R.id.qiehoudan://卸货单
//                        //只有1是可以点击
                        if (type.equals("1")) {
                            flag = 1;//卸货单未上传
                        } else {
                            flag = 2;//已上传
                        }
                        Intent intent = new Intent(getActivity(), UpLoadXiehuoActivity.class);
                        intent.putExtra("order_id", getCurOrder().get(position).getOrder_id());
                        intent.putExtra("flag", flag);
                        startActivity(intent);
                        break;
                    case R.id.fahuodan://发货单
                        if (type.equals("1")){
                            flag = 2;//装货单还未上传
                        }else{
                            flag = 2;//装货单已上传
                        }
//                        if (!type.equals("0")) {
//                            flag = 2;//装货单已上传
//                        } else if (!type.equals("1")) {
//                            flag = 2;//装货单已上传
//                        } else {
//                            flag = 1;//装货单还未上传
//                        }
                        Intent intents1 = new Intent(getActivity(), UpLoadFaActivity.class);
                        intents1.putExtra("order_id", getCurOrder().get(position).getOrder_id());
                        intents1.putExtra("flag", flag);
                        startActivity(intents1);
                        break;
                    case R.id.querensongda:
                        //只有1是可以点击
//                        if(getCurOrder().getData().get(position).getType().equals("3")){
//                            querensongda.setClickable(true);
//                            Intent intent =new Intent(getActivity(), UpLoadXiehuoActivity.class);
//                            intent.putExtra("order_id",totaldata.getData().get(position).getOrder_id());
//                            startActivity(intent);
                        GetData.service(SharedPreferencesUtil.getInstance().getString("token"), Integer.parseInt(getCurOrder().get(position).getOrder_id()), BaseDingDanview.this, QUERENSONGDA);
                        OrdersBean.DataBean beans = getCurOrder().get(position);
                        beans.setType("4");
                        adapter.updata(position, beans);
//                        }else {
//                            view.setClickable(false);
//                        }
                        break;

                }


            }
        });


//
//        adapter.setOnItemClickListener(new BaseHolder.OnItemClickListener() {
//            @Override
//            public void OnItemClick(View view, final int position) {
//
//
//
//
//                view.findViewById(R.id.querena).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                    }
//                });
//
//                view.findViewById(R.id.quxiao2).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        GetData.cancel_order(SharedPreferencesUtil.getInstance().getString("token"), Integer.parseInt(totaldata.getData().get(position).getOrder_id())
//                                ,BaseDingDanview.this,QUXIAOUDINGDAN);
//                    }
//                });
//
//                final Button fahuodan = view.findViewById(fahuodan);
//                fahuodan.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        //只有1是可以点击
//                        if(totaldata.getData().get(position).getType().equals("1")){
//                            fahuodan.setClickable(true);
//                            Intent intent =new Intent(getActivity(), UpLoadFaActivity.class);
//                            intent.putExtra("order_id",totaldata.getData().get(position).getOrder_id());
//                            startActivity(intent);
//                        }else {
//                            fahuodan.setClickable(false);
//                        }
//
//
//                    }
//                });
//
//
//                final Button qiehoudan = view.findViewById(qiehoudan);
//                qiehoudan.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        //只有1是可以点击
//                        if(totaldata.getData().get(position).getType().equals("2")){
//                            qiehoudan.setClickable(true);
//                            Intent intent =new Intent(getActivity(), UpLoadXiehuoActivity.class);
//                            intent.putExtra("order_id",totaldata.getData().get(position).getOrder_id());
//                            startActivity(intent);
//                        }else {
//                            qiehoudan.setClickable(false);
//                        }
//
//
//                    }
//                });
//
//                final Button querensongda = view.findViewById(querensongda);
//                querensongda.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        //只有1是可以点击
//                        if(totaldata.getData().get(position).getType().equals("3")){
////                            querensongda.setClickable(true);
////                            Intent intent =new Intent(getActivity(), UpLoadXiehuoActivity.class);
////                            intent.putExtra("order_id",totaldata.getData().get(position).getOrder_id());
////                            startActivity(intent);
//
//                            GetData.service(SharedPreferencesUtil.getInstance().getString("token"), Integer.parseInt(totaldata.getData().get(position).getOrder_id()),BaseDingDanview.this,QUERENSONGDA);
//                        }else {
//                            querensongda.setClickable(false);
//                        }
//
//
//                    }
//                });
//
//            }
//        });
    }

    protected abstract List<OrdersBean.DataBean> getCurOrder();

    protected abstract void loadmore();

    protected abstract void refresh();

    OrdersBean totaldata;
    OrdersBean querendata;
    OrdersBean lishidata;
    OrdersBean jinxingdata;
    OrdersBean daifudata;
}
