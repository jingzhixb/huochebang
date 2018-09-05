package com.zhuye.huochebang.ui;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhuye.huochebang.BaseActivity;
import com.zhuye.huochebang.R;
import com.zhuye.huochebang.bean.Base;
import com.zhuye.huochebang.bean.TiXianBean;
import com.zhuye.huochebang.data.http.GetData;
import com.zhuye.huochebang.utils.SharedPreferencesUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class TiXianActivity extends BaseActivity {


    private static final int TIXIANBA = 10;
    private static final int WITHDRAWALS = 11;
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

    @BindView(R.id.ketijine)
    TextView ketijine;

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
//        CommonApi.getInstance().withdrawals(SharedPreferencesUtil.getInstance().getString("token")).subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<TiXianBean>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//                        Log.i("as", d.toString());
//                    }
//
//                    @Override
//                    public void onNext(@NonNull TiXianBean code) {
//                        Log.i("as", code.toString());
//                        if (code.getCode() == 200) {
//                            ccode = code;
//                            bank.setText(code.getData().getCard_name());
//                            jine.setText(code.getData().getMoney());
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
                cash_id = ccode.getData().getCash_id();
                card_name = ccode.getData().getCard_name();
                card = ccode.getData().getCard();
                ketijine.setText("可提现金额"+ccode.getData().getMoney());
                break;
        }
    }

    private void tianjiazhanghao() {
        startActivityForResult(new Intent(TiXianActivity.this,BankListActivity.class),1000);
    }
    Boolean ischag = false;
    String card;
    String card_name;
    String cash_id;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //// TODO: 2018/1/9 0009 有问题


        GetData.withdrawals(SharedPreferencesUtil.getInstance().getString("token")
                ,TiXianActivity.this,WITHDRAWALS);

//        ischag = getIntent().getBooleanExtra("ischage",false);
//        if(ischag){
//            card = getIntent().getStringExtra("card") ;
//            card_name = getIntent().getStringExtra("card_name") ;
//            cash_id = getIntent().getStringExtra("cash_id") ;
//            bank.setText(card_name);
//        }
    }

    private void tixian() {
        if(Float.parseFloat(getString(jine))>Float.parseFloat(ccode.getData().getMoney())){
            toast("金额大于您的财富");
            return;
        }



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

//        CommonApi.getInstance().tixian(SharedPreferencesUtil.getInstance().getString("token"),cash_id,monry).subscribeOn(Schedulers.newThread())
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
//
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
