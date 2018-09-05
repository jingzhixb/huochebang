package com.zhuye.huochebang.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhuye.huochebang.BaseActivity;
import com.zhuye.huochebang.R;
import com.zhuye.huochebang.adapter.car.CarHomeAdapter2;
import com.zhuye.huochebang.bean.HomeCarInfoBean;
import com.zhuye.huochebang.bean.MessageEvent;
import com.zhuye.huochebang.contants.Contans;
import com.zhuye.huochebang.data.http.GetData;
import com.zhuye.huochebang.utils.SharedPreferencesUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import scut.carson_ho.searchview.ICallBack;
import scut.carson_ho.searchview.SearchView;

public class CarManagerActivity extends BaseActivity {


    private static final int SELCETCAR = 10;
    private static final int GETINFO = 11 ;
    private static final int REFRESH = 12;
    private static final int LOADMORE = 13 ;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.tou)
    RelativeLayout tou;
    @BindView(R.id.guanlirecycle)
    RecyclerView guanlirecycle;
    @BindView(R.id.search)
    SearchView search;
    @BindView(R.id.header)
    ClassicsHeader mHeader;
    @BindView(R.id.footer)
    ClassicsFooter mFooter;
    @BindView(R.id.refreshlayout)
    SmartRefreshLayout mRefreshlayout;

    @Override
    protected int getResId() {
        return R.layout.activity_car_manager;
    }

    CarHomeAdapter2 adapter;





    @Override
    protected void initView() {
        super.initView();

        View view = LayoutInflater.from(CarManagerActivity.this).inflate(R.layout.bootomview, null);
        view.findViewById(R.id.xinzeng).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               startActivityIfNeeded(new Intent(CarManagerActivity.this, AddCarActivity.class),10);
            }
        });

        // guanlirecycle.addFooterView(view);
        adapter = new CarHomeAdapter2(R.layout.carhome_item);
        adapter.addFooterView(view);
        guanlirecycle.setAdapter(adapter);
        guanlirecycle.setLayoutManager(new LinearLayoutManager(CarManagerActivity.this));
        //        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        ////隐藏软键盘 //
        //        imm.hideSoftInputFromWindow(search.getWindowToken(), 0);
        //
        //        //((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(CarManagerActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        //        InputMethodManager imms = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        //        imms.showSoftInput(search,InputMethodManager.SHOW_FORCED);
        //
        //        imms.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘


        search.setOnClickSearch(new ICallBack() {
            @Override
            public void SearchAciton(String string) {
                //toast(string+"asdfas");

                OkGo.<String>post(Contans.BASE_URL + "/index.php/home/car/select_car")
                        .params("token", SharedPreferencesUtil.getInstance().getString("token"))
                        .params("license", string)
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                Log.i("as", response.body());
                            }

                            @Override
                            public void onError(Response<String> response) {
                                super.onError(response);
                                Log.i("as", response.body());
                            }
                        });
                GetData.select_car(SharedPreferencesUtil.getInstance().getString("token"), string, CarManagerActivity.this, SELCETCAR);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        page=1;
        mRefreshlayout.autoRefresh();
        GetData.homecarinfo(SharedPreferencesUtil.getInstance().getString("token"),page,CarManagerActivity.this,REFRESH);

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mRefreshlayout.finishRefresh();
            }
        },1500);
    }

    @Override
    protected void initListener() {
        super.initListener();
        //        adapter.setOnItemClickListener(new BaseHolder.OnItemClickListener() {
        //            @Override
        //            public void OnItemClick(View view, final int position) {
        //                view.findViewById(R.id.go).setOnClickListener(new View.OnClickListener() {
        //                    @Override
        //                    public void onClick(View view) {
        //                        Intent intent = new Intent(CarManagerActivity.this, SingerCarMangerActivity.class);
        //                        intent.putExtra("chexing", carInfoBean.getData().get(position).getModels());
        //                        intent.putExtra("chechang", carInfoBean.getData().get(position).getLeng());
        //                        intent.putExtra("xinghao", carInfoBean.getData().get(position).getLicense());
        //                        intent.putExtra("car_id", carInfoBean.getData().get(position).getCar_id());
        //                        intent.putExtra("type", carInfoBean.getData().get(position).getType());
        //                        startActivity(intent);
        //                    }
        //                });
        //            }
        //        });


        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(CarManagerActivity.this, SingerCarMangerActivity.class);
                intent.putExtra("chexing", mData.get(position).getModels());
                intent.putExtra("chechang", mData.get(position).getLeng());
                intent.putExtra("xinghao", mData.get(position).getLicense());
                intent.putExtra("car_id", Integer.valueOf(mData.get(position).getCar_id()));
                intent.putExtra("type", mData.get(position).getType());
                startActivity(intent);
            }
        });

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            }
        });

        mRefreshlayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page =1;
                GetData.homecarinfo(SharedPreferencesUtil.getInstance().getString("token"),page,CarManagerActivity.this,REFRESH);

                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mRefreshlayout.finishRefresh();
                    }
                },1500);
            }
        });

        mRefreshlayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                ++page;
                GetData.homecarinfo(SharedPreferencesUtil.getInstance().getString("token"),page,CarManagerActivity.this,LOADMORE);
               // mRefreshlayout.finishLoadmore(2,false);

                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mRefreshlayout.finishLoadmore();
                    }
                },1500);
            }
        });

    }



    HomeCarInfoBean carInfoBean;

    @Override
    protected void initData() {
        super.initData();

        getData(page);

//        CommonApi.getInstance().homecarinfo(SharedPreferencesUtil.getInstance().getString("token"), 1).subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<HomeCarInfoBean>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//                        Log.i("as", d.toString());
//                    }
//
//                    @Override
//                    public void onNext(@NonNull HomeCarInfoBean code) {
//                        Log.i("as", code.toString());
//                        if (code.getCode() == 200) {
//                            carInfoBean = code;
//                            adapter.addData(code.getData());
//                        }
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
    }

    private void getData(int page) {
        GetData.homecarinfo(SharedPreferencesUtil.getInstance().getString("token"),page,CarManagerActivity.this,GETINFO);
    }


    List<HomeCarInfoBean.DataBean> mData = new ArrayList<>();
    @Override
    public void success(int requestcode, Object o) {
        super.success(requestcode, o);
        switch (requestcode) {
            case SELCETCAR:
                adapter.clear();
                HomeCarInfoBean homeCarInfoBean = (HomeCarInfoBean) o;
                adapter.addData(homeCarInfoBean.getData());
                break;

            case GETINFO:
                carInfoBean = (HomeCarInfoBean) o;
                mData.addAll(carInfoBean.getData());
                adapter.addData(mData);
                break;

            case REFRESH:

                carInfoBean = (HomeCarInfoBean) o;
                adapter.clear();
                mData.clear();
                mData.addAll(carInfoBean.getData());
                adapter.addData(mData);
                mRefreshlayout.finishRefresh();
                guanlirecycle.scrollToPosition(0);
                break;

            case  LOADMORE:
                carInfoBean = (HomeCarInfoBean) o;
                mData.addAll(carInfoBean.getData());
                adapter.addData(mData);
                mRefreshlayout.finishLoadmore();
                break;
        }
    }

    @OnClick({R.id.back, R.id.search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.search:
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        /* Do something */
        adapter.clear();
        initData();
    }


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }


    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
