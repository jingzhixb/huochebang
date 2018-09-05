package com.zhuye.huochebanghuozhu.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhuye.huochebanghuozhu.R;
import com.zhuye.huochebanghuozhu.base.BaseActivity;
import com.zhuye.huochebanghuozhu.bean.Base;
import com.zhuye.huochebanghuozhu.bean.Code;
import com.zhuye.huochebanghuozhu.data.GetData;
import com.zhuye.huochebanghuozhu.data.http.CommonApi;
import com.zhuye.huochebanghuozhu.utils.CheckUtil;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class ChangeMobileActivity extends BaseActivity {


    private static final int GETCODE = 10;
    private static final int YANZHENG = 11;
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

    private void fasongyanzhengma() {

        mobi = shoujihao.getText().toString().trim();
        if(isEmpty(mobi)){
            toast("手机号不能为空");
            return;
        }
        if(!CheckUtil.isMobile(mobi)){
            toast("手机号格式错误");
            return;
        }

        String mobile = shoujihao.getText().toString().trim();
        GetData.getCode(mobile,ChangeMobileActivity.this,GETCODE);
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
                new CountDownTimer(59000, 1000) {
                    public void onTick(long millisUntilFinished) {
//                        LogUtil.i(TAG, "seconds remaining: " + millisUntilFinished / 1000);
                        huaquyanzhengma.setText("还有"+millisUntilFinished / 1000+"秒");
                        huaquyanzhengma.setEnabled(false);
                        huaquyanzhengma.setTextColor(getResources().getColor(R.color.dindansnor));
                    }
                    public void onFinish() {
                        huaquyanzhengma.setText("重新发送");
                        huaquyanzhengma.setEnabled(true);
                        huaquyanzhengma.setTextColor(getResources().getColor(R.color.colorPrimary));
                        //huaquyanzhengma.setBackgroundResource(R.drawable.btn_bg);
                    }

                }.start();



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



    @Override
    public void success(int requestcode, Object o) {
        super.success(requestcode, o);
        switch (requestcode){
            case YANZHENG:

                break;
            case GETCODE:
                Base messa = (Base) o;
                toast(messa.getMessage());
                break;
        }
    }

}
