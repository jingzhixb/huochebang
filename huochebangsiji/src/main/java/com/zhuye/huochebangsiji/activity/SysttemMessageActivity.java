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
import com.zhuye.huochebangsiji.adapter.SystemMessageAdapter2;
import com.zhuye.huochebangsiji.base.BaseActivity;
import com.zhuye.huochebangsiji.bean.LiuYanBean;
import com.zhuye.huochebangsiji.bean.MessageEvent;
import com.zhuye.huochebangsiji.bean.SystemMessage;
import com.zhuye.huochebangsiji.data.GetData;
import com.zhuye.huochebangsiji.utils.SharedPreferencesUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SysttemMessageActivity extends BaseActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.system_message)
    RecyclerView systemMessage;
    SystemMessageAdapter2 adapter;
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
        adapter = new SystemMessageAdapter2(R.layout.system_message_item);
        systemMessage.setAdapter(adapter);
        systemMessage.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initData() {
        super.initData();
        MessageEvent messageEvent = new MessageEvent();
        messageEvent.setNum(1000);
        messageEvent.setType(1);
        EventBus.getDefault().post(messageEvent);
        GetData.system_news(page, this, 1000);
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void success(int requestcode, Object o) {
        switch (requestcode){
            case 1000:
                dataBeans.clear();
                SystemMessage message = (SystemMessage) o;
                dataBeans.addAll(message.getData());
                adapter.addData(dataBeans);
                break;
            case  1001:
                dataBeans.clear();
                SystemMessage messages = (SystemMessage) o;
                dataBeans.addAll(messages.getData());
                adapter.addData(dataBeans);
                smartRefreshLayout.finishRefresh();
                break;
            case 1002:
                SystemMessage messagess = (SystemMessage) o;
                dataBeans.addAll(messagess.getData());
                adapter.addData(dataBeans);
                smartRefreshLayout.finishLoadmore();
                break;
        }

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


//    @Override
//    public void onStart() {
//        super.onStart();
//        EventBus.getDefault().register(this);
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        EventBus.getDefault().unregister(this);
//    }
//}

}
