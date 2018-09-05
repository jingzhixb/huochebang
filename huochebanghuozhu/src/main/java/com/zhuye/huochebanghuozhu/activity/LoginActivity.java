package com.zhuye.huochebanghuozhu.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;
import com.zhuye.huochebanghuozhu.MainActivity;
import com.zhuye.huochebanghuozhu.R;
import com.zhuye.huochebanghuozhu.base.BaseActivity;
import com.zhuye.huochebanghuozhu.bean.Base;
import com.zhuye.huochebanghuozhu.bean.PhoneLoginCde;
import com.zhuye.huochebanghuozhu.data.GetData;
import com.zhuye.huochebanghuozhu.utils.CheckUtil;
import com.zhuye.huochebanghuozhu.utils.SharedPreferencesUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity  implements PermissionListener{


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
//                .subscribe(new Observer<Code>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//                        Log.i("as",d.toString());
//                    }
//
//                    @Override
//                    public void onNext(@NonNull Code code) {
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
        if (!AndPermission.hasPermission(LoginActivity.this
                , Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.CALL_PHONE,Manifest.permission.CAMERA,Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            AndPermission.with(LoginActivity.this)
                    .requestCode(100)
                    .permission(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.CALL_PHONE,Manifest.permission.CAMERA,Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .rationale(new RationaleListener() {
                        @Override
                        public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
                            AndPermission.rationaleDialog(LoginActivity.this, rationale).show();
                        }
                    })
                    .send();
        }
        // TODO: 2018/1/12 0012
        if (SharedPreferencesUtil.getInstance().getString("token") != null && !TextUtils.isEmpty(SharedPreferencesUtil.getInstance().getString("token"))) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
        addActivity(this);
    }
    String mobil;
    private void loginwithcode() {
        mobil = phone.getText().toString().trim();
        String passwor = password.getText().toString().trim();
        if (isEmpty(phone) && isEmpty(password)) {
            toast("手机号或验证码不能为空");
            return;
        }
        if(!CheckUtil.isMobile(mobil)){
            toast("手机号格式错误");
            return;
        }

        GetData.codelogin(mobil,passwor,LoginActivity.this,LOGIN);

//        CommonApi.getInstance().codelogin(mobil, passwor).subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<PhoneLoginCde>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//                        Log.i("as", d.toString());
//                    }
//
//                    @Override
//                    public void onNext(@NonNull PhoneLoginCde code) {
//                        Log.i("as", code.toString());
//                        if (code.getCode() == 200) {
////                            SharedPreferencesUtil.getInstance().putString("token", code.getData().getToken());
////                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
////                            finish();
//                            handleData(code);
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

    @Override
    public void success(int requestcode, Object o) {
        super.success(requestcode, o);
        switch (requestcode){
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
        String token = code.getData().getToken();
        if(code.getData().getMessage()==0){
            Intent in = new Intent(LoginActivity.this, Regeist1Activity.class);
            in.putExtra("token",token);
            in.putExtra("tpe",0);
            startActivity(in);
        }else if(code.getData().getMessage()==1){
            if(code.getData().getAudit().equals("2")){
                Intent in = new Intent(LoginActivity.this, Regeist1Activity.class);
                in.putExtra("token",token);
                in.putExtra("tpe",1);
                startActivity(in);
            }else if(code.getData().getAudit().equals("1")){
                SharedPreferencesUtil.getInstance().putString("token",code.getData().getToken());
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("mobile",mobil);
                startActivity(intent);
                finish();
            }else if(code.getData().getAudit().equals("0")){
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
       // super.onBackPressed();
       // exit();
        //System.exit(1);
//        exit();
        finish();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[]
            grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        /**
         * 转给AndPermission分析结果。
         *
         * @param requestCode  请求码。
         * @param permissions  权限数组，一个或者多个。
         * @param grantResults 请求结果。
         * @param listener PermissionListener 对象。
         */
        if (requestCode == 100) {
            AndPermission.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
        }

    }
    @Override
    public void onSucceed(int requestCode, List<String> grantPermissions) {

    }

    @Override
    public void onFailed(int requestCode, List<String> deniedPermissions) {

    }
}
