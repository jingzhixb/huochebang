package com.zhuye.huochebanghuozhu;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.zhuye.huochebanghuozhu.contants.Contans;
import com.zhuye.huochebanghuozhu.utils.SharedPreferencesUtil;

import cn.jpush.android.api.JPushInterface;


/**
 * Created by Administrator on 2018/1/3 0003.
 */

public class HuoCheApp extends Application {
    public static IWXAPI mWxApi;

    @Override
    public void onCreate() {
        super.onCreate();
        Bugly.init(this, "dbb68a0424", true);
                SharedPreferencesUtil.init(this,"config", Context.MODE_PRIVATE);
        JPushInterface.setDebugMode(true);
        JPushInterface.init(getApplication());
        CrashReport.initCrashReport(getApplication(), "dbb68a0424", false);
        PlatformConfig.setWeixin("wxb419f4ed1c3e963b", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setQQZone("1106801584", "8lzmzkGIlhf9vLd7");
        UMShareAPI.get(this);
        registerToWX();
    }
    public void registerToWX() {
        //第二个参数是指你应用在微信开放平台上的AppID
        mWxApi = WXAPIFactory.createWXAPI(this, Contans.WEIXIN_APP_ID, false);
        // 将该app注册到微信
        mWxApi.registerApp(Contans.WEIXIN_APP_ID);
    }
    public Application getApplication(){
        return this;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // you must install multiDex whatever tinker is installed!
        MultiDex.install(base);


        // 安装tinker
        Beta.installTinker();
    }
}
//
//public class HuoCheApp extends TinkerApplication {
//
//    protected HuoCheApp() {
//        super(ShareConstants.TINKER_ENABLE_ALL, "com.zhuye.huochebanghuozhu.HuoCheAppLike",
//                "com.tencent.tinker.loader.TinkerLoader", false);
//    }
//}
