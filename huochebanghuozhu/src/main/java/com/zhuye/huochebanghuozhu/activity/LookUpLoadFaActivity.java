package com.zhuye.huochebanghuozhu.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bm.library.Info;
import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.zhuye.huochebanghuozhu.R;
import com.zhuye.huochebanghuozhu.base.BaseActivity;
import com.zhuye.huochebanghuozhu.bean.BangDanBean;
import com.zhuye.huochebanghuozhu.bean.Code;
import com.zhuye.huochebanghuozhu.contants.Contans;
import com.zhuye.huochebanghuozhu.data.GetData;
import com.zhuye.huochebanghuozhu.utils.SharedPreferencesUtil;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.iwf.photopicker.PhotoPicker;

public class LookUpLoadFaActivity extends BaseActivity {


    private static final int SHANGCHUAN = 10;
    private static final int VIEW_WEIGHT = 11;
    private EditText fahuo;
    private EditText shouhuo;
    private EditText ming;
    private EditText fahchehaouo;
    private EditText maozhong;
    private EditText pizhong;
    private EditText jingzhong;
    private EditText sibangyuan;
    private TextView tv_tile;
    private ImageView xixin;
    private ImageView back;
    private Button wancheng;
    private RelativeLayout layout_fa_unit,layout_fa_unit_view,layout_shou_unit,layout_shou_unit_view;
    private PhotoView img1, img2;
    private int flag =0;
private Info mRectF;

    @Override
    protected int getResId() {
        return R.layout.activity_up_load_fa;
    }


    @Override
    protected void initData() {
        super.initData();
        if (flag ==1) {//查看装货单
            tv_tile.setText("装货单详情");
//            type	int	是	1装货单 2卸货单
            GetData.view_weight(Integer.parseInt(order_id), SharedPreferencesUtil.getInstance().getString("token"),flag, this
                    , VIEW_WEIGHT);

        } else if (flag == 2){//查看卸货单
            tv_tile.setText("卸货单详情");
            GetData.view_weight(Integer.parseInt(order_id), SharedPreferencesUtil.getInstance().getString("token"),flag, this
                    , VIEW_WEIGHT);
        }
    }

    @Override
    protected void initView() {
        super.initView();
        fahuo = (EditText) findViewById(R.id.fahuo);
        shouhuo = (EditText) findViewById(R.id.shouhuo);
        ming = (EditText) findViewById(R.id.mingchengs);
        fahchehaouo = (EditText) findViewById(R.id.chehao);
        maozhong = (EditText) findViewById(R.id.maozhong);
        pizhong = (EditText) findViewById(R.id.pizhong);
        jingzhong = (EditText) findViewById(R.id.jingzhong);
        sibangyuan = (EditText) findViewById(R.id.sibangyuan);
        tv_tile = (TextView) findViewById(R.id.tv_title);
//        xixin = (ImageView) findViewById(R.id.xixin);
        back = (ImageView) findViewById(R.id.back);
        wancheng = (Button) findViewById(R.id.wancheng);
        img1 = (PhotoView) findViewById(R.id.img1);
        img2 = (PhotoView) findViewById(R.id.img2);
        layout_fa_unit =(RelativeLayout) findViewById(R.id.layout_fa_unit);
        layout_fa_unit_view =(RelativeLayout) findViewById(R.id.layout_fa_unit_view);
        layout_shou_unit =(RelativeLayout) findViewById(R.id.layout_shou_unit);
        layout_shou_unit_view =(RelativeLayout) findViewById(R.id.layout_shou_unit_view);
        order_id = getIntent().getStringExtra("ordre_id");
        flag = getIntent().getIntExtra("flag", 0);
    }

    String order_id;

    @Override
    public void success(int requestcode, Object o) {
        super.success(requestcode, o);
        switch (requestcode) {
            case SHANGCHUAN:
                Code message = (Code) o;
                toast(message.getMessage());
                break;
            case VIEW_WEIGHT:
                BangDanBean bangDanBean = (BangDanBean) o;
                if (flag == 1) {//装货单
//                    layout_fa_unit.setVisibility(View.VISIBLE);
//                    layout_fa_unit_view.setVisibility(View.VISIBLE);
//                    layout_shou_unit.setVisibility(View.VISIBLE);
//                    layout_shou_unit_view.setVisibility(View.VISIBLE);
                }else if (flag == 2){//卸货单
                    layout_fa_unit.setVisibility(View.GONE);
                    layout_fa_unit_view.setVisibility(View.GONE);
                    layout_shou_unit.setVisibility(View.GONE);
                    layout_shou_unit_view.setVisibility(View.GONE);
                }
                    fahuo.setText(bangDanBean.getData().getF_unit());
                    shouhuo.setText(bangDanBean.getData().getS_unit());
                    ming.setText(bangDanBean.getData().getGood_name());
                    fahchehaouo.setText(bangDanBean.getData().getCar_card());
                    maozhong.setText(bangDanBean.getData().getM_weight());
                    pizhong.setText(bangDanBean.getData().getP_weight());
                    jingzhong.setText(bangDanBean.getData().getWeight());
                    sibangyuan.setText(bangDanBean.getData().getDriver_name());
                    Glide.with(LookUpLoadFaActivity.this).load(Contans.BASE_URL + bangDanBean.getData().getPhoto()).into(img1);
                    Glide.with(LookUpLoadFaActivity.this).load(Contans.BASE_URL + bangDanBean.getData().getPhoto()).into(img2);
                //设置不可以双指缩放移动放大等操作，跟普通的image一模一样,默认情况下就是disenable()状态
               img2.setVisibility(View.GONE);
               img1.setVisibility(View.VISIBLE);
                img1.disenable();
                img1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        img1.setVisibility(View.GONE);
                        img2.setVisibility(View.VISIBLE);
                        //获取img1的信息
                        mRectF = img1.getInfo();
                        //让img2从img1的位置变换到他本身的位置
                        img2.animaFrom(mRectF);
                    }
                });
                // 需要启动缩放需要手动开启
                img2.disenable();
                img2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 让img2从自身位置变换到原来img1图片的位置大小
                        img2.animaTo(mRectF, new Runnable() {
                            @Override
                            public void run() {
                                img2.setVisibility(View.GONE);
                                img1.setVisibility(View.VISIBLE);
                            }
                        });
                    }
                });
                break;
        }
    }
    @Override
    public void onBackPressed() {
        if (img2.getVisibility() == View.VISIBLE) {
            img2.animaTo(mRectF, new Runnable() {
                @Override
                public void run() {
                    img2.setVisibility(View.GONE);
                    img1.setVisibility(View.VISIBLE);
                }
            });
        } else {
            super.onBackPressed();
        }
    }


    @Override
    protected void initListener() {
        super.initListener();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//        xixin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //选择图片
//                //selectPic();
//            }
//        });

        wancheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmpty(fahuo)) {
                    toast("发货单位不能为空");
                    return;
                }
                if (isEmpty(shouhuo)) {
                    toast("收货单位不能为空");
                    return;
                }

                if (isEmpty(fahchehaouo)) {
                    toast("车牌号不能为空");
                    return;
                }
                if (isEmpty(maozhong)) {
                    toast("毛重重量不能为空");
                    return;
                }
                if (isEmpty(pizhong)) {
                    toast("皮重重量不能为空");
                    return;
                }
                if (isEmpty(jingzhong)) {
                    toast("净重重量不能为空");
                    return;
                }
                if (isEmpty(sibangyuan)) {
                    toast("司磅员不能为空");
                    return;
                }

                if (bitmap == null) {
                    toast("请选择图片");
                    return;
                }
//                GetData.weight_img(SharedPreferencesUtil.getInstance().getString("token"),bitmap,order_id,
//                        "1",getString(fahuo),getString(shouhuo),getString(mingcheng),getString(fahchehaouo)
//                        ,getString(maozhong),getString(pizhong),getString(jingzhong),getString(sibangyuan),UpLoadFaActivity.this,SHANGCHUAN);
            }
        });
    }

//    private void selectPic() {
//        PhotoPicker.builder()
//                .setPhotoCount(1)//可选择图片数量
//                .setShowCamera(true)//是否显示拍照按钮
//                .setShowGif(true)//是否显示动态图
//                .setPreviewEnabled(true)//是否可以预览
//                .start(UpLoadFaActivity.this, 308);
//    }

    File bitmap = null;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 308) {
            if (data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS) != null) {
                ArrayList<String> photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                //可以同时插入多张图片
                for (String imagePath : photos) {
                    //Log.i("NewActivity", "###path=" + imagePath);
                    //curfragment1.setshenzheng(imagePath);
                    // bitmap = FilesUtil.getSmallBitmap(UpLoadFaActivity.this, imagePath);//压缩图片
                    xixin.setImageURI(Uri.fromFile(bitmap));
                }
                //totalpath.add(1, photos.get(0));
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
