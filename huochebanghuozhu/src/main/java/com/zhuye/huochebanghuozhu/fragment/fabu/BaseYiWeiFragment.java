package com.zhuye.huochebanghuozhu.fragment.fabu;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhuye.huochebanghuozhu.R;
import com.zhuye.huochebanghuozhu.activity.HuaWuActivity;
import com.zhuye.huochebanghuozhu.adapter.fabu.DetailItemAdapter;
import com.zhuye.huochebanghuozhu.base.BaseHolder;
import com.zhuye.huochebanghuozhu.bean.GoodsDeailBean;
import com.zhuye.huochebanghuozhu.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.Unbinder;


/**
 * Created by Administrator on 2018/1/19 0019.
 */

public abstract class BaseYiWeiFragment extends BaseFragment {


    protected static final int REFRESH = 14;
    protected static final int LOADMOEW = 15;
    protected int page = 1;
    protected static final int WEIJIE = 10;
    protected static final int YIJIE = 11;
    @BindView(R.id.godds_detail_rv)
    RecyclerView goddsDetailRv;
    Unbinder unbinder;
    @BindView(R.id.header)
    ClassicsHeader header;
    @BindView(R.id.footer)
    ClassicsFooter footer;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    Unbinder unbinder1;

    @Override
    protected void initView() {
        adapter = new DetailItemAdapter(getActivity());
        goddsDetailRv.setAdapter(adapter);
        goddsDetailRv.setLayoutManager(new LinearLayoutManager(getActivity()));
    }


    @Override
    protected int getResId() {
        return R.layout.fragment_weijie;
    }

    GoodsDeailBean bean;




    @Override
    protected void initListener() {
        super.initListener();
        adapter.setOnItemClickListener(new BaseHolder.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), HuaWuActivity.class);
                intent.putExtra("good_id", bean.getData().get(position).getGood_id());
                startActivity(intent);

            }
        });

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refresh();
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        smartRefreshLayout.finishRefresh();
                    }
                }, 2500);
            }
        });

        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        smartRefreshLayout.finishRefresh();
                    }
                }, 2500);
                loadmore();
            }
        });
    }

    protected abstract void loadmore();

    protected abstract void refresh();


    protected Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };

}



    

