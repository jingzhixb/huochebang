package com.zhuye.huochebangsiji.activity;

import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.zhuye.huochebangsiji.R;
import com.zhuye.huochebangsiji.base.BaseActivity;
import com.zhuye.huochebangsiji.fragment.MessageFragment;

import butterknife.BindView;

public class LookDingDanActivity extends BaseActivity {


    @BindView(R.id.fragment)
    FrameLayout fragment;

    @Override
    protected int getResId() {
        return (R.layout.activity_look_ding_dan);

    }

    int type;

    @Override
    protected void initView() {
        super.initView();
        type = getIntent().getIntExtra("type", 0);
    }


    MessageFragment fragmen;
    @Override
    protected void initData() {
        super.initData();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmen =  new  MessageFragment();
        fragmentTransaction.replace(R.id.fragment,fragmen,"ding");
        fragmentTransaction.commit();



//        switch (type){
//            case 1:
//                fragment.setCurrentPos(0);
//                break;
//            case 2:
//                fragme.setCurrentPos(1);
//                break;
//            case 3:
//                fragment.setCurrentPos(2);
//                break;
//            case 4:
//                fragment.setCurrentPos(3);
//                break;
//            case 5:
//                fragment.setCurrentPos(4);
//                break;
//        }

//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                fragment.setCurrentPos(type - 1);
//            }
//        },2000);

        //fragmentTransaction.
    }

    @Override
    protected void onResume() {
        super.onResume();
        fragmen.showBack();
        fragmen.setCurrentPos(type - 1);
    }


}
