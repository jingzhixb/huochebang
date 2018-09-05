package com.zhuye.huochebanghuozhu.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.zhuye.huochebanghuozhu.R;
import com.zhuye.huochebanghuozhu.base.BaseActivity;
import com.zhuye.huochebanghuozhu.bean.Code;
import com.zhuye.huochebanghuozhu.data.http.CommonApi;
import com.zhuye.huochebanghuozhu.utils.SharedPreferencesUtil;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class EditPass2Activity extends BaseActivity {


    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.repass)
    EditText repass;
    @BindView(R.id.complect)
    Button complect;

    @Override
    protected int getResId() {
        return R.layout.activity_edit_pass2;
    }


    @Override
    protected void initView() {
        super.initView();

        mobile =  getIntent().getStringExtra("mobile");
    }

    String mobile;
    @OnClick({R.id.back, R.id.complect})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.complect:
                editpass();
                break;
        }
    }

    private void editpass() {
        if(isEmpty(password) || isEmpty(repass)){
            toast("密码不能为空");
            return;
        }
        if(!getString(password).equals(getString(repass))){
            toast("密码不一致");
            return;
        }

        CommonApi.getInstance().forget_ms(mobile,getString(password)).subscribeOn(io.reactivex.schedulers.Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Code>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }
                    @Override
                    public void onNext(@NonNull Code code) {
                        if(code.getCode() == 200){
                            toast(code.getMessage());
                            SharedPreferencesUtil.getInstance().putString("token","");
                            startActivity(new Intent(EditPass2Activity.this,LoginActivity.class));
                        }else if(code.getCode() == 201){
                            toast(code.getMessage());
                        }else if(code.getCode() == 203){
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
