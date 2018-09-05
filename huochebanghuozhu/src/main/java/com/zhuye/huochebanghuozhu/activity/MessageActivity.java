package com.zhuye.huochebanghuozhu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhuye.huochebanghuozhu.R;
import com.zhuye.huochebanghuozhu.base.BaseActivity;
import com.zhuye.huochebanghuozhu.bean.NewsNumBean;
import com.zhuye.huochebanghuozhu.data.GetData;
import com.zhuye.huochebanghuozhu.data.http.CommonApi;
import com.zhuye.huochebanghuozhu.utils.SharedPreferencesUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MessageActivity extends BaseActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.tou)
    RelativeLayout tou;
    @BindView(R.id.image1)
    ImageView image1;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.xitongmessage)
    RelativeLayout xitongmessage;
    @BindView(R.id.image2)
    ImageView image2;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.liuyanmessage)
    RelativeLayout liuyanmessage;
    @BindView(R.id.xitongnum)
    TextView xitongnum;
    @BindView(R.id.liuyannum)
    TextView liuyannum;

    @Override
    protected int getResId() {
        return R.layout.activity_message;
    }

    @OnClick({R.id.back, R.id.xitongmessage, R.id.liuyanmessage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.xitongmessage:
                startActivity(new Intent(MessageActivity.this, SysttemMessageActivity.class));
                break;
            case R.id.liuyanmessage:
                startActivity(new Intent(MessageActivity.this, LiuYanMessageActivity.class));
                break;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        CommonApi.getInstance().news_num(SharedPreferencesUtil.getInstance().getString("token")).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsNumBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull NewsNumBean code) {
                        Log.i("as", code.toString());
                        if (code.getCode() == 200) {
                            int  n1 = Integer.parseInt(code.getData().getSystem_num());
                            if(n1==0){
                                xitongnum.setVisibility(View.GONE);
                            }else {
                                xitongnum.setVisibility(View.VISIBLE);
                                xitongnum.setText(n1+"");
                            }

                            int  n2 = Integer.parseInt(code.getData().getNews_num());
                            if(n2==0){
                                liuyannum.setVisibility(View.GONE);
                            }else {
                                liuyannum.setVisibility(View.VISIBLE);
                                liuyannum.setText(n2+"");
                            }
                        }else {

                        }

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i("as", e.toString());
                        //meFragmentView.error();
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
}
