package com.zhuye.huochebanghuozhu.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhuye.huochebanghuozhu.R;
import com.zhuye.huochebanghuozhu.base.BaseActivity;
import com.zhuye.huochebanghuozhu.bean.GoodsBean;
import com.zhuye.huochebanghuozhu.data.GetData;
import com.zhuye.huochebanghuozhu.utils.SharedPreferencesUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HuaWuActivity extends BaseActivity {


    private static final int GOOD_DETIAL = 10;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.mingcheng)
    TextView mingcheng;
    @BindView(R.id.zhongliang)
    TextView zhongliang;
    @BindView(R.id.shifa)
    TextView shifa;
    @BindView(R.id.mudi)
    TextView mudi;
    @BindView(R.id.cheliang)
    TextView cheliang;
    @BindView(R.id.yunfei1)
    TextView yunfei1;
    @BindView(R.id.fabuaio)
    TextView fabuaio;
    @BindView(R.id.lianxi)
    TextView lianxi;
    @BindView(R.id.shuoming)
    TextView shuoming;
    @BindView(R.id.xiehuo)
    TextView xiehuo;
    @BindView(R.id.yunfei2)
    TextView yunfei2;

    @Override
    protected int getResId() {
        return R.layout.activity_hua_wu;
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
        GetData.good_detail(Integer.parseInt(good_id), SharedPreferencesUtil.getInstance().getString("token"), this, GOOD_DETIAL);
    }


    @Override
    public void success(int requestcode, Object o) {
        super.success(requestcode, o);
        switch (requestcode) {
            case GOOD_DETIAL:
                GoodsBean beab = (GoodsBean) o;
                mingcheng.setText(beab.getData().getGood_type());
                zhongliang.setText(beab.getData().getWeight()+"吨");
                shifa.setText(beab.getData().getStart_addr());
                mudi.setText(beab.getData().getEnd_addr());
                cheliang.setText(beab.getData().getCar_require());

//                yunfei1.setText(beab.getData().getPrice());
                yunfei2.setText(beab.getData().getPrice()+"元/吨");
                fabuaio.setText(beab.getData().getGood_type());
                lianxi.setText(beab.getData().getMobile());
                shuoming.setText(beab.getData().getIntro());
                xiehuo.setText(beab.getData().getTime());

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
}
