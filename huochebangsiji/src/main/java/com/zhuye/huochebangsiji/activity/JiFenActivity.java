package com.zhuye.huochebangsiji.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.bumptech.glide.Glide;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhuye.huochebangsiji.R;
import com.zhuye.huochebangsiji.adapter.JinFenAdapter;
import com.zhuye.huochebangsiji.base.BaseActivity;
import com.zhuye.huochebangsiji.base.BaseHolder;
import com.zhuye.huochebangsiji.bean.AliPayBean;
import com.zhuye.huochebangsiji.bean.Code;
import com.zhuye.huochebangsiji.bean.MessageEvent;
import com.zhuye.huochebangsiji.bean.VipListBean;
import com.zhuye.huochebangsiji.contants.Contans;
import com.zhuye.huochebangsiji.data.GetData;
import com.zhuye.huochebangsiji.utils.SharedPreferencesUtil;
import com.zhuye.huochebangsiji.widget.RoundedCornerImageView;
import com.zhuye.huochebangsiji.wxapi.PayEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JiFenActivity extends BaseActivity {


    private static final int VIP = 10;
    private static final int MAIVIP = 11;
    private static final int QUANXIAN = 12;
    private static final int SDK_PAY_FLAG = 13;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.jifen_touxiang)
    RoundedCornerImageView jifenTouxiang;
    @BindView(R.id.jinfen_name)
    TextView jinfenName;
    @BindView(R.id.jinfen_jifenshu)
    TextView jinfenJifenshu;
    @BindView(R.id.jinfen_rv)
    RecyclerView jinfenRv;
    @BindView(R.id.jinfen_jine)
    TextView jinfenJine;
    @BindView(R.id.jifen_pay)
    Button jifenPay;

    JinFenAdapter adapter;
    @BindView(R.id.jifen_quanxina)
    TextView jifenQuanxina;
    private int vip_id = 0;
    private String money = "";

    @Override
    protected int getResId() {
        return R.layout.activity_ji_fen;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void initView() {
        super.initView();
        adapter = new JinFenAdapter(this);
        jinfenRv.setAdapter(adapter);
        jinfenRv.setLayoutManager(new GridLayoutManager(this, 4));
        EventBus.getDefault().register(this);
    }


    String jifen;
    String face;
    String name;

    @Override
    protected void initData() {
        super.initData();
        jifen = getIntent().getStringExtra("jifen");
        face = getIntent().getStringExtra("face");
        name = getIntent().getStringExtra("name");
        jinfenName.setText(name);
        jinfenJifenshu.setText("积分:"+jifen);
        Glide.with(this).load(Contans.BASE_URL+face).into(jifenTouxiang);
        GetData.vip("", this, VIP);
    }

    VipListBean viplistbean;
    Code result;

    @Override
    public void success(int requestcode, Object o) {
        super.success(requestcode, o);
        switch (requestcode) {
            case VIP:
                viplistbean = (VipListBean) o;
                adapter.addData(viplistbean.getData());
                for (int i = 0; i < viplistbean.getData().size(); i++) {
                    viplistbean.getData().get(i).setSelected(false);
                }
                break;
            case MAIVIP:
//                AliPayBean ji = (AliPayBean) o;
//               final String url = ji.getData();
////                result = (Code) o;
//                //toast(result.getData() + result.getMessage());
//
//                Runnable payRunnable = new Runnable() {
//
//                    @Override
//                    public void run() {
//                        PayTask alipay = new PayTask(JiFenActivity.this);
//                        Map<String, String> resul = alipay.payV2(url, true);
//                        Log.i("msp", resul.toString());
//
//                        Message msg = new Message();
//                        msg.what = Contans.SDK_PAY_FLAG;
//                        msg.obj = resul;
//                        mHandler.sendMessage(msg);
//                    }
//                };
//                // 必须异步调用
//                Thread payThread = new Thread(payRunnable);
//                payThread.start();
                break;

            case QUANXIAN:


                break;
        }
    }
    Integer selectedpos = 0;

    @Override
    protected void initListener() {
        super.initListener();
        adapter.setOnItemClickListener(new BaseHolder.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                for (int i = 0; i < viplistbean.getData().size(); i++) {
                    if (i == position) {
                        viplistbean.getData().get(i).setSelected(true);
                    } else {
                        viplistbean.getData().get(i).setSelected(false);
                    }
                }
                vip_id = Integer.valueOf(viplistbean.getData().get(position).getVip_id());
                money = viplistbean.getData().get(position).getMoney();
                selectedpos = position + 1;
                jinfenJine.setText(viplistbean.getData().get(position).getMoney());
                adapter.addData2(viplistbean.getData(), 0);
            }
        });
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(PayEvent event) {
        /* Do something */
            if (event.code ==2){
             finish();
            }
    };


    @OnClick({R.id.back, R.id.jifen_pay,R.id.jifen_quanxina})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.jifen_pay:
                if (selectedpos == 0) {
                    toast("请选择金额");
                    return;
                }
                Intent intent = new Intent(JiFenActivity.this,PayActivity.class);
                intent.putExtra("vip_id",vip_id);
                intent.putExtra("momney",money);
                startActivity(intent);
                break;

            case R.id.jifen_quanxina:
//                GetData.index("",this,QUANXIAN);
                OkGo.<String>post(Contans.BASE_URL+"/index.php/home/article/index")
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                Log.i("asd",response.body());
                                String html = response.body();
                                Intent  intent = new Intent(JiFenActivity.this,VipDetailActivity.class);
                                intent.putExtra("html",html);
                                startActivity(intent);
//                toast(html);
                            }
                        });
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
