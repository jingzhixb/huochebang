package com.zhuye.huochebangsiji.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhuye.huochebangsiji.R;
import com.zhuye.huochebangsiji.base.BaseActivity;
import com.zhuye.huochebangsiji.bean.MessageEvent;
import com.zhuye.huochebangsiji.data.GetData;
import com.zhuye.huochebangsiji.utils.SharedPreferencesUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessageActivity extends BaseActivity {

    private static final int VIEWSYS = 100;
    private static final int VIEWPERSON = 101;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.tou)
    RelativeLayout tou;
    @BindView(R.id.image1)
    ImageView image1;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.xitongmessage)
    RelativeLayout xitongmessage;
    @BindView(R.id.image2)
    ImageView image2;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.liuyanmessage)
    RelativeLayout liuyanmessage;
    @BindView(R.id.syetemnum)
    TextView syetemnum;
    @BindView(R.id.personnum)
    TextView personnum;

    @Override
    protected int getResId() {
        return R.layout.activity_message;
    }

    @OnClick({R.id.back, R.id.xitongmessage, R.id.liuyanmessage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.xitongmessage:
                startActivity(new Intent(MessageActivity.this, SysttemMessageActivity.class));
                GetData.view_news(SharedPreferencesUtil.getInstance().getString("token"),1,MessageActivity.this,VIEWSYS);
                break;
            case R.id.liuyanmessage:
               // GetData.view_news(SharedPreferencesUtil.getInstance().getString("token"),1,MessageActivity.this,VIEWPERSON);
                startActivity(new Intent(MessageActivity.this, LiuYanMessageActivity.class));
                break;
        }
    }




    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {/* Do something */
       // toast("手袋了");
    };


    @Override
    protected void initView() {
        super.initView();
        String system = getIntent().getStringExtra("system");
        String person = getIntent().getStringExtra("person");

        syetemnum.setText(system.equals("0")?"":system);
        personnum.setText(person.equals("0")?"":person);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @Override
    public void success(int requestcode, Object o) {
        super.success(requestcode, o);
        switch (requestcode){
            case  VIEWSYS:
                syetemnum.setText("");
                break;
            case VIEWPERSON:
                personnum.setText("");
                break;
        }
    }
}
