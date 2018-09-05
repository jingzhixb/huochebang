package com.zhuye.huochebang.ui;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhuye.huochebang.BaseActivity;
import com.zhuye.huochebang.R;
import com.zhuye.huochebang.adapter.car.SingerCarAdapter;
import com.zhuye.huochebang.adapter.car.SingerCarAdapter2;
import com.zhuye.huochebang.bean.Code;
import com.zhuye.huochebang.bean.DriverListBean;
import com.zhuye.huochebang.bean.HomeCarInfoBean;
import com.zhuye.huochebang.data.http.CommonApi;
import com.zhuye.huochebang.data.http.GetData;
import com.zhuye.huochebang.utils.SharedPreferencesUtil;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class SingerCarMangerActivity extends BaseActivity {

    private static final int GETINFO = 15;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.chepai)
    TextView chepai;
    @BindView(R.id.state)
    TextView state;
    @BindView(R.id.chechang)
    TextView chechang;
    @BindView(R.id.chexing)
    TextView chexing;
    @BindView(R.id.number)
    TextView number;
    @BindView(R.id.siji)
    RecyclerView siji;
    @BindView(R.id.next)
    Button next;

    @Override
    protected int getResId() {
        return R.layout.activity_singer_car_manger;
    }

    SingerCarAdapter2 mSingerCarAdapter2 ;

    int car_id;
    @Override
    protected void initView() {
        super.initView();

        String chexing1 = getIntent().getStringExtra("chexing");
        String checha = getIntent().getStringExtra("chechang");
        String xinghao = getIntent().getStringExtra("xinghao");
        car_id= getIntent().getIntExtra("car_id",0);
        String type = getIntent().getStringExtra("type");
        chepai.setText(xinghao);
        chechang.setText(checha);
        chexing.setText(chexing1);
        if(type.equals("0")){
            state.setText("空闲中");
        }else if(type.equals("1")){
            state.setText("出车中");
        }
        mSingerCarAdapter2 = new SingerCarAdapter2(R.layout.singer_man_item);
       // adapter = new SingerCarAdapter(SingerCarMangerActivity.this);
        siji.setAdapter(mSingerCarAdapter2);
        siji.setLayoutManager(new LinearLayoutManager(SingerCarMangerActivity.this));
    }
    SingerCarAdapter adapter;


    @Override
    protected void initListener() {
        super.initListener();

//        adapter.setOnItemClickListener(new BaseHolder.OnItemClickListener() {
//            @Override
//            public void OnItemClick(View view, final int position) {
//
//                view.findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        deletedriver(position);
//                    }
//                });
//            }
//        });
//
        mSingerCarAdapter2.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.delete:
                        deletedriver(position);
                        break;
                }
            }
        });
    }

    private void deletedriver(final int position) {
        CommonApi.getInstance().del_driver(SharedPreferencesUtil.getInstance().getString("token"),Integer.parseInt(data.getData().get(position).getDriver_id())).subscribeOn(io.reactivex.schedulers.Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Code>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i("as",d.toString());
                    }

                    @Override
                    public void onNext(@NonNull Code code) {
                        Log.i("as",code.toString());

                        if(code.getCode() == 200){
                            // toast(code.getMessage());
                            // finish();
                            carInfoBean.getData().get(0).getDriver().remove(position);
                            mSingerCarAdapter2.clear();
                            mSingerCarAdapter2.addData(carInfoBean.getData().get(0).getDriver());
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i("as",e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    HomeCarInfoBean carInfoBean;
    @Override
    public void success(int requestcode, Object o) {
        super.success(requestcode, o);
       switch (requestcode){
           case GETINFO:
               carInfoBean = (HomeCarInfoBean) o;
               number.setText(carInfoBean.getData().size()+"个");
               //mData.addAll(carInfoBean.getData());
             //  adapter.addData(mData);
               mSingerCarAdapter2.addData(carInfoBean.getData().get(0).getDriver());
               break;
       }
    }

    DriverListBean data;
    @Override
    protected void initData() {
        super.initData();

        GetData.homecarinfo(SharedPreferencesUtil.getInstance().getString("token"),page,SingerCarMangerActivity.this,GETINFO);
//        CommonApi.getInstance().homecainvite_driverrinfo(SharedPreferencesUtil.getInstance().getString("token"),car_id).subscribeOn(io.reactivex.schedulers.Schedulers.newThread())
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
//                        data = code;
//                        number.setText(code.getData().size()+"个");
//                        if(code.getCode() == 200){
//                           // toast(code.getMessage());
//                           // finish();
//                            //adapter.addData(code.getData());
//                            mSingerCarAdapter2.addData(code.getData());
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

    @OnClick({R.id.back, R.id.next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.next:
                Intent intent = new Intent(SingerCarMangerActivity.this,YanQingSiJIActivity.class);
                intent.putExtra("car_id",car_id);
                startActivity(intent);
                break;
        }
    }
}
