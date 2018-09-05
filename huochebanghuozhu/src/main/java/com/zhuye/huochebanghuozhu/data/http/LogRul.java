package com.zhuye.huochebanghuozhu.data.http;

import android.util.Log;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/5/29 0029.
 */

public class LogRul implements Interceptor {


    public Boolean open = false;

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request =chain.request();

        if(open){
            HttpUrl httpUrl = request.url();
            String url =   httpUrl.url().toString();
            String method = request.method();
            StringBuilder builder = new StringBuilder();
            if(method.equalsIgnoreCase("post")){
                FormBody body = (FormBody) request.body();
                for (int  i = 0; i<body.size();i++){
                    String name = body.name(i);
                    String value = body.value(i);
                    builder.append("/");
                    builder.append(name);
                    builder.append("/");
                    builder.append(value);
                }
            }
            Log.i("LogRul",url+builder.toString());
            Log.i("LogRul",method);
            Log.i("LogRul",builder.toString());
        }

//        MultipartBody body = (MultipartBody) request.body();
//        List<MultipartBody.Part> parts = body.parts();
        return chain.proceed(request);
    }
}
