package com.zhuye.huochebang.adapter;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhuye.huochebang.R;
import com.zhuye.huochebang.base.BaseHolder;
import com.zhuye.huochebang.base.BaseRecycleAdapter;
import com.zhuye.huochebang.bean.DriverListBean;
import com.zhuye.huochebang.contants.Contans;
import com.zhuye.huochebang.data.http.CommonApi;
import com.zhuye.huochebang.ui.SiJiShenqingActivity;
import com.zhuye.huochebang.utils.DensityUtil;
import com.zhuye.huochebang.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2018/1/4 0004.
 */

public class SiJIShenAdaper extends BaseQuickAdapter<DriverListBean.DataBean, BaseViewHolder> {
    public SiJIShenAdaper(int layoutResId, @Nullable List<DriverListBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DriverListBean.DataBean item) {
        AppCompatImageView tou = helper.getView(R.id.tou);
        Glide.with(mContext).load(Contans.BASE_URL+item.getFace()).into(tou);
        helper.setText(R.id.name,item.getName());
        helper.addOnClickListener(R.id.agreeaa);
        helper.addOnClickListener(R.id.refuse);
//        ((TextView)holder.getView(R.id.name)).setText(((DriverListBean.DataBean)data.get(position)).getName());
////        ((TextView)holder.getView(R.id.name)).setText(((DriverListBean.DataBean)data.get(position)).getName());
//        //((TextView)holder.getView(R.id.name)).setText(((DriverListBean.DataBean)data.get(position)).getName());
    }


//    public SiJIShenAdaper(Context conn) {
//        super(conn);
//    }
//
//    @Override
//    protected int getResId() {
//        return R.layout.sijishenitem;
//    }
//
//    @Override
//    protected void conver(BaseHolder holder, final int position) {
//
//        AppCompatImageView tou = holder.getView(R.id.tou);
//        Glide.with(conn).load(Contans.BASE_URL+((DriverListBean.DataBean)data.get(position)).getFace()).into(tou);
//        ((TextView)holder.getView(R.id.name)).setText(((DriverListBean.DataBean)data.get(position)).getName());
////        ((TextView)holder.getView(R.id.name)).setText(((DriverListBean.DataBean)data.get(position)).getName());
//        //((TextView)holder.getView(R.id.name)).setText(((DriverListBean.DataBean)data.get(position)).getName());
////        holder.getView(R.id.agreeaa).setOnClickListener(new View.OnClickListener() {//同意
////            @Override
////            public void onClick(View view) {
//////                SiJiShenqingActivity.alertbotom(position);
////            }
////        });
//    }
}
