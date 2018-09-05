package com.zhuye.huochebanghuozhu.activity;

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
import com.zhuye.huochebanghuozhu.utils.CheckUtil;
import com.zhuye.huochebanghuozhu.utils.SharedPreferencesUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class ChangeMobile2Activity extends BaseActivity {

    private static final int GETCODE = 11;
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

    private static final int YANZHENG =  10 ;
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

    private void huoqu() {
        String mobile =  shoujihao.getText().toString().trim();
        if(isEmpty(mobile)){
            toast("手机号不能为空");
            return;
        }

        if(!CheckUtil.isMobile(mobile)){
            toast("手机号格式有误");
            return;
        }

        GetData.getCode(mobile,ChangeMobile2Activity.this,GETCODE);
    }

    @Override
    public void success(int requestcode, Object o) {
        super.success(requestcode, o);
        switch (requestcode){
            case YANZHENG:
                Code messag = (Code) o;
                toast(messag.getMessage());
                break;
            case GETCODE:
                Base messa = (Base) o;
                toast(messa.getMessage());
                break;
        }
    }

}
