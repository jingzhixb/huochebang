package com.zhuye.huochebangsiji.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhuye.huochebangsiji.R;
import com.zhuye.huochebangsiji.adapter.OilCardHomeAdapter2;
import com.zhuye.huochebangsiji.base.BaseActivity;
import com.zhuye.huochebangsiji.bean.OilCardListBean;
import com.zhuye.huochebangsiji.data.GetData;
import com.zhuye.huochebangsiji.utils.SharedPreferencesUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class YouKaChonZhiActivity extends BaseActivity implements View.OnClickListener {
    private static final int OILCARD = 10;

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.yue)
    TextView yue;
    @BindView(R.id.chongzhi)
    Button chongzhi;
    @BindView(R.id.shouzhijilu)
    TextView shouzhijilu;
    @BindView(R.id.jiaoyijilu)
    RecyclerView jiaoyijilu;
    @BindView(R.id.yuezhen)
    TextView yuezhen;
//    @BindView(R.id.jiege)
//    TextView jiege;
//    @BindView(R.id.close)
//    ImageView close;
//    @BindView(R.id.yuea)
//    RelativeLayout yuea;
//    @BindView(R.id.xuanjifen)
//    ImageView xuanjifen;
//    @BindView(R.id.xuanalipay)
//    ImageView xuanalipay;
//    @BindView(R.id.alipay)
//    RelativeLayout alipay;
//    @BindView(R.id.xuanweixin)
//    ImageView xuanweixin;
//    @BindView(R.id.weixin)
//    RelativeLayout weixin;
//    @BindView(R.id.xuanyinlian)
//    ImageView xuanyinlian;
//    @BindView(R.id.yinlian)
//    RelativeLayout yinlian;
//    @BindView(R.id.queding)
//    Button queding;

    @Override
    protected int getResId() {
        return R.layout.activity_you_ka_chon_zhi;
    }


    OilCardHomeAdapter2 mOilCardHomeAdapter2;

    @Override
    protected void initView() {
        super.initView();
        mOilCardHomeAdapter2 = new OilCardHomeAdapter2(R.layout.oilcard_item);
        jiaoyijilu.setAdapter(mOilCardHomeAdapter2);
        jiaoyijilu.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initData() {
        super.initData();
        initWindows();
        GetData.oilcard(SharedPreferencesUtil.getInstance().getString("token"), this, OILCARD);
    }

    OilCardListBean oilCardListBean;

    @Override
    public void success(int requestcode, Object o) {
        super.success(requestcode, o);
        switch (requestcode) {
            case OILCARD:
                oilCardListBean = (OilCardListBean) o;
                // yue.setText(oilCardListBean.getData().getMoney());
                yuezhen.setText(oilCardListBean.getData().getMoney());
                mOilCardHomeAdapter2.addData(oilCardListBean.getData().getOilcard_log());
                break;
        }
    }

    @OnClick({R.id.back, R.id.chongzhi, R.id.shouzhijilu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.chongzhi:
                Intent intent = new Intent(YouKaChonZhiActivity.this, ChongZhiActivity.class);
                intent.putExtra("money", oilCardListBean.getData().getMoney());
                intent.putExtra("yue", oilCardListBean.getData().getBalance_money());
                intent.putExtra("hao", oilCardListBean.getData().getCord());
                startActivity(intent);

               // aliertWindow(view);
                break;

            case R.id.shouzhijilu:
                startActivity(new Intent(YouKaChonZhiActivity.this, ShouzhijulvActivity.class));
                break;
        }
    }

    PopupWindow window;

    private void aliertWindow(View view) {
        WindowManager.LayoutParams lp = YouKaChonZhiActivity.this.getWindow().getAttributes();
        lp.alpha = 0.4f;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
        window.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    public void initWindows() {
        window = new PopupWindow(YouKaChonZhiActivity.this);
        window.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        window.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        View search = View.inflate(YouKaChonZhiActivity.this, R.layout.bottom_zhifu, null);
        ButterKnife.bind(search);
        TextView jiege = search.findViewById(R.id.jiege);
        ImageView close = search.findViewById(R.id.close);

        RelativeLayout yuea = search.findViewById(R.id.yuea);
        xuanyue= search.findViewById(R.id.xuanyue);
        yuea.setOnClickListener(YouKaChonZhiActivity.this);
        RelativeLayout jifen = search.findViewById(R.id.jifen);
        //ImageView xuanjifen = search.findViewById(R.id.xuanjifen);
        jifen.setVisibility(View.GONE);
        RelativeLayout alipay = search.findViewById(R.id.alipay);
        xuanalipay = search.findViewById(R.id.xuanalipay);
        alipay.setOnClickListener(YouKaChonZhiActivity.this);
        final RelativeLayout weixin = search.findViewById(R.id.weixin);
        xuanweixin = search.findViewById(R.id.xuanweixin);
        weixin.setOnClickListener(YouKaChonZhiActivity.this);
//        RelativeLayout yinlian = search.findViewById(R.id.yinlian);
//        xuanyinlian = search.findViewById(R.id.xuanyinlian);
//        yinlian.setOnClickListener(YouKaChonZhiActivity.this);
        Button queding = search.findViewById(R.id.queding);
        queding.setOnClickListener(YouKaChonZhiActivity.this);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(window!=null&&window.isShowing()){
                    window.dismiss();
                }
            }
        });

        window.setContentView(search);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setOutsideTouchable(true);
        window.setFocusable(true);
        window.update();

        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            // 在dismiss中恢复透明度
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                getWindow().setAttributes(lp);
            }
        });

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

     int position;
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.yuea:
                position = 1;
              //  toast(position+"");
                selectBackground(position);
                break;
            case R.id.alipay:
                position = 2;
              //  toast(position+"");
                selectBackground(position);
                break;
            case R.id.weixin:
                position = 3;
              //  toast(position+"");
                selectBackground(position);
                break;
//            case R.id.yinlian:
//                position = 4;
//               // toast(position+"");
//                selectBackground(position);
//                break;
            case R.id.queding:

                break;
        }
    }

    ImageView xuanyue;
    ImageView xuanalipay;
    ImageView xuanweixin;
//    ImageView xuanyinlian;
    private void selectBackground(int position) {
        xuanyue.setImageResource(position==1 ?R.drawable.gouxuan_on:R.drawable.gouxuan_off);
        xuanalipay.setImageResource(position==2 ?R.drawable.gouxuan_on:R.drawable.gouxuan_off);
        xuanweixin.setImageResource(position==3 ?R.drawable.gouxuan_on:R.drawable.gouxuan_off);
//        xuanyinlian.setImageResource(position==4 ?R.drawable.gouxuan_on:R.drawable.gouxuan_off);
    }
}
