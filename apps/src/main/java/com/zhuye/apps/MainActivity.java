package com.zhuye.apps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //AdManager.getInstance(Context context).init(String appId, String appSecret, boolean isEnableYoumiLog);
    }
}
