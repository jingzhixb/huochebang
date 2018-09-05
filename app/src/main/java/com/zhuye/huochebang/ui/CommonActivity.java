package com.zhuye.huochebang.ui;

import android.util.Log;
import android.webkit.WebView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhuye.huochebang.BaseActivity;
import com.zhuye.huochebang.R;
import com.zhuye.huochebang.utils.SharedPreferencesUtil;

import butterknife.BindView;

public class CommonActivity extends BaseActivity {

    @BindView(R.id.webview)
    WebView webview;

    @Override
    protected int getResId() {
        return R.layout.activity_common;
    }

    @Override
    protected void initData() {
        super.initData();
        int type =  getIntent().getIntExtra("type",0);

        OkGo.<String>post("http://whale.zyeo.net/index.php/home/commonpart/article").params("token", SharedPreferencesUtil.getInstance().getString("token"))
                .params("article_id",4)
                .execute(new StringCallback() {//content
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.i("as",response.body());
                        ;
                        String aaa = response.body().substring(response.body().indexOf("<p"),response.body().indexOf("},")-1);


                       webview.getSettings().setDefaultTextEncodingName("UTF-8");//设置默认为utf-8
                        webview.loadData(aaa, "text/html", "UTF-8");//API提供的标准用法，无法解决乱码问题

                        //webview.loadData(aaa, "text/html; charset=UTF-8", null);//这种写法可以正确解码
//                        try {
//                            JSONObject jsonObject = new JSONObject(response.body());
//                            //jsonObject.getJSONObject("comm")
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Log.i("as",response.body());
                    }
                });
    }
}
