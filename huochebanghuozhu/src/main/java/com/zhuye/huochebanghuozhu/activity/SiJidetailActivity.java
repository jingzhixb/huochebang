package com.zhuye.huochebanghuozhu.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhuye.huochebanghuozhu.R;
import com.zhuye.huochebanghuozhu.base.BaseActivity;
import com.zhuye.huochebanghuozhu.bean.DriverDetailBean;
import com.zhuye.huochebanghuozhu.data.GetData;
import com.zhuye.huochebanghuozhu.utils.SharedPreferencesUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class SiJidetailActivity extends BaseActivity {


    private static final int DETAIL = 10;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.chepai)
    TextView chepai;
    @BindView(R.id.chexing)
    TextView chexing;
    @BindView(R.id.state)
    TextView state;
    @BindView(R.id.xingming)
    TextView xingming;
    @BindView(R.id.mobile)
    TextView mobile;
    @BindView(R.id.duanxin)
    Button duanxin;
    @BindView(R.id.call)
    Button call;

    @Override
    protected int getResId() {
        return R.layout.activity_si_jidetail;
    }


    String mobiles;
    String jisi_id;
    String car_id;
    String type;
    String m;

    @Override
    protected void initView() {
        super.initView();
        mobiles = getIntent().getStringExtra("mobile");
        car_id = getIntent().getStringExtra("car_id");
        jisi_id = getIntent().getStringExtra("jisi_id");
        type = getIntent().getStringExtra("type");
        m = getIntent().getStringExtra("models");
        chexing.setText(m);
        if(type.equals("0")){
            state.setText("空闲");
        }else {
            state.setText("出车中");
        }
//        mobile.setText(mobiles);
    }

    @Override
    protected void initData() {
        super.initData();
        GetData.driver_detail(SharedPreferencesUtil.getInstance().getString("token"), Integer.parseInt(jisi_id), Integer.parseInt(car_id), this, DETAIL);
    }

    DriverDetailBean bean;

    @Override
    public void success(int requestcode, Object o) {
        super.success(requestcode, o);
        switch (requestcode) {
            case DETAIL:
                bean = (DriverDetailBean) o;
                mobile.setText(bean.getData().getMobile());
                chepai.setText(bean.getData().getLicense());
                xingming.setText(bean.getData().getName());
                break;
        }
    }

    @OnClick({R.id.back, R.id.duanxin, R.id.call})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.duanxin:
                faxiaoxi();
                break;
            case R.id.call:
                call();
                break;
        }
    }

    private void call() {
        //Uri uri = Uri.parse("tel:"+1008611);
        Intent intent = new Intent(Intent.ACTION_CALL, Uri
                .parse("tel:" + bean.getData().getMobile()));
        // 如果需要将内容传过去增加如下代码
        //intent.putExtra("sms_body", "");
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(intent);
    }

    private void faxiaoxi() {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri
                .parse("smsto:" + bean.getData().getMobile()));
        // 如果需要将内容传过去增加如下代码
        intent .putExtra("sms_body", "");
        startActivity(intent);
    }
}
