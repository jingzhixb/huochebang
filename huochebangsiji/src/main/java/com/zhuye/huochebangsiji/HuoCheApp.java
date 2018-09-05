package com.zhuye.huochebangsiji;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.support.multidex.MultiDex;

import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.zhuye.huochebangsiji.contants.Contans;
import com.zhuye.huochebangsiji.utils.SharedPreferencesUtil;

import cn.jpush.android.api.JPushInterface;



/**
 * Created by Administrator on 2018/1/3 0003.
 */

public class HuoCheApp extends Application {
    public static IWXAPI mWxApi;


    @Override
    public void onCreate() {
        super.onCreate();
        Bugly.init(this, "27f5315a86", false);
        SharedPreferencesUtil.init(this,"config", Context.MODE_PRIVATE);
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setQQZone("1106618613", "nz08que3o7zNDkoP");

        UMShareAPI.get(this);
        MultiDex.install(this);
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        registerToWX();
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // you must install multiDex whatever tinker is installed!
        MultiDex.install(base);

        // 安装tinker
        Beta.installTinker();
    }
    public void registerToWX() {
        //第二个参数是指你应用在微信开放平台上的AppID
        mWxApi = WXAPIFactory.createWXAPI(this, Contans.WEIXIN_APP_ID, false);
        // 将该app注册到微信
        mWxApi.registerApp(Contans.WEIXIN_APP_ID);
    }
}
