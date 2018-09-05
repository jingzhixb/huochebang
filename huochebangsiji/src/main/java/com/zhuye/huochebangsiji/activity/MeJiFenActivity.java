package com.zhuye.huochebangsiji.activity;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhuye.huochebangsiji.R;
import com.zhuye.huochebangsiji.base.BaseActivity;
import com.zhuye.huochebangsiji.contants.Contans;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MeJiFenActivity extends BaseActivity {


    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.tvv)
    TextView tvv;
    @BindView(R.id.webview)
    WebView webview;

    @Override
    protected int getResId() {
        return R.layout.activity_me_ji_fen;
    }

    //http://whale.zyeo.net/index.php/home/index/jifen
    @Override
    protected void initData() {
        super.initData();

        webview.loadUrl(Contans.BASE_URL + "/index.php/home/index/jifen/article_id/3");
        OkGo.<String>post(Contans.BASE_URL + "/index.php/home/index/jifen/article_id/3")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.i("as", response.body());


                        tvv.setText(Html.fromHtml(response.body()));
                        //webView.loadData(response.body(),"text/html","utf8");
                        //webView.loadUrl("http://www.baidu.com");
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Log.i("as", response.body());
                    }
                });
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
