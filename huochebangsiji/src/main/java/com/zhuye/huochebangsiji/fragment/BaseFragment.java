package com.zhuye.huochebangsiji.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhuye.huochebangsiji.base.BaseActivity;
import com.zhuye.huochebangsiji.base.BaseView;
import com.zhuye.huochebangsiji.bean.Base;
import com.zhuye.huochebangsiji.widget.CustomProgressDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zzzy on 2017/11/20.
 */

public abstract class BaseFragment extends Fragment implements BaseView {


    public View rootView;
    Unbinder unbinder;
    private CustomProgressDialog dialog;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = View.inflate(getActivity(),getResId(),null);
        //initView();
        unbinder = ButterKnife.bind(this, view);
        rootView = view;
        dialog = new CustomProgressDialog(getActivity(), "加载中");
        dialog.setCanceledOnTouchOutside(false);
        initView();
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();
        initListener();
    }

    protected void initListener() {
    }

    protected void initData() {
    }

    protected abstract void initView();

    protected abstract int getResId() ;


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }



    @Override
    public void loding() {
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        },1000);
    }

    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };

    @Override
    public void finishLoding() {
//        if (dialog != null && dialog.isShowing()) {
//            dialog.dismiss();
//        }
    }

    @Override
    public void error(Object o) {
        Base base = (Base) o;
        toast(base.getMessage());
    }

    @Override
    public void empty() {

    }

    public void toast(String content){
        ((BaseActivity)getActivity()).toast(content);
    }
}
