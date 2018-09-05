package com.zhuye.huochebanghuozhu.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zhuye.huochebanghuozhu.MainActivity;
import com.zhuye.huochebanghuozhu.R;
import com.zhuye.huochebanghuozhu.base.BaseActivity;
import com.zhuye.huochebanghuozhu.bean.PhoneLoginCde;
import com.zhuye.huochebanghuozhu.data.GetData;
import com.zhuye.huochebanghuozhu.utils.CheckUtil;
import com.zhuye.huochebanghuozhu.utils.MD5Utils;
import com.zhuye.huochebanghuozhu.utils.SharedPreferencesUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginpasswordActivity extends BaseActivity {

    private static final int PASSLOGIN = 111;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.denglu)
    Button denglu;
    @BindView(R.id.forgetpass)
    TextView forgetpass;
    @BindView(R.id.regeist)
    TextView regeist;
    @BindView(R.id.loginpassqord)
    TextView loginpassqord;


    @Override
    protected int getResId() {
        return R.layout.activity_loginpassword;
    }

    @OnClick({R.id.denglu, R.id.forgetpass, R.id.regeist, R.id.loginpassqord})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.denglu:
                login();
                break;
            case R.id.forgetpass:
                startActivity(new Intent(LoginpasswordActivity.this,Editpass1Activity.class));
                break;
            case R.id.regeist:
                startActivity(new Intent(LoginpasswordActivity.this,Regeist2Activity.class));
                break;
            case R.id.loginpassqord:
                startActivity(new Intent(LoginpasswordActivity.this,LoginActivity.class));
                finish();
                break;
        }
    }
    String mobil;
    private void login() {

        mobil = phone.getText().toString().trim();
        String passwor = password.getText().toString().trim();
        if(isEmpty(phone)  &&isEmpty(password)){
            toast("手机号或密码不能为空");
            return;
        }

        if(!CheckUtil.isMobile(mobil)){
            toast("手机号格式错误");
            return;
        }

//        OkGo.<String>post(Contans.BASE_URL+"/index.php/home/passport/login")
//                .params("mobile",mobil)
//                .params("password",passwor)
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(Response<String> response) {
//                        Log.i("as",response.toString());
//                    }
//
//                    @Override
//                    public void onError(Response<String> response) {
//                        super.onError(response);
//                        Log.i("as",response.toString());
//                    }
//                });


        passwor = MD5Utils.MD5(passwor);
        GetData.passLogin(mobil,passwor,LoginpasswordActivity.this,PASSLOGIN);

//        CommonApi.getInstance().passLogin(mobil,passwor).subscribeOn(io.reactivex.schedulers.Schedulers.newThread())
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
//                            // toast(code.getData().getToken());
//                            //// TODO: 2018/1/3 0003  登录成功是没有处理第一个等绿叶
//                            handleData(code);
//
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
            case PASSLOGIN:
                handleData((PhoneLoginCde) o);
                break;
        }
    }

    private void handleData(PhoneLoginCde code) {
        String token = code.getData().getToken();
        if(code.getData().getMessage()==0){
            Intent in = new Intent(LoginpasswordActivity.this, Regeist1Activity.class);
            in.putExtra("token",token);
            in.putExtra("tpe",0);
            startActivity(in);
        }else if(code.getData().getMessage()==1){
            if(code.getData().getAudit().equals("2")){
                Intent in = new Intent(LoginpasswordActivity.this, Regeist1Activity.class);
                in.putExtra("token",token);
                in.putExtra("tpe",1);
                startActivity(in);
            }else if(code.getData().getAudit().equals("1")){
                SharedPreferencesUtil.getInstance().putString("token",code.getData().getToken());
                Intent intent = new Intent(LoginpasswordActivity.this, MainActivity.class);
                intent.putExtra("mobile",mobil);
                startActivity(intent);
                finish();
            }else if(code.getData().getAudit().equals("0")){
                toast("审核中");
            }
        }
    }
}
