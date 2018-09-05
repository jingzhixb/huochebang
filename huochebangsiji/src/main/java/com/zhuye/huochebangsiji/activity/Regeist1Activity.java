package com.zhuye.huochebangsiji.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.zhuye.huochebangsiji.R;
import com.zhuye.huochebangsiji.adapter.BaseRegeistAdapter;
import com.zhuye.huochebangsiji.base.BaseActivity;
import com.zhuye.huochebangsiji.bean.ShenHeBean;
import com.zhuye.huochebangsiji.bean.UploadImgBean;
import com.zhuye.huochebangsiji.contants.Contans;
import com.zhuye.huochebangsiji.data.http.CommonApi;
import com.zhuye.huochebangsiji.fragment.BaseRegeistFragment;
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

public class Regeist1Activity extends BaseActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.next)
    Button next;
    @BindView(R.id.viewpagertab)
    SmartTabLayout viewpagertab;
    @BindView(R.id.vp)
    ViewPager vp;


    @Override
    protected int getResId() {
        return R.layout.activity_regeist1;
    }
    BaseRegeistAdapter adapters;
    BaseRegeistFragment curfragment1;


    String token;


    @Override
    protected void initView() {
        super.initView();
        adapters = new BaseRegeistAdapter(getSupportFragmentManager());
        vp.setAdapter(adapters);
        viewpagertab.setViewPager(vp);

        curfragment1 = (BaseRegeistFragment) adapters.getItem(0);

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
    }

    private int pagepos;

    @Override
    protected void initListener() {
        super.initListener();
        viewpagertab.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pagepos = position;
                curfragment1 = (BaseRegeistFragment) adapters.getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @OnClick({R.id.back, R.id.next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
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

        CommonApi.getInstance().message(token).subscribeOn(Schedulers.newThread())
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
                           // name.setText(code.getData().getName());
                            curfragment1.setName(code.getData().getName());
                            Glide.with(Regeist1Activity.this).load(Contans.BASE_URL + code.getData().getFace()).into(curfragment1.gettou());
                            Glide.with(Regeist1Activity.this).load(Contans.BASE_URL + code.getData().getCard1()).into(curfragment1.getshenzheng());
                            Glide.with(Regeist1Activity.this).load(Contans.BASE_URL + code.getData().getCard2()).into(curfragment1.getshenfan());


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

        //getSupportFragmentManager().beginTransaction();
    }

    private void tijiao() {
        if (isEmpty(curfragment1.getName())) {
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

        // }

        // if(totalpath.size())
        //uploadImg();
        Intent intent = null;
        if(pagepos==0){
            intent = new Intent(Regeist1Activity.this,Regeist3Activity.class);
        }else if(pagepos==1){
            intent = new Intent(Regeist1Activity.this,Regeist4Activity.class);
        }

        intent.putExtra("name",curfragment1.getName());
        intent.putExtra("touxiang",totalpath.get(0));
        intent.putExtra("zheng",totalpath.get(1));
        intent.putExtra("fan",totalpath.get(2));
        intent.putExtra("token",token);
        //intent = new Intent(Regeist1Activity.this,Regeist3Activity.class);
        startActivity(intent);
    }

    private void uploadImg() {
        loadface();
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
                        curfragment1.setTouxiang(imagePath);
                      //  File bitmap = FilesUtil.getSmallBitmap(Regeist1Activity.this, imagePath);//压缩图片
                        //touxiang.setImageURI(Uri.fromFile(bitmap));
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
                        curfragment1.setshenzheng(imagePath);
                        //File bitmap = FilesUtil.getSmallBitmap(Regeist1Activity.this, imagePath);//压缩图片
                        //shenfenzhen.setImageURI(Uri.fromFile(bitmap));
                    }
                    totalpath.add(1, photos.get(0));
                } else if (requestCode == 307) {
                    ArrayList<String> photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                    //可以同时插入多张图片
                    for (String imagePath : photos) {
                        //Log.i("NewActivity", "###path=" + imagePath);
                        curfragment1.setshenfan(imagePath);
                        //File bitmap = FilesUtil.getSmallBitmap(Regeist1Activity.this, imagePath);//压缩图片
                       // shenfenfan.setImageURI(Uri.fromFile(bitmap));
                    }
                    totalpath.add(2, photos.get(0));
                } else if (requestCode == 306) {
                    ArrayList<String> photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                    //可以同时插入多张图片
                    for (String imagePath : photos) {
                        //Log.i("NewActivity", "###path=" + imagePath);
                        File bitmap = FilesUtil.getSmallBitmap(Regeist1Activity.this, imagePath);//压缩图片
                       // zhizhao.setImageURI(Uri.fromFile(bitmap));
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
                            loadzhao();
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

        OkGo.<String>post(Contans.BASE_URL+"/index.php/home/passport/user_messsage")
                .params("name", curfragment1.getName())
                .params("card1", loadpath.get(1))
                .params("card2", loadpath.get(2))
                .params("license", loadpath.get(3))
                .params("driving_book", "")
                .params("cord", "")
                .params("encode", "")
                .params("type", "")
                .params("face", loadpath.get(0))
                .params("token", token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.i("as", response.body());
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Log.i("as", response.body());
                    }
                });


    }

}
