package com.zhuye.huochebangsiji.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhuye.huochebangsiji.R;
import com.zhuye.huochebangsiji.base.BaseActivity;
import com.zhuye.huochebangsiji.bean.Base;
import com.zhuye.huochebangsiji.bean.TiXianBean;
import com.zhuye.huochebangsiji.data.GetData;
import com.zhuye.huochebangsiji.utils.SharedPreferencesUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class TiXianActivity extends BaseActivity {


    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.bank)
    TextView bank;
    @BindView(R.id.go)
    ImageView go;
    @BindView(R.id.jine)
    EditText jine;
    @BindView(R.id.quanbu)
    TextView quanbu;
    @BindView(R.id.tixian)
    Button tixian;

    @Override
    protected int getResId() {
        return R.layout.activity_ti_xian;
    }

    TiXianBean ccode;
    @Override
    protected void initData() {
        super.initData();

        GetData.withdrawals(SharedPreferencesUtil.getInstance().getString("token")
                ,TiXianActivity.this,WITHDRAWALS);
    }
    private static final int TIXIANBA = 10;
    private static final int WITHDRAWALS = 11;



    @Override
    public void success(int requestcode, Object o) {
        super.success(requestcode, o);
        switch (requestcode){
            case TIXIANBA:
                Base base = (Base) o;
                toast(base.getMessage());
                break;

            case WITHDRAWALS:
                ccode = (TiXianBean) o;
                bank.setText(ccode.getData().getCard_name());
                jine.setText(ccode.getData().getMoney());
                break;
        }
    }


    @Override
    public void error(Object o) {
        super.error(o);
        Base base = (Base) o;
        toast(base.getMessage());

    }

    @OnClick({R.id.back, R.id.go, R.id.quanbu, R.id.tixian})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.go:
                tianjiazhanghao();
                break;
            case R.id.quanbu:

                break;
            case R.id.tixian:

                tixian();
                break;
        }
    }

    private void tianjiazhanghao() {
        startActivityForResult(new Intent(TiXianActivity.this,BankListActivity.class),1000);
    }
    Boolean ischag = false ;
    String card;
    String card_name;
    String cash_id;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //// TODO: 2018/1/9 0009 有问题 
        ischag = getIntent().getBooleanExtra("ischage",false);
        if(ischag){
            card = getIntent().getStringExtra("card") ;
            card_name = getIntent().getStringExtra("card_name") ;
            cash_id = getIntent().getStringExtra("cash_id") ;
            bank.setText(card_name);
        }
    }

    private void tixian() {
//        if(Float.parseFloat(getString(jine))>Float.parseFloat(ccode.getData().getMoney())){
//            toast("金额大于您的财富");
//            return;
//        }

//        if(Float.parseFloat(getString(jine))==0){
//            toast("金额为0，无法体现");
//            return;
//        }



        Float monry = Float.parseFloat(getString(jine));
        if(!ischag){
            if(ccode!=null)
            cash_id =  ccode.getData().getCash_id();
        }


        GetData.tixian(SharedPreferencesUtil.getInstance().getString("token"),cash_id,monry
                ,TiXianActivity.this,TIXIANBA);

    }
}
