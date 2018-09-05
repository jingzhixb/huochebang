package com.zhuye.huochebangsiji.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhuye.huochebangsiji.R;
import com.zhuye.huochebangsiji.activity.LoginActivity;
import com.zhuye.huochebangsiji.activity.YouKaChonZhiActivity;
import com.zhuye.huochebangsiji.utils.SharedPreferencesUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/1/9 0009.
 */

public class SijiDatingFragment extends BaseFragment {

    @BindView(R.id.youkafuwu)
    TextView youkafuwu;
    @BindView(R.id.etcfuwu)
    TextView etcfuwu;
    @BindView(R.id.ivyouka)
    ImageView ivyouka;
    Unbinder unbinder;

    @Override
    protected void initView() {

    }


    @Override
    protected int getResId() {
        return R.layout.fragment_dating;
    }


    @OnClick({R.id.youkafuwu, R.id.etcfuwu,R.id.ivyouka})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivyouka:
            case R.id.youkafuwu:
                if (SharedPreferencesUtil.getInstance().getString("token") == null && TextUtils.isEmpty(SharedPreferencesUtil.getInstance().getString("token"))) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                    //getActivity().finish();
                } else {
                    startActivity(new Intent(getActivity(), YouKaChonZhiActivity.class));
                }
                break;
            case R.id.etcfuwu:

                break;
        }
    }


    @Override
    public void success(int requestcode, Object o) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
