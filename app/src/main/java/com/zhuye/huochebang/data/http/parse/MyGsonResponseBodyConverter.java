package com.zhuye.huochebang.data.http.parse;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.zhuye.huochebang.bean.Base;
import com.zhuye.huochebang.bean.Notify;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by Administrator on 2018/2/2 0002.
 */

public class MyGsonResponseBodyConverter<T extends Base> implements Converter<ResponseBody, T> {

    private final Gson gson;
    private final Type type;

    public MyGsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override public T convert(ResponseBody value) throws IOException {

        String json = value.string();
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        // 解析code
        JsonPrimitive jsonPrimitive = jsonObject.getAsJsonPrimitive("code");
        int code = 0;
        if (jsonPrimitive != null) {
            code = jsonPrimitive.getAsInt();
        }
        // 解析message
        JsonElement jsonElement = jsonObject.get("message");
        String message = null;
        if (jsonElement != null) {
            message = jsonElement.getAsString();
        }

        T t = null;
        try {
            // 通过反射获取泛型的实例对象
            Class<T> clazz = (Class<T>) type;
            t = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (code != 200 ) {

            // 解析message
            JsonElement jsonElemen = jsonObject.get("message");
            String messag = null;
            if (jsonElemen != null) {
                messag = jsonElemen.getAsString();
                t.message = message;
            }

            // 按停服公告的格式解析，封装到notify字段中
            try {
                JsonElement element = jsonObject.get("data");
               // t.notify = gson.fromJson(jsonObject.getAsJsonObject("data"), Notify.class);
                if(!TextUtils.isEmpty(element.getAsString())){
                    t.notify = gson.fromJson(element.getAsString(), Notify.class);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // 按标准格式解析
            return gson.fromJson(json, type);
        }

        return t;





//        try {
//
////            String data = value.string();
////            //Gson gson =  new Gson();
////            if(!data.contains("200")){
////                Log.i("as",data);
////                //Code code = gson.fromJson(data, Code.class);
////                value.close();
////                throw new JsonParseException(data);
////                //return (T) code;
////            }else {
//                return adapter.fromJson(value.charStream());
//            //}
//
//        } finally {
//            value.close();
//        }
    }
}
