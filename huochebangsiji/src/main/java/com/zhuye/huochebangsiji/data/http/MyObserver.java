package com.zhuye.huochebangsiji.data.http;


import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhuye.huochebangsiji.base.BaseActivity;
import com.zhuye.huochebangsiji.base.BaseView;
import com.zhuye.huochebangsiji.fragment.BaseFragment;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2018/1/11 0011.
 */

public abstract class MyObserver<T> implements Observer<T>   {

    private BaseView baseView;
    TextView lodingview;
    ImageView empty;







    public MyObserver(BaseView baseView) {
        this.baseView = baseView;
        Context context = null;
        if(baseView instanceof BaseFragment){
            context = ((BaseFragment) baseView).getActivity();
        }else if(baseView instanceof BaseActivity){
            context = (Context) baseView;
        }

        //lodingview = LayoutInflater.from(context).inflate(R.)
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        baseView.loding();
    }


    @Override
    public void onError(@NonNull Throwable e) {
       // baseView.error();
      baseView.finishLoding();
    }

    @Override
    public void onComplete() {
        //baseView.error();
        baseView.finishLoding();
    }

//    @Override
//    public void onNext(T t) {
//        if(t.getCode()==200){
//
//        }
//    }
}
