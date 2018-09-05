package com.zhuye.huochebangsiji.activity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bm.library.Info;
import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;
import com.zhuye.huochebangsiji.R;
import com.zhuye.huochebangsiji.base.BaseActivity;
import com.zhuye.huochebangsiji.bean.BangDanBean;
import com.zhuye.huochebangsiji.bean.Code;
import com.zhuye.huochebangsiji.contants.Contans;
import com.zhuye.huochebangsiji.data.GetData;
import com.zhuye.huochebangsiji.utils.FilesUtil;
import com.zhuye.huochebangsiji.utils.SharedPreferencesUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.iwf.photopicker.PhotoPicker;

public class UpLoadFaActivity extends BaseActivity implements PermissionListener {


    private static final int SHANGCHUAN = 10;
    private static final int DETAIL = 1012;
    @BindView(R.id.img2)
    PhotoView img2;
    @BindView(R.id.img1)
    PhotoView img1;
    private EditText fahuo;
    private EditText shouhuo;
    private EditText mingcheng;
    private EditText fahchehaouo;
    private EditText maozhong;
    private EditText pizhong;
    private EditText jingzhong;
    private EditText sibangyuan;
    private TextView tv_huo_title;
    private ImageView xixin;
    private ImageView back;
    private Button wancheng;
    private Intent intent;
    private String order_id;
    private int flag = 0;

    @Override
    protected int getResId() {
        return R.layout.activity_up_load_fa;
    }

    @Override
    protected void initView() {
        super.initView();
        if (!AndPermission.hasPermission(UpLoadFaActivity.this
                , Manifest.permission.CAMERA, Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            AndPermission.with(UpLoadFaActivity.this)
                    .requestCode(100)
                    .permission(Manifest.permission.CAMERA, Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .rationale(new RationaleListener() {
                        @Override
                        public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
                            AndPermission.rationaleDialog(UpLoadFaActivity.this, rationale).show();
                        }
                    })
                    .send();
        }
        tv_huo_title = (TextView) findViewById(R.id.tv_huo_title);
        fahuo = (EditText) findViewById(R.id.fahuo);
        shouhuo = (EditText) findViewById(R.id.shouhuo);
        mingcheng = (EditText) findViewById(R.id.mingcheng);
        fahchehaouo = (EditText) findViewById(R.id.chehao);
        maozhong = (EditText) findViewById(R.id.maozhong);
        pizhong = (EditText) findViewById(R.id.pizhong);
        jingzhong = (EditText) findViewById(R.id.jingzhong);
        sibangyuan = (EditText) findViewById(R.id.sibangyuan);

        xixin = (ImageView) findViewById(R.id.xixin);
        back = (ImageView) findViewById(R.id.back);
        wancheng = (Button) findViewById(R.id.wancheng);
        intent = getIntent();
        order_id = intent.getStringExtra("order_id");
        flag = intent.getIntExtra("flag", 0);
        if (flag == 2) {
            tv_huo_title.setText("装货详情");
            GetData.view_weight(SharedPreferencesUtil.getInstance().getString("token"), Integer.valueOf(order_id), 1, UpLoadFaActivity.this, DETAIL);
        } else {
            tv_huo_title.setText("录入装货信息");
        }
    }
    private Info mRectF;

    @Override
    public void success(int requestcode, Object o) {
        super.success(requestcode, o);
        switch (requestcode) {
            case SHANGCHUAN:
                Code message = (Code) o;
                toast(message.getMessage());
                Message msg = new Message();
                msg.arg1 = 1;
                GetData.getHandler().sendMessage(msg);
                finish();
                break;
            case DETAIL:
                BangDanBean bangDanBean = (BangDanBean) o;
                if (bangDanBean.getCode() == 200) {
                    fahuo.setText(bangDanBean.getData().getF_unit());
                    shouhuo.setText(bangDanBean.getData().getS_unit());
                    mingcheng.setText(bangDanBean.getData().getGood_name());
                    fahchehaouo.setText(bangDanBean.getData().getCar_card());
                    maozhong.setText(bangDanBean.getData().getM_weight());
                    pizhong.setText(bangDanBean.getData().getP_weight());
                    jingzhong.setText(bangDanBean.getData().getWeight());
                    sibangyuan.setText(bangDanBean.getData().getDriver_name());
                    Glide.with(UpLoadFaActivity.this).load(Contans.BASE_URL + bangDanBean.getData().getPhoto()).into(xixin);
                    fahuo.setEnabled(false);
                    shouhuo.setEnabled(false);
                    mingcheng.setEnabled(false);
                    fahchehaouo.setEnabled(false);
                    maozhong.setEnabled(false);
                    pizhong.setEnabled(false);
                    jingzhong.setEnabled(false);
                    sibangyuan.setEnabled(false);
                    wancheng.setVisibility(View.GONE);
                    xixin.setClickable(false);
                    Glide.with(UpLoadFaActivity.this).load(Contans.BASE_URL + bangDanBean.getData().getPhoto()).into(img1);
                    Glide.with(UpLoadFaActivity.this).load(Contans.BASE_URL + bangDanBean.getData().getPhoto()).into(img2);

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
                }
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

        xixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //选择图片
                selectPic();
            }
        });

        wancheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(isEmpty(fahuo)){
//                    toast("发货单位不能为空");
//                    return;
//                }
//                if(isEmpty(shouhuo)){
//                    toast("收货单位不能为空");
//                    return;
//                }
//                if(isEmpty(mingcheng)){
//                    toast("名称不能为空");
//                    return;
//                }
//                if(isEmpty(fahchehaouo)){
//                    toast("车牌号不能为空");
//                    return;
//                }
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
//                if(isEmpty(sibangyuan)){
//                    toast("司磅员不能为空");
//                    return;
//                }

                if (bitmap == null) {
                    toast("请选择图片");
                    return;
                }
                GetData.weight_img(SharedPreferencesUtil.getInstance().getString("token"), bitmap, order_id,
                        "1", getString(fahuo), getString(shouhuo), getString(mingcheng), getString(fahchehaouo)
                        , getString(maozhong), getString(pizhong), getString(jingzhong), getString(sibangyuan), UpLoadFaActivity.this, SHANGCHUAN);
            }
        });
    }

    private void selectPic() {
        PhotoPicker.builder()
                .setPhotoCount(1)//可选择图片数量
                .setShowCamera(true)//是否显示拍照按钮
                .setShowGif(true)//是否显示动态图
                .setPreviewEnabled(true)//是否可以预览
                .start(UpLoadFaActivity.this, 308);
    }

    File bitmap = null;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 308) {
            if (data != null) {
                if (data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS) != null) {
                    ArrayList<String> photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                    //可以同时插入多张图片
                    for (String imagePath : photos) {
                        bitmap = FilesUtil.getSmallBitmap(UpLoadFaActivity.this, imagePath);//压缩图片
                        xixin.setImageURI(Uri.fromFile(bitmap));
                    }
                    //totalpath.add(1, photos.get(0));
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[]
            grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        /**
         * 转给AndPermission分析结果。
         *
         * @param requestCode  请求码。
         * @param permissions  权限数组，一个或者多个。
         * @param grantResults 请求结果。
         * @param listener PermissionListener 对象。
         */
        if (requestCode == 100) {
            AndPermission.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
        }

    }

    @Override
    public void onSucceed(int requestCode, List<String> grantPermissions) {

    }

    @Override
    public void onFailed(int requestCode, List<String> deniedPermissions) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
