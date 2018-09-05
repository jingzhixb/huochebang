package com.zhuye.huochebangsiji.activity;


import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhuye.huochebangsiji.R;
import com.zhuye.huochebangsiji.base.BaseActivity;
import com.zhuye.huochebangsiji.bean.Base;
import com.zhuye.huochebangsiji.bean.Code;
import com.zhuye.huochebangsiji.data.http.CommonApi;
import com.zhuye.huochebangsiji.utils.CheckUtil;
import com.zhuye.huochebangsiji.utils.DaojinUtils;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class ChangeMobileActivity extends BaseActivity {


    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.shoujihao)
    EditText shoujihao;
    @BindView(R.id.qian)
    ImageView qian;
    @BindView(R.id.yanzhengm)
    EditText yanzhengm;
    @BindView(R.id.huaquyanzhengma)
    TextView huaquyanzhengma;
    @BindView(R.id.zhecu)
    Button zhecu;



    @Override
    protected int getResId() {
        return R.layout.activity_change_mobile;

    }
    String  mobi;

    @Override
    protected void initData() {
        super.initData();
        mobi = getIntent().getStringExtra("mobile");
        shoujihao.setText(mobi);
    }

    @OnClick({R.id.back, R.id.qian, R.id.huaquyanzhengma, R.id.zhecu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.qian:
                break;
            case R.id.huaquyanzhengma:
                DaojinUtils.daojiShi(ChangeMobileActivity.this,huaquyanzhengma);
                fasongyanzhengma();
                break;
            case R.id.zhecu:
                String yanzheng = yanzhengm.getText().toString().trim();
              mobi = shoujihao.getText().toString().trim();
                if(isEmpty(mobi)){
                    toast("手机号不能为空");
                    return;
                }

                if(isEmpty(yanzheng)){
                    toast("验证码不能为空");
                    return;
                }

                if(!CheckUtil.isMobile(mobi)){
                    toast("手机号格式不正确");
                    return;
                }

                CommonApi.getInstance().forget_code(mobi,yanzheng).subscribeOn(io.reactivex.schedulers.Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Code>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }
                            @Override
                            public void onNext(@NonNull Code code) {
                                if(code.getCode() == 200){
                                    toast(code.getMessage());
                                    Intent intent = new Intent(ChangeMobileActivity.this,ChangeMobile2Activity.class);
                                    startActivity(intent);
                                }else if(code.getCode() == 201){
                                    toast(code.getMessage());
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
                break;
        }
    }

    private void fasongyanzhengma() {
        String mobile = shoujihao.getText().toString().trim();
        CommonApi.getInstance().getCode(mobile).subscribeOn(io.reactivex.schedulers.Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Base>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i("as",d.toString());
                    }

                    @Override
                    public void onNext(@NonNull Base code) {
                        Log.i("as",code.toString());
                        if(code.getCode() == 200){
                            toast(code.getMessage());
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
}
