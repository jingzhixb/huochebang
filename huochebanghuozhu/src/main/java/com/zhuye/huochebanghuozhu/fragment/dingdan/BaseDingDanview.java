package com.zhuye.huochebanghuozhu.fragment.dingdan;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhuye.huochebanghuozhu.R;
import com.zhuye.huochebanghuozhu.activity.DingDanDetailActivity;
import com.zhuye.huochebanghuozhu.activity.LookUpLoadFaActivity;
import com.zhuye.huochebanghuozhu.activity.ZhiFuActivity;
import com.zhuye.huochebanghuozhu.adapter.DingDanAdapter2;
import com.zhuye.huochebanghuozhu.bean.Base;
import com.zhuye.huochebanghuozhu.bean.OrderBean;
import com.zhuye.huochebanghuozhu.contants.Contans;
import com.zhuye.huochebanghuozhu.data.GetData;
import com.zhuye.huochebanghuozhu.fragment.BaseFragment;
import com.zhuye.huochebanghuozhu.utils.DensityUtil;
import com.zhuye.huochebanghuozhu.utils.SharedPreferencesUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/1/11 0011.
 */

public abstract class BaseDingDanview extends BaseFragment {


    protected static final int ORDERDELETE = 15;
    protected static final int REFRESH = 16;
    protected static final int LOADMORE = 17;
    protected static final int QUERENORDER = 202;
    private static final int XIUGAI = 7845;
    protected int page = 1;
    View rootView= null;
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
    private Intent intent;
    private int flag = 0;
    Unbinder unbinder1;
    //    @BindView(R.id.empty)
    TextView empty;

    @Override
    protected void initView() {

    }

    @Override
    public void empty() {
        super.empty();
//        empty.setVisibility(View.VISIBLE);
    }

    protected abstract DingDanAdapter2 getAdapter();

    @Override
    protected int getResId() {
        return R.layout.fragment_dingdan_rv;
    }
    protected int removepos;
    protected Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };
    private static final int CANCLE = 8742;
    @Override
    protected void initListener() {
        super.initListener();
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                String order_id = getCurOrder().get(position).getOrder_id();
                String price = getCurOrder().get(position).getPrice();
                String type = getCurOrder().get(position).getType();
                switch (view.getId()) {
                    case R.id.shanchu:
                        GetData.cancel_order(SharedPreferencesUtil.getInstance().getString("token"),
                                getCurOrder().get(position).getOrder_id()
                                ,BaseDingDanview.this, CANCLE);
                        break;

                    case R.id.xiugaijiage:
                        alertWindow(view,order_id);
                        break;

                    case R.id.zhifu://支付
//                        toast("去支付");
                        removepos = position;
                        intent = new Intent(getActivity(), ZhiFuActivity.class);
                        intent.putExtra("flag", 2);
                        intent.putExtra("order_id", Integer.valueOf(order_id));
                        intent.putExtra("price", price);
                        startActivity(intent);
                        break;
                    case R.id.queixao:
                        //toast("取消了");
//                        adapter.remove(position);
//                        quxiao(position);


                        //  查看装货单
                        intent = new Intent(getActivity(), LookUpLoadFaActivity.class);
                        intent.putExtra("ordre_id", order_id);
                        intent.putExtra("flag",1 );
                        startActivity(intent);
                        break;
//                    case R.id.face:
//                        toast("fcse");
//                        break;
                    case R.id.queixao1:
//                        toast("fcse");
                        adapter.remove(position);
                        quxiao(position);
                        break;
                    case R.id.fahuodan://查看装货单
                        intent = new Intent(getActivity(), LookUpLoadFaActivity.class);
                        intent.putExtra("ordre_id", order_id);
                        intent.putExtra("flag",1 );
                        startActivity(intent);
                        break;
                    case R.id.qiehoudan://查看卸货单
                        intent = new Intent(getActivity(), LookUpLoadFaActivity.class);
                        intent.putExtra("ordre_id", order_id);
                        intent.putExtra("flag",2 );
                        startActivity(intent);
                        break;
                    case R.id.kanqiehoudan://查看卸货单
                        String order_i = getCurOrder().get(position).getOrder_id();
                        Intent inten = new Intent(getActivity(), LookUpLoadFaActivity.class);
                        inten.putExtra("ordre_id", order_i);
                        inten.putExtra("flag",2 );
                        startActivity(inten);
                        break;
                    case R.id.kanfahuodan://查看装货单
                        String order = getCurOrder().get(position).getOrder_id();
                        Intent inte = new Intent(getActivity(), LookUpLoadFaActivity.class);
                        inte.putExtra("ordre_id", order);
                        inte.putExtra("flag",1 );
                        startActivity(inte);
                        break;
                    case R.id.querenzhifu:
                      GetData.order_finish(Integer.valueOf(order_id),SharedPreferencesUtil.getInstance().getString("token"),BaseDingDanview.this,QUERENORDER);
                        break;
                    //case
                    default:

                        break;

                }
                refresh();
            }

        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (curpos != null) {
                    Intent in = new Intent(getActivity(), DingDanDetailActivity.class);
                    in.putExtra("start", curpos.getData().get(position).getStart_city());
                    in.putExtra("end", curpos.getData().get(position).getEnd_city());
                    in.putExtra("order_id", curpos.getData().get(position).getOrder_id());
                    startActivity(in);
                }
            }
        });

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
    }


    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }

    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getActivity().getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        getActivity().getWindow().setAttributes(lp);
    }
    PopupWindow popupWindow;
    private void alertWindow(final View view, final String order_id) {
        final View vie = View.inflate(getActivity(), R.layout.edit_price, null);
        popupWindow = new PopupWindow(getActivity());
        popupWindow.setContentView(vie);
        popupWindow.setWidth(DensityUtil.dip2px(getActivity(),260));
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        // 背景的处理
        setBackgroundAlpha(0.5f);//设置屏幕透明度
        final EditText editText = vie.findViewById(R.id.editText);
        TextView textView2 = vie.findViewById(R.id.textView2);
        TextView textView3 = vie.findViewById(R.id.textView3);

        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                     }
            }
        });


        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(editText.getText().toString().trim())){
                    toast("请输入修改后的价格");
                }else {
                    if (popupWindow.isShowing()) {
                        popupWindow.dismiss();
                    }

                    OkGo.<String>post(Contans.BASE+"index.php/home/ownerorder/modify_price")
                            .params("order_id",order_id)
                            .params("token",SharedPreferencesUtil.getInstance().getString("token"))
                            .params("price",Float.parseFloat(editText.getText().toString().trim()))
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(Response<String> response) {

                                    Base base = new Gson().fromJson(response.body(),Base.class);
                                    toast(base.getMessage());
//                                    if(view instanceof TextView){
//                                        ((TextView) view).setText(editText.getText().toString().trim());
//                                    }

                                    if(base.getCode()==200){
                                        try {
                                            LinearLayout linearLayout = (LinearLayout) view.getParent().getParent().getParent();
                                            TextView textView =  linearLayout.findViewById(R.id.jine);
                                            textView.setText(editText.getText().toString().trim());
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }


//                                    initData();
                                }

                                @Override
                                public void onError(Response<String> response) {
                                    super.onError(response);
                                }
                            });

//                    GetData.modify_price(Integer.parseInt(order_id),SharedPreferencesUtil.getInstance().getString("token"),
//                            Float.parseFloat(editText.getText().toString().trim()),BaseDingDanview.this,XIUGAI);
                }
            }
        });

//        tv.setText("住宅");
//        tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (popupWindow.isShowing()) {
//                    popupWindow.dismiss();
//                }
//                //title.setText("收入记录");
//
//                Intent intent = new Intent(getActivity(),AddChuZ1Activity.class);
//                intent.putExtra("type","1");
//                startActivity(intent);
////                start();
//                type = 0;
//
//            }
//        });
//        vie.findViewById(R.id.tixian).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (popupWindow.isShowing()) {
//                    popupWindow.dismiss();
//                }
//                //title.setText("收入记录");
//                //start(AddChuZu2Activity.class);
//                type = 0;
//                Intent intent = new Intent(getActivity(),AddChuZu2Activity.class);
//                intent.putExtra("type","1");
//                startActivity(intent);
//            }
//        });
//
//        vie.findViewById(R.id.shangpu).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (popupWindow.isShowing()) {
//                    popupWindow.dismiss();
//                }
//                //title.setText("收入记录");
//                type = 0;
////                start(AddChuZu3Activity.class);
//                Intent intent = new Intent(getActivity(),AddChuZu3Activity.class);
//                intent.putExtra("type","1");
//                startActivity(intent);
//            }
//        });
//
//        vie.findViewById(R.id.gongyechang).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (popupWindow.isShowing()) {
//                    popupWindow.dismiss();
//                }
//                // start(AddChuZu4Activity.class);
//                Intent intent = new Intent(getActivity(),AddChuZu4Activity.class);
//                intent.putExtra("type","1");
//                startActivity(intent);
//            }
//        });
        //popupWindow.showAtLocation(vie, Gravity.BOTTOM, 0, 0);
        popupWindow.showAtLocation(vie, Gravity.CENTER, 0, 0);
        // popupWindow.showAsDropDown(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                // popupWindow隐藏时恢复屏幕正常透明度
                setBackgroundAlpha(1.0f);
            }
        });
    }


    protected abstract void loadmore();

    protected abstract void refresh();

    public void getData(int page, int type, int shoutype) {
        GetData.order(page, SharedPreferencesUtil.getInstance().getString("token"), type, this, shoutype);
    }

    protected void quxiao(int position) {
        OrderBean cur = getCurrentOrder();
        GetData.cancel_order(SharedPreferencesUtil.getInstance().getString("token"),
                Integer.parseInt(cur.getData().get(position).getOrder_id()),
                BaseDingDanview.this, ORDERDELETE);
    }

    protected abstract OrderBean getCurrentOrder();

    protected OrderBean total;
    protected OrderBean quren;
    protected OrderBean jinxing;
    protected OrderBean history;
    protected OrderBean daifu;
    protected OrderBean curpos;


    public void clear() {
        if (adapter != null) {
            adapter.clear();
        }
    }

    int pos;

    public void setCurpos(int pos) {
        this.pos = pos;
    }

    public void getCurorder() {
        switch (pos) {
            case 0:
                curpos = total;
                break;
            case 1:
                curpos = daifu;
                break;
            case 2:
                curpos = jinxing;
                break;
            case 3:
                curpos = quren;
                break;
            case 4:
                curpos = history;
                break;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
       rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        empty = rootView.findViewById(R.id.empty);
        smartRefreshLayout = rootView.findViewById(R.id.smartRefreshLayout);
        classicsHeader = rootView.findViewById(R.id.header);
        classicsFooter = rootView.findViewById(R.id.footer);
        smartRefreshLayout.setRefreshContent(dingdanRv);
        dingdanRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = getAdapter();
        adapter.setEmptyView(R.layout.layout_empty,dingdanRv);
        dingdanRv.setAdapter(adapter);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
    }


    @Override
    public void success(int requestcode, Object o) {
        switch (requestcode){
            case XIUGAI:
                Base base = (Base) o;
                toast(((Base) o).getMessage());
                initData();
                break;
            case CANCLE:
               try {
                   toast(((Base) o).getMessage());
               }catch (Exception e){
                   refresh();
               }
                break;
        }
    }

    protected abstract List<OrderBean.DataBean> getCurOrder();
}
