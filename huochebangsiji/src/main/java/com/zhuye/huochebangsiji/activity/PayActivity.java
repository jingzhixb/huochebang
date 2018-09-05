package com.zhuye.huochebangsiji.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zhuye.huochebangsiji.R;
import com.zhuye.huochebangsiji.base.BaseActivity;
import com.zhuye.huochebangsiji.bean.AliPayBean;
import com.zhuye.huochebangsiji.bean.WeixinBean;
import com.zhuye.huochebangsiji.contants.Contans;
import com.zhuye.huochebangsiji.data.GetData;
import com.zhuye.huochebangsiji.utils.SharedPreferencesUtil;
import com.zhuye.huochebangsiji.wxapi.PayEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PayActivity extends BaseActivity {
    private static final int VIP_API = 2003;
    private static final int VIP_WEIXIN = 2002;
    private IWXAPI iwxapi; //微信支付api
    @BindView(R.id.back_pay_siji)
    ImageView backPaySiji;
    @BindView(R.id.pay_siji)
    TextView paySiji;
    @BindView(R.id.price_siji)
    TextView priceSiji;
    @BindView(R.id.iv3)
    ImageView iv3;
    @BindView(R.id.xuanalipay)
    ImageView xuanalipay;
    @BindView(R.id.alipay)
    RelativeLayout alipay;
    @BindView(R.id.iv4)
    ImageView iv4;
    @BindView(R.id.xuanweixin)
    ImageView xuanweixin;
    @BindView(R.id.weixin)
    RelativeLayout weixin;
    @BindView(R.id.btn_pay)
    Button btnPay;
    private int position = 0;
    private Intent intent;
    private String money = "";
    private int vip_id = 0;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {

            if (msg.what == Contans.SDK_PAY_FLAG) {

                PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                /**
                 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                 */
                String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                String resultStatus = payResult.getResultStatus();
                // 判断resultStatus 为9000则代表支付成功
                if (TextUtils.equals(resultStatus, "9000")) {
                    // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                    Toast.makeText(PayActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    btnPay.setClickable(false);
                    EventBus.getDefault().post(new PayEvent("",2));
                    finish();
                } else {
                    // 该笔订单真实的支付结果，需要依赖 服务端的异步通知。
                    btnPay.setClickable(true);
                    Toast.makeText(PayActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                }
            }else if (msg.arg1 == 103){
                finish();
                btnPay.setClickable(false);
            }else if(msg.arg1 ==1000){

                EventBus.getDefault().post(new PayEvent("取消支付",2));
                finish();
            }
        }

        ;
    };

    @Override
    protected int getResId() {
        return R.layout.activity_pay;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        super.initView();
        intent = getIntent();
        vip_id = intent.getIntExtra("vip_id", 0);
        money = intent.getStringExtra("momney");
        priceSiji.setText(money+"元");


        EventBus.getDefault().register(this);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(PayEvent event) {
        /* Do something */
        if (event.code ==3){
            EventBus.getDefault().post(new PayEvent("取消支付",2));
            finish();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.back_pay_siji, R.id.alipay, R.id.weixin, R.id.btn_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_pay_siji:
                finish();
                break;
            case R.id.alipay://支付宝
                position = 1;
                selectBackground(position);
                break;
            case R.id.weixin://微信
                position = 2;
                selectBackground(position);
                break;
            case R.id.btn_pay:



                if (position == 0) {
                    toast("请选择支付方式");
                    return;
                }
                if (position == 1) {//支付宝支付
                    GetData.maivip(SharedPreferencesUtil.getInstance().getString("token"), vip_id, PayActivity.this, VIP_API);
                } else if (position == 2) {
                    GetData.maivipWeixin(SharedPreferencesUtil.getInstance().getString("token"), vip_id, PayActivity.this, VIP_WEIXIN);
                }
                break;
        }
    }

    private void selectBackground(int position) {
        xuanalipay.setImageResource(position == 1 ? R.drawable.gouxuan_on : R.drawable.gouxuan_off);
        xuanweixin.setImageResource(position == 2 ? R.drawable.gouxuan_on : R.drawable.gouxuan_off);
    }

    @Override
    public void success(int requestcode, Object o) {
        super.success(requestcode, o);
        switch (requestcode) {
            case VIP_API:
                AliPayBean ji = (AliPayBean) o;
                final String url = ji.getData();
                Runnable payRunnable = new Runnable() {

                    @Override
                    public void run() {
                        PayTask alipay = new PayTask(PayActivity.this);
                        Map<String, String> resul = alipay.payV2(url, true);
                        Log.i("msp", resul.toString());
                        Message msg = new Message();
                        msg.what = Contans.SDK_PAY_FLAG;
                        msg.obj = resul;
                        mHandler.sendMessage(msg);
                    }
                };
                // 必须异步调用
                Thread payThread = new Thread(payRunnable);
                payThread.start();
                break;
            case VIP_WEIXIN://微信
                WeixinBean weixinBean = (WeixinBean) o;
                String appid = weixinBean.getData().getAppid();
                String partnerid = weixinBean.getData().getPartnerid();
                String prepayid = weixinBean.getData().getPrepayid();
//            String packages =  weixinBean.getData().getPackages();
                String noncestr = weixinBean.getData().getNoncestr();
                String timestamp = weixinBean.getData().getTimestamp();
                String sign = weixinBean.getData().getSign();
                WXPay(appid, partnerid, prepayid, "Sign=WXPay", noncestr, timestamp, sign);
                break;
        }
    }

    private void WXPay(final String appid, final String partnerId, final String prepayId, final String packageValue,
                       final String nonceStr, final String timeStamp, final String sign) {
        GetData.WX_FLAG = 2;
        iwxapi = WXAPIFactory.createWXAPI(PayActivity.this, Contans.WEIXIN_APP_ID); //初始化微信api
        iwxapi.registerApp(Contans.WEIXIN_APP_ID); //注册appid  appid可以在开发平台获取
        Runnable payRunnable = new Runnable() {  //这里注意要放在子线程
            @Override
            public void run() {
                PayReq request = new PayReq(); //调起微信APP的对象
//                下面是设置必要的参数，也就是前面说的参数,这几个参数从何而来请看上面说明
                request.appId = appid;
                request.partnerId = partnerId;
                request.prepayId = prepayId;
                request.packageValue = "Sign=WXPay";
                request.nonceStr = nonceStr;
                request.timeStamp = timeStamp;
                request.sign = sign;
                iwxapi.sendReq(request);//发送调起微信的请求
            }
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }
}
