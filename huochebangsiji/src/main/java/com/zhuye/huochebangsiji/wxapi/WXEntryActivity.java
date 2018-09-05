package com.zhuye.huochebangsiji.wxapi;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.widget.Toast;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.zhuye.huochebangsiji.HuoCheApp;
import com.zhuye.huochebangsiji.data.GetData;

import retrofit2.http.GET;

/**
 * Created by Administrator on 2018/3/23 0023.
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
    public void onResp(BaseResp baseResp) {

        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                //支付回调
                if (baseResp.getType()== ConstantsAPI.COMMAND_PAY_BY_WX){
                Toast.makeText(this, "支付成功", Toast.LENGTH_LONG).show();
                if (GetData.WX_FLAG == 1) {//货源
                    Message msg = new Message();
                    msg.arg1 = 102;
                    msg.what = 10000;
                    GetData.getHandler().sendMessage(msg);
                }else if (GetData.WX_FLAG == 2){//VIP
                    Message msg = new Message();
                    msg.arg1 = 103;
                    GetData.getHandler().sendMessage(msg);
                }else if (GetData.WX_FLAG == 3){// 油卡充值
                    Message msg = new Message();
                    msg.arg1 = 103;
                    msg.what = 10000;
                    GetData.getHandler().sendMessage(msg);
                }
                }
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
//                LogUtils.log("ERR_USER_CANCEL");
                System.out.println(BaseResp.ErrCode.ERR_USER_CANCEL);
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
