package com.zhuye.huochebang.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zhuye.huochebang.BaseActivity;
import com.zhuye.huochebang.MainActivity;
import com.zhuye.huochebang.R;
import com.zhuye.huochebang.bean.Base;
import com.zhuye.huochebang.bean.PhoneLoginCde;
import com.zhuye.huochebang.data.http.GetData;
import com.zhuye.huochebang.utils.CheckUtil;
import com.zhuye.huochebang.utils.SharedPreferencesUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {


    private static final int LOGIN = 10;
    private static final int GETCODE = 11;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.denglu)
    Button denglu;
    @BindView(R.id.regeist)
    TextView regeist;
    @BindView(R.id.loginpassqord)
    TextView loginpassqord;
    @BindView(R.id.huaquyanzhengma)
    TextView huaquyanzhengma;

    @Override
    protected int getResId() {
        return R.layout.activity_login;
    }

    @OnClick({R.id.denglu, R.id.regeist, R.id.loginpassqord,R.id.huaquyanzhengma})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.denglu:
                loginwithcode();
                break;
            case R.id.regeist:
                startActivity(new Intent(LoginActivity.this, Regeist2Activity.class));
                break;
            case R.id.loginpassqord:
                startActivity(new Intent(LoginActivity.this, LoginpasswordActivity.class));
                finish();
                break;
            case R.id.huaquyanzhengma:
                fasongyanzhengma();
                break;
        }
    }


    private void fasongyanzhengma() {
        String mobile = phone.getText().toString().trim();

        if(isEmpty(mobile)){
            toast("手机号不能为空");
            return;
        }

        if(!CheckUtil.isMobile(mobile)){
            toast("手机号格式错误");
            return;
        }

        huaquyanzhengma.setEnabled(false);
        new CountDownTimer(59000, 1000) {
            public void onTick(long millisUntilFinished) {
//                        LogUtil.i(TAG, "seconds remaining: " + millisUntilFinished / 1000);
                huaquyanzhengma.setText("还有"+millisUntilFinished / 1000+"秒");
                huaquyanzhengma.setTextColor(getResources().getColor(R.color.dindansnor));
            }
            public void onFinish() {
                huaquyanzhengma.setText("重新发送");
                huaquyanzhengma.setEnabled(true);
                huaquyanzhengma.setTextColor(getResources().getColor(R.color.colorPrimary));
                //huaquyanzhengma.setBackgroundResource(R.drawable.btn_bg);
            }

        }.start();

        GetData.getCode(mobile,LoginActivity.this,GETCODE);

//        CommonApi.getInstance().getCode(mobile).subscribeOn(io.reactivex.schedulers.Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<Base>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//                        Log.i("as",d.toString());
//                    }
//
//                    @Override
//                    public void onNext(@NonNull Base code) {
//                        Log.i("as",code.toString());
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
    protected void initView() {
        super.initView();
        if (SharedPreferencesUtil.getInstance().getString("token") != null && !TextUtils.isEmpty(SharedPreferencesUtil.getInstance().getString("token"))) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }
    String mobil;
    private void loginwithcode() {
        mobil = phone.getText().toString().trim();
        String passwor = password.getText().toString().trim();
        if (isEmpty(phone) && isEmpty(password)) {
            toast("手机号或验证码不能为空");
            return;
        }

        if (!CheckUtil.isMobile(mobil)) {
            toast("手机号格式错误");
            return;
        }


        GetData.codelogin(mobil, passwor, LoginActivity.this, LOGIN);

//        CommonApi.getInstance().codelogin(mobil,passwor).subscribeOn(io.reactivex.schedulers.Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<PhoneLoginCde>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//                        Log.i("as",d.toString());
//                    }
//
//                    @Override
//                    public void onNext(@NonNull PhoneLoginCde code) {
//                        Log.i("as",code.toString());
//                        if(code.getCode() == 200){
////                            SharedPreferencesUtil.getInstance().putString("token",code.getData().getToken());
////                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
////                            finish();
//                            handleData(code);
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
        switch (requestcode) {
            case LOGIN:
                handleData((PhoneLoginCde) o);
                break;

            case GETCODE:
                Base base = (Base) o;
                toast(base.getMessage());
                break;

        }
    }

    private void handleData(PhoneLoginCde code) {
        if (code.getData().getMessage() == 0) {
            Intent in = new Intent(LoginActivity.this, Regeist1Activity.class);
            in.putExtra("token",code.getData().getToken());
            in.putExtra("tpe", 0);
            startActivity(in);
        } else if (code.getData().getMessage() == 1) {
            if (code.getData().getAudit().equals("2")) {
                Intent in = new Intent(LoginActivity.this, Regeist1Activity.class);
                in.putExtra("tpe", 1);
                startActivity(in);
            } else if (code.getData().getAudit().equals("1")) {
                SharedPreferencesUtil.getInstance().putString("token", code.getData().getToken());
                SharedPreferencesUtil.getInstance().putString("mobile",mobil );
                SharedPreferencesUtil.getInstance().putString("encode",code.getData().getEncode() );
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            } else if (code.getData().getAudit().equals("0")) {
                toast("审核中");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        exit();
    }
}
