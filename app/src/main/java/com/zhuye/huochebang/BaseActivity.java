package com.zhuye.huochebang;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.zhuye.huochebang.bean.Base;
import com.zhuye.huochebang.bean.BaseView;
import com.zhuye.huochebang.wodiget.CustomProgressDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {


    protected Handler mHandler = new Handler();

    protected int page = 1;

    protected View errorview;
    protected View lodingview;
    protected View emptyview;
    private CustomProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getResId());
        unbinder=  ButterKnife.bind(this);

        lodingview = View.inflate(this,R.layout.loding,null);
        errorview = LayoutInflater.from(this).inflate(R.layout.error,null);

        dialog = new CustomProgressDialog(this, "加载中");
        dialog.setCanceledOnTouchOutside(false);
        initView();
        initData();
        initListener();
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
   protected ViewGroup.LayoutParams parama  = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    public String  getString(EditText et){
       return et.getText().toString().trim();
    }


//    //返回键处理
//    Long time;
//    @Override
//    public void onBackPressed() {
//       // super.onBackPressed();
//        Long curtime =  SystemClock.currentThreadTimeMillis();
//        if(time ==null || time - curtime > 30000){
//            toast("确定吗");
//            return;
//        }
//       if(time -curtime<30000){
//           super.onBackPressed();
//       }
//    }


    //activity 集合处理


    @Override
    public void loding() {
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }
    }

    @Override
    public void finishLoding() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    @Override
    public void error(Object o) {
        Base base = (Base) o;
        toast(base.getMessage());
    }

    @Override
    public void empty() {

    }

    @Override
    public void success(int requestcode, Object o) {

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
    //记录当前栈里所有activity
    private List<Activity> activities = new ArrayList<Activity>();
    //记录需要一次性关闭的页面
    private static final List<Activity> activitys = new ArrayList<Activity>();
}
