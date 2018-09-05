package com.zhuye.huochebangsiji.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhuye.huochebangsiji.R;
import com.zhuye.huochebangsiji.adapter.ShengShiAdaper;
import com.zhuye.huochebangsiji.base.BaseActivity;
import com.zhuye.huochebangsiji.base.BaseHolder;
import com.zhuye.huochebangsiji.bean.CitysBean;
import com.zhuye.huochebangsiji.bean.Code;
import com.zhuye.huochebangsiji.bean.HelpBean;
import com.zhuye.huochebangsiji.bean.JsonBean;
import com.zhuye.huochebangsiji.bean.UploadImgBean;
import com.zhuye.huochebangsiji.contants.Contans;
import com.zhuye.huochebangsiji.data.GetData;
import com.zhuye.huochebangsiji.data.http.CommonApi;
import com.zhuye.huochebangsiji.utils.DensityUtil;
import com.zhuye.huochebangsiji.utils.FilesUtil;
import com.zhuye.huochebangsiji.utils.SharedPreferencesUtil;
import com.zhuye.huochebangsiji.widget.RoundedCornerImageView;

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

    private static final int TIJIAO = 20;
    String jifen;
    String face;
    String nam;
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
    @BindView(R.id.edit)
    TextView edit;
    TextView shifa;
    TextView mudi;
    @BindView(R.id.bianjiziliao)
    Button bianjiziliao;
    @BindView(R.id.chepaihao)
    TextView chepaihao;

    @Override
    protected int getResId() {
        return R.layout.activity_edit_info;
    }

    CitysBean citysBean;


    @Override
    protected void initView() {
        super.initView();
        shifa = (TextView) findViewById(R.id.shifa);
        mudi = (TextView) findViewById(R.id.mudi);


        jifen = getIntent().getStringExtra("mobile");
        face = getIntent().getStringExtra("face");
        nam = getIntent().getStringExtra("name");

        String suozai = getIntent().getStringExtra("suozai");
        String shif = getIntent().getStringExtra("shifa");
        String mud = getIntent().getStringExtra("mudi");
        String chepai = getIntent().getStringExtra("chepai");
        chepaihao.setText(chepai);

        shi.setText(suozai);
        shifa.setText(shif);
        mudi.setText(mud);

        String suozaisheng = getIntent().getStringExtra("suozaisheng");
        String suozaishi = getIntent().getStringExtra("suozaishi");
        String shifaid = getIntent().getStringExtra("shifaid");
        String mudiid = getIntent().getStringExtra("mudiid");

        try {
            suozai_provinceid = Integer.valueOf(suozaisheng);
            suozai_cityid = Integer.valueOf(suozaishi);

            start_cityid = Integer.valueOf(shifaid);
            end_cityid = Integer.valueOf(mudiid);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        personName.setText(nam);
        lianxi.setText(jifen);
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

        CommonApi.getInstance().city().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CitysBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull CitysBean code) {
                        Log.i("as", code.toString());
                        if (code.getCode() == 200) {
                            citysBean = code;
                            handleCityData();
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

    Integer end_provinceid = 0;
    Integer end_cityid = 0;
    Integer end_areaid = 0;

    private void selsectEndCity() {
        OptionsPickerView pvOptions = new OptionsPickerView.Builder(EditInfoActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
//                String tx = options1Items.get(options1).getPickerViewText()+
//                        options2Items.get(options1).get(options2)+
//                        options3Items.get(options1).get(options2).get(options3);

                //Toast.makeText(getActivity(),"opt",Toast.LENGTH_SHORT).show();
                Log.i("as", options1 + "s" + options2 + "s" + options3 + "y");
                end_provinceid = Integer.parseInt(citysBean.getData().get(options1).getProvince_id());
                end_cityid = Integer.parseInt(citysBean.getData().get(options1).getCity().get(options2).getCity_id());
                end_areaid = Integer.parseInt(citysBean.getData().get(options1).getCity().get(options2).getArea().get(options3).getArea_id());
//                modi2.setText(citysBean.getData().get(options1).getName()+citysBean.getData().get(options1).getCity().get(options2).getName()
//                        +citysBean.getData().get(options1).getCity().get(options2).getArea().get(options3).getName());
                mudi.setText(citysBean.getData().get(options1).getCity().get(options2).getName());

                if (start_cityid == 0) {
                    start_cityid = null;
                }

            }
        })
                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        if (options1Items.size() > 0) {
            pvOptions.setPicker(options1Items, options2Items);//三级选择器
            pvOptions.show();
        }

    }


    @Override
    public void success(int requestcode, Object o) {
        super.success(requestcode, o);
        switch (requestcode) {
            case TIJIAO:
                Code code = (Code) o;
                toast(code.getMessage());
                break;
            case BAINJIZI:
                try {
                    toast(((Code) o).getMessage());
                } catch (Exception e) {
                    finish();
                }
                break;
        }
    }

    @Override
    protected void initListener() {
        super.initListener();
        mudi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selsectEndCity();
            }
        });

        shifa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectStartCity();
            }
        });
    }

    Integer start_provinceid = 0;
    Integer start_cityid = 0;
    Integer start_areaid = 0;

    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();

    private void selectStartCity() {
        OptionsPickerView pvOptions = new OptionsPickerView.Builder(EditInfoActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
//                String tx = options1Items.get(options1).getPickerViewText()+
//                        options2Items.get(options1).get(options2)+
//                        options3Items.get(options1).get(options2).get(options3);

                //Toast.makeText(getActivity(),"opt",Toast.LENGTH_SHORT).show();

                //Log.i("as", options1 + "s" + options2 + "s" + options3 + "y");
                start_provinceid = Integer.parseInt(citysBean.getData().get(options1).getProvince_id());
                start_cityid = Integer.parseInt(citysBean.getData().get(options1).getCity().get(options2).getCity_id());
                //start_areaid = Integer.parseInt(citysBean.getData().get(options1).getCity().get(options2).getArea().get(options3).getArea_id());
//                shifa2.setText(citysBean.getData().get(options1).getName()+citysBean.getData().get(options1).getCity().get(options2).getName()
//                        +citysBean.getData().get(options1).getCity().get(options2).getArea().get(options3).getName());
                //startcity.setText(citysBean.getData().get(options1).getCity().get(options2).getName());

                shifa.setText(citysBean.getData().get(options1).getCity().get(options2).getName());
                // GetData.driver(1,start_cityid,end_cityid,data,SijiDatingFragment.this,SOUSUO);
            }
        })
                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        if (options1Items.size() > 0) {
            pvOptions.setPicker(options1Items, options2Items);//三级选择器
            pvOptions.show();
        }
    }

    private static final int BAINJIZI = 7513;

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

    List<String> pho = new ArrayList<>();

    private static final int XINGSHIZHE = 100;

    @OnClick({R.id.edit, R.id.person_name, R.id.back, R.id.person_detail_touxiang, R.id.golianxi, R.id.sheng, R.id.shi, R.id.bianjiziliao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bianjiziliao:
                tijiaoquba();


                break;
            case R.id.edit:
                // TODO: 2018/1/22 0022 修改资料上传


                GetData.edit_info(SharedPreferencesUtil.getInstance().getString("token"), suozai_provinceid, suozai_cityid, start_cityid, end_cityid, EditInfoActivity.this, TIJIAO);
                break;
            case R.id.person_name:
                //alertname();
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
                //selecttouxiang();
                break;
            case R.id.golianxi:

                Intent inten = new Intent(EditInfoActivity.this, ChangeMobileActivity.class);
                inten.putExtra("mobile", jifen);
                startActivity(inten);
                //alertlianxi();
                break;
            case R.id.sheng:
                // alertsheng(view);
                break;
            case R.id.shi:
                //  alertShi(view);
                selectsuozai();
                break;
        }
    }


    Integer suozai_cityid;
    Integer suozai_provinceid;


    private void selectsuozai() {
        OptionsPickerView pvOptions = new OptionsPickerView.Builder(EditInfoActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
//                String tx = options1Items.get(options1).getPickerViewText()+
//                        options2Items.get(options1).get(options2)+
//                        options3Items.get(options1).get(options2).get(options3);

                //Toast.makeText(getActivity(),"opt",Toast.LENGTH_SHORT).show();

                //Log.i("as", options1 + "s" + options2 + "s" + options3 + "y");
                suozai_provinceid = Integer.parseInt(citysBean.getData().get(options1).getProvince_id());
                suozai_cityid = Integer.parseInt(citysBean.getData().get(options1).getCity().get(options2).getCity_id());
                //start_areaid = Integer.parseInt(citysBean.getData().get(options1).getCity().get(options2).getArea().get(options3).getArea_id());
//                shifa2.setText(citysBean.getData().get(options1).getName()+citysBean.getData().get(options1).getCity().get(options2).getName()
//                        +citysBean.getData().get(options1).getCity().get(options2).getArea().get(options3).getName());
                //startcity.setText(citysBean.getData().get(options1).getCity().get(options2).getName());

                shi.setText(citysBean.getData().get(options1).getCity().get(options2).getName());
                // GetData.driver(1,start_cityid,end_cityid,data,SijiDatingFragment.this,SOUSUO);
            }
        })
                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        if (options1Items.size() > 0) {
            pvOptions.setPicker(options1Items, options2Items);//三级选择器
            pvOptions.show();
        }
    }

    private void selecttouxiang() {
        PhotoPicker.builder()
                .setPhotoCount(1)//可选择图片数量
                .setShowCamera(true)//是否显示拍照按钮
                .setShowGif(true)//是否显示动态图
                .setPreviewEnabled(true)//是否可以预览
                .start(EditInfoActivity.this, 306);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == 306) {
                ArrayList<String> photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                //可以同时插入多张图片
                for (String imagePath : photos) {
                    //Log.i("NewActivity", "###path=" + imagePath);
                    File bitmap = FilesUtil.getSmallBitmap(EditInfoActivity.this, imagePath);//压缩图片
                    personDetailTouxiang.setImageURI(Uri.fromFile(bitmap));
                }

                //totalpath.add(3,photos.get(0));
            }

            if (requestCode == XINGSHIZHE) {
                ArrayList<String> photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                //可以同时插入多张图片
                for (String imagePath : photos) {
                    //Log.i("NewActivity", "###path=" + imagePath);

                    File bitmap = FilesUtil.getSmallBitmap(EditInfoActivity.this, imagePath);//压缩图片
                    personDetailTouxiang.setImageURI(Uri.fromFile(bitmap));
                }
                pho.addAll(photos);
            }
        } catch (Exception c) {

        }

    }

    private void alertlianxi() {
        final AlertDialog diala = new AlertDialog.Builder(EditInfoActivity.this).create();
        View view = View.inflate(EditInfoActivity.this, R.layout.aliet_edit, null);
        diala.setView(view);
        diala.show();
        TextView tishi = view.findViewById(R.id.tishi);
        tishi.setText("请输入联系方式");

        final EditText tv1 = view.findViewById(R.id.qrcode);


        view.findViewById(R.id.queren).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //personAge.setText(city[pos]);
                if (diala != null && diala.isShowing()) {
                    diala.dismiss();
                }

                if (isEmpty(tv1)) {
                    toast("请输入内容");
                    return;
                }
                phone = getString(tv1);
            }
        });
        view.findViewById(R.id.quxiao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (diala != null && diala.isShowing()) {
                    diala.dismiss();
                }
            }
        });
    }

    String phone;
    String name;

    private void alertname() {
        final AlertDialog dial = new AlertDialog.Builder(EditInfoActivity.this).create();
        View view = View.inflate(EditInfoActivity.this, R.layout.aliet_edit, null);
        dial.setView(view);
        dial.show();

        TextView tishi = view.findViewById(R.id.tishi);
        tishi.setText("请输入姓名");
        final EditText tv = view.findViewById(R.id.qrcode);
        view.findViewById(R.id.queren).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //personAge.setText(city[pos]);
                if (dial != null && dial.isShowing()) {
                    dial.dismiss();
                }

                if (isEmpty(tv)) {
                    toast("请输入内容");
                    return;
                }
                name = getString(tv);
            }
        });
        view.findViewById(R.id.quxiao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dial != null && dial.isShowing()) {
                    dial.dismiss();
                }
            }
        });
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

    private void handleCityData() {
        for (int i = 0; i < citysBean.getData().size(); i++) {//遍历省份
            JsonBean data = new JsonBean();
            data.setName(citysBean.getData().get(i).getName());
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < citysBean.getData().get(i).getCity().size(); c++) {//遍历该省份的所有城市
                String CityName = citysBean.getData().get(i).getCity().get(c).getName();
                CityList.add(CityName);//添加城市

                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (citysBean.getData().get(i).getCity().get(c).getArea() == null
                        || citysBean.getData().get(i).getCity().get(c).getArea().size() == 0) {
                    City_AreaList.add("");
                } else {

                    for (int d = 0; d < citysBean.getData().get(i).getCity().get(c).getArea().size(); d++) {//该城市对应地区所有数据
                        String AreaName = citysBean.getData().get(i).getCity().get(c).getArea().get(d).getName();

                        City_AreaList.add(AreaName);//添加该城市所有地区数据
                    }
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */

            options1Items.add(data);
        }

    }
}
