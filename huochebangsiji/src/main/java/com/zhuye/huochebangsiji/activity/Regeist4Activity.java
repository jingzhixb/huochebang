package com.zhuye.huochebangsiji.activity;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhuye.huochebangsiji.R;
import com.zhuye.huochebangsiji.base.BaseActivity;
import com.zhuye.huochebangsiji.bean.Code;
import com.zhuye.huochebangsiji.bean.UploadImgBean;
import com.zhuye.huochebangsiji.contants.Contans;
import com.zhuye.huochebangsiji.data.http.CommonApi;
import com.zhuye.huochebangsiji.utils.FilesUtil;

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

public class Regeist4Activity extends BaseActivity {

    String token;
    private static final int XINGSHIZHE = 100;
    String touxiang;
    String zheng;
    String fan;
    String name;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.jishizheng)
    ImageView jishizheng;
    @BindView(R.id.jiamengdaima)
    EditText jiamengdaima;
    @BindView(R.id.next)
    Button next;

    @Override
    protected int getResId() {
        return R.layout.activity_regeist4;
    }


    @Override
    protected void initView() {
        super.initView();

        touxiang = getIntent().getStringExtra("touxiang");
        zheng = getIntent().getStringExtra("zheng");
        fan = getIntent().getStringExtra("fan");
        name = getIntent().getStringExtra("name");
        token = getIntent().getStringExtra("token");

    }



    @OnClick({R.id.back, R.id.jishizheng, R.id.next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.jishizheng:
                selectjiashi();
                break;
            case R.id.next:
                tijiao();
                break;
        }
    }

    private void tijiao() {

        if(isEmpty(shizheng)){
            toast("请选择驾驶证");
            return;
        }

        if(isEmpty(jiamengdaima)){
            toast("请输入驾驶证");
            return;
        }

        uploadImg();
    }
    private void uploadImg() {
        loadface();
    }

    private List<String> loadpath = new ArrayList<>();
    private void loadface() {
        CommonApi.getInstance().upimg(token, FilesUtil.getSmallBitmap(Regeist4Activity.this, touxiang)).subscribeOn(Schedulers.newThread())
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

    private void loadzheng() {
        CommonApi.getInstance().upimg(token, FilesUtil.getSmallBitmap(Regeist4Activity.this, zheng)).subscribeOn(Schedulers.newThread())
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
        CommonApi.getInstance().upimg(token, FilesUtil.getSmallBitmap(Regeist4Activity.this, fan)).subscribeOn(Schedulers.newThread())
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
                            loadjiazhao();
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

    private void loadjiazhao() {
        CommonApi.getInstance().upimg(token, FilesUtil.getSmallBitmap(Regeist4Activity.this, shizheng)).subscribeOn(Schedulers.newThread())
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
        List<String> car_require = new ArrayList<>();

        OkGo.<String>post(Contans.BASE_URL+"/index.php/home/passport/user_messsage")
                .params("name", name)
                .params("card1", loadpath.get(1))
                .params("card2", loadpath.get(2))
                .params("license", null)
                .params("driving_book", loadpath.get(3))
                .params("cord", null)
                .params("encode", "")
                .params("type", "0")
                .params("face", loadpath.get(0))
                .params("token", token)
                .params("licen","A138134")
                //.params("token", token)
                .addUrlParams("car_require",car_require)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.i("as", response.body());
                        Code code = new Gson().fromJson(response.body(),Code.class);
                        if(response.body().contains("200")){
                            toast(code.getMessage());
                        }
                        startActivity(new Intent(Regeist4Activity.this,LoginActivity.class));
                        finish();
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Log.i("as", "sadf");
                    }
                });
    }



    private void selectjiashi() {
        PhotoPicker.builder()
                .setPhotoCount(1)//可选择图片数量
                .setShowCamera(true)//是否显示拍照按钮
                .setShowGif(true)//是否显示动态图
                .setPreviewEnabled(true)//是否可以预览
                .start(Regeist4Activity.this, XINGSHIZHE);
    }

    String shizheng ;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == XINGSHIZHE) {
            ArrayList<String> photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
            //可以同时插入多张图片
            for (String imagePath : photos) {
                //Log.i("NewActivity", "###path=" + imagePath);

                File bitmap = FilesUtil.getSmallBitmap(Regeist4Activity.this, imagePath);//压缩图片
                jishizheng.setImageURI(Uri.fromFile(bitmap));
            }
            shizheng = photos.get(0);
            //totalpath.add(1, photos.get(0));
        }
    }
}
