package com.zhuye.huochebangsiji.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.zhuye.huochebangsiji.R;
import com.zhuye.huochebangsiji.bean.Base;
import com.zhuye.huochebangsiji.helper.StateViewHelper;
import com.zhuye.huochebangsiji.widget.CustomProgressDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    View errorview;

    protected BaseRecycleAdapter adapter;

    private CustomProgressDialog dialog;

    @Override
    public void error(Object o) {
        Base base = (Base) o;
        toast(base.getMessage());
    }

    //记录当前栈里所有activity
    private List<Activity> activities = new ArrayList<Activity>();
    //记录需要一次性关闭的页面
    private static final List<Activity> activitys = new ArrayList<Activity>();


    protected Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            handlerData(msg);
        }
    };

    protected void handlerData(Message msg) {

    }


    protected StateViewHelper stateViewHelper;

    protected View rootview;
    protected View lodingview;
    protected View emptyview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestBefore();
        super.setContentView(getResId());
        unbinder=  ButterKnife.bind(this);

        dialog = new CustomProgressDialog(this, "加载中");
        dialog.setCanceledOnTouchOutside(false);
        lodingview = View.inflate(this,R.layout.loding,null);
        errorview = LayoutInflater.from(this).inflate(R.layout.error,null);
        emptyview = LayoutInflater.from(this).inflate(R.layout.empty,null);
       // errorview.setVisibility(View.INVISIBLE);
        initView();
        initData();
        initListener();

        //初始化 为ContentView
        stateViewHelper = new BaseStateViewWrapper(this);
        setMode(StateViewHelper.MODE_CONTENT);
        stateViewHelper.setContentRoot((ViewGroup) findViewById(R.id.container));
        //添加三种mode的View进去
//        stateViewHelper.setModeView(R.layout.loding, StateViewHelper.MODE_LOADING);
//        stateViewHelper.setModeView(R.layout.error, StateViewHelper.MODE_ERROR);
//        stateViewHelper.setModeView(R.layout.empty, StateViewHelper.MODE_EMPTY);


    }

    protected Context getContexta() {
        return null;
    }

    protected void requestBefore() {
    }

    protected  void initListener(){

    }

    protected  void initData(){

    }

    protected void initView() {
    }

    protected  Unbinder unbinder;


    protected abstract int getResId();

    public void toast(String content){
        Toast.makeText(this,content,Toast.LENGTH_SHORT).show();
    }

    public Boolean isEmpty(EditText et){
        if(TextUtils.isEmpty(et.getText().toString().trim())){
            return true;
        }else {
            return false;
        }
    }

    public Boolean isEmpty(String et){
        if(TextUtils.isEmpty(et.trim())){
            return true;
        }else {
            return false;
        }
    }

    public String  getString(EditText et){
       return et.getText().toString().trim();
    }

    @Override
    public void loding() {
//        lodingview.setVisibility(View.VISIBLE);
//        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
//        addContentView(lodingview,params);
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }
    }

    @Override
    public void finishLoding() {
        //lodingview.setVisibility(View.INVISIBLE);
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

//    @Override
//    public void error() {
//       // errorview.setVisibility(View.VISIBLE);
//
//        //toast();
//    }

    @Override
    public void success(int requestcode, Object o) {
       // errorview.setVisibility(View.INVISIBLE);
    }

    @Override
    public void empty() {
       // errorview.setVisibility(View.INVISIBLE);
    }



    @Override
    public void finish() {
        activitys.remove(this);
        super.finish();
    }

//    public void clearActivity(){
//        for(Class cls : activitys){
//            ((BaseActivity)cls).finish();
//        }
//    }
//




    protected Long exitTime = 0l;


    private class BaseStateViewWrapper extends StateViewHelper {

        public BaseStateViewWrapper(Context context) {
            super(context);
        }
        @Override
        public boolean onViewClear(View subView) {
            return subView != null ;
        }
    }


    //先去找父View一个级别的view，如果为null，则去找子VIew里面的view
    public View findViewById(int id){
        View view = super.findViewById(id);
        if(view!=null){
            return view;
        } else {
            return stateViewHelper.findViewById(id);
        }
    }

    //设置ContentVIew，子类调用
    public void setContentView(int layoutResID) {
        stateViewHelper.setContentView(layoutResID);
    }
    public void setContentView(View view) {
        stateViewHelper.setContentView(view);
    }
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        stateViewHelper.setContentView(view, params);
    }
    public void setModeView(View view, int mode) {
        stateViewHelper.setModeView(view, mode);
    }
    public void setModeView(int view, int mode) {
        stateViewHelper.setModeView(view, mode);
    }
    public void setMode(int mode) {
        stateViewHelper.setMode(mode);
    }
    public int getMode() {
        return stateViewHelper.getMode();
    }

    /**
     * 新建了一个activity
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        activitys.add(activity);
    }


    /**
     * 结束指定的Activity
     *
     * @param activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            this.activities.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /*
   *给临时Activitys
   * 添加activity
   * */
    public void addTemActivity(Activity activity) {
        activitys.add(activity);
    }

    public void finishTemActivity(Activity activity) {
        if (activity != null) {
            this.activitys.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /*
    * 退出指定的Activity
    * */
    public void exitActivitys() {
        for (Activity activity : activitys) {
            if (activity != null) {
                activity.finish();
            }
        }
    }

    /**
     * 应用退出，结束所有的activity
     */
    public void exit() {
        for (Activity activity : activitys) {
            if (activity != null) {
                activity.finish();
            }
        }
        System.exit(0);
    }

}
