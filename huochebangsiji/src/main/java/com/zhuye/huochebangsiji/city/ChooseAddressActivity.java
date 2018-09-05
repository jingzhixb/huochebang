package com.zhuye.huochebangsiji.city;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.zhuye.huochebangsiji.R;
import com.zhuye.huochebangsiji.base.BaseActivity;
import com.zhuye.huochebangsiji.base.BaseHolder;
import com.zhuye.huochebangsiji.bean.CityBean;
import com.zhuye.huochebangsiji.bean.CitysBean;
import com.zhuye.huochebangsiji.data.GetData;
import com.zhuye.huochebangsiji.helper.StateViewHelper;
import com.zhuye.huochebangsiji.utils.SharedPreferencesUtil;
import com.zhuye.huochebangsiji.widget.RoundedCornerImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;


public class ChooseAddressActivity extends BaseActivity {

    private static final int CITYSS = 10;

    @BindView(R.id.sidrbar)
    SideBar sideBar;
    @BindView(R.id.iv_back)
    RoundedCornerImageView ivBack;
    @BindView(R.id.toolbar2)
    Toolbar toolbar2;
    @BindView(R.id.country_lvcountry)
    ListView sortListView;
    @BindView(R.id.dialog)
    TextView dialog;


    private CharacterParser characterParser;
    private List<SortModel2> SourceDateList;

    /**
     * 汉字转成拼音的类
     */
    private PinyinComparator pinyinComparator;
    private ListSortAdapter adapter;
    private String[] strings;
    private String[] city;


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView();
//        unbinder = ButterKnife.bind(this);
//    }


    @Override
    protected void initView() {
        super.initView();
        lodingview = LayoutInflater.from(this).inflate(R.layout.loding, (ViewGroup) rootview);
        initViews();
        initData();
    }

    @Override
    protected int getResId() {
        return R.layout.activity_choose_address;
    }

    CityBean bean;
    CitysBean data;

    @Override
    public void success(int requestcode, Object o) {
        super.success(requestcode, o);
        //ListSortAdapter
        data = (CitysBean) o;
        Map<String, String> map = new HashMap<String, String>();
        for (int i = 0; i < data.getData().size(); i++) {
            for (int j = 0; j < data.getData().get(i).getCity().size(); j++) {
                map.put(data.getData().get(i).getCity().get(j).getName(), data.getData().get(i).getCity().get(j).getCity_id());
            }
        }
        SourceDateList = filledData(map);
        //根据a-z进行排序源数据
        Collections.sort(SourceDateList, pinyinComparator);
//        创建adapter
        adapter = new ListSortAdapter(ChooseAddressActivity.this, SourceDateList);
//        给listView设置数据
        sortListView.setAdapter(adapter);

        setMode(StateViewHelper.MODE_LOADING);
    }






    @Override
    protected Context getContexta() {
        return this;
    }

    protected void initData() {
        GetData.city(1, this, CITYSS);



//        OkGo.<String>post(Contans.BASE_URL)
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(Response<String> response) {
//                        Log.i("---",response.body());
//                        if(response.body().contains("200")){
//                            Gson gson = new Gson();
//                            bean = gson.fromJson(response.body(),CityBean.class);
//
//                            // bean.getData().getList().getA().size();
//
//                            Map<String,String> map = new HashMap<String, String>();
//
//                            for (int i = 0;i <bean.getData().getList().getA().size();i++){
//                                map.put(bean.getData().getList().getA().get(i).getCity(),bean.getData().getList().getA().get(i).getCity_code());
//                            }
//
//                            for (int i = 0;i <bean.getData().getList().getB().size();i++){
//                                map.put(bean.getData().getList().getB().get(i).getCity(),bean.getData().getList().getB().get(i).getCity_code());
//                            }
//                            for (int i = 0;i <bean.getData().getList().getC().size();i++){
//                                map.put(bean.getData().getList().getC().get(i).getCity(),bean.getData().getList().getC().get(i).getCity_code());
//                            }
//                            for (int i = 0;i <bean.getData().getList().getD().size();i++){
//                                map.put(bean.getData().getList().getD().get(i).getCity(),bean.getData().getList().getD().get(i).getCity_code());
//                            }
//                            for (int i = 0;i <bean.getData().getList().getE().size();i++){
//                                map.put(bean.getData().getList().getE().get(i).getCity(),bean.getData().getList().getE().get(i).getCity_code());
//                            }
//                            for (int i = 0;i <bean.getData().getList().getF().size();i++){
//                                map.put(bean.getData().getList().getF().get(i).getCity(),bean.getData().getList().getF().get(i).getCity_code());
//                            }
//                            for (int i = 0;i <bean.getData().getList().getG().size();i++){
//                                map.put(bean.getData().getList().getG().get(i).getCity(),bean.getData().getList().getG().get(i).getCity_code());
//                            }
//                            for (int i = 0;i <bean.getData().getList().getH().size();i++){
//                                map.put(bean.getData().getList().getH().get(i).getCity(),bean.getData().getList().getH().get(i).getCity_code());
//                            }
//                            for (int i = 0;i <bean.getData().getList().getJ().size();i++){
//                                map.put(bean.getData().getList().getJ().get(i).getCity(),bean.getData().getList().getJ().get(i).getCity_code());
//                            }
//                            for (int i = 0;i <bean.getData().getList().getK().size();i++){
//                                map.put(bean.getData().getList().getK().get(i).getCity(),bean.getData().getList().getK().get(i).getCity_code());
//                            }
//                            for (int i = 0;i <bean.getData().getList().getL().size();i++){
//                                map.put(bean.getData().getList().getL().get(i).getCity(),bean.getData().getList().getL().get(i).getCity_code());
//                            }
//                            for (int i = 0;i <bean.getData().getList().getM().size();i++){
//                                map.put(bean.getData().getList().getM().get(i).getCity(),bean.getData().getList().getM().get(i).getCity_code());
//                            }
//                            for (int i = 0;i <bean.getData().getList().getN().size();i++){
//                                map.put(bean.getData().getList().getN().get(i).getCity(),bean.getData().getList().getN().get(i).getCity_code());
//                            }
//                            for (int i = 0;i <bean.getData().getList().getP().size();i++){
//                                map.put(bean.getData().getList().getP().get(i).getCity(),bean.getData().getList().getP().get(i).getCity_code());
//                            }
//                            for (int i = 0;i <bean.getData().getList().getQ().size();i++){
//                                map.put(bean.getData().getList().getQ().get(i).getCity(),bean.getData().getList().getQ().get(i).getCity_code());
//                            }
//                            for (int i = 0;i <bean.getData().getList().getR().size();i++){
//                                map.put(bean.getData().getList().getR().get(i).getCity(),bean.getData().getList().getR().get(i).getCity_code());
//                            }
//
//                            for (int i = 0;i <bean.getData().getList().getS().size();i++){
//                                map.put(bean.getData().getList().getS().get(i).getCity(),bean.getData().getList().getS().get(i).getCity_code());
//                            }
//                            for (int i = 0;i <bean.getData().getList().getT().size();i++){
//                                map.put(bean.getData().getList().getT().get(i).getCity(),bean.getData().getList().getT().get(i).getCity_code());
//                            }
//                            for (int i = 0;i <bean.getData().getList().getW().size();i++){
//                                map.put(bean.getData().getList().getW().get(i).getCity(),bean.getData().getList().getW().get(i).getCity_code());
//                            }
//
//                            for (int i = 0;i <bean.getData().getList().getX().size();i++){
//                                map.put(bean.getData().getList().getX().get(i).getCity(),bean.getData().getList().getX().get(i).getCity_code());
//                            }
//                            for (int i = 0;i <bean.getData().getList().getY().size();i++){
//                                map.put(bean.getData().getList().getY().get(i).getCity(),bean.getData().getList().getY().get(i).getCity_code());
//                            }
//                            for (int i = 0;i <bean.getData().getList().getZ().size();i++){
//                                map.put(bean.getData().getList().getZ().get(i).getCity(),bean.getData().getList().getZ().get(i).getCity_code());
//                            }
//                            SourceDateList = filledData(map);
//                                   // filledData(getResources().getStringArray(R.array.date));
////       根据a-z进行排序源数据
//                            Collections.sort(SourceDateList, pinyinComparator);
////        创建adapter
//                            adapter = new ListSortAdapter(ChooseAddressActivity.this, SourceDateList);
////        给listView设置数据
//                            sortListView.setAdapter(adapter);
//
//                            ma = new HashMap();
//
//                            for (int i = 0;i<bean.getData().getHot().size();i++){
//                                ma.put(bean.getData().getHot().get(i).getCity(),bean.getData().getHot().get(i).getCity_code());
//                            }
//                            ada.addData(ma);
//                        }
//                    }
//
//                    @Override
//                    public void onError(Response<String> response) {
//                        super.onError(response);
//                    }
//                });
    }


    Map ma;
    HotAdapter ada;

    @Override
    public void onBackPressed() {
        Intent in = new Intent();
        in.putExtra("city", SharedPreferencesUtil.getInstance().getString("city", ""));
        in.putExtra("citycode", SharedPreferencesUtil.getInstance().getString("code", ""));
        setResult(100, in);
        finish();

    }

    private void initViews() {
//        实例化汉字转拼音
//        获取characterParser的实例
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent();
                in.putExtra("city", SharedPreferencesUtil.getInstance().getString("city", ""));
                in.putExtra("citycode", SharedPreferencesUtil.getInstance().getString("code", ""));
                setResult(100, in);
                finish();
            }
        });
        characterParser = CharacterParser.getInstance();

        pinyinComparator = new PinyinComparator();

        sideBar.setTextView(dialog);

        //设置右触摸监听
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                //该字母首次出现的位置
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    sortListView.setSelection(position);
                }

            }
        });
        View headerView = View.inflate(this, R.layout.address_header, null);
        TextView tv_now = (TextView) headerView.findViewById(R.id.tv_now);

        tv_now.setText(getIntent().getStringExtra("now"));
        tv_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ChooseAddressActivity.this, "当前位置", Toast.LENGTH_SHORT).show();

            }
        });
        RecyclerView tv_hot = (RecyclerView) headerView.findViewById(R.id.tv_hot);

        ada = new HotAdapter(this);
        tv_hot.setAdapter(ada);
        tv_hot.setLayoutManager(new GridLayoutManager(this, 4));
        ada.setOnItemClickListener(new BaseHolder.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                //CommentUtils.toast(ChooseAddressActivity.this,ma.);
                String city = (String) ((TextView) view.findViewById(R.id.name)).getText();
                String citycode = (String) ma.get(city);
                Intent in = new Intent();
                in.putExtra("city", city);
                in.putExtra("citycode", citycode);
                setResult(100, in);
                finish();
            }
        });

//        tv_hot.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                这里要利用adapter.getItem(position)来获取当前position所对应的对象
//                Toast.makeText(ChooseAddressActivity.this, "热门位置", Toast.LENGTH_SHORT).show();
//            }
//        });
        sortListView.addHeaderView(headerView);
        sortListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (position > 0) {
                    //Toast.makeText(getApplication(), ((SortModel2) adapter.getItem(position - 1)).getCitycode(), Toast.LENGTH_SHORT).show();
                    SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
                    sp.edit().putString("Location", ((SortModel2) adapter.getItem(position - 1)).getCitycode()).commit();
                    Intent in = new Intent();
                    in.putExtra("city", ((SortModel2) adapter.getItem(position - 1)).getName());
                    in.putExtra("citycode", ((SortModel2) adapter.getItem(position - 1)).getCitycode());
                    setResult(100, in);
                    finish();
                }
            }
        });
//       OkGo.get(MyApplication.ip+"mobile/indexapi/c").execute(new StringCallback() {
//        @Override
//        public void onSuccess(String s, Call call, Response response) {
//            try {
//                JSONObject jsonObject =  new  JSONObject(s);
//                if (jsonObject.getString("code").equals("402")) {
//
//                    List<CityId> city =  JSON.parseArray(jsonObject.getString("data"), CityId.class);
////                   写不了接口 转成字符串数组
//                   String[]  strings =  new String[]{};
//                    for (int i = 0;i<city.size();i++){
//                        String cityName = city.get(i).getName();
//
//                    }
//                    SourceDateList= filledData(strings);
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//    });


//        mClearEditText.addTextChangedListener(new TextWatcher() {
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                //µ±ÊäÈë¿òÀïÃæµÄÖµÎª¿Õ£¬¸üÐÂÎªÔ­À´µÄÁÐ±í£¬·ñÔòÎª¹ýÂËÊý¾ÝÁÐ±í
//                filterData(s.toString());
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count,
//                                          int after) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//            }
//        });


    }

    /**
     * ÎªListViewÌî³äÊý¾Ý
     * 填充数据
     *
     * @param date
     * @return
     */


    private List<SortModel> filledData(String[] date) {
        List<SortModel> mSortList = new ArrayList<SortModel>();

        for (int i = 0; i < date.length; i++) {
            SortModel sortModel = new SortModel();
            sortModel.setName(date[i]);
//            汉字转拼音
            String pinyin = characterParser.getSelling(date[i]);
//            转换为大写
            String sortString = pinyin.substring(0, 1).toUpperCase();

//            正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]")) {
                sortModel.setSortLetters(sortString.toUpperCase());
            } else {
                sortModel.setSortLetters("#");
            }

            mSortList.add(sortModel);
        }
        return mSortList;

    }


    private List<SortModel2> filledData(Map date) {
        List<SortModel2> mSortList = new ArrayList<SortModel2>();
        Iterator iter = date.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            SortModel2 sortModel = new SortModel2();
            sortModel.setCitycode((String) entry.getValue());
            sortModel.setName((String) entry.getKey());
            String pinyin = characterParser.getSelling((String) entry.getKey());
            String sortString = pinyin.substring(0, 1).toUpperCase();
            if (sortString.matches("[A-Z]")) {
                sortModel.setSortLetters(sortString.toUpperCase());
            } else {
                sortModel.setSortLetters("#");
            }
            mSortList.add(sortModel);
        }
        return mSortList;
    }

    /**
     * 根据输入框中的值来过滤数据并更新ListView
     *
     * @param filterStr
     */
    private void filterData(String filterStr) {
        List<SortModel2> filterDateList = new ArrayList<SortModel2>();

        if (TextUtils.isEmpty(filterStr)) {
            filterDateList = SourceDateList;
        } else {
            filterDateList.clear();
            for (SortModel2 sortModel : SourceDateList) {
                String name = sortModel.getName();
                if (name.indexOf(filterStr.toString()) != -1 || characterParser.getSelling(name).startsWith(filterStr.toString())) {
                    filterDateList.add(sortModel);
                }
            }
        }

        //  根据a-z进行排序
        Collections.sort(filterDateList, pinyinComparator);
        adapter.updateListView(filterDateList);
    }

    @Override
    protected void onDestroy() {
        //ButterKnife.unbind(this);
        OkGo.getInstance().cancelTag(this);
        super.onDestroy();
    }
}
