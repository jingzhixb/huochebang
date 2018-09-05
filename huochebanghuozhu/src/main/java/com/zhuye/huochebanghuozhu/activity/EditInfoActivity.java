package com.zhuye.huochebanghuozhu.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhuye.huochebanghuozhu.R;
import com.zhuye.huochebanghuozhu.adapter.me.ShengShiAdaper;
import com.zhuye.huochebanghuozhu.base.BaseActivity;
import com.zhuye.huochebanghuozhu.base.BaseHolder;
import com.zhuye.huochebanghuozhu.bean.CitysBean;
import com.zhuye.huochebanghuozhu.bean.Code;
import com.zhuye.huochebanghuozhu.bean.HelpBean;
import com.zhuye.huochebanghuozhu.bean.UploadImgBean;
import com.zhuye.huochebanghuozhu.contants.Contans;
import com.zhuye.huochebanghuozhu.data.GetData;
import com.zhuye.huochebanghuozhu.data.http.CommonApi;
import com.zhuye.huochebanghuozhu.utils.DensityUtil;
import com.zhuye.huochebanghuozhu.utils.FilesUtil;
import com.zhuye.huochebanghuozhu.utils.SharedPreferencesUtil;
import com.zhuye.huochebanghuozhu.widget.RoundedCornerImageView;

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

public class EditInfoActivity extends BaseActivity {


    private static final int BAINJIZI = 7513;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.person_detail_touxiang)
    RoundedCornerImageView personDetailTouxiang;
    @BindView(R.id.person_name)
    EditText personName;
    @BindView(R.id.lianxi)
    TextView lianxi;
    @BindView(R.id.golianxi)
    ImageView golianxi;
    @BindView(R.id.sheng)
    TextView sheng;
    @BindView(R.id.shi)
    TextView shi;
    @BindView(R.id.tou)
    RelativeLayout tou;
    @BindView(R.id.bianjiziliao)
    Button bianjiziliao;
    @BindView(R.id.chapaihao)
    TextView chapaihao;

    @Override
    protected int getResId() {
        return R.layout.activity_edit_info;
    }

    CitysBean citysBean;


    String mobile;
    String face;
    String nam;

    @Override
    protected void initView() {
        super.initView();

        mobile = getIntent().getStringExtra("mobile");
        face = getIntent().getStringExtra("face");
        nam = getIntent().getStringExtra("name");

        String suozai = getIntent().getStringExtra("suozai");
        String shif = getIntent().getStringExtra("shifa");
        String mud = getIntent().getStringExtra("mudi");
        String chepai = getIntent().getStringExtra("chepai");

        chapaihao.setText(chepai);

        shi.setText(suozai);

        String suozaisheng = getIntent().getStringExtra("suozaisheng");
        String suozaishi = getIntent().getStringExtra("suozaishi");
        String shifaid = getIntent().getStringExtra("shifaid");
        String mudiid = getIntent().getStringExtra("mudiid");


        personName.setText(nam);
        lianxi.setText(mobile);
        Glide.with(this).load(Contans.BASE_URL + face).into(personDetailTouxiang);
    }

    @Override
    protected void initData() {
        super.initData();

        OkGo.<String>post(Contans.BASE_URL + "/index.php/home/commonpart/city")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.i("as", response.body());
                        citysBean = new Gson().fromJson(response.body(), CitysBean.class);
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Log.i("as", response.body());
                    }
                });

//        CommonApi.getInstance().city(null).subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<CitysBean>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(@NonNull CitysBean code) {
//                        Log.i("as", code.toString());
//                        if (code.getCode() == 200) {
//
//                            citysBean = code;
//                        }
//
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        Log.i("as", e.toString());
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
    }

    private Boolean cantijiao = false;
    private static final int XINGSHIZHE = 100;

    @OnClick({R.id.back, R.id.person_detail_touxiang, R.id.golianxi, R.id.sheng, R.id.shi, R.id.bianjiziliao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bianjiziliao:
                tijiaoquba();
//                cantijiao = !cantijiao;
//                if(cantijiao){
//                    bianjiziliao.setText("提交资料");
//                    // TODO: 2018/7/10 0010
//                    tijiaoquba();
//                }else {
//                    bianjiziliao.setText("编辑资料");
//                }


                break;
            case R.id.back:
                finish();
                break;
            case R.id.person_detail_touxiang:
                PhotoPicker.builder()
                        .setPhotoCount(1)//可选择图片数量
                        .setShowCamera(true)//是否显示拍照按钮
                        .setShowGif(true)//是否显示动态图
                        .setPreviewEnabled(true)//是否可以预览
                        .start(EditInfoActivity.this, XINGSHIZHE);
                break;
            case R.id.golianxi:
                Intent inten = new Intent(EditInfoActivity.this, ChangeMobileActivity.class);
                inten.putExtra("mobile", mobile);
                startActivity(inten);
                break;
            case R.id.sheng:
                alertsheng(view);
                break;
            case R.id.shi:
                alertShi(view);
                break;
        }
    }


    List<String> pho = new ArrayList<>();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == XINGSHIZHE) {

            try {
                ArrayList<String> photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                //可以同时插入多张图片
                for (String imagePath : photos) {
                    //Log.i("NewActivity", "###path=" + imagePath);

                    File bitmap = FilesUtil.getSmallBitmap(EditInfoActivity.this, imagePath);//压缩图片
                    personDetailTouxiang.setImageURI(Uri.fromFile(bitmap));
                }
                pho.addAll(photos);
            } catch (Exception e) {

            }


//            shizheng = photos.get(0);
            //totalpath.add(1, photos.get(0));
        }
    }

    private void tijiaoquba() {
        final String name = personName.getText().toString().trim();
        if (!name.equals("")) {
            if (pho.size() == 0) {
                GetData.edit_face(SharedPreferencesUtil.getInstance().getString("token"), name, face, EditInfoActivity.this, BAINJIZI);
            } else {
                CommonApi.getInstance().upimg(SharedPreferencesUtil.getInstance().getString("token"),
                        FilesUtil.getSmallBitmap(EditInfoActivity.this, pho.get(0)))
                        .subscribeOn(Schedulers.newThread())
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
//                                    loadpath.add(code.getData().getFace());
//                                    loadzheng();
                                    GetData.edit_face(SharedPreferencesUtil.getInstance().getString("token"),
                                            name, code.getData().getFace(), EditInfoActivity.this, BAINJIZI);
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

        } else {
            toast("请输入昵称");
        }

    }

    private void alertShi(View view) {
        View vie = View.inflate(EditInfoActivity.this, R.layout.menu_item, null);
        final PopupWindow popupWind = new PopupWindow(EditInfoActivity.this);
        popupWind.setContentView(vie);
        popupWind.setWidth(DensityUtil.dip2px(EditInfoActivity.this, 101));
        popupWind.setHeight(DensityUtil.dip2px(EditInfoActivity.this, 109));
        popupWind.setBackgroundDrawable(new ColorDrawable(0x00000000));
        popupWind.setOutsideTouchable(true);
        popupWind.setFocusable(true);
        shidata.clear();
        if (citysBean != null) {
            for (int i = 0; i < citysBean.getData().get(selectedsheng).getCity().size(); i++) {
                //data.add(citysBean.getData().get(i).getName());
                HelpBean bean = new HelpBean();
                bean.setName(citysBean.getData().get(selectedsheng).getCity().get(i).getName());
                bean.setId(citysBean.getData().get(selectedsheng).getCity().get(i).getCity_id());
                shidata.add(bean);
            }
        }


        RecyclerView rl = vie.findViewById(R.id.content);
        ShengShiAdaper adapter = new ShengShiAdaper(EditInfoActivity.this);
        rl.setAdapter(adapter);
        rl.setLayoutManager(new LinearLayoutManager(EditInfoActivity.this));
        adapter.addData(shidata);

        adapter.setOnItemClickListener(new BaseHolder.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                if (popupWind.isShowing()) {
                    popupWind.dismiss();
                }
                selectedshi = position;
                shi.setText(shidata.get(position).getName());

            }
        });
//        vie.findViewById(R.id.shouru).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (popupWindow.isShowing()) {
//                    popupWindow.dismiss();
//                }
//                title.setText("收入记录");
//
//                adapter.clear();
//                getshourudata();
//            }
//        });

        //popupWindow.showAtLocation(vie, Gravity.BOTTOM, 0, 0);
        popupWind.showAsDropDown(view);
    }

    ArrayList<HelpBean> shidata = new ArrayList();
    private int selectedsheng;
    private int selectedshi;
    ArrayList<HelpBean> data = new ArrayList();

    private void alertsheng(View view) {
        View vie = View.inflate(EditInfoActivity.this, R.layout.menu_item, null);
        final PopupWindow popupWindow = new PopupWindow(EditInfoActivity.this);
        popupWindow.setContentView(vie);
        popupWindow.setWidth(DensityUtil.dip2px(EditInfoActivity.this, 101));
        popupWindow.setHeight(DensityUtil.dip2px(EditInfoActivity.this, 109));
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);

        data.clear();

        if (citysBean != null) {
            for (int i = 0; i < citysBean.getData().size(); i++) {
                HelpBean bean = new HelpBean();
                bean.setId(citysBean.getData().get(i).getProvince_id());
                bean.setName(citysBean.getData().get(i).getName());
                data.add(bean);
            }
        }
        RecyclerView rl = vie.findViewById(R.id.content);
        ShengShiAdaper adapter = new ShengShiAdaper(EditInfoActivity.this);
        rl.setAdapter(adapter);
        rl.setLayoutManager(new LinearLayoutManager(EditInfoActivity.this));
        adapter.addData(data);
        adapter.setOnItemClickListener(new BaseHolder.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
                selectedsheng = position;
                sheng.setText(data.get(position).getName());
            }
        });
//        vie.findViewById(R.id.shouru).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (popupWindow.isShowing()) {
//                    popupWindow.dismiss();
//                }
//                title.setText("收入记录");
//
//                adapter.clear();
//                getshourudata();
//            }
//        });

        //popupWindow.showAtLocation(vie, Gravity.BOTTOM, 0, 0);
        popupWindow.showAsDropDown(view);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void success(int requestcode, Object o) {
        super.success(requestcode, o);
        switch (requestcode) {
            case BAINJIZI:
                try {
                    Code code = (Code) o;
                    toast(((Code) o).getMessage());
                } catch (Exception e) {
                    finish();
                }
                break;
        }
    }
}
