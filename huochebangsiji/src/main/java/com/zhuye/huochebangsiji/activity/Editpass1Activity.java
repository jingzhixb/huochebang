package com.zhuye.huochebangsiji.activity;

import android.content.Intent;
import android.os.CountDownTimer;
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
import com.zhuye.huochebangsiji.data.GetData;
import com.zhuye.huochebangsiji.data.http.CommonApi;
import com.zhuye.huochebangsiji.utils.SharedPreferencesUtil;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class Editpass1Activity extends BaseActivity {


    private static final int GETCODE = 10;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.go)
    ImageView go;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.huaquyanzhengma)
    TextView huaquyanzhengma;
    @BindView(R.id.next)
    Button next;

    @Override
    protected int getResId() {
        return R.layout.activity_editpass1;
    }


    String mobile;
    @Override
    protected void initData() {
        super.initData();
        mobile  =  SharedPreferencesUtil.getInstance().getString("mobile");
        phone.setText(mobile);
    }


    @OnClick({R.id.back, R.id.go, R.id.huaquyanzhengma, R.id.next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.go:
                // 处理
                break;
            case R.id.huaquyanzhengma:
                huoquyanzhengma();
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
                break;
            case R.id.next:

                handleda();
                break;
        }
    }

    private void handleda() {

//        final String mobile =  phone.getText().toString().trim();
//        if(isEmpty(mobile)){
//            toast("手机号不能为空");
//            return;
//        }

        String code = password.getText().toString().trim();
        if(isEmpty(code)){
            toast("验证码不能为空");
            return;
        }

        CommonApi.getInstance().forget_code(mobile,code).subscribeOn(io.reactivex.schedulers.Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Code>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }
                    @Override
                    public void onNext(@NonNull Code code) {
                        if(code.getCode() == 200){
                            toast(code.getMessage());
                            Intent intent = new Intent(Editpass1Activity.this,EditPass2Activity.class);
                            intent.putExtra("mobile",mobile);
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
    }

    private void huoquyanzhengma() {
//        String mobile =  phone.getText().toString().trim();
//        if(isEmpty(mobile)){
//            toast("手机号不能为空");
//            return;
//        }

        GetData.getCode(mobile,Editpass1Activity.this,GETCODE);
//        CommonApi.getInstance().getCode(mobile).subscribeOn(io.reactivex.schedulers.Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<Base>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(@NonNull Base code) {
//                        if(code.getCode() == 200){
//                            toast(code.getMessage());
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


    @Override
    public void success(int requestcode, Object o) {
        super.success(requestcode, o);
        switch (requestcode){
            case GETCODE:
                Base base = (Base) o;
                toast(base.getMessage());
                break;
        }
    }
}
