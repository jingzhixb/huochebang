package com.zhuye.huochebangsiji.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhuye.huochebangsiji.R;
import com.zhuye.huochebangsiji.adapter.MoaneyHomeAdapter2;
import com.zhuye.huochebangsiji.base.BaseActivity;
import com.zhuye.huochebangsiji.bean.MoneyManBean;
import com.zhuye.huochebangsiji.data.http.CommonApi;
import com.zhuye.huochebangsiji.utils.SharedPreferencesUtil;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MoneyMangerActivity extends BaseActivity {


    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.shouzhijilu)
    TextView shouzhijilu;
    @BindView(R.id.yuezhen)
    TextView yuezhen;
    @BindView(R.id.jiaoyijilu)
    RecyclerView jiaoyijilu;
    @BindView(R.id.tixian)
    Button tixian;

    @Override
    protected int getResId() {
        return R.layout.activity_money_manger;
    }

    MoaneyHomeAdapter2 adapter;
    protected void initView() {
        super.initView();
        adapter = new MoaneyHomeAdapter2(R.layout.money_home_item);
        jiaoyijilu.setAdapter(adapter);
        jiaoyijilu.setLayoutManager(new LinearLayoutManager(MoneyMangerActivity.this));
    }
    @Override
    protected void initData() {
        super.initData();

        CommonApi.getInstance().moneymanage(SharedPreferencesUtil.getInstance().getString("token")).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MoneyManBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i("as", d.toString());
                    }

                    @Override
                    public void onNext(@NonNull MoneyManBean code) {
                        Log.i("as", code.toString());
                        if (code.getCode() == 200) {
                            yuezhen.setText(code.getData().getMoney());
                            adapter.addData(code.getData().getLog());
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


    @OnClick({R.id.back, R.id.shouzhijilu, R.id.tixian})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.shouzhijilu:
                startActivity(new Intent(MoneyMangerActivity.this,ShouzhijulvActivity.class));
                break;
            case R.id.tixian:
                Intent intent = new Intent(MoneyMangerActivity.this,TiXianActivity.class);
                startActivity(intent);
                break;
        }
    }


}
