package com.zhuye.huochebang.data.http;

import com.zhuye.huochebang.bean.BankListBean;
import com.zhuye.huochebang.bean.Base;
import com.zhuye.huochebang.bean.CarListBean;
import com.zhuye.huochebang.bean.CarModelBean;
import com.zhuye.huochebang.bean.CheChangBean;
import com.zhuye.huochebang.bean.Code;
import com.zhuye.huochebang.bean.DriverListBean;
import com.zhuye.huochebang.bean.HomeCarInfoBean;
import com.zhuye.huochebang.bean.MoneyLog;
import com.zhuye.huochebang.bean.MoneyManBean;
import com.zhuye.huochebang.bean.PhoneLoginCde;
import com.zhuye.huochebang.bean.RegeiserCode;
import com.zhuye.huochebang.bean.ShenHeBean;
import com.zhuye.huochebang.bean.TiXianBean;
import com.zhuye.huochebang.bean.UploadImgBean;
import com.zhuye.huochebang.bean.YaoDriverBean;

import java.io.File;

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

    public static CommonApi getInstance() {
        if (instance == null)
            instance = new CommonApi();
        return instance;
    }


    public Observable<Code> logout(String token){
        return service.logout(token);
    }


    /**
     * 获取验证码
     * @param mobile
     * @return
     */

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
        return service.getregister(mobile,password,code,3);
    }

    /**
     * 登录--账户密码
     * @param mobile
     * @param password
     * @return
     */

    public Observable<PhoneLoginCde> passLogin(String mobile ,String password){
        return service.passLogin(mobile,password,3);
    }

    /**
     * 短信登录
     * @param mobile
     * @param code
     * @return
     */
    public Observable<PhoneLoginCde> codelogin(String mobile ,String code){
        return service.codelogin(mobile,code,3);
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

    public Observable<Code> user_messsage(String name ,String card1,String card2,String license,String face,String token ){
        return service.user_messsage(name,card1,card2,license,"","","","",face,token);
    }


    public Observable<ShenHeBean> message(String token){
        return service.message(token);
    }

    public Observable<DriverListBean> driver(String token,int page){
        return service.driver(token,page);
    }

    public Observable<CarListBean> car_list(String token,int page){
        return service.car_list(token,page);
    }


    public Observable<DriverListBean> agree(String token,int driver_jionid,int driver_id,String... car_id){
        return service.agree(token,driver_jionid,driver_id,car_id);
    }


    public Observable<Code> refuse(String token,int driver_jionid,String reason){
        return service.refuse(token,driver_jionid,reason);
    }
    public Observable<HomeCarInfoBean> homecarinfo(String token, int page){
        return service.homecarinfo(token,page);
    }


    public Observable<DriverListBean> homecainvite_driverrinfo(String token, int car_id){
        return service.invite_driver(token,car_id);
    }


    public Observable<Base> add_driver(String token, int car_id,int driver_id){
        return service.add_driver(token,car_id,driver_id);
    }

    public Observable<Code> add_car(String token, String license,String models_id,String commander_id){
        return service.add_car(token,license,models_id,commander_id);
    }

    public Observable<CarModelBean> models(String token){
        return service.models(token);
    }

    public Observable<CheChangBean> chechang(String token){
        return service.chechang(token);
    }

    public Observable<MoneyManBean> moneymanage(String token){
        return service.moneymanage(token);
    }

    public Observable<Code> forget_code(String mobile,String code){
        return service.forget_code(mobile,code);
    }


    public Observable<Code> forget_ms(String mobile,String password){
        return service.forget_ms(mobile,password);
    }

    public Observable<YaoDriverBean> sel_driver(String mobile, String keyword){
        return service.sel_driver(mobile,keyword);
    }

    public Observable<HomeCarInfoBean> select_car(String token, String license){
        return service.select_car(token,license);
    }

    public Observable<TiXianBean> withdrawals(String token){
        return service.withdrawals(token);
    }

    public Observable<Base> tixian(String token,String cash_id,Float money){
        return service.tixian(token,cash_id,money);
    }

    public Observable<Code> add_cash(String token,String card,String card_name,String name){
        return service.add_cash(token,card,card_name,name);
    }

    public Observable<BankListBean> list_card(String token){
        return service.list_card(token);
    }

    public Observable<Code> del_cash(String token,String card_id){
        return service.del_cash(token,card_id);
    }

    public Observable<Code> default_card(String token,String card_id){
        return service.default_card(token,card_id);
    }


    public Observable<MoneyLog> income_log(String token, int page){
        return service.income_log(token,page);
    }


    public Observable<MoneyLog> cash_log(String token, int page){
        return service.cash_log(token,page);
    }

    public Observable<Code> del_driver(String token, int car_driverid){
        return service.del_driver(token,car_driverid);
    }
}
