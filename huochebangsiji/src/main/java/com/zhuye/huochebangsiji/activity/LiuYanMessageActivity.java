package com.zhuye.huochebangsiji.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhuye.huochebangsiji.R;
import com.zhuye.huochebangsiji.adapter.LiuYanAdapter;
import com.zhuye.huochebangsiji.base.BaseActivity;
import com.zhuye.huochebangsiji.bean.LiuYanBean;
import com.zhuye.huochebangsiji.data.GetData;
import com.zhuye.huochebangsiji.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.http.PATCH;

public class LiuYanMessageActivity extends BaseActivity {


    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.liuyanrv)
    RecyclerView liuyanrv;
    @BindView(R.id.tou)
    RelativeLayout tou;
    @BindView(R.id.header)
    ClassicsHeader header;
    @BindView(R.id.footer)
    ClassicsFooter footer;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    private int page = 1;
    private List<LiuYanBean.DataBean> dataBeans = new ArrayList<>();

    @Override
    protected int getResId() {
        return R.layout.activity_liu_yan_message;
    }

    LiuYanAdapter adapter;

    @Override
    protected void initView() {
        super.initView();
        adapter = new LiuYanAdapter(this);
        liuyanrv.setAdapter(adapter);
        liuyanrv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initData() {
        super.initData();
        GetData.liuyanmessage(SharedPreferencesUtil.getInstance().getString("token"), page, this, 1000);
    }

    @Override
    public void success(int requestcode, Object o) {
        switch (requestcode) {
            case 1000:
                dataBeans.clear();
                LiuYanBean liuYanBean = (LiuYanBean) o;
                dataBeans.addAll(liuYanBean.getData());
                adapter.addData(dataBeans);
                break;
            case 1002:
                LiuYanBean liuYanBeanss = (LiuYanBean) o;
                dataBeans.addAll(liuYanBeanss.getData());
                adapter.addData(dataBeans);
                smartRefreshLayout.finishLoadmore();
            case 1001:
                dataBeans.clear();
                LiuYanBean liuYanBeans = (LiuYanBean) o;
                dataBeans.addAll(liuYanBeans.getData());
                adapter.addData(dataBeans);
                smartRefreshLayout.finishRefresh();
                break;

        }

    }


    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void initListener() {
        super.initListener();
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        smartRefreshLayout.finishRefresh();
                    }
                }, 2000);
                page = 1;
                GetData.liuyanmessage(SharedPreferencesUtil.getInstance().getString("token"), page, LiuYanMessageActivity.this, 1001);
            }
        });

        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        smartRefreshLayout.finishLoadmore();
                    }
                }, 2500);
                page++;
                GetData.liuyanmessage(SharedPreferencesUtil.getInstance().getString("token"), page, LiuYanMessageActivity.this, 1002);
            }
        });
    }
}