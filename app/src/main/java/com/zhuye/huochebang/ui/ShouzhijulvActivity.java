package com.zhuye.huochebang.ui;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhuye.huochebang.BaseActivity;
import com.zhuye.huochebang.R;
import com.zhuye.huochebang.adapter.money.AhouRuadapter;
import com.zhuye.huochebang.bean.MoneyLog;
import com.zhuye.huochebang.data.http.CommonApi;
import com.zhuye.huochebang.data.http.GetData;
import com.zhuye.huochebang.utils.DensityUtil;
import com.zhuye.huochebang.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ShouzhijulvActivity extends BaseActivity {


    @Override
    public void empty() {
        super.empty();
        mEmpty.setVisibility(View.VISIBLE);
    }

    private static final int GETINCOM =10 ;
    private static final int LOADMORE = 11;
    private static final int REFRESH = 12 ;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.menu)
    ImageView menu;
    @BindView(R.id.recycle)
    RecyclerView recycle;
    @BindView(R.id.jeader)
    ClassicsHeader mJeader;
    @BindView(R.id.footer)
    ClassicsFooter mFooter;
    @BindView(R.id.smartRefresh)
    SmartRefreshLayout mSmartRefresh;
    @BindView(R.id.empty)
    TextView mEmpty;

    @Override
    protected int getResId() {
        return R.layout.activity_shouzhijulv;
    }

    AhouRuadapter adapter;

    @Override
    protected void initView() {
        super.initView();
        adapter = new AhouRuadapter(R.layout.money_home_item);
        recycle.setAdapter(adapter);
        recycle.setLayoutManager(new LinearLayoutManager(ShouzhijulvActivity.this));
    }

    int page = 1;

    @Override
    protected void initData() {
        super.initData();
        //getshourudata();
        GetData.income_log(SharedPreferencesUtil.getInstance().getString("token"), page,ShouzhijulvActivity.this,
        GETINCOM);
    }

    private int type = 0 ;



    MoneyLog code;
    List<MoneyLog.DataBean> mDataBeans = new ArrayList<>();
    @Override
    public void success(int requestcode, Object o) {
        super.success(requestcode, o);
        switch (requestcode){
            case GETINCOM:
                code = (MoneyLog) o;
                mDataBeans.addAll(code.getData());
                adapter.addData(mDataBeans);
                if(mDataBeans.size()==0){
                    mEmpty.setVisibility(View.VISIBLE);
                }else {
                    mEmpty.setVisibility(View.GONE);
                }
                break;

            case REFRESH:
                code = (MoneyLog) o;
                mDataBeans.clear();
                adapter.clear();
                mDataBeans.addAll(code.getData());
                adapter.addData(mDataBeans);
                recycle.scrollToPosition(0);
                break;
            case LOADMORE:
                code = (MoneyLog) o;
                mDataBeans.addAll(code.getData());
                adapter.addData(mDataBeans);
              //  recycle.scrollToPosition(0);
                break;

        }
    }




//    private void getshourudata() {
//        CommonApi.getInstance().income_log(SharedPreferencesUtil.getInstance().getString("token"), page).subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<MoneyLog>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//                        Log.i("as", d.toString());
//                    }
//
//                    @Override
//                    public void onNext(@NonNull MoneyLog code) {
//                        Log.i("as", code.toString());
//                        if (code.getCode() == 200) {
//                            adapter.addData(code.getData());
//                            if(code.getData().size()==0){
//                                mEmpty.setVisibility(View.VISIBLE);
//                            }else {
//                                mEmpty.setVisibility(View.GONE);
//                            }
//                        }
//                        //toast(code.getMessage());
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        Log.i("as", e.toString());
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//    }

    @OnClick({R.id.back, R.id.menu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.menu:
                View vie = View.inflate(ShouzhijulvActivity.this, R.layout.menu_item, null);
                final PopupWindow popupWindow = new PopupWindow(ShouzhijulvActivity.this);
                popupWindow.setContentView(vie);
                popupWindow.setWidth(DensityUtil.dip2px(ShouzhijulvActivity.this, 101));
                popupWindow.setHeight(DensityUtil.dip2px(ShouzhijulvActivity.this, 109));
                popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
                popupWindow.setOutsideTouchable(true);
                popupWindow.setFocusable(true);

                vie.findViewById(R.id.shouru).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (popupWindow.isShowing()) {
                            popupWindow.dismiss();
                        }
                        title.setText("收入记录");

                        type = 0;
                        adapter.clear();
                        //getshourudata();
                        GetData.income_log(SharedPreferencesUtil.getInstance().getString("token"), page,ShouzhijulvActivity.this,
                                GETINCOM);
                    }
                });

                vie.findViewById(R.id.tixian).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (popupWindow.isShowing()) {
                            popupWindow.dismiss();
                        }
                        title.setText("提现记录");
                        adapter.clear();
                        type = 1;
                        //getTixianData();
                        GetData.getTixianData(SharedPreferencesUtil.getInstance().getString("token"), page,ShouzhijulvActivity.this,
                                GETINCOM);
                    }
                });
                //popupWindow.showAtLocation(vie, Gravity.BOTTOM, 0, 0);
                popupWindow.showAsDropDown(view);
                break;

        }
    }

    private void getTixianData() {
        CommonApi.getInstance().cash_log(SharedPreferencesUtil.getInstance().getString("token"), page).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MoneyLog>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i("as", d.toString());
                    }

                    @Override
                    public void onNext(@NonNull MoneyLog code) {
                        Log.i("as", code.toString());
                        if (code.getCode() == 200) {
                            adapter.addData(code.getData());
                            if(code.getData().size()==0){
                                mEmpty.setVisibility(View.VISIBLE);
                            }else {
                                mEmpty.setVisibility(View.GONE);
                            }
                        }
                       // toast(code.getMessage());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i("as", e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @Override
    protected void initListener() {
        super.initListener();

        mSmartRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                if(type==0){
                    GetData.income_log(SharedPreferencesUtil.getInstance().getString("token"), page,ShouzhijulvActivity.this,
                            REFRESH);
                }else {
                    GetData.getTixianData(SharedPreferencesUtil.getInstance().getString("token"), page,ShouzhijulvActivity.this,
                            REFRESH);
                }

                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSmartRefresh.finishRefresh();
                    }
                },2000);
            }
        });


        mSmartRefresh.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                ++page;
                if(type==0){
                    GetData.income_log(SharedPreferencesUtil.getInstance().getString("token"), page,ShouzhijulvActivity.this,
                            LOADMORE);
                }else {
                    GetData.getTixianData(SharedPreferencesUtil.getInstance().getString("token"), page,ShouzhijulvActivity.this,
                            LOADMORE);
                }

                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSmartRefresh.finishLoadmore();
                    }
                },2000);
            }
        });
    }


}
