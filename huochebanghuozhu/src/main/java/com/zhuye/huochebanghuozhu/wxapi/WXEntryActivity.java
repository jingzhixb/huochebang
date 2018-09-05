package com.zhuye.huochebanghuozhu.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.zhuye.huochebanghuozhu.HuoCheApp;
import com.zhuye.huochebanghuozhu.data.GetData;

/**
 * 微信
 */
public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

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
                Toast.makeText(this, "分享成功", Toast.LENGTH_LONG).show();
//                Message msg = new Message();
//                msg.arg1 = 101;
//                GetData.getHandler().sendMessage(msg);

//                }
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
//                LogUtils.log("ERR_USER_CANCEL");
                System.out.println(BaseResp.ErrCode.ERR_USER_CANCEL);
                Toast.makeText(this, "分享取消", Toast.LENGTH_LONG).show();
                //发送取消
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
//                LogUtils.log("ERR_AUTH_DENIED");
                System.out.println(BaseResp.ErrCode.ERR_AUTH_DENIED);
                Toast.makeText(this, "分享失败", Toast.LENGTH_LONG).show();
                //发送被拒绝
                break;
            case BaseResp.ErrCode.ERR_BAN:
                System.out.println(BaseResp.ErrCode.ERR_BAN);
                Toast.makeText(this, "分享失败", Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
        finish();
    }
}
