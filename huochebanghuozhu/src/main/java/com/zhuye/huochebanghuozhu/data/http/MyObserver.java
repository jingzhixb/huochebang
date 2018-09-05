package com.zhuye.huochebanghuozhu.data.http;

import com.zhuye.huochebanghuozhu.presenter.BaseView;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2018/1/11 0011.
 */

public abstract class MyObserver<T> implements Observer<T>   {

    private BaseView baseView;

    public MyObserver(BaseView baseView) {
        this.baseView = baseView;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        baseView.loding();
    }


    @Override
    public void onError(@NonNull Throwable e) {
        //baseView.error();
        baseView.finishLoding();
    }

    @Override
    public void onComplete() {
        //baseView.error();
        baseView.finishLoding();
    }
}
