package com.zhuye.huochebang.ui;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhuye.huochebang.BaseActivity;
import com.zhuye.huochebang.R;
import com.zhuye.huochebang.adapter.SiJIShenAdaper;
import com.zhuye.huochebang.adapter.siji.SelectCarAdapter;
import com.zhuye.huochebang.base.BaseHolder;
import com.zhuye.huochebang.bean.CarListBean;
import com.zhuye.huochebang.bean.Code;
import com.zhuye.huochebang.bean.DriverListBean;
import com.zhuye.huochebang.data.http.CommonApi;
import com.zhuye.huochebang.data.http.GetData;
import com.zhuye.huochebang.utils.DensityUtil;
import com.zhuye.huochebang.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class SiJiShenqingActivity extends BaseActivity {


    private static final int DRIVERLIST = 10;
    private static final int CARLIST = 11;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.shenqingrecycle)
    RecyclerView shenqingrecycle;

    @Override
    protected int getResId() {
        return R.layout.activity_si_ji_shenqing;
    }

    SiJIShenAdaper adaper;
    SelectCarAdapter selectCarAdapter;

    @Override
    protected void initView() {
        super.initView();
        shenqingrecycle.setLayoutManager(new LinearLayoutManager(SiJiShenqingActivity.this));
//        adaper = new SiJIShenAdaper(R.layout.sijishenitem,);
//        shenqingrecycle.setAdapter(adaper);
        selectCarAdapter = new SelectCarAdapter(SiJiShenqingActivity.this);


        dat = new HashMap(30);
        for (int i = 0; i < 30; i++) {
            dat.put(i, false);
        }

    }


    @Override
    public void empty() {
        super.empty();
        emptyview = LayoutInflater.from(this).inflate(R.layout.empty, null);
        addContentView(emptyview, parama);
    }

    @Override
    public void success(int requestcode, Object o) {
        super.success(requestcode, o);
        switch (requestcode) {
            case DRIVERLIST:
                final DriverListBean driverListBean = (DriverListBean) o;
//                adaper.addData(driverListBean.getData());
                adaper = new SiJIShenAdaper(R.layout.sijishenitem, driverListBean.getData());
                shenqingrecycle.setAdapter(adaper);
                adaper.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        if (view.getId() == R.id.agreeaa){
                            alertbotom(driverListBean.getData().get(position).getDriver_jionid(),driverListBean.getData().get(position).getDriver_id());
                        }else if (view.getId() == R.id.refuse){
                            aliertLiyou(driverListBean.getData().get(position).getDriver_jionid());
                        }
                    }
                });
                break;
            case CARLIST:
                carListBean = (CarListBean) o;
                //adaper.addData(carListBean.getData());
                for (int i = 0; i < carListBean.getData().size(); i++) {
                    carListBean.getData().get(i).setIsselect(false);
                }
                break;
            case 111://同意
                Code code = (Code) o;
            toast(code.getMessage());
            break;
            case 112://拒绝
                Code codes = (Code) o;
                toast(codes.getMessage());
                break;
                default:
                    break;

        }
    }

    CarListBean carListBean;
    DriverListBean driverListBean;

    @Override
    protected void initData() {
        super.initData();

        GetData.driver(SharedPreferencesUtil.getInstance().getString("token"), 1, SiJiShenqingActivity.this, DRIVERLIST);
        GetData.car_list(SharedPreferencesUtil.getInstance().getString("token"), 1, SiJiShenqingActivity.this, CARLIST);
//        CommonApi.getInstance().driver(SharedPreferencesUtil.getInstance().getString("token")).subscribeOn(io.reactivex.schedulers.Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<DriverListBean>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//                        Log.i("as",d.toString());
//                    }
//
//                    @Override
//                    public void onNext(@NonNull DriverListBean code) {
//                        Log.i("as",code.toString());
//                        if(code.getCode() == 200){
//                            driverListBean = code;
//                            adaper.addData(code.getData());
//                        }
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        Log.i("as",e.toString());
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });

//        CommonApi.getInstance().car_list(SharedPreferencesUtil.getInstance().getString("token")).subscribeOn(io.reactivex.schedulers.Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<CarListBean>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//                        Log.i("as",d.toString());
//                    }
//
//                    @Override
//                    public void onNext(@NonNull CarListBean code) {
//                        if(code.getCode() == 200){
//                           carListBean = code;
//                            for(int i = 0; i<carListBean.getData().size();i++){
//                                carListBean.getData().get(i).setIsselect(false);
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        Log.i("as",e.toString());
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }

    @Override
    protected void initListener() {
        super.initListener();


//        adaper.setOnItemClickListener(new BaseHolder.OnItemClickListener() {
//            @Override
//            public void OnItemClick(View view, final int position) {
//                view.findViewById(R.id.agreeaa).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        alertbotom(position);
//                    }
//                });
//
//                view.findViewById(R.id.refuse).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        aliertLiyou(position);
//                    }
//                });
//            }
//        });
    }

    private void aliertLiyou( final String driver_jionid) {
        final AlertDialog dialog = new AlertDialog.Builder(SiJiShenqingActivity.this).create();
        View view = View.inflate(SiJiShenqingActivity.this, R.layout.aliet_tousu, null);
        dialog.setView(view);
        final EditText et = view.findViewById(R.id.tousu);
        et.setHint("请输入拒绝理由");
        view.findViewById(R.id.queren).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tousu = et.getText().toString();
                if (TextUtils.isEmpty(tousu)) {
                    toast("请输入拒绝理由");
                    return;
                }
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                GetData.refuse(SharedPreferencesUtil.getInstance().getString("token"),
                        Integer.valueOf(driver_jionid),tousu, SiJiShenqingActivity.this,112);
//                CommonApi.getInstance().refuse(SharedPreferencesUtil.getInstance().getString("token"),
//                        Integer.valueOf(driver_jionid), tousu).subscribeOn(io.reactivex.schedulers.Schedulers.newThread())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new Observer<Code>() {
//                            @Override
//                            public void onSubscribe(@NonNull Disposable d) {
//                                Log.i("as", d.toString());
//                            }
//
//                            @Override
//                            public void onNext(@NonNull Code code) {
//                                Log.i("as", code.toString());
//                                if (code.getCode() == 200) {
//                                    toast("已拒绝");
//                                }
//                            }
//
//                            @Override
//                            public void onError(@NonNull Throwable e) {
//                                Log.i("as", e.toString());
//                            }
//
//                            @Override
//                            public void onComplete() {
//
//                            }
//                        });

            }
        });

        view.findViewById(R.id.quxiao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    public void alertbotom( final String driver_jionid, final String driver_id) {
        View vie = View.inflate(SiJiShenqingActivity.this, R.layout.bottom_select_car, null);
        final PopupWindow popupWindow = new PopupWindow(SiJiShenqingActivity.this);
        popupWindow.setContentView(vie);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(DensityUtil.dip2px(SiJiShenqingActivity.this, 349));
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);

        vie.findViewById(R.id.guanbi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
            }
        });


        vie.findViewById(R.id.queding).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }

                List<String> data = new ArrayList();
                for (int i = 0; i < carListBean.getData().size(); i++) {
                    if (carListBean.getData().get(i).getIsselect()) {
                        data.add(carListBean.getData().get(i).getCar_id());
                    }
                }
                if (data.size() < 1) {
                    toast("请选择车辆");
                    return;
                }
                String[] ids = new String[data.size()];
                for (int i = 0; i < data.size(); i++) {
                    ids[i] = data.get(i);
                }
                GetData.agree(SharedPreferencesUtil.getInstance().getString("token"),
                        Integer.valueOf(driver_jionid), Integer.valueOf(driver_id), SiJiShenqingActivity.this,111,ids);
//                CommonApi.getInstance().agree(SharedPreferencesUtil.getInstance().getString("token"),
//                        Integer.valueOf(driver_jionid), Integer.valueOf(driver_id), ids).subscribeOn(io.reactivex.schedulers.Schedulers.newThread())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new Observer<DriverListBean>() {
//                            @Override
//                            public void onSubscribe(@NonNull Disposable d) {
//                                Log.i("as", d.toString());
//                            }
//
//                            @Override
//                            public void onNext(@NonNull DriverListBean code) {
//                                Log.i("as", code.toString());
//                                if (code.getCode() == 200) {
//                                    toast("已同意");
//                                    finish();
//                                }
//                            }
//
//                            @Override
//                            public void onError(@NonNull Throwable e) {
//                                Log.i("as", e.toString());
//                                finish();
//                            }
//
//                            @Override
//                            public void onComplete() {
//
//                            }
//                        });
            }
        });
        RecyclerView rv = vie.findViewById(R.id.selectcar);
        rv.setAdapter(selectCarAdapter);
        selectCarAdapter.addData(carListBean.getData());

        selectCarAdapter.setOnItemClickListener(new BaseHolder.OnItemClickListener() {
            @Override
            public void OnItemClick(final View view, int position) {
                if (carListBean.getData().get(position).getIsselect()) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            view.findViewById(R.id.xianshi).setVisibility(View.INVISIBLE);
                        }
                    });
                    //dat.get(position)=false;
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            view.findViewById(R.id.xianshi).setVisibility(View.VISIBLE);
                        }
                    });
                }
                carListBean.getData().get(position).setIsselect(!carListBean.getData().get(position).getIsselect());
                // dat.put(position, !dat.get(position));
            }
        });
        rv.setLayoutManager(new LinearLayoutManager(SiJiShenqingActivity.this));
        popupWindow.showAtLocation(vie, Gravity.BOTTOM, 0, 0);
    }

    public Map<Integer, Boolean> dat;
}
