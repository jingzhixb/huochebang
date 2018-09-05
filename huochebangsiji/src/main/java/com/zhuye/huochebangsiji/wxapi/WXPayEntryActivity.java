package com.zhuye.huochebangsiji.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.zhuye.huochebangsiji.HuoCheApp;
import com.zhuye.huochebangsiji.data.GetData;


import org.greenrobot.eventbus.EventBus;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //这句没有写,是不能执行回调的方法的
        HuoCheApp.mWxApi.handleIntent(getIntent(), this);
    }

    @Override
    public void onReq(BaseReq baseReq) {


    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        HuoCheApp.mWxApi.handleIntent(intent,this);
    }



    @Override
    public void onResp(BaseResp baseResp) {
//        baseResp.checkArgs();
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                //支付回调
//                if (baseResp.getType()== ConstantsAPI.COMMAND_PAY_BY_WX){
                Toast.makeText(this, "支付成功", Toast.LENGTH_LONG).show();
                EventBus.getDefault().post(new PayEvent("支付成功",3));
//                Message msg = new Message();
//                msg.arg1 = 101;
//                GetData.getHandler().sendMessage(msg);
//                EventBus.getDefault().post(new PayEvent("支付成功",2));
//                }
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
//                LogUtils.log("ERR_USER_CANCEL");
                System.out.println(BaseResp.ErrCode.ERR_USER_CANCEL);
                Toast.makeText(this, "取消支付", Toast.LENGTH_LONG).show();
//                Message msg2 = new Message();
//                msg2.arg1 = 1000;
//                GetData.getHandler().sendMessage(msg2);
                //发送取消

                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
//                LogUtils.log("ERR_AUTH_DENIED");
                System.out.println(BaseResp.ErrCode.ERR_AUTH_DENIED);
                //发送被拒绝
                break;
            case BaseResp.ErrCode.ERR_BAN:
                System.out.println(BaseResp.ErrCode.ERR_BAN);
                break;
            default:
                break;
        }
        finish();
    }
}
