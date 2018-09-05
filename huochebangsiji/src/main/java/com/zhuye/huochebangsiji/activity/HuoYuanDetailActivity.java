package com.zhuye.huochebangsiji.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhuye.huochebangsiji.R;
import com.zhuye.huochebangsiji.base.BaseActivity;
import com.zhuye.huochebangsiji.bean.Code;
import com.zhuye.huochebangsiji.bean.GoodDetailBean;
import com.zhuye.huochebangsiji.contants.Contans;
import com.zhuye.huochebangsiji.data.GetData;
import com.zhuye.huochebangsiji.utils.DensityUtil;
import com.zhuye.huochebangsiji.utils.SharedPreferencesUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HuoYuanDetailActivity extends BaseActivity {


    private static final int GOODDETAIL = 10;
    private static final int JIEDAN = 11;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.map)
    RelativeLayout map;

    @Override
    protected int getResId() {
        return R.layout.activity_huo_yuan_detail;
    }


    String good_id;

    @Override
    protected void initView() {
        super.initView();
        good_id = getIntent().getStringExtra("good_id");

    }

    @Override
    protected void initData() {
        super.initData();
        initWindows();
        GetData.good_detail(SharedPreferencesUtil.getInstance().getString("token"), Integer.parseInt(good_id), this, GOODDETAIL);
    }

    PopupWindow window;

    TextView name;
    TextView mingcheng;
    TextView zhongliang;
    TextView shifa;
    TextView mudi;
    TextView cheliang;
    TextView yunfei;
    TextView lianxi;
    TextView shuoming;
    TextView xiehuo;
    ImageView touxing;
    Button jiedan;
    View search;
    private void initWindows() {
        window = new PopupWindow(this);
        window.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        window.setHeight(DensityUtil.dip2px(this, 380));
        search= View.inflate(this, R.layout.huoyuandetali_bottom, null);
        name = (TextView) search.findViewById(R.id.jinfen_name);
        mingcheng = (TextView) search.findViewById(R.id.mingcheng);
        zhongliang = (TextView) search.findViewById(R.id.zhongliang);
        shifa = (TextView) search.findViewById(R.id.shifa);
        mudi = (TextView) search.findViewById(R.id.mudi);
        cheliang = (TextView) search.findViewById(R.id.cheliang);
        yunfei = (TextView) search.findViewById(R.id.yunfei);
        lianxi = (TextView) search.findViewById(R.id.lianxi);
        shuoming = (TextView) search.findViewById(R.id.shuoming);
        xiehuo = (TextView) search.findViewById(R.id.xiehuo);
        touxing = (ImageView) search.findViewById(R.id.jifen_touxiang);
        jiedan = (Button) search.findViewById(R.id.jiedan);

        jiedan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetData.order(SharedPreferencesUtil.getInstance().getString("token"), Integer.parseInt(good_id),HuoYuanDetailActivity.this,JIEDAN);
            }
        });

        window.setContentView(search);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setOutsideTouchable(true);
        window.setFocusable(true);

    }

    GoodDetailBean gooddetailbean;

    Code message;
    @Override
    public void success(int requestcode, Object o) {
        super.success(requestcode, o);
        switch (requestcode) {
            case GOODDETAIL:
                gooddetailbean = (GoodDetailBean) o;
                name.setText(gooddetailbean.getData().getName());
                mingcheng.setText(gooddetailbean.getData().getType_name());
                zhongliang.setText(gooddetailbean.getData().getWeight());
                shifa.setText(gooddetailbean.getData().getStart_addr());
                cheliang.setText(gooddetailbean.getData().getCar_require());
                yunfei.setText(gooddetailbean.getData().getPrice());
                lianxi.setText(gooddetailbean.getData().getMobile());
                shuoming.setText(gooddetailbean.getData().getIntro());
                xiehuo.setText(gooddetailbean.getData().getTime());
                //name.setText(gooddetailbean.getData().getName());
                Glide.with(HuoYuanDetailActivity.this).load(Contans.BASE_URL + gooddetailbean.getData().getFace()).into(touxing);
                break;

            case JIEDAN:
                message = (Code) o;
                toast(message.getMessage());
                jiedan.setText("已接单");
                jiedan.setClickable(false);

                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.back, R.id.map})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.map:
                if(window!=null){
                    window.showAtLocation(search, Gravity.BOTTOM,0,0);
                }
                break;
        }
    }
}
