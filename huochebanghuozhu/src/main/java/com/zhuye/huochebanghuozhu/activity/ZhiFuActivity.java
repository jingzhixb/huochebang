package com.zhuye.huochebanghuozhu.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.zhuye.huochebanghuozhu.R;
import com.zhuye.huochebanghuozhu.base.BaseActivity;
import com.zhuye.huochebanghuozhu.bean.AliPayBean;
import com.zhuye.huochebanghuozhu.bean.MessageEvent;
import com.zhuye.huochebanghuozhu.bean.WeixinBean;
import com.zhuye.huochebanghuozhu.contants.Contans;
import com.zhuye.huochebanghuozhu.data.GetData;
import com.zhuye.huochebanghuozhu.event.PayEvent;
import com.zhuye.huochebanghuozhu.other.PayResult;
import com.zhuye.huochebanghuozhu.utils.SharedPreferencesUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZhiFuActivity extends BaseActivity {
    private static final int ORDERWEIXINPAY = 203;
    private static final int ORDERAPIPAY = 202;
    private static final int YUEPAY = 17;
    private static final int PAYALIBAO = 18000;
    private static final int WEIXINBAO = 204;
    @BindView(R.id.back_pay)
    ImageView backPay;
    @BindView(R.id.tou)
    RelativeLayout tou;
    @BindView(R.id.jiege)
    TextView jiege;
    @BindView(R.id.price)
    TextView price;
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
    @BindView(R.id.queding)
    Button queding;
    private int position = 0;
    private int order_id = 0;
    private int flag = 0;
    private String money = "";
    private Intent intent;
    private IWXAPI iwxapi; //微信支付api
     Handler payHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == Contans.SDK_PAY_FLAG) {

                PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                /**
                 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为结束的通知。
                 */
                String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                String resultStatus = payResult.getResultStatus();
                // 判断resultStatus 为9000则代表支付成功
                if (TextUtils.equals(resultStatus, "9000")) {
                    // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                    AlertDialog.Builder builder = new AlertDialog.Builder(ZhiFuActivity.this);
                    builder.setTitle("！提示:");
                    builder.setMessage("支付成功");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            queding.setClickable(false);
                            finish();
                        }
                    });
                    builder.create().show();
                } else {
                    // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                    queding.setClickable(true);
                    toast("支付失败");
                    finish();
                }
            }else if (msg.arg1 == 101){
                queding.setClickable(false);
                finish();
            }

        }
    };

    @Override
    protected int getResId() {
        return R.layout.activity_zhi_fu;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        intent = getIntent();
        flag = intent.getIntExtra("flag", 0);
        if (flag == 1) {//保证金支付
            money = intent.getStringExtra("money");
            jiege.setText("需支付保证金：");
            price.setText(money + "元");
        } else if (flag == 2) {//订单支付
            order_id = intent.getIntExtra("order_id", 0);
            money = intent.getStringExtra("price");
            jiege.setText("需支付：");
            price.setText(money + "元");
        }

        EventBus.getDefault().register(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(PayEvent messageEvent) {
       // mText.setText(messageEvent.getMessage());
//        toast(22+messageEvent.message);
        finish();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.back_pay, R.id.alipay, R.id.weixin, R.id.queding})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_pay:
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
            case R.id.queding://确认支付
                if (position == 0) {
                    toast("请选择支付方式");
                    return;
                }
                if (flag == 1) {//保證金支付
                    if (position == 1) {//支付宝
                        GetData.pay_aliay(SharedPreferencesUtil.getInstance().getString("token"), ZhiFuActivity.this, PAYALIBAO);
                    } else if (position == 2) {//微信
                        GetData.pay_bao_weixin(SharedPreferencesUtil.getInstance().getString("token"), ZhiFuActivity.this, WEIXINBAO);

                    }
                } else if (flag == 2) {//订单支付
                    if (position == 1) {
                        GetData.pay_order(SharedPreferencesUtil.getInstance().getString("token"), order_id, ZhiFuActivity.this, ORDERAPIPAY);
                    } else if (position == 2) {
                        GetData.pay_weixin(SharedPreferencesUtil.getInstance().getString("token"), order_id, ZhiFuActivity.this, ORDERWEIXINPAY);
                    }
                }
                break;
        }
    }

    /**
     * 支付寶支付
     *
     * @param requestUrl
     */
    private void aliPay(final String requestUrl) {
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(ZhiFuActivity.this);
                Map<String, String> result = alipay.payV2(requestUrl, true);
                Log.i("msp", result.toString());
                Message msg = new Message();
                msg.what = Contans.SDK_PAY_FLAG;
                msg.obj = result;
                payHandler.sendMessage(msg);
            }
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    private void WXPay(final String appid, final String partnerId, final String prepayId, final String packageValue,
                       final String nonceStr, final String timeStamp, final String sign) {
        iwxapi = WXAPIFactory.createWXAPI(this, Contans.WEIXIN_APP_ID); //初始化微信api
        iwxapi.registerApp(Contans.WEIXIN_APP_ID); //注册appid  appid可以在开发平台获取

        Runnable payRunnable = new Runnable() {  //这里注意要放在子线程
            @Override
            public void run() {
                PayReq request = new PayReq(); //调起微信APP的对象
                //下面是设置必要的参数，也就是前面说的参数,这几个参数从何而来请看上面说明
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

    @Override
    public void success(int requestcode, Object o) {
        super.success(requestcode, o);
        switch (requestcode) {
            case PAYALIBAO:
                AliPayBean ji = (AliPayBean) o;
                String url = ji.getData();
                aliPay(url);
                break;
            case WEIXINBAO:
                WeixinBean weixinBean = (WeixinBean) o;
                String appid = weixinBean.getData().getAppid();
                String partnerid = weixinBean.getData().getPartnerid();
                String prepayid = weixinBean.getData().getPrepayid();
                String noncestr = weixinBean.getData().getNoncestr();
                String timestamp = weixinBean.getData().getTimestamp();
                String sign = weixinBean.getData().getSign();
                WXPay(appid, partnerid, prepayid, "Sign=WXPay", noncestr, timestamp, sign);
                break;
            case ORDERAPIPAY:
                AliPayBean aliPayBean = (AliPayBean) o;
                String urls = aliPayBean.getData();
                aliPay(urls);
                break;
            case ORDERWEIXINPAY:
                WeixinBean weixinBeans = (WeixinBean) o;
                String appids = weixinBeans.getData().getAppid();
                String partnerids = weixinBeans.getData().getPartnerid();
                String prepayids = weixinBeans.getData().getPrepayid();
                String noncestrs = weixinBeans.getData().getNoncestr();
                String timestamps = weixinBeans.getData().getTimestamp();
                String signs = weixinBeans.getData().getSign();
                WXPay(appids, partnerids, prepayids, "Sign=WXPay", noncestrs, timestamps, signs);
                break;
            default:
                break;
        }

    }

    private void selectBackground(int position) {
        xuanalipay.setImageResource(position == 1 ? R.drawable.gouxuan_on : R.drawable.gouxuan_off);
        xuanweixin.setImageResource(position == 2 ? R.drawable.gouxuan_on : R.drawable.gouxuan_off);
    }
}
