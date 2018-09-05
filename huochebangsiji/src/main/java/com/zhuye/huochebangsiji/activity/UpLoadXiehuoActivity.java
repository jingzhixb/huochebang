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
import butterknife.OnClick;
import me.iwf.photopicker.PhotoPicker;

public class UpLoadXiehuoActivity extends BaseActivity implements PermissionListener {


    private static final int SHANGCHUAN = 10;
    private static final int DETAIL = 1011;
    @BindView(R.id.back)
    ImageView back;

    @BindView(R.id.wancheng)
    Button wancheng;
    @BindView(R.id.xiehuoimg)
    ImageView xiehuoimg;
    @BindView(R.id.car_num)
    EditText carNum;
    @BindView(R.id.maozhong)
    EditText maozhong;
    @BindView(R.id.pizhong)
    EditText pizhong;
    @BindView(R.id.jingzhong)
    EditText jingzhong;
    @BindView(R.id.sibangyuan)
    EditText sibangyuan;
    @BindView(R.id.mingcheng)
    EditText mingcheng;
    @BindView(R.id.tv_xie)
    TextView tvXie;
    @BindView(R.id.img1)
    PhotoView img1;
    @BindView(R.id.img2)
    PhotoView img2;
    private int flag = 0;

    @Override
    protected int getResId() {
        return R.layout.activity_up_load_xiehuo;
    }

    String order_id;

    @Override
    protected void initView() {
        super.initView();
        if (!AndPermission.hasPermission(UpLoadXiehuoActivity.this
                , Manifest.permission.CAMERA, Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            AndPermission.with(UpLoadXiehuoActivity.this)
                    .requestCode(100)
                    .permission(Manifest.permission.CAMERA, Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .rationale(new RationaleListener() {
                        @Override
                        public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
                            AndPermission.rationaleDialog(UpLoadXiehuoActivity.this, rationale).show();
                        }
                    })
                    .send();
        }
        order_id = getIntent().getStringExtra("order_id");
        flag = getIntent().getIntExtra("flag", 0);
        if (flag == 2) {
            tvXie.setText("卸货详情");
            GetData.view_weight(SharedPreferencesUtil.getInstance().getString("token"), Integer.valueOf(order_id), 2, UpLoadXiehuoActivity.this, DETAIL);
        } else {
            tvXie.setText("录入卸货信息");
        }
    }

    private void selectPic() {
        PhotoPicker.builder()
                .setPhotoCount(1)//可选择图片数量
                .setShowCamera(true)//是否显示拍照按钮
                .setShowGif(true)//是否显示动态图
                .setPreviewEnabled(true)//是否可以预览
                .start(UpLoadXiehuoActivity.this, 308);
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
                        //Log.i("NewActivity", "###path=" + imagePath);
                        //curfragment1.setshenzheng(imagePath);
                        bitmap = FilesUtil.getSmallBitmap(UpLoadXiehuoActivity.this, imagePath);//压缩图片
                        xiehuoimg.setImageURI(Uri.fromFile(bitmap));
                    }
                }
                //totalpath.add(1, photos.get(0));
            }
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

    private Info mRectF;

    @Override
    public void success(int requestcode, Object o) {
        super.success(requestcode, o);
        switch (requestcode) {
            case SHANGCHUAN:
                Code message = (Code) o;
                toast(message.getMessage());
                Message msg = new Message();
                msg.arg1 = 2;
                GetData.getHandler().sendMessage(msg);
                finish();
                break;
            case DETAIL:
                BangDanBean bangDanBean = (BangDanBean) o;
                if (bangDanBean.getCode() == 200) {
                    mingcheng.setText(bangDanBean.getData().getGood_name());
                    carNum.setText(bangDanBean.getData().getCar_card());
                    maozhong.setText(bangDanBean.getData().getM_weight());
                    pizhong.setText(bangDanBean.getData().getP_weight());
                    jingzhong.setText(bangDanBean.getData().getWeight());
                    sibangyuan.setText(bangDanBean.getData().getDriver_name());
                    Glide.with(UpLoadXiehuoActivity.this).load(Contans.BASE_URL + bangDanBean.getData().getPhoto()).into(xiehuoimg);
                    mingcheng.setEnabled(false);
                    carNum.setEnabled(false);
                    maozhong.setEnabled(false);
                    pizhong.setEnabled(false);
                    jingzhong.setEnabled(false);
                    sibangyuan.setEnabled(false);
                    wancheng.setVisibility(View.GONE);
                    xiehuoimg.setClickable(false);



                    Glide.with(UpLoadXiehuoActivity.this).load(Contans.BASE_URL + bangDanBean.getData().getPhoto()).into(img1);
                    Glide.with(UpLoadXiehuoActivity.this).load(Contans.BASE_URL + bangDanBean.getData().getPhoto()).into(img2);

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
        }
    }

    @OnClick({R.id.back, R.id.xiehuoimg, R.id.wancheng})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.xiehuoimg:
                //选择图片
                selectPic();
                break;
            case R.id.wancheng:
                String ming = mingcheng.getText().toString();
                String carnum = carNum.getText().toString();
                String mao = maozhong.getText().toString();
                String pi = pizhong.getText().toString();
                String jing = jingzhong.getText().toString();
                String sibang = sibangyuan.getText().toString();
//                if (isEmpty(ming)) {
//                    toast("名称不能为空");
//                    return;
//                }
//                if (isEmpty(carnum)) {
//                    toast("车牌号不能为空");
//                    return;
//                }
                if (isEmpty(mao)) {
                    toast("毛重重量不能为空");
                    return;
                }
                if (isEmpty(pi)) {
                    toast("皮重重量不能为空");
                    return;
                }
                if (isEmpty(jing)) {
                    toast("净重重量不能为空");
                    return;
                }
//                if (isEmpty(sibang)) {
//                    toast("司磅员不能为空");
//                    return;
//                }
                if (bitmap == null) {
                    toast("请上传卸货图片");
                    return;
                }
                GetData.weight_img(SharedPreferencesUtil.getInstance().getString("token"), bitmap, order_id,
                        "2", "", "", ming, carnum
                        , mao, pi, jing, sibang, UpLoadXiehuoActivity.this, SHANGCHUAN);
                break;
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
