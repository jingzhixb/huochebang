package com.zhuye.huochebangsiji.activity;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.zhuye.huochebangsiji.R;
import com.zhuye.huochebangsiji.adapter.OilCardHomeAdapter3;
import com.zhuye.huochebangsiji.base.BaseActivity;
import com.zhuye.huochebangsiji.bean.OilChargeListBean;
import com.zhuye.huochebangsiji.data.GetData;
import com.zhuye.huochebangsiji.utils.DensityUtil;
import com.zhuye.huochebangsiji.utils.SharedPreferencesUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class ShouzhijulvActivity extends BaseActivity {


    private static final int DETAIL = 10;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.menu)
    ImageView menu;
    @BindView(R.id.recycle)
    RecyclerView recycle;

    @Override
    protected int getResId() {
        return R.layout.activity_shouzhijulv;
    }

    OilCardHomeAdapter3 adapter;
    @Override
    protected void initView() {
        super.initView();
        adapter = new OilCardHomeAdapter3(R.layout.oilcard_item);
        recycle.setAdapter(adapter);
        recycle.setLayoutManager(new LinearLayoutManager(ShouzhijulvActivity.this));
    }

    @Override
    public void success(int requestcode, Object o) {
        super.success(requestcode, o);
        OilChargeListBean oilChargeListBean = (OilChargeListBean) o;
        adapter.addData(oilChargeListBean.getData());
    }

    int page = 1;
    @Override
    protected void initData() {
        super.initData();

        GetData.oil_recharge(SharedPreferencesUtil.getInstance().getString("token")
        ,page,ShouzhijulvActivity.this,DETAIL);
       // getshourudata();
    }

    private void getshourudata() {
//        CommonApi.getInstance().income_log(SharedPreferencesUtil.getInstance().getString("token"),page).subscribeOn(Schedulers.newThread())
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
//                        }
//                        toast(code.getMessage());
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

    @OnClick({R.id.back, R.id.menu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.menu:
                View vie = View.inflate(ShouzhijulvActivity.this, R.layout.menu_item1, null);
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
                        //adapter.clear();
                        getshourudata();
                    }
                });

                vie.findViewById(R.id.tixian).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (popupWindow.isShowing()) {
                            popupWindow.dismiss();
                        }
                        title.setText("提现记录");
                       // adapter.clear();
                        getTixianData();
                    }
                });
                //popupWindow.showAtLocation(vie, Gravity.BOTTOM, 0, 0);
                popupWindow.showAsDropDown(view);
                break;

        }
    }

    private void getTixianData() {
//        CommonApi.getInstance().cash_log(SharedPreferencesUtil.getInstance().getString("token"),page).subscribeOn(Schedulers.newThread())
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
//                        }
//                        toast(code.getMessage());
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
}
