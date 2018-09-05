package com.zhuye.huochebangsiji.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zhuye.huochebangsiji.R;
import com.zhuye.huochebangsiji.base.BaseActivity;
import com.zhuye.huochebangsiji.bean.AliPayBean;
import com.zhuye.huochebangsiji.bean.Base;
import com.zhuye.huochebangsiji.bean.WeixinBean;
import com.zhuye.huochebangsiji.contants.Contans;
import com.zhuye.huochebangsiji.data.GetData;
import com.zhuye.huochebangsiji.utils.SharedPreferencesUtil;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.zhuye.huochebangsiji.contants.Contans.SDK_PAY_FLAG;

public class ChongZhiActivity extends BaseActivity {


    private static final int YUEECODE = 8784;
    private static final int WEICODE = 78745;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.tou)
    RelativeLayout tou;
    @BindView(R.id.bank)
    TextView bank;
    @BindView(R.id.go)
    ImageView go;
    @BindView(R.id.jine)
    EditText jine;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.kahao)
    TextView kahao1;
    @BindView(R.id.edit)
    ImageView edit;
    @BindView(R.id.aliimg)
    ImageView aliimg;
    @BindView(R.id.aliimgcjoice)
    ImageView aliimgcjoice;
    @BindView(R.id.zhifubao)
    RelativeLayout zhifubao;
    @BindView(R.id.wechat)
    ImageView wechat;
    @BindView(R.id.wechatcjoice)
    ImageView wechatcjoice;
    @BindView(R.id.weixin)
    RelativeLayout weixin;
    @BindView(R.id.chongzhi)
    Button chongzhi;
    @BindView(R.id.yuechongzhi2)
    ImageView yuechongzhi2;
    @BindView(R.id.yuechongzhi)
    RelativeLayout yuechongzhi;
    @BindView(R.id.wodeyue)
    TextView wodeyue;

    @Override
    protected int getResId() {
        return R.layout.activity_chong_zhi;
    }

    String money;
    String woyue;
    String hao;

    @Override
    protected void initView() {
        super.initView();
        money = getIntent().getStringExtra("money");
        woyue = getIntent().getStringExtra("yue");
        hao = getIntent().getStringExtra("hao");
//        jine.setText(woyue + hao);

        wodeyue.setHint("余额充值("+woyue+")");
        kahao1.setText("卡号:" + hao);
    }

    private int current = 3;
    private static final int alipry = 1;
    private static final int weixina = 2;
    private static final int yue = 3;

    @OnClick({R.id.back, R.id.jine, R.id.name, R.id.kahao, R.id.zhifubao, R.id.weixin, R.id.chongzhi, R.id.yuechongzhi2, R.id.yuechongzhi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.yuechongzhi:
                current = yue;
                changestate();
                break;

            case R.id.yuechongzhi2:
                current = yue;
                changestate();
                break;

            case R.id.back:
                finish();
                break;
            case R.id.jine:

                break;
            case R.id.name:
                //alertEditname();
                break;
            case R.id.kahao:
                //alertEditKahao();
                break;
            case R.id.zhifubao:
                current = alipry;
                changestate();
//                editstate(alipry);
                break;
            case R.id.weixin:
                current = weixina;
                changestate();
//                editstate(weixina);
                break;
            case R.id.chongzhi:
                try {
                    Float f = Float.valueOf(jine.getText().toString().trim());
                    if (f <= 0) {
                        toast("请输入正确的金额");
                        return;
                    }
//                if(jine)
                    chongzhiqu(f);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void chongzhiqu(Float f) {

        switch (current) {
            case yue:
                GetData.balance_oil(SharedPreferencesUtil.getInstance().getString("token"), f, ChongZhiActivity.this, YUEECODE);
                break;

            case alipry:
                showxuan(aliimgcjoice, yuechongzhi2, wechatcjoice);
               // GetData.alipay_oil(SharedPreferencesUtil.getInstance().getString("token"), f, ChongZhiActivity.this, YUEECODE);

                OkGo.<String>post(Contans.BASE+"index.php/home/alipay/oil")
                        .params("token",SharedPreferencesUtil.getInstance().getString("token"))
                        .params("money",f)
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                Log.i("as",response.body());


                                AliPayBean aliPay = null;
                                try {
                                    aliPay = new Gson().fromJson(response.body(),AliPayBean.class);
                                    if(aliPay.getCode()==200){
                                        final AliPayBean finalAliPay = aliPay;
                                        Runnable payRunnable = new Runnable() {
                                            @Override
                                            public void run() {
                                                PayTask alipay1 = new PayTask(ChongZhiActivity.this);
                                                Map<String, String> result = alipay1.payV2
                                                        (finalAliPay.getData(), true);//这里的orderInfo就是上面说的orderInfo
                                                Message msg = new Message();
                                                msg.what = SDK_PAY_FLAG;
                                                msg.obj = result;
                                                mandler.sendMessage(msg);
                                            }
                                        };

                                        Thread payThread = new Thread(payRunnable);
                                        payThread.start();
                                    }else {
                                        toast(aliPay.getMessage());
                                    }
                                } catch (JsonSyntaxException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onError(Response<String> response) {
                                super.onError(response);
                                Log.i("as",response.body());
                            }
                        });
                break;
            case weixina:
                showxuan(wechatcjoice, aliimgcjoice, yuechongzhi2);
                GetData.wxpay_oil(SharedPreferencesUtil.getInstance().getString("token"), f, ChongZhiActivity.this, WEICODE);
                break;

        }
    }

    Handler mandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1000:
                    finish();
                    break;
                case SDK_PAY_FLAG:
                    PayResult payResult = new PayResult((Map<String,String>) msg.obj);
                    String resultStatus = payResult.getResultStatus();
                    if (resultStatus.equals("9000")) {
                        Toast.makeText(ChongZhiActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }else if (resultStatus.equals("4000")){

                        // 4000为支付失败，包括用户主动取消支付，或者系统返回的错误
                        Toast.makeText(ChongZhiActivity.this, "支付失败", Toast.LENGTH_SHORT).show();

                    }else if (resultStatus.equals("6001")){

                        // 6001为取消支付，或者系统返回的错误
                        Toast.makeText(ChongZhiActivity.this, "取消支付", Toast.LENGTH_SHORT).show();

                    }else if (resultStatus.equals("8000")) {
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        Toast.makeText(ChongZhiActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();
                    }else {
                        // 其他为系统返回的错误
                        Toast.makeText(ChongZhiActivity.this, "支付错误", Toast.LENGTH_SHORT).show();

                    }
                    break;
            }
        }
    };

    private void changestate() {
        switch (current) {
            case yue:
                showxuan(yuechongzhi2, aliimgcjoice, wechatcjoice);
                break;

            case alipry:
                showxuan(aliimgcjoice, yuechongzhi2, wechatcjoice);
                break;
            case weixina:
                showxuan(wechatcjoice, aliimgcjoice, yuechongzhi2);
                break;
        }
    }

    private void showxuan(ImageView yuechongzhi2, ImageView aliimgcjoice, ImageView wechatcjoice) {
        yuechongzhi2.setImageResource(R.drawable.on);
        aliimgcjoice.setImageResource(R.drawable.off);
        wechatcjoice.setImageResource(R.drawable.off);
    }

    private void editstate(int state) {
        if (state == alipry) {
            aliimgcjoice.setImageResource(R.drawable.on);
            wechatcjoice.setImageResource(R.drawable.off);
            current = alipry;
        } else if (state == weixina) {
            aliimgcjoice.setImageResource(R.drawable.off);
            wechatcjoice.setImageResource(R.drawable.on);
            current = weixina;
        }
    }

    private void alertEditKahao() {
        final AlertDialog dial = new AlertDialog.Builder(ChongZhiActivity.this).create();
        View view = View.inflate(ChongZhiActivity.this, R.layout.aliet_edit, null);
        dial.setView(view);
        dial.show();


        final EditText tv = view.findViewById(R.id.qrcode);
        TextView tishi = view.findViewById(R.id.tishi);

        tishi.setText("请输入卡号");
        view.findViewById(R.id.queren).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //personAge.setText(city[pos]);
                if (dial != null && dial.isShowing()) {
                    dial.dismiss();
                }

                if (isEmpty(tv)) {
                    toast("请输入内容");
                    return;
                }
                kahao = getString(tv);
                kahao1.setText(kahao);
                //phone = getString(tv1);
            }
        });
        view.findViewById(R.id.quxiao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dial != null && dial.isShowing()) {
                    dial.dismiss();
                }
            }
        });

    }

    private String kahao;
    private String youkacode;

    private void alertEditname() {
        final AlertDialog diala = new AlertDialog.Builder(ChongZhiActivity.this).create();
        View view = View.inflate(ChongZhiActivity.this, R.layout.aliet_edit, null);
        diala.setView(view);
        diala.show();


        final EditText tv1 = view.findViewById(R.id.qrcode);
        TextView tishi = view.findViewById(R.id.tishi);

        tishi.setText("请输入油卡名称");
        view.findViewById(R.id.queren).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //personAge.setText(city[pos]);
                if (diala != null && diala.isShowing()) {
                    diala.dismiss();
                }

                if (isEmpty(tv1)) {
                    toast("请输入内容");
                    return;
                }
                youkacode = getString(tv1);
                name.setText(youkacode);
                //phone = getString(tv1);
            }
        });
        view.findViewById(R.id.quxiao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (diala != null && diala.isShowing()) {
                    diala.dismiss();
                }
            }
        });
    }


    @Override
    public void success(int requestcode, Object o) {
        super.success(requestcode, o);
        Base base = (Base) o;
        switch (requestcode) {
            case YUEECODE:
                toast(((Base) o).getMessage());
                finish();
                break;
            case WEICODE:
                WeixinBean weixinBean = (WeixinBean) o;
                String appid = weixinBean.getData().getAppid();
                String partnerid = weixinBean.getData().getPartnerid();
                String prepayid= weixinBean.getData().getPrepayid();
//                String prepayid= "wx06153409612752dd3c8f3ac43024664561";
                //            String packages =  weixinBean.getData().getPackages();
                String  noncestr= weixinBean.getData().getNoncestr();
                String timestamp = weixinBean.getData().getTimestamp();
                String sign = weixinBean.getData().getSign();
                WXPay(appid,partnerid,prepayid,"Sign=WXPay",noncestr,timestamp,sign);
                break;
        }
    }
    private IWXAPI iwxapi; //微信支付api
    private void WXPay(final String appid, final String partnerId,final String prepayId, final String packageValue,
                       final String nonceStr, final String timeStamp, final String sign) {
        GetData.WX_FLAG = 3;
        iwxapi = WXAPIFactory.createWXAPI(ChongZhiActivity.this, Contans.WEIXIN_APP_ID); //初始化微信api
        iwxapi.registerApp(Contans.WEIXIN_APP_ID); //注册appid  appid可以在开发平台获取

        Runnable payRunnable = new Runnable() {  //这里注意要放在子线程
            @Override
            public void run() {
                PayReq request = new PayReq(); //调起微信APP的对象
//                下面是设置必要的参数，也就是前面说的参数,这几个参数从何而来请看上面说明
                request.appId = appid;
                request.partnerId = partnerId;
                request.prepayId = prepayId;
                request.packageValue ="Sign=WXPay";
                request.nonceStr = nonceStr;
                request.timeStamp = timeStamp;
                request.sign = sign;
                iwxapi.sendReq(request);//发送调起微信的请求
            }
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
