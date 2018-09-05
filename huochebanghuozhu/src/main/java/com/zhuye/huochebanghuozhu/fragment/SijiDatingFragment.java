package com.zhuye.huochebanghuozhu.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;
import com.zhuye.huochebanghuozhu.MainActivity;
import com.zhuye.huochebanghuozhu.R;
import com.zhuye.huochebanghuozhu.activity.LoginActivity;
import com.zhuye.huochebanghuozhu.activity.PeiSongBean;
import com.zhuye.huochebanghuozhu.activity.SiJidetailActivity;
import com.zhuye.huochebanghuozhu.adapter.dading.DaTingHomeAdapter2;
import com.zhuye.huochebanghuozhu.adapter.fabu.GoodNameAdapter;
import com.zhuye.huochebanghuozhu.base.BaseHolder;
import com.zhuye.huochebanghuozhu.bean.CitysBean;
import com.zhuye.huochebanghuozhu.bean.DaTingBean;
import com.zhuye.huochebanghuozhu.bean.JsonBean;
import com.zhuye.huochebanghuozhu.data.GetData;
import com.zhuye.huochebanghuozhu.utils.MyItemAni;
import com.zhuye.huochebanghuozhu.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;

import static com.zhuye.huochebanghuozhu.R.id.phone;

/**
 * Created by Administrator on 2018/1/9 0009.
 */

public class SijiDatingFragment extends BaseFragment implements PermissionListener {


    private static final int SELECT = 11;
    private static final int SOUSUO = 12;
    List<String> dataset;
    List<String> todataset;
    List<String> tyepdataset;
    String mobile = "";

    @BindView(R.id.dading_rv)
    RecyclerView dadingRv;

    RelativeLayout root3;
    RelativeLayout root2;
    RelativeLayout root;

    TextView startcity;
    TextView endcity;

    SmartRefreshLayout smartRefreshLayout;
    DaTingHomeAdapter2 adapter2;
    private List<DaTingBean.DataBean> daTingBeanList = new ArrayList<>();
    private int page = 1;
    DaTingBean datas;
    PopupWindow window;
    @Override
    protected void initView() {
        root3 = rootView.findViewById(R.id.root3);
        root2 = rootView.findViewById(R.id.root2);
        root = rootView.findViewById(R.id.root);
        startcity = rootView.findViewById(R.id.startcity);
        endcity = rootView.findViewById(R.id.endcity);
        smartRefreshLayout = rootView.findViewById(R.id.smartRefreshLayout);

        dataset = new LinkedList<>(Arrays.asList("河南", "河南", "河南", "河南"));
        //haha.setTextSize(DensityUtil.dip2px(getActivity(),12));
        todataset = new LinkedList<>(Arrays.asList("河北"));
        tyepdataset = new LinkedList<>(Arrays.asList("定制搜索"));

        adapter2 = new DaTingHomeAdapter2(R.layout.dating_home_item);
        dadingRv.setAdapter(adapter2);
        dadingRv.setLayoutManager(new LinearLayoutManager(getActivity()));
    }



    @Override
    protected void initData() {
        super.initData();
        GetData.city(1, this, GET_CITY);
        GetData.select(this, SELECT);
        //初始化司机大厅
        //GetData.driver(1, SharedPreferencesUtil.getInstance().getString("city"),);
        GetData.driver(page, null, null, null, SijiDatingFragment.this, SOUSUO);
        initPopupWindows();
    }

    private Integer chexingyong;
    private Integer chechang;
    private Integer chexing;
    List<Integer> data;

    private void initPopupWindows() {
        window = new PopupWindow(getActivity());
        window.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        window.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        View search = View.inflate(getActivity(), R.layout.bottom_select_car, null);
        window.setContentView(search);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setOutsideTouchable(true);
        window.setFocusable(true);

        selectCarAdapter = new GoodNameAdapter(getActivity());
        selectCarAdapter2 = new GoodNameAdapter(getActivity());
        selectCarAdapter3 = new GoodNameAdapter(getActivity());

        RecyclerView rv = search.findViewById(R.id.selectcar);
        RecyclerView selectcarlength = search.findViewById(R.id.selectcarlength);
        final RecyclerView selectcarmodel = search.findViewById(R.id.selectcarmodel);
        ImageView guanbi = search.findViewById(R.id.guanbi);
        Button queding = search.findViewById(R.id.queding);


        guanbi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (window != null && window.isShowing()) {
                    window.dismiss();
                }
            }
        });


        queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (window != null && window.isShowing()) {
                    window.dismiss();
                }
                String start = startcity.getText().toString().trim();
                String end = endcity.getText().toString().trim();


                data = new ArrayList<Integer>();
                if (chexingyong != null) {
                    data.add(Integer.valueOf(peisongbean.getData().get(0).getType_arr().get(chexingyong).getChose_id()));
                }

                if (chechang != null) {
                    data.add(Integer.valueOf(peisongbean.getData().get(1).getType_arr().get(chechang).getChose_id()));
                }

                if (chexing != null) {
                    data.add(Integer.valueOf(peisongbean.getData().get(2).getType_arr().get(chexing).getChose_id()));
                }

                //  String startid = citysBean.getData().get(start_provinceid).getCity().get(start_cityid).getName();
                // String startid2 = citysBean.getData().get(start_provinceid).getCity().get(start_cityid).getCity_id();
                // Log.i("as")
//                toast(start_cityid + "");
                //String endid = citysBean.getData().get(end_provinceid).getCity().get(end_cityid).getCity_id();

                //Log.i("as",start_cityid+"--"+end_cityid+"--"+data);
                if (start_cityid == 0) {
                    start_cityid = null;
                }
                GetData.driver(page, start_cityid, end_cityid, data, SijiDatingFragment.this, SOUSUO);
//                private Integer chexingyong;
//                private Integer chechang;
//                private Integer chexing;

            }
        });

        ((SimpleItemAnimator) selectcarlength.getItemAnimator()).setSupportsChangeAnimations(false);
        selectcarlength.setItemAnimator(new MyItemAni());

        rv.setAdapter(selectCarAdapter);
        rv.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        selectcarlength.setAdapter(selectCarAdapter2);
        selectcarlength.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        selectcarmodel.setAdapter(selectCarAdapter3);
        selectcarmodel.setLayoutManager(new GridLayoutManager(getActivity(), 3));
    }

    GoodNameAdapter selectCarAdapter;
    GoodNameAdapter selectCarAdapter2;
    GoodNameAdapter selectCarAdapter3;
    private static final int GET_CITY = 9;

    @Override
    protected void initListener() {
        super.initListener();
        selectCarAdapter.setOnItemClickListener(new BaseHolder.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                chexingyong = position;

//                if (peisongbean.getData().get(0).getType_arr().get(position).getIsselect()) {
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            view.findViewById(R.id.fabu_name).setVisibility(View.INVISIBLE);
//                        }
//                    });
//                    //dat.get(position)=false;
//                } else {
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            view.findViewById(R.id.fabu_name).setVisibility(View.VISIBLE);
//                        }
//                    });
//                }
                int count = selectCarAdapter.getItemCount();
                for (int i = 0; i < count; i++) {
//                    if(i ==position){
//                        ((TextView)view.findViewById(R.id.fabu_name)).setTextColor(Color.RED);
//                    }else {
//
//                        ((TextView)view.findViewById(R.id.fabu_name)).setTextColor(Color.RED);
//                    }
                }


                for (int i = 0; i < peisongbean.getData().get(0).getType_arr().size(); i++) {
                    peisongbean.getData().get(0).getType_arr().get(i).setIsselect(false);
                }
                peisongbean.getData().get(0).getType_arr().get(position).setIsselect(true);
                // dat.put(position, !dat.get(position));
                //selectCarAdapter.clear();
                selectCarAdapter.addData2(peisongbean.getData().get(0).getType_arr(), 0);
                //selectcariid = position + 1;
                // Log.i("as", selectcariid + "");
                // cheliang2.setText(peisongbean.getData().get(0).getType_arr().get(position).getChose_name());

            }
        });

        selectCarAdapter2.setOnItemClickListener(new BaseHolder.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int positi) {
                Log.i("as", positi + "");
                chechang = positi;
                for (int i = 0; i < peisongbean.getData().get(1).getType_arr().size(); i++) {
                    peisongbean.getData().get(1).getType_arr().get(i).setIsselect(false);
                }
                peisongbean.getData().get(1).getType_arr().get(positi).setIsselect(true);
                // dat.put(position, !dat.get(position));
                //selectCarAdapter2.clear();
                selectCarAdapter2.addData2(peisongbean.getData().get(1).getType_arr(), 0);
            }
        });
        selectCarAdapter3.setOnItemClickListener(new BaseHolder.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int posi) {
                chexing = posi;

                for (int i = 0; i < peisongbean.getData().get(2).getType_arr().size(); i++) {
                    peisongbean.getData().get(2).getType_arr().get(i).setIsselect(false);
                }
                peisongbean.getData().get(2).getType_arr().get(posi).setIsselect(true);
                // dat.put(position, !dat.get(position));
                //selectCarAdapter2.clear();
                selectCarAdapter3.addData2(peisongbean.getData().get(2).getType_arr(), 0);
            }
        });


        root3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectStartCity();
            }
        });

        root2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selsectEndCity();
            }
        });

        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWindow(v);
            }
        });

        adapter2.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {

                    case R.id.phone:
                        // toast("dasinahu");
                        mobile = daTingBeanList.get(position).getMobile();
                        alertdialog(mobile);
                        break;
                }
            }
        });

        adapter2.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                if (SharedPreferencesUtil.getInstance().getString("token") == null || TextUtils.isEmpty(SharedPreferencesUtil.getInstance().getString("token"))) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                    //getActivity().finish();
                } else {
                    String mobile = daTingBeanList.get(position).getMobile();
                    Intent intent = new Intent(getActivity(), SiJidetailActivity.class);
                    intent.putExtra("mobile", mobile);
                    intent.putExtra("car_id", daTingBeanList.get(position).getCar_id());
                    intent.putExtra("jisi_id", daTingBeanList.get(position).getUid());
                    intent.putExtra("type", daTingBeanList.get(position).getType());
                    intent.putExtra("models", daTingBeanList.get(position).getModels());
                    startActivity(intent);
                }
            }
        });
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                adapter2.clear();
                daTingBeanList.clear();
                GetData.driver(page, start_cityid, end_cityid, data, SijiDatingFragment.this, SOUSUO);
                smartRefreshLayout.finishRefresh();
            }
        });
        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                GetData.driver(page, start_cityid, end_cityid, data, SijiDatingFragment.this, SOUSUO);
                smartRefreshLayout.finishLoadmore();
            }
        });
    }

    private Boolean showdown = true;

    private void showWindow(View v) {
        if (window != null) {
            //startAnim();
            window.showAsDropDown(root);
            showdown = false;
        }
    }


    private void alertdialog(final String mobile) {
        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();
        View view = View.inflate(getActivity(), R.layout.aliet, null);
        dialog.setView(view);
        dialog.show();


        ((TextView) view.findViewById(phone)).setText(mobile);
        view.findViewById(R.id.queren).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //personAge.setText(city[pos]);
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                if (!AndPermission.hasPermission(getActivity()
                        , android.Manifest.permission.CALL_PHONE)) {
                    AndPermission.with(getActivity())
                            .requestCode(100)
                            .permission(android.Manifest.permission.CALL_PHONE)
                            .rationale(new RationaleListener() {
                                @Override
                                public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
                                    AndPermission.rationaleDialog(getActivity(), rationale).show();
                                }
                            })
                            .send();
                }
                //打电话
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mobile));
                // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });
        view.findViewById(R.id.quxiao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });
    }

    Integer end_provinceid = 0;
    Integer end_cityid = 0;
    Integer end_areaid = 0;

    private void selsectEndCity() {
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
                // end_areaid = Integer.parseInt(citysBean.getData().get(options1).getCity().get(options2).getArea().get(options3).getArea_id());
//                modi2.setText(citysBean.getData().get(options1).getName()+citysBean.getData().get(options1).getCity().get(options2).getName()
//                        +citysBean.getData().get(options1).getCity().get(options2).getArea().get(options3).getName());
                endcity.setText(citysBean.getData().get(options1).getCity().get(options2).getName());

//                if(start_cityid==0){
//                    start_cityid = null;
//                }
                Log.i("as", start_cityid + "--" + end_cityid + "--" + data);
                GetData.driver(page, start_cityid, end_cityid, data, SijiDatingFragment.this, SOUSUO);
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


    Integer start_provinceid = 0;
    Integer start_cityid = 0;
    Integer start_areaid = 0;

    private void selectStartCity() {
        OptionsPickerView pvOptions = new OptionsPickerView.Builder(getActivity(), new OptionsPickerView.OnOptionsSelectListener() {
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
                startcity.setText(citysBean.getData().get(options1).getCity().get(options2).getName());

//                Log.i("as", start_cityid + "--" + end_cityid + "--" + data);


//                if(end_cityid==0){
//                    end_cityid = null;
//                }

                GetData.driver(page, start_cityid, end_cityid, data, SijiDatingFragment.this, SOUSUO);
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

    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

    @Override
    protected int getResId() {
        return R.layout.fragment_dating;
    }

    CitysBean citysBean;
    PeiSongBean peisongbean;

    @Override
    public void success(int requestcode, Object o) {

        switch (requestcode) {
            case GET_CITY:
                citysBean = (CitysBean) o;
                handleCityData();
                break;
            case SELECT:
                peisongbean = (PeiSongBean) o;
                for (int j = 0; j < peisongbean.getData().size(); j++) {
                    for (int i = 0; i < peisongbean.getData().get(j).getType_arr().size(); i++) {
                        peisongbean.getData().get(j).getType_arr().get(i).setIsselect(false);
                    }
                }
                selectCarAdapter.addData(peisongbean.getData().get(0).getType_arr());
                selectCarAdapter2.addData(peisongbean.getData().get(1).getType_arr());
                selectCarAdapter3.addData(peisongbean.getData().get(2).getType_arr());
                break;
            case SOUSUO:

                datas = (DaTingBean) o;
                if(datas==null||datas.getData()==null||datas.getData().size()==0){
                    return;
                }
                adapter2.clear();
                daTingBeanList.clear();

                daTingBeanList.addAll(datas.getData());
                adapter2.addData(daTingBeanList);
                break;
        }
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
        AndPermission.onRequestPermissionsResult(requestCode, permissions, grantResults, this);

    }

    @Override
    public void onSucceed(int requestCode, List<String> grantPermissions) {

    }

    @Override
    public void onFailed(int requestCode, List<String> deniedPermissions) {

    }
}
