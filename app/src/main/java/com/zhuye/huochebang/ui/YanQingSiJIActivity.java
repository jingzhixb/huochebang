package com.zhuye.huochebang.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.zhuye.huochebang.BaseActivity;
import com.zhuye.huochebang.R;
import com.zhuye.huochebang.adapter.car.YaoqingAdapter2;
import com.zhuye.huochebang.bean.Base;
import com.zhuye.huochebang.bean.DriverListBean;
import com.zhuye.huochebang.bean.YaoDriverBean;
import com.zhuye.huochebang.data.http.CommonApi;
import com.zhuye.huochebang.data.http.GetData;
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
import scut.carson_ho.searchview.ICallBack;
import scut.carson_ho.searchview.SearchView;

public class YanQingSiJIActivity extends BaseActivity {


    private static final int GETLIST = 10;
    private static final int YAOQING = 15;
    private static final int SHUAXIN = 16 ;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.guanlirecycle)
    RecyclerView guanlirecycle;
    @BindView(R.id.search1)
    SearchView mSearch1;
    @BindView(R.id.header)
    ClassicsHeader mHeader;
    @BindView(R.id.footer)
    ClassicsFooter mFooter;
    @BindView(R.id.refreshlayout)
    SmartRefreshLayout mRefreshlayout;


    @Override
    protected int getResId() {
        return R.layout.activity_yan_qing_si_ji;
    }


    YaoqingAdapter2 adaper;

    @Override
    protected void initView() {
        super.initView();
        adaper = new YaoqingAdapter2(R.layout.yaoting_item);
        guanlirecycle.setAdapter(adaper);
        guanlirecycle.setLayoutManager(new LinearLayoutManager(YanQingSiJIActivity.this));
    }

    int car_id = 0;
    @Override
    protected void initData() {
        super.initData();
       car_id = getIntent().getIntExtra("car_id", 0);
        GetData.homecainvite_driverrinfo(SharedPreferencesUtil.getInstance().getString("token"),
                car_id, YanQingSiJIActivity.this, GETLIST);
    }
    private static final int REFRESH = 12;
    private static final int LOADMORE = 13;

    DriverListBean mDriverListBean;

    List<DriverListBean.DataBean>  mData = new ArrayList<>();

    @Override
    public void success(int requestcode, Object o) {
        super.success(requestcode, o);
        switch (requestcode) {
            case GETLIST:
                mDriverListBean = (DriverListBean) o;
                mData.addAll(mDriverListBean.getData());
                adaper.addData(mData);
                break;

            case REFRESH:
                mDriverListBean = (DriverListBean) o;
                adaper.clear();
                mData.clear();
                mData.addAll(mDriverListBean.getData());
                adaper.addData(mData);
                mRefreshlayout.finishRefresh();
                break;

            case  LOADMORE:
                mDriverListBean = (DriverListBean) o;
                mData.addAll(mDriverListBean.getData());
                adaper.addData(mData);
                mRefreshlayout.finishLoadmore();
                break;

            case YAOQING:
                Base base = (Base) o;
                toast(base.getMessage());
                //刷新列表
                GetData.homecainvite_driverrinfo(SharedPreferencesUtil.getInstance().getString("token"),
                        car_id, YanQingSiJIActivity.this, SHUAXIN);
                break;
            case SHUAXIN:
                mDriverListBean = (DriverListBean) o;
                adaper.clear();
                mData.clear();
                mData.addAll(mDriverListBean.getData());
                adaper.addData(mData);
                break;

        }
    }

    @OnClick({R.id.back, R.id.search1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;

            case R.id.search1:
                //search();
                break;
        }
    }

    String text;

    private void search() {

        if (isEmpty(text)) {
            toast("请输入搜索内容");
            return;
        }

        CommonApi.getInstance().sel_driver(SharedPreferencesUtil.getInstance().getString("token"), "").subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<YaoDriverBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i("as", d.toString());
                    }

                    @Override
                    public void onNext(@NonNull YaoDriverBean code) {
                        Log.i("as", code.toString());
                        if (code.getCode() == 200) {
                            // adaper.addData(code.getData());
                        }
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
    protected void initListener() {
        super.initListener();
        mSearch1.setOnClickSearch(new ICallBack() {
            @Override
            public void SearchAciton(String string) {
                text = string;
                search();
            }
        });



        adaper.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.blacknumber_yichu:

                        GetData.add_driver(SharedPreferencesUtil.getInstance().getString("token")
                        ,car_id, Integer.parseInt(mData.get(position).getDriver_id()),YanQingSiJIActivity.this,YAOQING);
                        break;
                }
            }
        });

//        mRefreshlayout.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh(RefreshLayout refreshlayout) {
//                page =1;
//                GetData.homecainvite_driverrinfo(SharedPreferencesUtil.getInstance().getString("token"),page,YanQingSiJIActivity.this,REFRESH);
//
//                mHandler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        mRefreshlayout.finishRefresh();
//                    }
//                },1500);
//            }
//        });
//
//        mRefreshlayout.setOnLoadmoreListener(new OnLoadmoreListener() {
//            @Override
//            public void onLoadmore(RefreshLayout refreshlayout) {
//                ++page;
//                GetData.homecainvite_driverrinfo(SharedPreferencesUtil.getInstance().getString("token"),page,YanQingSiJIActivity.this,LOADMORE);
//                // mRefreshlayout.finishLoadmore(2,false);
//
//                mHandler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        mRefreshlayout.finishLoadmore();
//                    }
//                },1500);
//            }
//        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
