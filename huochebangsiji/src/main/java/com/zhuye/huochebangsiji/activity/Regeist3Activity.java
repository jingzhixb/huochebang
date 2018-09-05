package com.zhuye.huochebangsiji.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhuye.huochebangsiji.R;
import com.zhuye.huochebangsiji.adapter.GoodNameAdapter;
import com.zhuye.huochebangsiji.base.BaseActivity;
import com.zhuye.huochebangsiji.base.BaseHolder;
import com.zhuye.huochebangsiji.bean.Code;
import com.zhuye.huochebangsiji.bean.PeiSongBean;
import com.zhuye.huochebangsiji.bean.UploadImgBean;
import com.zhuye.huochebangsiji.contants.Contans;
import com.zhuye.huochebangsiji.data.GetData;
import com.zhuye.huochebangsiji.data.http.CommonApi;
import com.zhuye.huochebangsiji.utils.FilesUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.iwf.photopicker.PhotoPicker;

public class Regeist3Activity extends BaseActivity {


    private static final int XINGSHIZHE = 100;
    private static final int JIASHIZHEN = 101;
    private static final int YINGYUNZIZHI = 109;
    private static final int CHAIXUAN = 10;
    private static final int ACOMMANDER_MODELS = 102;
    @BindView(R.id.jishizheng)
    ImageView jishizheng;
    @BindView(R.id.xingshizheng)
    ImageView xingshizheng;
    @BindView(R.id.youkahao)
    EditText youkahao;
    @BindView(R.id.next)
    Button next;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.xuanze)
    TextView xuanze;
    @BindView(R.id.yingyunzizhi)
    ImageView yingyunzizhi;

    @Override
    protected int getResId() {
        return R.layout.activity_regeist3;
    }

    String touxiang;
    String zheng;
    String fan;
    String name;

    @Override
    protected void initView() {
        super.initView();
        touxiang = getIntent().getStringExtra("touxiang");
        zheng = getIntent().getStringExtra("zheng");
        fan = getIntent().getStringExtra("fan");
        name = getIntent().getStringExtra("name");

        token = getIntent().getStringExtra("token");

        for (int i = 0; i < 2; i++) {
            totalpath.add(i, "");
        }
    }

    String token;

    @Override
    protected void initData() {
        super.initData();
        // GetData.shaixuan("", this, CHAIXUAN);
        GetData.acommander_models(this, ACOMMANDER_MODELS);
    }


    PeiSongBean persong;

    @Override
    public void success(int requestcode, Object o) {
        super.success(requestcode, o);
        switch (requestcode) {

            case CHAIXUAN:
                // persong = (PeiSongBean) o;

                break;
            case ACOMMANDER_MODELS:
                persong = (PeiSongBean) o;
                for (int j = 0; j < persong.getData().size(); j++) {
                    for (int i = 0; i < persong.getData().get(j).getType_arr().size(); i++) {
                        persong.getData().get(j).getType_arr().get(i).setIsselect(false);
                    }
                }
                break;
        }
    }

    private List<String> totalpath = new ArrayList<>(2);

    @OnClick({R.id.jishizheng, R.id.xingshizheng, R.id.next, R.id.back, R.id.xuanze,R.id.yingyunzizhi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.yingyunzizhi:
                selectyingyunzizhi();
                break;
            case R.id.jishizheng:
                selectjishizheng();
                break;
            case R.id.xingshizheng:
                selectxingshizheng();
                break;
            case R.id.next:
                tijiao();
                break;
            case R.id.back:
                finish();
                break;
            case R.id.xuanze:
                alertCarbotom();
                break;
        }
    }

    private void selectyingyunzizhi() {
        PhotoPicker.builder()
                .setPhotoCount(1)//可选择图片数量
                .setShowCamera(true)//是否显示拍照按钮
                .setShowGif(true)//是否显示动态图
                .setPreviewEnabled(true)//是否可以预览
                .start(Regeist3Activity.this, YINGYUNZIZHI);
    }

    int selectcarchang;
    int selectcarxing;

    private void alertCarbotom() {
        if (persong == null) {
            return;
        }
        View vie = View.inflate(Regeist3Activity.this, R.layout.bottom_select_car, null);
        final PopupWindow popupWindo = new PopupWindow(Regeist3Activity.this);
        popupWindo.setContentView(vie);
        popupWindo.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindo.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindo.setBackgroundDrawable(new ColorDrawable(0x00000000));
        popupWindo.setOutsideTouchable(true);
        popupWindo.setFocusable(true);

        vie.findViewById(R.id.guanbi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupWindo.isShowing()) {
                    popupWindo.dismiss();
                }
            }
        });


        vie.findViewById(R.id.queding).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (selectcarchang == 0) {
                    toast("请选择车长");
                    return;
                }
                if (selectcarxing == 0) {
                    toast("请选择车型");
                    return;
                }

                if (popupWindo.isShowing()) {
                    popupWindo.dismiss();
                }
                xuanze.setText(
                        persong.getData().get(0).getType_arr().get(selectcarchang - 1).getChose_name() + " " +
                                persong.getData().get(1).getType_arr().get(selectcarxing - 1).getChose_name());

            }
        });
        final GoodNameAdapter selectCarAdapter2 = new GoodNameAdapter(Regeist3Activity.this);
        final GoodNameAdapter selectCarAdapter3 = new GoodNameAdapter(Regeist3Activity.this);


        RecyclerView selectcarlength = vie.findViewById(R.id.selectcarlength);
        RecyclerView selectcarmodel = vie.findViewById(R.id.selectcarmodel);


        selectcarlength.setAdapter(selectCarAdapter2);
        selectcarlength.setLayoutManager(new GridLayoutManager(Regeist3Activity.this, 3));

        selectcarmodel.setAdapter(selectCarAdapter3);
        selectcarmodel.setLayoutManager(new GridLayoutManager(Regeist3Activity.this, 3));


        popupWindo.showAtLocation(vie, Gravity.BOTTOM, 0, 0);


        selectCarAdapter2.addData(persong.getData().get(0).getType_arr());
        selectCarAdapter3.addData(persong.getData().get(1).getType_arr());

        selectCarAdapter2.setOnItemClickListener(new BaseHolder.OnItemClickListener() {
            @Override
            public void OnItemClick(final View view, int position) {
                if (persong.getData().get(0).getType_arr().get(position).getIsselect()) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            view.findViewById(R.id.fabu_name).setVisibility(View.INVISIBLE);
                        }
                    });
                    //dat.get(position)=false;
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            view.findViewById(R.id.fabu_name).setVisibility(View.VISIBLE);
                        }
                    });
                }
                for (int i = 0; i < persong.getData().get(0).getType_arr().size(); i++) {
                    persong.getData().get(0).getType_arr().get(i).setIsselect(false);
                }
                persong.getData().get(0).getType_arr().get(position).setIsselect(!persong.getData().get(0).getType_arr().get(position).getIsselect());
                // dat.put(position, !dat.get(position));
                //selectCarAdapter.clear();
                selectCarAdapter2.addData2(persong.getData().get(0).getType_arr(), 0);
                selectcarchang = position + 1;
                Log.i("as", selectcarchang + "");
            }
        });


        selectCarAdapter3.setOnItemClickListener(new BaseHolder.OnItemClickListener() {
            @Override
            public void OnItemClick(final View view, int position) {
                if (persong.getData().get(1).getType_arr().get(position).getIsselect()) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            view.findViewById(R.id.fabu_name).setVisibility(View.INVISIBLE);
                        }
                    });
                    //dat.get(position)=false;
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            view.findViewById(R.id.fabu_name).setVisibility(View.VISIBLE);
                        }
                    });
                }
                for (int i = 0; i < persong.getData().get(1).getType_arr().size(); i++) {
                    persong.getData().get(1).getType_arr().get(i).setIsselect(false);
                }
                persong.getData().get(1).getType_arr().get(position).setIsselect(!persong.getData().get(1).getType_arr().get(position).getIsselect());
                // dat.put(position, !dat.get(position));
                //selectCarAdapter.clear();
                selectCarAdapter3.addData2(persong.getData().get(1).getType_arr(), 0);
                selectcarxing = position + 1;
                Log.i("as", selectcarxing + "");
            }
        });

    }

    private void tijiao() {

        if (isEmpty(totalpath.get(0))) {
            toast("请上传驾驶证");
            return;
        }
        if (isEmpty(totalpath.get(1))) {
            toast("请上传行驶证");
            return;
        }

        if (isEmpty(totalpath.get(2))) {
            toast("请上传营运证资质");
            return;
        }

//        if(TextUtils.isEmpty(youkahao.getText().toString().trim())){
//            toast("请输入油卡号");
//            return;
//        }

        if (selectcarchang == 0) {
            toast("请选择车辆类型");
            return;
        }


        uploadImg();
    }

    @Override
    public void onBackPressed() {

    }

    private void uploadImg() {
        loadface();
    }

    private List<String> loadpath = new ArrayList<>();

    private void loadface() {
        CommonApi.getInstance().upimg(token, FilesUtil.getSmallBitmap(Regeist3Activity.this, touxiang)).subscribeOn(Schedulers.newThread())
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
        CommonApi.getInstance().upimg(token, FilesUtil.getSmallBitmap(Regeist3Activity.this, zheng)).subscribeOn(Schedulers.newThread())
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
        CommonApi.getInstance().upimg(token, FilesUtil.getSmallBitmap(Regeist3Activity.this, fan)).subscribeOn(Schedulers.newThread())
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
        CommonApi.getInstance().upimg(token, FilesUtil.getSmallBitmap(Regeist3Activity.this, totalpath.get(0))).subscribeOn(Schedulers.newThread())
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
                            loadxingshi();
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

    private void loadxingshi() {
        CommonApi.getInstance().upimg(token, FilesUtil.getSmallBitmap(Regeist3Activity.this, totalpath.get(1))).subscribeOn(Schedulers.newThread())
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
                            loadyingyunzhizhao();
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

    private void loadyingyunzhizhao() {
        CommonApi.getInstance().upimg(token, FilesUtil.getSmallBitmap(Regeist3Activity.this, totalpath.get(2))).subscribeOn(Schedulers.newThread())
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
        car_require.add(persong.getData().get(0).getType_arr().get(selectcarchang).getChose_id());
        car_require.add(persong.getData().get(1).getType_arr().get(selectcarxing).getChose_id());

        OkGo.<String>post(Contans.BASE_URL + "/index.php/home/passport/user_messsage")
                .params("name", name)
                .params("card1", loadpath.get(1))
                .params("card2", loadpath.get(2))
                .params("license", loadpath.get(3))
                .params("driving_book", loadpath.get(4))
                .params("zizhi", loadpath.get(5))
                .params("cord", youkahao.getText().toString().trim())
                .params("encode", "")
                .params("type", "1")
                .params("face", loadpath.get(0))
                .params("token", token)
                .params("licen", "A138134")
                //.params("token", SharedPreferencesUtil.getInstance().getString("token"))
                .addUrlParams("car_require", car_require)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.i("as", response.body());
                        Code code = new Gson().fromJson(response.body(), Code.class);
                        if (response.body().contains("200")) {
                            toast(code.getMessage());
                        }
                        startActivity(new Intent(Regeist3Activity.this, LoginActivity.class));
                        finish();
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Log.i("as", "sadf");
                    }
                });
    }


    private void selectxingshizheng() {
        PhotoPicker.builder()
                .setPhotoCount(1)//可选择图片数量
                .setShowCamera(true)//是否显示拍照按钮
                .setShowGif(true)//是否显示动态图
                .setPreviewEnabled(true)//是否可以预览
                .start(Regeist3Activity.this, XINGSHIZHE);
    }

    private void selectjishizheng() {
        PhotoPicker.builder()
                .setPhotoCount(1)//可选择图片数量
                .setShowCamera(true)//是否显示拍照按钮
                .setShowGif(true)//是否显示动态图
                .setPreviewEnabled(true)//是否可以预览
                .start(Regeist3Activity.this, JIASHIZHEN);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == XINGSHIZHE) {
            ArrayList<String> photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
            //可以同时插入多张图片
            for (String imagePath : photos) {
                //Log.i("NewActivity", "###path=" + imagePath);

                File bitmap = FilesUtil.getSmallBitmap(Regeist3Activity.this, imagePath);//压缩图片
                xingshizheng.setImageURI(Uri.fromFile(bitmap));
            }
            totalpath.add(1, photos.get(0));
        } else if (requestCode == JIASHIZHEN) {
            ArrayList<String> photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
            //可以同时插入多张图片
            for (String imagePath : photos) {
                //Log.i("NewActivity", "###path=" + imagePath);

                File bitmap = FilesUtil.getSmallBitmap(Regeist3Activity.this, imagePath);//压缩图片
                jishizheng.setImageURI(Uri.fromFile(bitmap));
            }
            totalpath.add(0, photos.get(0));
        } else if (requestCode == YINGYUNZIZHI) {
            ArrayList<String> photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
            //可以同时插入多张图片
            for (String imagePath : photos) {
                //Log.i("NewActivity", "###path=" + imagePath);

                File bitmap = FilesUtil.getSmallBitmap(Regeist3Activity.this, imagePath);//压缩图片
                yingyunzizhi.setImageURI(Uri.fromFile(bitmap));
            }
            totalpath.add(2, photos.get(0));
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
