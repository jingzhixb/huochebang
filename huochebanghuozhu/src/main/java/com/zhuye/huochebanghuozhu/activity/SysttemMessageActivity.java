package com.zhuye.huochebanghuozhu.activity;

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
import com.zhuye.huochebanghuozhu.R;
import com.zhuye.huochebanghuozhu.adapter.me.SystemMessageAdapter;
import com.zhuye.huochebanghuozhu.base.BaseActivity;
import com.zhuye.huochebanghuozhu.bean.SystemMessage;
import com.zhuye.huochebanghuozhu.data.GetData;
import com.zhuye.huochebanghuozhu.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SysttemMessageActivity extends BaseActivity {

    private static final int VIEWS = 987;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.system_message)
    RecyclerView systemMessage;
    SystemMessageAdapter adapter;
    @BindView(R.id.tou)
    RelativeLayout tou;
    @BindView(R.id.header)
    ClassicsHeader header;
    @BindView(R.id.footer)
    ClassicsFooter footer;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    private int page = 1;
    private List<SystemMessage.DataBean> dataBeans = new ArrayList<>();
    @Override
    protected int getResId() {
        return R.layout.activity_systtem_message;
    }

    @Override
    protected void initView() {
        super.initView();

        systemMessage.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    protected void initData() {
        super.initData();
        GetData.system_news(page, this, 1000);
        GetData.view_news(SharedPreferencesUtil.getInstance().getString("token"),1,this,VIEWS);
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void success(int requestcode, Object o) {
        switch (requestcode){

            case VIEWS:
//                toast();
                break;
            case 1000:
                dataBeans.clear();
                SystemMessage message = (SystemMessage) o;
                dataBeans.addAll(message.getData());
                if(dataBeans==null){
                    return;
                }
                try {
                    adapter.addData(dataBeans);
                }catch (Exception e){

                }

                break;
            case  1001:
                dataBeans.clear();
                SystemMessage messages = (SystemMessage) o;
                dataBeans.addAll(messages.getData());
//                adapter.addData(dataBeans);
                try {
                    adapter.addData(dataBeans);
                }catch (Exception e){

                }
                smartRefreshLayout.finishRefresh();
                break;
            case 1002:
                SystemMessage messagess = (SystemMessage) o;
                dataBeans.addAll(messagess.getData());
                adapter = new SystemMessageAdapter(R.layout.system_message_item,messagess.getData());
                systemMessage.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                smartRefreshLayout.finishLoadmore();
                break;
        }
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
                GetData.system_news( page, SysttemMessageActivity.this, 1001);
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
                GetData.system_news( page, SysttemMessageActivity.this, 1002);
            }
        });
    }
}
