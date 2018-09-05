package com.zhuye.huochebangsiji.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhuye.huochebangsiji.R;
import com.zhuye.huochebangsiji.base.BaseActivity;
import com.zhuye.huochebangsiji.bean.Base;
import com.zhuye.huochebangsiji.bean.RegeiserCode;
import com.zhuye.huochebangsiji.data.GetData;
import com.zhuye.huochebangsiji.utils.MD5Utils;

import butterknife.BindView;
import butterknife.OnClick;

public class Regeist2Activity extends BaseActivity {

    private static final int GETCODE = 10 ;
    private static final int ZHUCE = 11;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.yanzhengma)
    EditText yanzhengma;
    @BindView(R.id.huaquyanzhengma)
    TextView huaquyanzhengma;
    @BindView(R.id.zhecu)
    Button zhecu;


    @Override
    protected int getResId() {
        return R.layout.activity_regeist2;
    }



    @OnClick({R.id.back, R.id.huaquyanzhengma, R.id.zhecu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.huaquyanzhengma:

                String mobile =  phone.getText().toString().trim();
                if(isEmpty(mobile)){
                    toast("手机号不能为空");
                    return;
                }

                huaquyanzhengma.setEnabled(false);

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

                GetData.getCode(mobile,Regeist2Activity.this,GETCODE);
//                CommonApi.getInstance().getCode(mobile).subscribeOn(io.reactivex.schedulers.Schedulers.newThread())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new Observer<Base>() {
//                            @Override
//                            public void onSubscribe(@NonNull Disposable d) {
//                                Log.i("as",d.toString());
//                            }
//
//                            @Override
//                            public void onNext(@NonNull Base code) {
//                                Log.i("as",code.toString());
//                                if(code.getCode() == 200){
//                                    toast(code.getMessage());
//                                }
//                            }
//
//                            @Override
//                            public void onError(@NonNull Throwable e) {
//                                Log.i("as",e.toString());
//                            }
//
//                            @Override
//                            public void onComplete() {
//
//                            }
//                        });
                break;
            case R.id.zhecu:
              String yanzheng = yanzhengma.getText().toString().trim();
              String mobil = phone.getText().toString().trim();
              String passwor = password.getText().toString().trim();
              if(isEmpty(phone)  &&isEmpty(password)){
                  toast("手机号或密码不能为空");
                  return;
              }

              if(isEmpty(yanzhengma)){
                  toast("请输入验证码");
                  return;
              }

                passwor = MD5Utils.MD5(passwor);
              GetData.getRegeister(mobil,passwor,yanzheng,Regeist2Activity.this,ZHUCE);
//                CommonApi.getInstance().getRegeister(mobil,passwor,yanzheng).subscribeOn(io.reactivex.schedulers.Schedulers.newThread())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new Observer<RegeiserCode>() {
//                            @Override
//                            public void onSubscribe(@NonNull Disposable d) {
//                                Log.i("as",d.toString());
//                            }
//
//                            @Override
//                            public void onNext(@NonNull RegeiserCode code) {
//                                Log.i("as",code.toString());
//                                if(code.getCode() == 200){
//                                   // toast(code.getData().getToken());
//                                   // SharedPreferencesUtil.getInstance().putString("token",code.getData().getToken());
//                                    startActivity(new Intent(Regeist2Activity.this, Regeist1Activity.class));
//                                    finish();
//                                }
//                            }
//
//                            @Override
//                            public void onError(@NonNull Throwable e) {
//
//                                Log.i("as",e.toString());
//                            }
//
//                            @Override
//                            public void onComplete() {
//
//                            }
//                        });

                break;
        }
    }

    Base base;
    @Override
    public void success(int requestcode, Object o) {
        super.success(requestcode, o);
        switch (requestcode){

            case GETCODE:
                base = (Base) o;
                toast(base.getMessage());
                break;

            case ZHUCE:
                RegeiserCode regeiserCode = (RegeiserCode) o;
                toast(regeiserCode.getMessage());
                Intent intent = new Intent(Regeist2Activity.this, Regeist1Activity.class);
                intent.putExtra("token",regeiserCode.getData().getToken());
                startActivity(intent);
                finish();
                break;

        }
    }
}
