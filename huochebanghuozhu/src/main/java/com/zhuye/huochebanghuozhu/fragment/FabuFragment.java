package com.zhuye.huochebanghuozhu.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.lljjcoder.citypickerview.widget.CityPicker;
import com.zhuye.huochebanghuozhu.R;
import com.zhuye.huochebanghuozhu.activity.HuoWuDetailActivity;
import com.zhuye.huochebanghuozhu.activity.PeiSongBean;
import com.zhuye.huochebanghuozhu.activity.ZhiFuActivity;
import com.zhuye.huochebanghuozhu.adapter.fabu.GoodNameAdapter;
import com.zhuye.huochebanghuozhu.base.BaseHolder;
import com.zhuye.huochebanghuozhu.bean.Base;
import com.zhuye.huochebanghuozhu.bean.CitysBean;
import com.zhuye.huochebanghuozhu.bean.Code;
import com.zhuye.huochebanghuozhu.bean.JiaoFouBean;
import com.zhuye.huochebanghuozhu.bean.JsonBean;
import com.zhuye.huochebanghuozhu.data.GetData;
import com.zhuye.huochebanghuozhu.utils.CheckUtil;
import com.zhuye.huochebanghuozhu.utils.SharedPreferencesUtil;

import org.chenglei.widget.datepicker.DatePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.lzy.okgo.utils.HttpUtils.runOnUiThread;

//import org.chenglei.widget.datepicker.DatePicker;

/**
 * Created by Administrator on 2018/1/9 0009.
 */

public class FabuFragment extends BaseFragment {
    private static final int JIAOBAOZHENG = 20;
    @BindView(R.id.googsmessage)
    ImageView googsmessage;
    @BindView(R.id.mingcheng2)
    EditText mingcheng2;
    @BindView(R.id.huoweuname)
    ImageView huoweuname;
    @BindView(R.id.zhongliang2)
    EditText zhongliang2;
    @BindView(R.id.shifa2)
    EditText shifa2;
    @BindView(R.id.shifa1go)
    ImageView shifa1go;
    @BindView(R.id.modi2)
    EditText modi2;
    @BindView(R.id.mudigo)
    ImageView mudigo;
    @BindView(R.id.cheliang2)
    EditText cheliang2;
    @BindView(R.id.chelianggo)
    ImageView chelianggo;
    @BindView(R.id.miaoshu2)
    EditText miaoshu2;
    @BindView(R.id.miaoshugo)
    ImageView miaoshugo;
    @BindView(R.id.yunfei2)
    EditText yunfei2;
    @BindView(R.id.fabuaio1)
    TextView fabuaio1;
    @BindView(R.id.fabuaio2)
    EditText fabuaio2;
    @BindView(R.id.lianxi2)
    EditText lianxi2;
    @BindView(R.id.shuoming2)
    EditText shuoming2;
    @BindView(R.id.xiehuo2)
    EditText xiehuo2;
    @BindView(R.id.fabu)
    Button fabu;
    Unbinder unbinder;


    private static final int GET_CITY = 9;
    private static final int CAR_REQURE = 11;
    private static final int PEISONG_SHUO = 12;
    private static final int FABU_BA = 13;
    @BindView(R.id.shuoming1)
    TextView shuoming1;
    Unbinder unbinder1;
    @BindView(R.id.fapiaogo)
    ImageView fapiaogo;
    @BindView(R.id.peisongshuominggo)
    ImageView peisongshuominggo;
    @BindView(R.id.xiehuoshijiango)
    ImageView xiehuoshijiango;
    @BindView(R.id.baoxian)
    ImageView baoxian;
    @BindView(R.id.ggyou)
    RadioButton ggyou;
    @BindView(R.id.ggwu)
    RadioButton ggwu;
    @BindView(R.id.gggroup)
    RadioGroup gggroup;
    @BindView(R.id.dianhuowu)
    RelativeLayout dianhuowu;
    @BindView(R.id.dianshifa)
    RelativeLayout dianshifa;
    @BindView(R.id.dianmudi)
    RelativeLayout dianmudi;
    @BindView(R.id.diancheliang)
    RelativeLayout diancheliang;
    @BindView(R.id.dianxijie)
    RelativeLayout dianxijie;
    @BindView(R.id.dianpeisong)
    RelativeLayout dianpeisong;
    @BindView(R.id.dianshijian)
    RelativeLayout dianshijian;
    @BindView(R.id.etshifadi)
    EditText etshifadi;
    @BindView(R.id.etmodidi)
    EditText etmodidi;
    @BindView(R.id.yshuoming2)
    EditText yshuoming2;
    @BindView(R.id.maibaoxian)
    LinearLayout maibaoxian;


    @Override
    protected void initView() {

    }

    @Override
    protected int getResId() {
        return R.layout.fragment_fabu;
    }


    @Override
    protected void initData() {
        super.initData();
        initNavi();
        //卸货时间默认初始值
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date current = new Date(System.currentTimeMillis());//获取当前时间
        String inTime = sdf.format(current);
        xiehuo2.setText(inTime);
        GetData.city(null, this, GET_CITY);
        GetData.goodtype(0, this, 10);
        GetData.commander_models(0, this, CAR_REQURE);
        GetData.hade_model(0, this, PEISONG_SHUO);
    }

    Boolean jia = true;

    @OnClick({R.id.dianshijian, R.id.dianpeisong, R.id.diancheliang, R.id.dianmudi, R.id.dianshifa,
            R.id.dianhuowu, R.id.xiehuoshijiango, R.id.peisongshuominggo, R.id.shuoming1,
            R.id.baoxian, R.id.googsmessage, R.id.huoweuname, R.id.zhongliangwu,
            R.id.shifa1go, R.id.mudigo, R.id.chelianggo, R.id.miaoshugo, R.id.fapiaogo,
            R.id.fabu,R.id.maibaoxian})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.dianhuowu:
            case R.id.huoweuname:
                alertbotom();
                break;

            case R.id.dianshijian:
            case R.id.xiehuoshijiango:
                selectData();

                break;

            case R.id.dianpeisong:
            case R.id.peisongshuominggo:
                alertshuomingbotom();
                break;
            case R.id.shuoming1:
                break;
            case R.id.maibaoxian:
            case R.id.baoxian:
                if (jia) {
                    baoxian.setImageResource(R.drawable.gouxuan_off);
                } else {
                    baoxian.setImageResource(R.drawable.gouxuan_on);
                }
                jia = !jia;
                break;
            case R.id.googsmessage:
                //跳转到详情页面
                Intent intent = new Intent(getActivity(), HuoWuDetailActivity.class);
                intent.putExtra("type", 0);
                startActivity(intent);
                break;

            case R.id.zhongliangwu:
                break;
            case R.id.dianshifa:
            case R.id.shifa1go:
                //selectshifaCity();
                if (citysBean == null) {
                    return;
                }
                selectshifaCity1();
                break;
            case R.id.dianmudi:
            case R.id.mudigo:
                if (citysBean == null) {
                    return;
                }
                selectmudiCity1();
                break;
            case R.id.diancheliang:
            case R.id.chelianggo:
                alertCarbotom();
                break;
            case R.id.miaoshugo:

                break;
            case R.id.fapiaogo:
                break;
            case R.id.fabu:
                //startActivity(new Intent(getActivity(), LookXingChengActivity.class));
                //startActivity(new Intent(getActivity(), DaoHangActivity.class));

                fabuba();
                break;
        }
    }

    int lyear;
    int lmonthOfYear;
    int ldayOfMonth;

    private void selectData() {
        View vi1 = View.inflate(getActivity(), R.layout.data_select_bottom, null);
        final PopupWindow popupWind = new PopupWindow(getActivity());
        popupWind.setContentView(vi1);
        popupWind.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWind.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWind.setBackgroundDrawable(new ColorDrawable(0x00000000));
        popupWind.setOutsideTouchable(true);
        popupWind.setFocusable(true);
        popupWind.showAtLocation(vi1, Gravity.BOTTOM, 0, 0);

        DatePicker picker = vi1.findViewById(R.id.datapicker);

        picker.setTextColor(Color.BLACK)
                .setFlagTextColor(Color.BLACK)
                .setBackground(Color.WHITE)
                .setRowNumber(5)
                .setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        lyear = year;
                        lmonthOfYear = monthOfYear;
                        ldayOfMonth = dayOfMonth;
                        Log.i("DatePicker", year + "-" + monthOfYear + "-" + dayOfMonth);
                        xiehuo2.setText(year + "-" + monthOfYear + "-" + dayOfMonth);
                    }
                });

        vi1.findViewById(R.id.quxiao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupWind.isShowing()) {
                    popupWind.dismiss();
                }
            }
        });

        vi1.findViewById(R.id.queren).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (popupWind.isShowing()) {
                    popupWind.dismiss();
                }

            }
        });

    }

    List<String> huowu;
    Float weight;
    List<String> carrequire;
    List<String> car_require;
    String car_info;
    Float price;
    int invoice;
    String mobile;
    List<String> peisongshuo;
    List<String> hand_mode;
    String time;
    String remark;
    String typeid_bz;
    List<String> type_id;

    private void fabuba() {


//        if(TextUtils.isEmpty(xiehuo2.getText().toString().trim())){
//            toast("请选择卸货时间");
//            return;
//        }

//        toast(xiehuo2.getText().toString().trim());



        huowu = new ArrayList<>();
        if (zhongleiid == 0) {
            toast("请选择货物类型");
            return;
        }
        huowu.add(peiSongBean.getData().get(0).getType_arr().get(zhongleiid - 1).getChose_id());
        type_id = huowu;

//        if(type_id.size()==0){
//            toast("请选择货物类型");
//            return;
//        }
        if (TextUtils.isEmpty(zhongliang2.getText().toString().trim())) {
            toast("请选择货物重量");
            return;
        }

        weight = Float.parseFloat(zhongliang2.getText().toString().trim());
//        if(weight==0){
//            toast("请选择货物重量");
//            return;
//        }
        //GetData.fubu(0, this, FABU_BA, type_id, weight, car_require, car_info, price, invoice, mobile, hand_mode, time, start_provinceid, start_cityid, start_areaid, end_provinceid, end_cityid, end_areaid, remark, typeid_bz);

        if (start_provinceid == 0) {
            toast("请选择始发地");
            return;
        }

        if (end_provinceid == 0) {
            toast("请选择目的地");
            return;
        }
        carrequire = new ArrayList<>();

        if (selectcariid == 0) {
            toast("请选择车辆要求");
            return;
        }
        carrequire.add(carpeiSongBean.getData().get(0).getType_arr().get(selectcariid - 1).getChose_id());
        carrequire.add(carpeiSongBean.getData().get(1).getType_arr().get(selectcarchang - 1).getChose_id());
        carrequire.add(carpeiSongBean.getData().get(2).getType_arr().get(selectcarxing - 1).getChose_id());
        car_require = carrequire;

        //GetData.fubu(0,this,FABU_BA,type_id,weight,car_require,car_info,price,invoice,mobile,hand_mode,time,start_provinceid,start_cityid,start_areaid,end_provinceid,end_cityid,end_areaid,remark,typeid_bz);
        car_info = miaoshu2.getText().toString().trim();

        if (car_require.size() < 3) {
            toast("请选择车辆要求");
            return;
        }

        if (TextUtils.isEmpty(car_info)) {
            toast("请选择车辆细节描述");
            return;
        }

        if (TextUtils.isEmpty(yunfei2.getText().toString().trim())) {
            toast("请选择运费价格");
            return;
        }

        price = Float.parseFloat(yunfei2.getText().toString().trim());

        //// TODO: 2018/1/31 0031
        if (invoice == 0) {
            toast("请输入发票类型");
            return;
        }

        //String mobile  =lianxi2.getText().toString().trim()+"1320";
        mobile = lianxi2.getText().toString().trim();

        if (TextUtils.isEmpty(mobile)) {
            toast("请输入电话号码");
            return;
        }

        if (!CheckUtil.isMobile(mobile)) {
            toast("手机格式有误");
            return;
        }

//        if (shuomingid == 0) {
//            toast("请选择配送说明");
//            return;
//        }

        try {
            peisongshuo = new ArrayList<>();
            peisongshuo.add(shuomingSongBean.getData().get(0).getType_arr().get(shuomingid - 1).getChose_id());
            //peisongshuo.add("sfd");
            hand_mode = peisongshuo;
        } catch (Exception e) {
            e.printStackTrace();
        }
//        if(peisongshuo.size() < 1){
//            toast("请选择配送说明");
//            return;
//        }



//        if (lyear == 0) {
//            toast("请选择卸货时间");
//            return;
//        }
        if(TextUtils.isEmpty(xiehuo2.getText().toString().trim())){
            toast("请选择卸货时间");
            return;
        }
//        time = lyear + "-" + lmonthOfYear + "-" + ldayOfMonth;
        time = xiehuo2.getText().toString().trim();
        remark = "";
        typeid_bz = "";
        GetData.jiaofou(SharedPreferencesUtil.getInstance().getString("token"), FabuFragment.this, JIAOBAOZHENG);
    }


    private void initNavi() {
//        BaiduNaviManager.getInstance().init(getActivity(), "", "",
//                new BaiduNaviManager.NaviInitListener() {
//                    @Override
//                    public void onAuthResult(int status, final String msg) {
//                        if (0 == status) {
//                            //authinfo = "key校验成功!";
//                        } else {
//                            //authinfo = "key校验失败, " + msg;
//                        }
//                        getActivity().runOnUiThread(new Runnable() {
//
//                            @Override
//                            public void run() {
//                                Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
//                            }
//                        });
//                    }
//
//                    public void initSuccess() {
//                        Toast.makeText(getActivity(), "百度导航引擎初始化成功", Toast.LENGTH_SHORT).show();
//                    }
//
//                    public void initStart() {
//                        Toast.makeText(getActivity(), "百度导航引擎初始化开始", Toast.LENGTH_SHORT).show();
//                    }
//
//                    public void initFailed() {
//                        Toast.makeText(getActivity(), "百度导航引擎初始化失败", Toast.LENGTH_SHORT).show();
//                    }
//                }, null /*mTTSCallback*/);
    }


    Integer peisongshuopos;

    private void alertshuomingbotom() {
        if (shuomingSongBean == null) {
            return;
        }
        View vie = View.inflate(getActivity(), R.layout.bottom_select_car, null);
        final PopupWindow popupWind = new PopupWindow(getActivity());
        popupWind.setContentView(vie);
        popupWind.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWind.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWind.setBackgroundDrawable(new ColorDrawable(0x00000000));
        popupWind.setOutsideTouchable(true);
        popupWind.setFocusable(true);


        TextView chexing = vie.findViewById(R.id.chexing);
        TextView chexing2 = vie.findViewById(R.id.chexing2);
        TextView chechang = vie.findViewById(R.id.chechang);

        chechang.setVisibility(View.GONE);
        chexing2.setVisibility(View.GONE);
        chexing.setText("卸货说明");

        vie.findViewById(R.id.guanbi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupWind.isShowing()) {
                    popupWind.dismiss();
                }
            }
        });

        vie.findViewById(R.id.queding).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (shuomingid == 0) {
                    toast("请选择说明内容");
                    return;
                }
                if (popupWind.isShowing()) {
                    popupWind.dismiss();
                }
                shuoming2.setText(shuomingSongBean.getData().get(0).getType_arr().get(shuomingid - 1).getChose_name());
            }
        });
        final GoodNameAdapter selectCarAdapter = new GoodNameAdapter(getActivity());
        RecyclerView rv = vie.findViewById(R.id.selectcar);
        rv.setAdapter(selectCarAdapter);
        rv.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        popupWind.showAtLocation(vie, Gravity.BOTTOM, 0, 0);
        selectCarAdapter.addData(shuomingSongBean.getData().get(0).getType_arr());

        selectCarAdapter.setOnItemClickListener(new BaseHolder.OnItemClickListener() {
            @Override
            public void OnItemClick(final View view, int position) {
                if (shuomingSongBean.getData().get(0).getType_arr().get(position).getIsselect()) {
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
                for (int i = 0; i < shuomingSongBean.getData().get(0).getType_arr().size(); i++) {
                    shuomingSongBean.getData().get(0).getType_arr().get(i).setIsselect(false);
                }
                shuomingSongBean.getData().get(0).getType_arr().get(position).setIsselect(!shuomingSongBean.getData().get(0).getType_arr().get(position).getIsselect());
                // dat.put(position, !dat.get(position));
                //selectCarAdapter.clear();
                selectCarAdapter.addData2(shuomingSongBean.getData().get(0).getType_arr(), 0);
                shuomingid = position + 1;
                Log.i("as", shuomingid + "");
            }
        });
    }

    Integer celectchexing;
    Integer celectchexing2;
    Integer celectchang;


    private void alertCarbotom() {
        if (carpeiSongBean == null) {
            return;
        }
        View vie = View.inflate(getActivity(), R.layout.bottom_select_car, null);
        final PopupWindow popupWindo = new PopupWindow(getActivity());
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

                if (selectcariid == 0) {
                    toast("请选择用车类型");
                    return;
                }
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
                cheliang2.setText(carpeiSongBean.getData().get(0).getType_arr().get(selectcariid - 1).getChose_name()
                        + " " + carpeiSongBean.getData().get(1).getType_arr().get(selectcarchang - 1).getChose_name() + " " +
                        carpeiSongBean.getData().get(2).getType_arr().get(selectcarxing - 1).getChose_name());

            }
        });
        final GoodNameAdapter selectCarAdapter = new GoodNameAdapter(getActivity());
        final GoodNameAdapter selectCarAdapter2 = new GoodNameAdapter(getActivity());
        final GoodNameAdapter selectCarAdapter3 = new GoodNameAdapter(getActivity());

        RecyclerView rv = vie.findViewById(R.id.selectcar);
        RecyclerView selectcarlength = vie.findViewById(R.id.selectcarlength);
        final RecyclerView selectcarmodel = vie.findViewById(R.id.selectcarmodel);


        rv.setAdapter(selectCarAdapter);
        rv.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        selectcarlength.setAdapter(selectCarAdapter2);
        selectcarlength.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        selectcarmodel.setAdapter(selectCarAdapter3);
        selectcarmodel.setLayoutManager(new GridLayoutManager(getActivity(), 3));


        popupWindo.showAtLocation(vie, Gravity.BOTTOM, 0, 0);


        selectCarAdapter.addData(carpeiSongBean.getData().get(0).getType_arr());
        selectCarAdapter2.addData(carpeiSongBean.getData().get(1).getType_arr());
        selectCarAdapter3.addData(carpeiSongBean.getData().get(2).getType_arr());

        selectCarAdapter.setOnItemClickListener(new BaseHolder.OnItemClickListener() {
            @Override
            public void OnItemClick(final View view, int position) {
                if (carpeiSongBean.getData().get(0).getType_arr().get(position).getIsselect()) {
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
                for (int i = 0; i < carpeiSongBean.getData().get(0).getType_arr().size(); i++) {
                    carpeiSongBean.getData().get(0).getType_arr().get(i).setIsselect(false);
                }
                carpeiSongBean.getData().get(0).getType_arr().get(position).setIsselect(!carpeiSongBean.getData().get(0).getType_arr().get(position).getIsselect());
                // dat.put(position, !dat.get(position));
                //selectCarAdapter.clear();
                selectCarAdapter.addData2(carpeiSongBean.getData().get(0).getType_arr(), 0);
                selectcariid = position + 1;
                Log.i("as", selectcariid + "");

            }
        });
        selectCarAdapter2.setOnItemClickListener(new BaseHolder.OnItemClickListener() {
            @Override
            public void OnItemClick(final View view, int position) {
                if (carpeiSongBean.getData().get(1).getType_arr().get(position).getIsselect()) {
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
                for (int i = 0; i < carpeiSongBean.getData().get(1).getType_arr().size(); i++) {
                    carpeiSongBean.getData().get(1).getType_arr().get(i).setIsselect(false);
                }
                carpeiSongBean.getData().get(1).getType_arr().get(position).setIsselect(!carpeiSongBean.getData().get(1).getType_arr().get(position).getIsselect());
                // dat.put(position, !dat.get(position));
                //selectCarAdapter.clear();
                selectCarAdapter2.addData2(carpeiSongBean.getData().get(1).getType_arr(), 0);
                selectcarchang = position + 1;
                Log.i("as", selectcarchang + "");
            }
        });


        selectCarAdapter3.setOnItemClickListener(new BaseHolder.OnItemClickListener() {
            @Override
            public void OnItemClick(final View view, int position) {
                if (carpeiSongBean.getData().get(2).getType_arr().get(position).getIsselect()) {
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
                for (int i = 0; i < carpeiSongBean.getData().get(2).getType_arr().size(); i++) {
                    carpeiSongBean.getData().get(2).getType_arr().get(i).setIsselect(false);
                }
                carpeiSongBean.getData().get(2).getType_arr().get(position).setIsselect(!carpeiSongBean.getData().get(2).getType_arr().get(position).getIsselect());
                // dat.put(position, !dat.get(position));
                //selectCarAdapter.clear();
                selectCarAdapter3.addData2(carpeiSongBean.getData().get(2).getType_arr(), 0);
                selectcarxing = position + 1;
                Log.i("as", selectcarxing + "");
            }
        });

    }

    int start_provinceid = 0;
    int start_cityid = 0;
    int start_areaid = 0;


    int end_provinceid = 0;
    int end_cityid = 0;
    int end_areaid = 0;

    private void selectmudiCity1() {
        OptionsPickerView pvOptions = new OptionsPickerView.Builder(getActivity(), new OptionsPickerView.OnOptionsSelectListener() {
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
                modi2.setText(citysBean.getData().get(options1).getName() + citysBean.getData().get(options1).getCity().get(options2).getName()
                        + citysBean.getData().get(options1).getCity().get(options2).getArea().get(options3).getName());
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
            pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
            pvOptions.show();
        }
    }

    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

//    private ArrayList<CitysBean.DataBean> options1Items = new ArrayList<>();
//    private ArrayList<ArrayList<CitysBean.DataBean.CityBean>> options2Items = new ArrayList<>();
//    private ArrayList<ArrayList<ArrayList<CitysBean.DataBean.CityBean.AreaBean>>> options3Items = new ArrayList<>();

    private void selectshifaCity1() {
        OptionsPickerView pvOptions = new OptionsPickerView.Builder(getActivity(), new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
//                String tx = options1Items.get(options1).getPickerViewText()+
//                        options2Items.get(options1).get(options2)+
//                        options3Items.get(options1).get(options2).get(options3);

                //Toast.makeText(getActivity(),"opt",Toast.LENGTH_SHORT).show();

                Log.i("as", options1 + "s" + options2 + "s" + options3 + "y");
                start_provinceid = Integer.parseInt(citysBean.getData().get(options1).getProvince_id());
                start_cityid = Integer.parseInt(citysBean.getData().get(options1).getCity().get(options2).getCity_id());
                start_areaid = Integer.parseInt(citysBean.getData().get(options1).getCity().get(options2).getArea().get(options3).getArea_id());
                shifa2.setText(citysBean.getData().get(options1).getName() + citysBean.getData().get(options1).getCity().get(options2).getName()
                        + citysBean.getData().get(options1).getCity().get(options2).getArea().get(options3).getName());
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
            pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
            pvOptions.show();
        }
    }

    private void selectshifaCity() {
        CityPicker cityPicker = new CityPicker.Builder(getActivity())
                .textSize(14)
                .title("地址选择")
                .titleBackgroundColor("#FFFFFF")
                .confirTextColor("#696969")
                .cancelTextColor("#696969")
                .province("江苏省")
                .city("常州市")
                .district("天宁区")
                .textColor(Color.parseColor("#000000"))
                .provinceCyclic(true)
                .cityCyclic(false)
                .districtCyclic(false)
                .visibleItemsCount(7)
                .itemPadding(10)
                .onlyShowProvinceAndCity(false)
                .build();
        cityPicker.show();
        //监听方法，获取选择结果
        cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                //省份
                String province = citySelected[0];
                //城市
                String city = citySelected[1];
                //区县（如果设定了两级联动，那么该项返回空）
                String district = citySelected[2];
                //邮编
                String code = citySelected[3];
                //为TextView赋值
                Toast.makeText(getActivity(), province + city + district, Toast.LENGTH_SHORT).show();
                // new_address_area.setText(province.trim() + "-" + city.trim() + "-" + district.trim());
            }
        });

    }

    PeiSongBean peiSongBean;
    CitysBean citysBean = null;
    PeiSongBean carpeiSongBean;
    PeiSongBean shuomingSongBean;
    Code message;

    @Override
    public void success(int requestcode, Object o) {
        switch (requestcode) {
            case FABU_BA:
                mingcheng2.setText("");
                zhongliang2.setText("");
                shifa2.setText("");
                modi2.setText("");
                cheliang2.setText("");
                miaoshu2.setText("");
                yunfei2.setText("");
                lianxi2.setText("");
                shuoming2.setText("");
                etshifadi.setText("");
                etmodidi.setText("");
                yshuoming2.setText("");
//                xiehuo2.setText("");
                Base messag = (Base) o;
                toast(messag.getMessage());
                Intent intent = new Intent(getActivity(), HuoWuDetailActivity.class);
                intent.putExtra("type", 1);
                startActivity(intent);
                break;
            case 10:
                peiSongBean = (PeiSongBean) o;
                for (int i = 0; i < peiSongBean.getData().get(0).getType_arr().size(); i++) {
                    peiSongBean.getData().get(0).getType_arr().get(i).setIsselect(false);
                }
                //peiSongBean.getData().get(0).getType_arr();
                break;

            case GET_CITY:
                citysBean = (CitysBean) o;
                //options1Items = (ArrayList<CitysBean.DataBean>) citysBean.getData();
                handleCityData();
                break;
            case CAR_REQURE:
                carpeiSongBean = (PeiSongBean) o;
                for (int j = 0; j < carpeiSongBean.getData().size(); j++) {
                    for (int i = 0; i < carpeiSongBean.getData().get(j).getType_arr().size(); i++) {
                        carpeiSongBean.getData().get(j).getType_arr().get(i).setIsselect(false);
                    }
                }
                break;
            case PEISONG_SHUO:
                shuomingSongBean = (PeiSongBean) o;
                for (int i = 0; i < shuomingSongBean.getData().get(0).getType_arr().size(); i++) {
                    shuomingSongBean.getData().get(0).getType_arr().get(i).setIsselect(false);
                }
                break;

            case JIAOBAOZHENG:
                JiaoFouBean jiaobaozheng = (JiaoFouBean) o;
                if (jiaobaozheng.getData().getType() == 1) {
                    intent = new Intent(getActivity(), ZhiFuActivity.class);
                    intent.putExtra("flag", 1);
                    intent.putExtra("money", jiaobaozheng.getData().getBond());
                    startActivity(intent);
                } else {
                    yizhifu();
                }
                break;

        }
    }

    private void yizhifu() {
        GetData.fubu(0, this, FABU_BA, type_id, weight, car_require,
                car_info, price, invoice, mobile, hand_mode,
                time, start_provinceid, start_cityid,
                start_areaid, end_provinceid, end_cityid, end_areaid,
                yshuoming2.getText().toString().trim(), typeid_bz
                ,etshifadi.getText().toString().trim(), etmodidi.getText().toString().trim());
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
            options3Items.add(Province_AreaList);

            options1Items.add(data);
        }


//                options1Items = (ArrayList<CitysBean.DataBean>) citysBean.getData();
//
//                for (int i=0;i<citysBean.getData().size();i++){//遍历省份
//                    ArrayList<CitysBean.DataBean.CityBean> CityList = new ArrayList<>();//该省的城市列表（第二级）
//                    ArrayList<ArrayList<CitysBean.DataBean.CityBean.AreaBean>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）
//
//                    for (int c=0; c<citysBean.getData().get(i).getCity().size(); c++){//遍历该省份的所有城市
//                        CitysBean.DataBean.CityBean CityName = citysBean.getData().get(i).getCity().get(c);
//                        CityList.add(CityName);//添加城市
//
//                        ArrayList<CitysBean.DataBean.CityBean.AreaBean> City_AreaList = new ArrayList<>();//该城市的所有地区列表
//
//                        //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
//                        if (citysBean.getData().get(i).getCity().get(c).getArea() == null
//                                ||citysBean.getData().get(i).getCity().get(c).getArea().size()==0) {
//                            CitysBean.DataBean.CityBean.AreaBean areaName = new CitysBean.DataBean.CityBean.AreaBean();
//                            areaName.setName("");
//                            City_AreaList.add(areaName);
//                        }else {
//
//                            for (int d=0; d < citysBean.getData().get(i).getCity().get(c).getArea().size(); d++) {//该城市对应地区所有数据
//                                CitysBean.DataBean.CityBean.AreaBean AreaName = citysBean.getData().get(i).getCity().get(c).getArea().get(d);
//
//                                City_AreaList.add(AreaName);//添加该城市所有地区数据
//                            }
//                        }
//                        Province_AreaList.add(City_AreaList);//添加该省所有地区数据
//                    }
//
//                    /**
//                     * 添加城市数据
//                     */
//                    options2Items.add(CityList);
//
//                    /**
//                     * 添加地区数据
//                     */
//                    options3Items.add(Province_AreaList);
//                }
    }


    private int zhongleiid = 0;
    private int shuomingid = 0;
    private int selectcariid = 0;
    private int selectcarchang = 0;
    private int selectcarxing = 0;

    private void alertbotom() {
        if (peiSongBean == null) {
            return;
        }
        View vie = View.inflate(getActivity(), R.layout.bottom_select_car, null);
        final PopupWindow popupWindow = new PopupWindow(getActivity());
        popupWindow.setContentView(vie);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);

        TextView chexing = vie.findViewById(R.id.chexing);
        TextView chexing2 = vie.findViewById(R.id.chexing2);
        TextView chechang = vie.findViewById(R.id.chechang);

        chechang.setVisibility(View.GONE);
        chexing2.setVisibility(View.GONE);
        chexing.setText("货物类型");

        vie.findViewById(R.id.guanbi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
            }
        });


        vie.findViewById(R.id.queding).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
//
//                List<String> data = new ArrayList();
//                for(int i = 0; i < carListBean.getData().size();i++){
//                    if( carListBean.getData().get(i).getIsselect()){
//                        data.add(carListBean.getData().get(i).getCar_id());
//                    }
//                }
//                if(data.size()<1){
//                    toast("请选择车辆");
//                    return;
//                }
//                String[] ids = new String[data.size()];
//                for(int i = 0; i < data.size();i++){
//                    ids[i] = data.get(i);
//                }
//                CommonApi.getInstance().agree(SharedPreferencesUtil.getInstance().getString("token"),driverListBean.getData().get(position).getDriver_jionid(),driverListBean.getData().get(position).getDriver_id(),ids).subscribeOn(io.reactivex.schedulers.Schedulers.newThread())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new Observer<DriverListBean>() {
//                            @Override
//                            public void onSubscribe(@NonNull Disposable d) {
//                                Log.i("as",d.toString());
//                            }
//
//                            @Override
//                            public void onNext(@NonNull DriverListBean code) {
//                                Log.i("as",code.toString());
//                                if(code.getCode() == 200){
//                                    toast("已同意");
//                                }
//                            }
//
//                            @Override
//                            public void onError(@NonNull Throwable e) {
//                                Log.i("as",e.toString());
//                            }
//
//                            @Override
//                            public void onComplete() {
//
//                            }
//                        });
            }
        });
        final GoodNameAdapter selectCarAdapter = new GoodNameAdapter(getActivity());
        RecyclerView rv = vie.findViewById(R.id.selectcar);
        rv.setAdapter(selectCarAdapter);
        rv.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        popupWindow.showAtLocation(vie, Gravity.BOTTOM, 0, 0);
        selectCarAdapter.addData(peiSongBean.getData().get(0).getType_arr());

        selectCarAdapter.setOnItemClickListener(new BaseHolder.OnItemClickListener() {
            @Override
            public void OnItemClick(final View view, int position) {
                if (peiSongBean.getData().get(0).getType_arr().get(position).getIsselect()) {
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
                for (int i = 0; i < peiSongBean.getData().get(0).getType_arr().size(); i++) {
                    peiSongBean.getData().get(0).getType_arr().get(i).setIsselect(false);
                }
                peiSongBean.getData().get(0).getType_arr().get(position).setIsselect(!peiSongBean.getData().get(0).getType_arr().get(position).getIsselect());
                // dat.put(position, !dat.get(position));
                //selectCarAdapter.clear();
                selectCarAdapter.addData2(peiSongBean.getData().get(0).getType_arr(), 0);
                zhongleiid = position + 1;
                Log.i("as", zhongleiid + "");
                mingcheng2.setText(peiSongBean.getData().get(0).getType_arr().get(position).getChose_name());
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
    }


    @Override
    protected void initListener() {
        super.initListener();
        gggroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.ggyou:
                        ggyou.setChecked(true);
                        ggwu.setChecked(false);
                        invoice = 1;
                        break;
                    case R.id.ggwu:
                        ggyou.setChecked(false);
                        ggwu.setChecked(true);
                        invoice = 2;
                        break;
                }
            }
        });
    }
}
