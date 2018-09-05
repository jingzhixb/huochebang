package com.zhuye.huochebangsiji.data.http;


import com.zhuye.huochebangsiji.bean.AliPayBean;
import com.zhuye.huochebangsiji.bean.BangDanBean;
import com.zhuye.huochebangsiji.bean.BankListBean;
import com.zhuye.huochebangsiji.bean.Base;
import com.zhuye.huochebangsiji.bean.ChaKanBean;
import com.zhuye.huochebangsiji.bean.CitysBean;
import com.zhuye.huochebangsiji.bean.Code;
import com.zhuye.huochebangsiji.bean.GoodDetailBean;
import com.zhuye.huochebangsiji.bean.HuoYuanBean;
import com.zhuye.huochebangsiji.bean.InvateBean;
import com.zhuye.huochebangsiji.bean.JiaMengBean;
import com.zhuye.huochebangsiji.bean.JiaMengSinBean;
import com.zhuye.huochebangsiji.bean.JinChengBean;
import com.zhuye.huochebangsiji.bean.LiuYanBean;
import com.zhuye.huochebangsiji.bean.MobileBean;
import com.zhuye.huochebangsiji.bean.MoneyManBean;
import com.zhuye.huochebangsiji.bean.NewsNumBean;
import com.zhuye.huochebangsiji.bean.OilCardListBean;
import com.zhuye.huochebangsiji.bean.OilChargeListBean;
import com.zhuye.huochebangsiji.bean.OrderDetailBean;
import com.zhuye.huochebangsiji.bean.OrdersBean;
import com.zhuye.huochebangsiji.bean.PeiSongBean;
import com.zhuye.huochebangsiji.bean.PhoneLoginCde;
import com.zhuye.huochebangsiji.bean.RechargeBean;
import com.zhuye.huochebangsiji.bean.RegeiserCode;
import com.zhuye.huochebangsiji.bean.ShenHeBean;
import com.zhuye.huochebangsiji.bean.SystemMessage;
import com.zhuye.huochebangsiji.bean.TiXianBean;
import com.zhuye.huochebangsiji.bean.UploadImgBean;
import com.zhuye.huochebangsiji.bean.UserInfoBean;
import com.zhuye.huochebangsiji.bean.VipListBean;
import com.zhuye.huochebangsiji.bean.WeixinBean;
import com.zhuye.huochebangsiji.bean.XiaoFeiBean;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


/**
 * Created by Administrator on 2018/1/3 0003.
 */

public class CommonApi {

    public static CommonApi instance;

    private CommonApiService service;

    public CommonApi() {
        service = HttpUtils.getRetrofit(HttpUtils.getOkhttp()).create(CommonApiService.class);
    }

    public Observable<Code> del_cash(String token, String card_id) {
        return service.del_cash(token, card_id);
    }

    public Observable<Code> add_cash(String token, String card, String card_name, String name) {
        return service.add_cash(token, card, card_name, name);
    }

    public Observable<Code> default_card(String token, String card_id) {
        return service.default_card(token, card_id);
    }

    public Observable<BankListBean> list_card(String token) {
        return service.list_card(token);
    }
    public Observable<Code> edit_face( String token, String name, String face){
        return service.edit_face( token,  name,  face);
    }
    public static CommonApi getInstance() {
        if (instance == null)
            instance = new CommonApi();
        return instance;
    }

    public Observable<ShenHeBean> message(String token) {
        return service.message(token);
    }

    public Observable<Code> forget_ms(String mobile, String password) {
        return service.forget_ms(mobile, password);
    }


    public Observable<Code> tixian(String token, String cash_id, Float money) {
        return service.tixian(token, cash_id, money);
    }


    public Observable<TiXianBean> withdrawals(String token) {
        return service.withdrawals(token);
    }

    /**
     * 获取验证码
     *
     * @param mobile
     * @return
     */

    public Observable<Base> getCode(String mobile) {
        return service.getCode(mobile);
    }

    /**
     * 手机号注册
     *
     * @param mobile
     * @param password
     * @param code
     * @return
     */
    public Observable<RegeiserCode> getRegeister(String mobile, String password, String code) {
        return service.getregister(mobile, password, code, 1);
    }

    /**
     * 登录--账户密码
     *
     * @param mobile
     * @param password
     * @return
     */

    public Observable<PhoneLoginCde> passLogin(String mobile, String password) {
        return service.passLogin(mobile, password, 1);
    }

    /**
     * 短信登录
     *
     * @param mobile
     * @param code
     * @return
     */
    public Observable<PhoneLoginCde> codelogin(String mobile, String code) {
        return service.codelogin(mobile, code, 1);
    }


    public Observable<MoneyManBean> moneymanage(String token) {
        return service.moneymanage(token);
    }

    /**
     * 图片上传
     *
     * @param token
     * @param filepath 图片路径
     * @return
     */
    public Observable<UploadImgBean> upimg(String token, File filepath) {
        File file = filepath;
        RequestBody bod = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("file", file.getName(), requestFile);
        return service.upimg(bod, body);
    }

    public Observable<UserInfoBean> user_info(String token) {
        return service.user_info(token);
    }

    public Observable<Code> forget_code(String mobile, String code) {
        return service.forget_code(mobile, code);
    }

    public Observable<Code> edit_info(String token, Integer province_id, Integer city_id, Integer start_city, Integer endcity_id) {
        return service.edit_info(token, province_id, city_id, start_city, endcity_id);
    }

    public Observable<MobileBean> getmobile(String token) {
        return service.getmobile(token);
    }

    public Observable<Code> new_mobile(String token, String mobile, String code) {
        return service.new_mobile(token, mobile, code);
    }

    public Observable<CitysBean> city() {
        return service.city();
    }

    public Observable<MobileBean> server(String token) {
        return service.server(token);
    }

    public Observable<InvateBean> invate(String token) {
        return service.invate(token, 1);
    }

    public Observable<XiaoFeiBean> consumption(String token, int page) {
        return service.consumption(token, page);
    }

    public Observable<RechargeBean> recharge(String token, int page) {
        return service.recharge(token, page);
    }

    public Observable<LiuYanBean> liuyanmessage(String token, int page) {
        return service.liuyanmessage(token, page);
    }

    public Observable<SystemMessage> system_news(int page) {
        return service.system_news(page);
    }

    public Observable<PeiSongBean> goodtype() {
        return service.goodtype();
    }

    public Observable<Base> fabu(String token,
                                 List<String> type_id, Float weight,
                                 List<String> car_require, String car_info,
                                 Float price, int invoice, String mobile, List<String> hand_mode, String time,
                                 int start_provinceid, int start_cityid,
                                 int start_areaid, int end_provinceid,
                                 int end_cityid, int end_areaid,
                                 String remark, String typeid_bz) {
        return service.fabu(token, type_id, weight, car_require, car_info, price, invoice, mobile, hand_mode, time, start_provinceid, start_cityid, start_areaid, end_provinceid, end_cityid, end_areaid, remark, typeid_bz);
    }


    public Observable<PeiSongBean> commander_models() {
        return service.commander_models();
    }


    public Observable<PeiSongBean> hade_model() {
        return service.hade_model();
    }

    public Observable<HuoYuanBean> good(String token, String city, int page, String leng_id, String model_id, String type_id) {
        return service.good(token, city, page, leng_id, model_id, type_id);
    }

    public Observable<GoodDetailBean> good_detail(String token, int good_id) {
        return service.good_detail(token, good_id);
    }

    public Observable<JiaMengBean> jion_list(String token, int page) {
        return service.jion_list(token, page);
    }


    public Observable<JiaMengSinBean> jion_select(String encode) {
        return service.jion_select(encode);
    }

    public Observable<Code> apply_jion(String token, int jion_id) {
        return service.apply_jion(token, jion_id);
    }

    public Observable<OilCardListBean> oilcard(String token) {
        return service.oilcard(token);
    }

    public Observable<OilChargeListBean> oil_recharge(String token, int page, int type) {
        return service.oil_recharge(token, page, type);
    }


    public Observable<OilChargeListBean> oil_recharge(String token, int page) {
        return service.oil_recharge(token, page);
    }

    public Observable<PeiSongBean> commander() {
        return service.commander();
    }

    public Observable<PeiSongBean> goodsgoodtype() {
        return service.goodsgoodtype();
    }

    public Observable<PeiSongBean> models() {
        return service.models();
    }

    public Observable<Code> logout(String token) {
        return service.logout(token);
    }

    public Observable<PeiSongBean> shaixuan() {
        return service.shaixuan();
    }


    public Observable<PeiSongBean> acommander_models() {
        return service.acommander_models();
    }

    public Observable<VipListBean> vip() {
        return service.vip();
    }


    public Observable<AliPayBean> maivip(String token, int vip_id) {
        return service.maivip(token, vip_id);
    }

    public Observable<WeixinBean> maivipWeixin(String token, int vip_id) {
        return service.maivipWeixin(token, vip_id);
    }

    public Observable<PeiSongBean> select() {
        return service.select();
    }

    public Observable<String> index() {
        return service.index();
    }


    public Observable<ChaKanBean> good_view(String token, int good_id) {
        return service.good_view(token, good_id);
    }

    public Observable<Code> good_cost(String token, int good_id) {
        return service.good_cost(token, good_id);
    }

    public Observable<Code> order(String token, int good_id) {
        return service.order(token, good_id);
    }

    public Observable<OrdersBean> order_list(String token, int page, int state) {
        return service.order_list(page, token, state);
    }

    public Observable<Code> confirm_order(String token, int order_id) {
        return service.confirm_order(token, order_id);
    }


    public Observable<Code> cancel_order(String token, int order_id) {
        return service.cancel_order(token, order_id, 1);
    }


    public Observable<Code> weight_img(String token, File filepath, String order_id, String type
            , String f_unit, String s_unit, String good_name, String car_card, String m_weight
            , String p_weight, String weight, String driver_name) {
        File file = filepath;


        Map<String, RequestBody> map = new HashMap<>();
        RequestBody tokenbo = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        RequestBody order_idbo = RequestBody.create(MediaType.parse("multipart/form-data"), order_id);
        RequestBody typebo = RequestBody.create(MediaType.parse("multipart/form-data"), type);
        RequestBody f_unitbo = RequestBody.create(MediaType.parse("multipart/form-data"), f_unit);
        RequestBody s_unitbo = RequestBody.create(MediaType.parse("multipart/form-data"), s_unit);
        RequestBody good_namebo = RequestBody.create(MediaType.parse("multipart/form-data"), good_name);
        RequestBody car_cardbo = RequestBody.create(MediaType.parse("multipart/form-data"), car_card);
        RequestBody m_weightbo = RequestBody.create(MediaType.parse("multipart/form-data"), m_weight);
        RequestBody p_weightbo = RequestBody.create(MediaType.parse("multipart/form-data"), p_weight);
        RequestBody weightbo = RequestBody.create(MediaType.parse("multipart/form-data"), weight);
        RequestBody driver_namebo = RequestBody.create(MediaType.parse("multipart/form-data"), driver_name);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        map.put("token", tokenbo);
        map.put("order_id", order_idbo);
        map.put("type", typebo);
        map.put("f_unit", f_unitbo);
        map.put("s_unit", s_unitbo);
        map.put("good_name", good_namebo);
        map.put("car_card", car_cardbo);
        map.put("m_weight", m_weightbo);
        map.put("p_weight", p_weightbo);
        map.put("weight", weightbo);
        map.put("driver_name", driver_namebo);
        return service.weight_img(map, body);
    }


    public Observable<Code> service(String token, int order_id) {
        return service.service(token, order_id);
    }

    /**
     * 榜单查看
     *
     * @param token
     * @param order_id
     * @return
     */
    public Observable<BangDanBean> view_weight(String token, int order_id, int type) {
        return service.view_weight(token, order_id, type);
    }

    public Observable<OrderDetailBean> order_detail(String token, int order_id) {
        return service.order_detail(token, order_id);
    }

    public Observable<JinChengBean> order_process(String token, int order_id) {
        return service.order_process(token, order_id);
    }

    public Observable<NewsNumBean> news_num(String token) {
        return service.news_num(token);
    }

    public Observable<Base> view_news(String token, int type) {
        return service.view_news(token, type);
    }

    public Observable<Base> pay_balance(String token, int good_id) {
        return service.pay_balance(token, good_id);
    }

    public Observable<Base> balance_scode(String token, int good_id) {
        return service.balance_scode(token, good_id);
    }

    /**
     * 订单支付 支付宝
     *
     * @param token
     * @return
     */
    public Observable<AliPayBean> pay_aliay(String token, int good_id) {
        return service.pay_aliay(token, good_id);
    }

    public Observable<WeixinBean> pay_weixin(String token, int good_id) {
        return service.pay_weixin(token, good_id);
    }

    public Observable<Base> balance_oil(String token, float money) {
        return service.balance_oil(token, money);
    }


    public Observable<Code> alipay_oil(String token, float money) {
        return service.alipay_oil(token, money);
    }


    public Observable<WeixinBean> wxpay_oil(String token, float money) {
        return service.wxpay_oil(token, money);
    }

    public Observable<Base> cancel_order(String token, String order_id) {
        return service.cancel_order(token, order_id,"1");
    }
}
