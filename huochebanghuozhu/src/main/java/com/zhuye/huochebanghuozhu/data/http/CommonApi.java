package com.zhuye.huochebanghuozhu.data.http;


import com.zhuye.huochebanghuozhu.activity.PeiSongBean;
import com.zhuye.huochebanghuozhu.bean.AliPayBean;
import com.zhuye.huochebanghuozhu.bean.BangDanBean;
import com.zhuye.huochebanghuozhu.bean.Base;
import com.zhuye.huochebanghuozhu.bean.CitysBean;
import com.zhuye.huochebanghuozhu.bean.Code;
import com.zhuye.huochebanghuozhu.bean.DaTingBean;
import com.zhuye.huochebanghuozhu.bean.DriverDetailBean;
import com.zhuye.huochebanghuozhu.bean.GoodsBean;
import com.zhuye.huochebanghuozhu.bean.GoodsDeailBean;
import com.zhuye.huochebanghuozhu.bean.InvateBean;
import com.zhuye.huochebanghuozhu.bean.JiaoFouBean;
import com.zhuye.huochebanghuozhu.bean.LiuYanBean;
import com.zhuye.huochebanghuozhu.bean.MobileBean;
import com.zhuye.huochebanghuozhu.bean.NewsNumBean;
import com.zhuye.huochebanghuozhu.bean.OrderBean;
import com.zhuye.huochebanghuozhu.bean.OrderDetailBean;
import com.zhuye.huochebanghuozhu.bean.PhoneLoginCde;
import com.zhuye.huochebanghuozhu.bean.RechargeBean;
import com.zhuye.huochebanghuozhu.bean.RegeiserCode;
import com.zhuye.huochebanghuozhu.bean.ShenHeBean;
import com.zhuye.huochebanghuozhu.bean.SystemMessage;
import com.zhuye.huochebanghuozhu.bean.UploadImgBean;
import com.zhuye.huochebanghuozhu.bean.UserInfoBean;
import com.zhuye.huochebanghuozhu.bean.WeixinBean;
import com.zhuye.huochebanghuozhu.bean.XiaoFeiBean;
import com.zhuye.huochebanghuozhu.bean.XingChengBean;

import java.io.File;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;


/**
 * Created by Administrator on 2018/1/3 0003.
 */

public class CommonApi {

    public static CommonApi instance;

    private CommonApiService service;

    public CommonApi() {
        service = HttpUtils.getRetrofit(HttpUtils.getOkhttp()).create(CommonApiService.class);
    }

    public Observable<OrderDetailBean> order_detail(String token, int order_id){
        return service.order_detail(token,order_id);
    }

    public Observable<Base> logout(String token){
        return service.logout(token);
    }

    public static CommonApi getInstance() {
        if (instance == null)
            instance = new CommonApi();
        return instance;
    }
    public Observable<ShenHeBean> message(String token){
        return service.message(token);
    }

    public Observable<Code> forget_ms(String mobile,String password){
        return service.forget_ms(mobile,password);
    }
    /**
     * 获取验证码
     * @param
     * @return
     */

    public Observable<Base> view_news(String token, int type) {
        return service.view_news(token, type);
    }

    public Observable<Base> getCode(String mobile){
        return service.getCode(mobile);
    }

    /**
     * 手机号注册
     * @param mobile
     * @param password
     * @param code
     *
     * @return
     */
    public Observable<RegeiserCode> getRegeister(String mobile , String password, String  code ){
        return service.getregister(mobile,password,code,2);
    }

    /**
     * 登录--账户密码
     * @param mobile
     * @param password
     * @return
     */

    public Observable<PhoneLoginCde> passLogin(String mobile , String password){
        return service.passLogin(mobile,password,2);
    }

    /**
     * 短信登录
     * @param mobile
     * @param code
     * @return
     */
    public Observable<PhoneLoginCde> codelogin(String mobile ,String code){
        return service.codelogin(mobile,code,2);
    }

    /**
     * 图片上传
     * @param token
     * @param filepath 图片路径
     * @return
     */
    public Observable<UploadImgBean> upimg(String token , File filepath){
        File file = filepath;
        RequestBody bod = RequestBody.create(MediaType.parse("multipart/form-data"),token);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("file", file.getName(), requestFile);
        return service.upimg(bod,body);
    }

    public Observable<UserInfoBean> user_info(String token){
        return service.user_info(token);
    }

    public Observable<Code> forget_code(String mobile,String code){
        return service.forget_code(mobile,code);
    }
    public Observable<Code> edit_info(String token,int province_id,int city_id,int start_city,int endcity_id){
        return service.edit_info(token,province_id,city_id,start_city,endcity_id);
    }

    public Observable<MobileBean> getmobile(String token){
        return service.getmobile(token);
    }

    public Observable<Code> new_mobile(String token,String mobile, String code){
        return service.new_mobile(token,mobile,code);
    }

    public Observable<CitysBean> city(Integer type){
        return service.city(type);
    }

    public Observable<MobileBean> server(String token){
        return service.server(token);
    }

    public Observable<InvateBean> invate(String token){
        return service.invate(token,1);
    }

    public Observable<XiaoFeiBean> consumption(String token, int page){
        return service.consumption(token,page);
    }

    public Observable<RechargeBean> recharge(String token, int page){
        return service.recharge(token,page);
    }

    public Observable<LiuYanBean> liuyanmessage(String token,int page){
        return service.liuyanmessage(token,page);
    }

    public Observable<SystemMessage> system_news(int page){
        return service.system_news(page);
    }

    public Observable<PeiSongBean> goodtype(){
        return service.goodtype();
    }

    public Observable<Base> fabu(String token,
                                        List<String> type_id,Float weight,
                                        List<String> car_require,String car_info,
                                        Float price,int invoice,String mobile,List<String> hand_mode,String time,
                                        int start_provinceid,int start_cityid,
                                        int start_areaid,int end_provinceid,
                                        int end_cityid,int end_areaid,
                                        String remark,String typeid_bz , String start_addr
            , String end_addr){
        return service.fabu(token,type_id,weight,car_require,car_info,price,invoice,mobile,hand_mode,
                time,start_provinceid,start_cityid,start_areaid,end_provinceid,end_cityid,end_areaid,remark,typeid_bz,start_addr,end_addr);
    }


    public Observable<PeiSongBean> commander_models(){
        return service.commander_models();
    }


    public Observable<PeiSongBean> hade_model(){
        return service.hade_model();
    }

    public Observable<DaTingBean> driver(int page,
                                         Integer start_city, Integer end_city,
                                         List<Integer> array){
        return service.driver(page,start_city,end_city,array);
    }


    public Observable<PeiSongBean> select(){
        return service.select();
    }

    public Observable<DriverDetailBean> driver_detail(String token, int uid, int car_id){
        return service.driver_detail(token,uid,car_id);
    }


    public Observable<GoodsDeailBean> goods(int page,
                                            int type, String token
                                        ){
        return service.goods(page,type,token);
    }

    public Observable<GoodsBean> good_detail(int good_id,
                                             String token
    ){
        return service.good_detail(good_id,token);
    }



    public Observable<OrderBean> order(int page, String token, int type){
        return service.order(page,token,type);
    }

    public Observable<BangDanBean> view_weight(int order_id, String token,int type){
        return service.view_weight(order_id,token,type);
    }
    public Observable<Code> order_finish(int order_id, String token){
        return service.order_finish(order_id,token);
    }

    public Observable<XingChengBean> order_process(int order_id, String token){
        return service.order_process(order_id,token);
    }

    public Observable<Code> modify_price(int order_id, String token,float price){
        return service.modify_price(order_id,token,price);
    }

    public Observable<Code> cancel_order(String token, int order_id){
        return service.cancel_order(token,order_id,2);
    }

    public Observable<JiaoFouBean> jiaofou(String token){
        return service.jiaofou(token);
    }

    /**
     * 保证金 支付宝支付
     * @param token
     * @return
     */
    public Observable<AliPayBean> pay_aliay(String token){
        return service.pay_aliay(token);
    }
    /**
     * 保证金 微信支付
     * @param token
     * @return
     */
    public Observable<WeixinBean> pay_bao_weixin(String token){
        return service.pay_bao_weixin(token);
    }

    public Observable<WeixinBean> pay_weixin(String token,int order_id){
        return service.pay_weixin(token,order_id);
    }
    /**
     * 保证金 支付宝支付
     * @param token
     * @return
     */
    public Observable<AliPayBean> pay_order(String token,int order_id){
        return service.pay_order(token,order_id);
    }
    public Observable<NewsNumBean> news_num(String token){
        return service.news_num(token);
    }

    public Observable<Code> edit_face( String token, String name, String face){
        return service.edit_face( token,  name,  face);
    }

    public Observable<Base> cancel_order(String token, String order_id) {
        return service.cancel_order(token, order_id,"1");
    }
}
