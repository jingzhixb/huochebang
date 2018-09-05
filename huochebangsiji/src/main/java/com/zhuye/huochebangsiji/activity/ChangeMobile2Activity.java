package com.zhuye.huochebangsiji.activity;

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


public class ChangeMobile2Activity extends BaseActivity {


    private static final int YANZHENG =  10 ;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.shoujihao)
    EditText shoujihao;
    @BindView(R.id.yanzhengm)
    EditText yanzhengm;
    @BindView(R.id.huaquyanzhengma)
    TextView huaquyanzhengma;
    @BindView(R.id.queding)
    Button queding;

    @Override
    protected int getResId() {
        return R.layout.activity_change_mobile2;
    }



    @OnClick({R.id.back, R.id.huaquyanzhengma, R.id.queding})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.huaquyanzhengma:
                huoqu();
                break;
            case R.id.queding:

                String yanzheng = yanzhengm.getText().toString().trim();
                final String mobil = shoujihao.getText().toString().trim();
                if(isEmpty(mobil)){
                    toast("手机号不能为空");
                    return;
                }

                if(isEmpty(yanzheng)){
                    toast("验证码不能为空");
                    return;
                }

                GetData.new_mobile(SharedPreferencesUtil.getInstance().getString("token"),mobil,yanzheng,ChangeMobile2Activity.this,YANZHENG);

                break;
        }
    }


    @Override
    public void success(int requestcode, Object o) {
        super.success(requestcode, o);
            Code messag = (Code) o;
            toast(messag.getMessage());

    }

    private void huoqu() {

        String mobile =  shoujihao.getText().toString().trim();
        if(isEmpty(mobile)){
            toast("手机号不能为空");
            return;
        }
        CommonApi.getInstance().getCode(mobile).subscribeOn(io.reactivex.schedulers.Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Base>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Base code) {
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
