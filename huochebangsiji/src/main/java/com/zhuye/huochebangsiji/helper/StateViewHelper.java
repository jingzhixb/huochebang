package com.zhuye.huochebangsiji.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2018/2/1 0001.
 */

public abstract class StateViewHelper {
    public static final int MODE_LOADING = 0;
    public static final int MODE_CONTENT = 1;
    public static final int MODE_EMPTY = 2;
    public static final int MODE_ERROR = 3;

    private int mMode = MODE_CONTENT;
    private View[] mModeViews = new View[4];
    private ViewGroup.LayoutParams[] mModeParams = new ViewGroup.LayoutParams[4];

    private Context mContext;
    private ViewGroup mContent ;

    public StateViewHelper(Context mContext) {
        this.mContext = mContext;
    }

    //将父View添加进来
    public void setContentRoot(ViewGroup contentView) {
        this.mContent = contentView;
    }

    //根据布局参数添加到父View里面
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        setModeView(view, params, MODE_CONTENT);
    }


    //根据Id设置View到父View里面
    public void setContentView(int layoutResID) {
        View view = LayoutInflater.from(mContext).inflate(layoutResID, mContent, false);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );
        setContentView(view, params);
    }
    //设置View到父View里面
    public void setContentView(View view) {
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );


        setContentView(view, params);
    }


    //添加四种布局 的View 和 Mode
    public void setModeView(View view, int mode) {
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );
        setModeView(view, params, mode);
    }
    public void setModeView(int view, int mode) {
        View subView = LayoutInflater.from(mContext).inflate(view, mContent, false);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );
        setModeView(subView, params, mode);
    }
    public void setModeView(View view, ViewGroup.LayoutParams params, int mode) {
        checkMode(mode);//检查，只允许4种View中的mode存在
        mModeViews[mode] = view;//添加mode 和 Params到数组中
        mModeParams[mode] = params;

        if(mMode == mode&&mContent!=null){//如果是ContentVIew的话 就添加到父VIew中
            clearContentView();
            mContent.addView(view,params);
        }
    }


    //通过父View去查找子VIew
    public View findViewById(int id){
        if(mContent!=null){
            View view = mContent.findViewById(id);
            return view;
        }
        return null;
    }

    //清楚父VIew中的子VIew
    private void clearContentView() {
        if( mContent!=null){
            for (int i = 0, size = mContent.getChildCount(); i < size; i++) {
                View subView = mContent.getChildAt(i);
                if (onViewClear(subView)) {
                    mContent.removeView(subView);
                }
            }
        }
    }

    //子类实现，如果子View不等于null 并且 不等于Toolbar，因为子VIew里面也会包含Toolbar，所以需要去掉
    public abstract boolean onViewClear(View subView);

    //根据逻辑设置不同的mode，然后去改变子VIew
    public void setMode(int mode){
        if(mode == mMode) return;
        checkMode(mode);
        mMode = mode;

        clearContentView();
        View mModeView = mModeViews[mode];
        if (mModeView == null) return;

        ViewGroup.LayoutParams mModeParam = mModeParams[mode];
        if (mModeParam == null) {
            mModeParam = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
            );
        }
        if (mContent == null) return;
        mContent.addView(mModeView,mModeParam);

        //如果是加载的View，就添加动画
//        if(mode==MODE_LOADING){
//            final ImageView mAnimationView = (ImageView) findViewById(R.id.h_x_loading);
////            Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(
////                    mContext, R.anim.loading_animation);
//            //mAnimationView.startAnimation(hyperspaceJumpAnimation);
//        }
    }

    private void checkMode(int mode) {
        if (mode != MODE_LOADING && mode != MODE_ERROR
                && mode != MODE_EMPTY && mode != MODE_CONTENT) {
            throw new IllegalStateException("illegal mode for content, please check StateViewHelper");
        }
    }

    public int getMode() {
        return mMode;
    }
}




