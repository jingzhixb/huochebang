package com.zhuye.huochebangsiji.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhuye.huochebangsiji.R;
import com.zhuye.huochebangsiji.adapter.JiaMengAdapter2;
import com.zhuye.huochebangsiji.base.BaseActivity;
import com.zhuye.huochebangsiji.bean.Code;
import com.zhuye.huochebangsiji.bean.JiaMengBean;
import com.zhuye.huochebangsiji.data.GetData;
import com.zhuye.huochebangsiji.utils.SharedPreferencesUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import scut.carson_ho.searchview.SearchView;


public class JiaMengListActivity extends BaseActivity {


    private static final int JOIN_LIST = 10;
    private static final int JOIN = 11;
    @BindView(R.id.back)
    ImageView back;

    @BindView(R.id.jiameng_rv)
    RecyclerView jiamengRv;
    @BindView(R.id.refreshlayout)
    SmartRefreshLayout refreshlayout;
    @BindView(R.id.header)
    ClassicsHeader header;
    @BindView(R.id.footer)
    ClassicsFooter footer;
    @BindView(R.id.search_view)
    SearchView searchView;


    @Override
    protected int getResId() {
        return R.layout.activity_jia_meng_list;
    }

    JiaMengAdapter2 mJiaMengAdapter2;
    @Override
    protected void initView() {
        super.initView();
        // header.setVisibility(View.GONE);
        jiamengRv.setLayoutManager(new LinearLayoutManager(this));
        mJiaMengAdapter2 = new JiaMengAdapter2(R.layout.jiameng_item);
        jiamengRv.setAdapter(mJiaMengAdapter2);
        refreshlayout.setRefreshContent(jiamengRv);
        //refreshlayout.autoRefresh();
        // refreshlayout.finishLoadmore();
        //refreshlayout.finishRefresh();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshlayout.finishRefresh();
            }
        }, 1000);

        footer.getArrowView().setVisibility(View.GONE);
        footer.getProgressView().setVisibility(View.GONE);
    }


    @Override
    protected void initListener() {
        super.initListener();

        refreshlayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayo) {
                refreshlayout.finishLoadmore();
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshlayo.finishRefresh();

                    }
                }, 1000);
            }
        });

        refreshlayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(final RefreshLayout refreshlayout) {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshlayout.finishLoadmore();

                    }
                }, 1000);
            }
        });


//        adapter.setOnItemClickListener(new BaseHolder.OnItemClickListener() {
//            @Override
//            public void OnItemClick(View view, final int position) {
//                view.findViewById(R.id.refuse).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        GetData.apply_jion(SharedPreferencesUtil.getInstance().getString("token"), Integer.parseInt(data.getData().get(position).getJion_id())
//                                , JiaMengListActivity.this, JOIN);
//                    }
//                });
//            }
//        });


        mJiaMengAdapter2.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.refuse:
                        GetData.apply_jion(SharedPreferencesUtil.getInstance().getString("token"), Integer.parseInt(data.getData().get(position).getJion_id())
                                , JiaMengListActivity.this, JOIN);
                        mButton = (Button) view;
                        break;


                }
            }
        });

        //searchView
    }


    Button mButton;

    @Override
    protected void initData() {
        super.initData();
        GetData.jion_list(SharedPreferencesUtil.getInstance().getString("token"), 1, this, JOIN_LIST);
    }

    Code code;
    JiaMengBean data;

    @Override
    public void success(int requestcode, Object o) {
        super.success(requestcode, o);
        switch (requestcode) {
            case JOIN_LIST:
                data = (JiaMengBean) o;
                mJiaMengAdapter2.addData(data.getData());
                break;

            case JOIN:
                code = (Code) o;
                toast(code.getMessage());
                mButton.setText("已申请");
                mButton.setEnabled(false);
                break;
        }
    }

    @OnClick({R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        // if(keyCode ==KeyEvent.)
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
