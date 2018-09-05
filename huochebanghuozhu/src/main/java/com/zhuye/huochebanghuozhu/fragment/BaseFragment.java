package com.zhuye.huochebanghuozhu.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.zhuye.huochebanghuozhu.R;
import com.zhuye.huochebanghuozhu.base.BaseActivity;
import com.zhuye.huochebanghuozhu.base.BaseRecycleAdapter;
import com.zhuye.huochebanghuozhu.bean.Base;
import com.zhuye.huochebanghuozhu.presenter.BaseView;
import com.zhuye.huochebanghuozhu.widget.CustomProgressDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zzzy on 2017/11/20.
 */

public abstract class BaseFragment extends Fragment implements BaseView {

    private CustomProgressDialog dialog;
    public View rootView;
    Unbinder unbinder;
    protected BaseRecycleAdapter adapter;
//    protected View emptyview;
    protected View errorview;
    protected View lodingview;

    protected RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = View.inflate(getActivity(),getResId(),null);

        unbinder = ButterKnife.bind(this, view);
        rootView = view;
        initView();
        dialog = new CustomProgressDialog(getActivity(), "加载中");
        dialog.setCanceledOnTouchOutside(false);
        lodingview = View.inflate(getActivity(), R.layout.loding,null);
        errorview = LayoutInflater.from(getActivity()).inflate(R.layout.error,null);

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


    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };
    @Override
    public void loding() {
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(dialog!=null)
                dialog.dismiss();
            }
        },1000);
    }

    @Override
    public void finishLoding() {

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
        ((BaseActivity) getActivity()).toast(content);
    }

}
