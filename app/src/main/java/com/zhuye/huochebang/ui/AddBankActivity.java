package com.zhuye.huochebang.ui;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.library.BandCardEditText;
import com.zhuye.huochebang.BaseActivity;
import com.zhuye.huochebang.R;
import com.zhuye.huochebang.bean.Base;
import com.zhuye.huochebang.data.http.GetData;
import com.zhuye.huochebang.utils.CheckUtil;
import com.zhuye.huochebang.utils.SharedPreferencesUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class AddBankActivity extends BaseActivity {


    private static final int GETCODE = 10;
    private static final int TIANJIA = 11 ;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.bank)
    TextView bank;
    @BindView(R.id.kahao)
    BandCardEditText kahao;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.zhenfen)
    EditText zhenfen;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.code)
    EditText code;
    @BindView(R.id.huaquyanzhengma)
    TextView huaquyanzhengma;
    @BindView(R.id.bangding)
    Button bangding;

    @Override
    protected int getResId() {
        return R.layout.activity_add_bank;
    }

    @Override
    protected void initListener() {
        super.initListener();
        kahao.setBankCardListener(new BandCardEditText.BankCardListener() {
            @Override
            public void success(String name) {
                bank.setText(name);
            }

            @Override
            public void failure() {

            }
        });
    }


    @Override
    public void success(int requestcode, Object o) {
        super.success(requestcode, o);
        switch (requestcode){
            case GETCODE:
                Base base = (Base) o;
                toast(base.getMessage());
                break;
            case TIANJIA:
                Base bas = (Base) o;
                toast(bas.getMessage());
                finish();
                break;
        }
    }

    @OnClick({R.id.back, R.id.huaquyanzhengma, R.id.bangding})
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
                if(!CheckUtil.isMobile(mobile)){
                    toast("手机号格式错误");
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
                //GetData.getCode(mobile,AddBankActivity.this,GETCODE);
                GetData.getCode(mobile,AddBankActivity.this,GETCODE);
                break;
            case R.id.bangding:
                // TODO: 2018/1/8 0008 处理验证码


                tijiao();
                break;
        }
    }



    private void tijiao() {
        if(isEmpty(kahao)){
            toast("请输入银行卡");
            return;
        }

        if(isEmpty(name)){
            toast("请输入姓名");
            return;
        }
        if(isEmpty(zhenfen)){
            toast("请输入省份证号码");
            return;
        }
        if(isEmpty(phone)){
            toast("请输入手机号");
            return;
        }

        if(isEmpty(code)){
            toast("请输入验证码");
            return;
        }

        GetData.add_cash(SharedPreferencesUtil.getInstance().getString("token"),getString(kahao),bank.getText().toString().trim(),getString(name),
                AddBankActivity.this,TIANJIA);

//        CommonApi.getInstance().add_cash(SharedPreferencesUtil.getInstance().getString("token"),getString(kahao),bank.getText().toString().trim(),getString(name)).subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<Code>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//                        Log.i("as", d.toString());
//                    }
//
//                    @Override
//                    public void onNext(@NonNull Code code) {
//                        Log.i("as", code.toString());
//                        if (code.getCode() == 200) {
//                        }
//                        toast(code.getMessage());
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
}
