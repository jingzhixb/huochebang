package com.zhuye.huochebanghuozhu.activity;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhuye.huochebanghuozhu.R;
import com.zhuye.huochebanghuozhu.base.BaseActivity;
import com.zhuye.huochebanghuozhu.bean.Code;
import com.zhuye.huochebanghuozhu.bean.ShenHeBean;
import com.zhuye.huochebanghuozhu.bean.UploadImgBean;
import com.zhuye.huochebanghuozhu.contants.Contans;
import com.zhuye.huochebanghuozhu.data.http.CommonApi;
import com.zhuye.huochebanghuozhu.utils.FilesUtil;
import com.zhuye.huochebanghuozhu.utils.SharedPreferencesUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.iwf.photopicker.PhotoPicker;

public class Regeist1Activity extends BaseActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.touxiang)
    ImageView touxiang;
    @BindView(R.id.shenfenzhen)
    ImageView shenfenzhen;
    @BindView(R.id.shenfenfan)
    ImageView shenfenfan;
    @BindView(R.id.zhizhao)
    ImageView zhizhao;
    @BindView(R.id.next)
    Button next;
    @BindView(R.id.gongsi)
    ImageView mGongsi;
    @BindView(R.id.geren)
    ImageView mGeren;


    @Override
    protected int getResId() {
        return R.layout.activity_regeist1;
    }

    String token;

    @Override
    protected void initView() {
        super.initView();
        tpe = getIntent().getIntExtra("tpe", 0);
        token = getIntent().getStringExtra("token");
    }

    int tpe;

    @Override
    protected void initData() {
        super.initData();

        if (tpe == 1) {
            //提交过数据
            chakan();
        }

        for (int i = 0; i < 4; i++) {
            totalpath.add(i, "");
        }

       // SharedPreferencesUtil.getInstance().putString("token","asd");
    }

    private Boolean gongsia = true;
    @OnClick({R.id.gongsi,R.id.geren,R.id.back, R.id.touxiang, R.id.shenfenzhen, R.id.shenfenfan, R.id.zhizhao, R.id.next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.geren:
                gongsia = false;
                mGongsi.setImageResource(R.drawable.gouxuan_off);
                mGeren.setImageResource(R.drawable.gouxuan_on);
                break;
            case R.id.gongsi:
                gongsia = true;
                mGongsi.setImageResource(R.drawable.gouxuan_on);
                mGeren.setImageResource(R.drawable.gouxuan_off);
                break;
            case R.id.back:
                finish();
                break;
            case R.id.touxiang:
                selecttouxiang();
                break;
            case R.id.shenfenzhen:
                selectzheng();
                break;
            case R.id.shenfenfan:
                selectfan();
                break;
            case R.id.zhizhao:
                selectzhao();
                break;
            case R.id.next:
                //startActivity(new Intent(Regeist1Activity.this,Regeist2Activity.class));
                tijiao();
                break;
        }
    }

    private void chakan() {
        //        OkGo.<String>post("http://192.168.1.29/car/index.php/home/passport/message")
        //                .params("token",SharedPreferencesUtil.getInstance().getString("token"))
        //                .execute(new StringCallback() {
        //                    @Override
        //                    public void onSuccess(Response<String> response) {
        //                        Log.i("as",response.body());
        //                    }
        //
        //                    @Override
        //                    public void onError(Response<String> response) {
        //                        super.onError(response);
        //                        Log.i("as",response.body());
        //                    }
        //                });

        CommonApi.getInstance().message(SharedPreferencesUtil.getInstance().getString("token")).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ShenHeBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i("as", d.toString());
                    }

                    @Override
                    public void onNext(@NonNull ShenHeBean code) {
                        Log.i("as", code.toString());
                        if (code.getCode() == 200) {
                            name.setText(code.getData().getName());
                            Glide.with(Regeist1Activity.this).load(Contans.BASE_URL + code.getData().getFace()).into(touxiang);
                            Glide.with(Regeist1Activity.this).load(Contans.BASE_URL + code.getData().getCard1()).into(shenfenzhen);
                            Glide.with(Regeist1Activity.this).load(Contans.BASE_URL + code.getData().getCard2()).into(shenfenfan);
                            Glide.with(Regeist1Activity.this).load(Contans.BASE_URL + code.getData().getLicense()).into(zhizhao);


                            totalpath.add(0, code.getData().getFace());
                            totalpath.add(1, code.getData().getCard1());
                            totalpath.add(2, code.getData().getCard2());
                            totalpath.add(3, code.getData().getLicense());
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i("as", e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void tijiao() {
        if (isEmpty(name)) {
            toast("请输入姓名");
            return;
        }
        //        if(totalpath.size()<4){
        //有图片没选择  判断哪一张并提示
        //            if(totalpath.size()==0){
        //                toast("请选择需上传的图片");
        //                return;
        //            }else if(totalpath.size() ==1){
        //
        //            }
        //// TODO: 2018/1/4 0004 有bug
        if (isEmpty(totalpath.get(0))) {
            toast("请上传头像");
            return;
        }
        if (isEmpty(totalpath.get(1))) {
            toast("请上传身份证正面");
            return;
        }
        if (isEmpty(totalpath.get(2))) {
            toast("请上传身份证反面");
            return;
        }
        if(gongsia){
            if (isEmpty(totalpath.get(3))) {
                toast("请上传营业执照");
                return;
            }
        }
        // }

        // if(totalpath.size())
        uploadImg();


    }

    private void uploadImg() {
        loadface();
    }

    private void selectzhao() {
        PhotoPicker.builder()
                .setPhotoCount(1)//可选择图片数量
                .setShowCamera(true)//是否显示拍照按钮
                .setShowGif(true)//是否显示动态图
                .setPreviewEnabled(true)//是否可以预览
                .start(Regeist1Activity.this, 306);
    }

    private void selectfan() {
        PhotoPicker.builder()
                .setPhotoCount(1)//可选择图片数量
                .setShowCamera(true)//是否显示拍照按钮
                .setShowGif(true)//是否显示动态图
                .setPreviewEnabled(true)//是否可以预览
                .start(Regeist1Activity.this, 307);
    }

    private void selectzheng() {
        PhotoPicker.builder()
                .setPhotoCount(1)//可选择图片数量
                .setShowCamera(true)//是否显示拍照按钮
                .setShowGif(true)//是否显示动态图
                .setPreviewEnabled(true)//是否可以预览
                .start(Regeist1Activity.this, 308);
    }

    private void selecttouxiang() {
        PhotoPicker.builder()
                .setPhotoCount(1)//可选择图片数量
                .setShowCamera(true)//是否显示拍照按钮
                .setShowGif(true)//是否显示动态图
                .setPreviewEnabled(true)//是否可以预览
                .start(Regeist1Activity.this, PhotoPicker.REQUEST_CODE);
    }

    private List<String> totalpath = new ArrayList<>(4);

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (data != null) {
                if (requestCode == 1) {
                    //处理调用系统图库
                } else if (requestCode == PhotoPicker.REQUEST_CODE) {
                    //异步方式插入图片
                    //insertImagesSync(data);
                    ArrayList<String> photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                    //可以同时插入多张图片
                    for (String imagePath : photos) {
                        //Log.i("NewActivity", "###path=" + imagePath);
                        File bitmap = FilesUtil.getSmallBitmap(Regeist1Activity.this, imagePath);//压缩图片
                        touxiang.setImageURI(Uri.fromFile(bitmap));
                        //                        OkGo.<String>post("http://192.168.1.29/car/index.php/home/passport/upimg")
                        //                                .params("token", SharedPreferencesUtil.getInstance().getString("token"))
                        //                                .params("file",bitmap)
                        //                                .execute(new StringCallback() {
                        //                                    @Override
                        //                                    public void onSuccess(Response<String> response) {
                        //                                        Log.i("as",response.body());
                        //                                    }
                        //
                        //                                    @Override
                        //                                    public void onError(Response<String> response) {
                        //                                        super.onError(response);
                        //                                        Log.i("as",response.body());
                        //                                    }
                        //                                });


                    }
                    totalpath.add(0, photos.get(0));
                } else if (requestCode == 308) {
                    ArrayList<String> photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                    //可以同时插入多张图片
                    for (String imagePath : photos) {
                        //Log.i("NewActivity", "###path=" + imagePath);
                        File bitmap = FilesUtil.getSmallBitmap(Regeist1Activity.this, imagePath);//压缩图片
                        shenfenzhen.setImageURI(Uri.fromFile(bitmap));
                    }
                    totalpath.add(1, photos.get(0));
                } else if (requestCode == 307) {
                    ArrayList<String> photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                    //可以同时插入多张图片
                    for (String imagePath : photos) {
                        //Log.i("NewActivity", "###path=" + imagePath);
                        File bitmap = FilesUtil.getSmallBitmap(Regeist1Activity.this, imagePath);//压缩图片
                        shenfenfan.setImageURI(Uri.fromFile(bitmap));
                    }
                    totalpath.add(2, photos.get(0));
                } else if (requestCode == 306) {
                    ArrayList<String> photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                    //可以同时插入多张图片
                    for (String imagePath : photos) {
                        //Log.i("NewActivity", "###path=" + imagePath);
                        File bitmap = FilesUtil.getSmallBitmap(Regeist1Activity.this, imagePath);//压缩图片
                        zhizhao.setImageURI(Uri.fromFile(bitmap));
                    }
                    totalpath.add(3, photos.get(0));
                }
            }
        }
    }

    private void loadface() {
        CommonApi.getInstance().upimg(token, FilesUtil.getSmallBitmap(Regeist1Activity.this, totalpath.get(0))).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UploadImgBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i("as", d.toString());
                    }

                    @Override
                    public void onNext(@NonNull UploadImgBean code) {
                        Log.i("as", code.toString());
                        if (code.getCode() == 200) {
                            loadpath.add(code.getData().getFace());
                            loadzheng();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i("as", e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private List<String> loadpath = new ArrayList<>();

    private void loadzheng() {
        CommonApi.getInstance().upimg(token, FilesUtil.getSmallBitmap(Regeist1Activity.this, totalpath.get(1))).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UploadImgBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i("as", d.toString());
                    }

                    @Override
                    public void onNext(@NonNull UploadImgBean code) {
                        Log.i("as", code.toString());
                        if (code.getCode() == 200) {
                            loadpath.add(code.getData().getFace());
                            loadfan();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i("as", e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void loadfan() {
        CommonApi.getInstance().upimg(token, FilesUtil.getSmallBitmap(Regeist1Activity.this, totalpath.get(2))).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UploadImgBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i("as", d.toString());
                    }

                    @Override
                    public void onNext(@NonNull UploadImgBean code) {
                        Log.i("as", code.toString());
                        if (code.getCode() == 200) {
                            loadpath.add(code.getData().getFace());
                            if(gongsia){
                                loadzhao();
                            }else {
                                chuanwan();
                            }
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i("as", e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void loadzhao() {
        CommonApi.getInstance().upimg(token, FilesUtil.getSmallBitmap(Regeist1Activity.this, totalpath.get(3))).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UploadImgBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i("as", d.toString());
                    }

                    @Override
                    public void onNext(@NonNull UploadImgBean code) {
                        Log.i("as", code.toString());
                        if (code.getCode() == 200) {
                            loadpath.add(code.getData().getFace());
                            chuanwan();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i("as", e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void chuanwan() {

        OkGo.<String>post( Contans.BASE_URL+"/index.php/home/passport/user_messsage")
                .params("name", name.getText().toString().trim())
                .params("card1", loadpath.get(1))
                .params("card2", loadpath.get(2))
                .params("license", gongsia ? loadpath.get(3):"")
                .params("driving_book", "")
                .params("cord", "")
                .params("encode", "")
                .params("type", gongsia ? 1:0)
                .params("face", loadpath.get(0))
                .params("token", token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.i("as", response.body());
                        Code code = new Gson().fromJson(response.body(), Code.class);
                        if (response.body().contains("200")) {
                            toast(code.getMessage());
                            finish();

                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Log.i("as", "asd");
                    }
                });


    }


}
