package com.zhuye.huochebangsiji.presenter;

import android.util.Log;

import com.zhuye.huochebangsiji.bean.InvateBean;
import com.zhuye.huochebangsiji.bean.MobileBean;
import com.zhuye.huochebangsiji.bean.UserInfoBean;
import com.zhuye.huochebangsiji.contract.MeFragmentContract;
import com.zhuye.huochebangsiji.data.http.CommonApi;
import com.zhuye.huochebangsiji.utils.SharedPreferencesUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/1/10 0010.
 */

public class MeFragmentpresenter {

    private MeFragmentContract.MeFragmentView meFragmentView;

    public MeFragmentpresenter(MeFragmentContract.MeFragmentView meFragmentView) {
        this.meFragmentView = meFragmentView;
    }

    public void loadData(final int requestcode) {
        CommonApi.getInstance().user_info(SharedPreferencesUtil.getInstance().getString("token")).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserInfoBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        meFragmentView.loding();
                    }

                    @Override
                    public void onNext(@NonNull UserInfoBean code) {
                        Log.i("as", code.toString());
                        if (code.getCode() == 200) {
                            meFragmentView.success(requestcode,code);
                        }else {
                            meFragmentView.empty();
                        }
                        meFragmentView.finishLoding();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i("as", e.toString());
                        //meFragmentView.error();
                        meFragmentView.finishLoding();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getKefuInfo(final int requestcode) {
        CommonApi.getInstance().server(SharedPreferencesUtil.getInstance().getString("token")).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MobileBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        meFragmentView.loding();
                    }

                    @Override
                    public void onNext(@NonNull MobileBean code) {
                        Log.i("as", code.toString());
                        if (code.getCode() == 200) {
                            meFragmentView.success(requestcode,code);
                        }else {
                            meFragmentView.empty();
                        }
                        meFragmentView.finishLoding();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i("as", e.toString());
                        //meFragmentView.error();
                        meFragmentView.finishLoding();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public void yaoqing(final int requestcode) {

        CommonApi.getInstance().invate(SharedPreferencesUtil.getInstance().getString("token")).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<InvateBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        meFragmentView.loding();
                    }

                    @Override
                    public void onNext(@NonNull InvateBean code) {
                        Log.i("as", code.toString());
                        if (code.getCode() == 200) {
                            meFragmentView.success(requestcode,code);
                        }else {
                            meFragmentView.empty();
                        }
                        meFragmentView.finishLoding();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i("as", e.toString());
                        //meFragmentView.error();
                        meFragmentView.finishLoding();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
