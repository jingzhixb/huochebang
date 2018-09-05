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

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


/**
 * Created by Administrator on 2018/1/3 0003.
 */

public interface CommonApiService {

    /**
     * 退登
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/passport/logout")
    Observable<Code>  logout(@Field("token") String token);


    @FormUrlEncoded
    @POST("index.php/home/passport/code")
    Observable<Base> getCode(@Field("mobile") String mobile);

    @FormUrlEncoded
    @POST("index.php/home/passport/register")
    Observable<RegeiserCode>  getregister(@Field("mobile") String mobile
                          , @Field("password") String password, @Field("code") String code , @Field("type") int type);



    @FormUrlEncoded
    @POST("index.php/home/passport/login")
    Observable<PhoneLoginCde>  passLogin(@Field("mobile") String mobile
            , @Field("password") String password, @Field("type") int type);


    @FormUrlEncoded
    @POST("index.php/home/passport/code_login")
    Observable<PhoneLoginCde>  codelogin(@Field("mobile") String mobile
            , @Field("code") String code ,@Field("type") int type);

    @Multipart
    @POST("index.php/home/passport/upimg")
    Observable<UploadImgBean>  upimg(@Part("token") RequestBody description
            , @Part MultipartBody.Part file);


    /**
     *
     * @param name
     * @param card1
     * @param card2
     * @param license
     * @param driving_book
     * @param cord  油卡
     * @param encode 无车司机-加盟商代码
     * @param type   0无车司机|货主-个人 1有车司机|货主-公司288
     * @param face
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/passport/user_messsage")
    Observable<Code>  user_messsage(@Field("name") String name
            , @Field("card1") String card1 ,@Field("card2") String card2,@Field("license") String license,@Field("driving_book") String driving_book
            ,@Field("cord") String cord
            ,@Field("encode") String encode,@Field("type") String type
            ,@Field("face") String face
            ,@Field("token") String token);


    /**
     * 用户审核信息
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/passport/message")
    Observable<ShenHeBean>  message(@Field("token") String token);


    /**
     * 申请司机--列表
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/jion/driver/token")
    Observable<DriverListBean>  driver(@Field("token") String token,@Field("page") int page);


    /**
     *  申请司机--车辆列表
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/car/car_list")
    Observable<CarListBean>  car_list(@Field("token") String token,@Field("page") int page);



//    @FormUrlEncoded
//    @POST("index.php/home/car/select_car")
//    Observable<CarListBean>  select_car(@Field("token") String token,@Field("license") String license);


    /**
     * 申请司机--同意
     * @param token
     * @param driver_jionid 司机申请主键id
     * @param driver_id  司机id
     * @param car_id  车辆id 数组
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/jion/agree")
    Observable<DriverListBean>  agree(@Field("token") String token
    ,@Field("driver_jionid") int driver_jionid,@Field("driver_id") int driver_id,@Field("car_id") String... car_id);


    /**
     *  申请司机--拒绝
     * @param token
     * @param driver_jionid 申请列表主键
     * @param reason  拒绝原因
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/jion/refuse")
    Observable<Code>  refuse(@Field("token") String token
            ,@Field("driver_jionid") int driver_jionid,@Field("reason") String reason);


    /**
     * 车辆管理||车辆详情
     * @param token
     * @param page
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/car/car/token")
    Observable<HomeCarInfoBean>  homecarinfo(@Field("token") String token
            , @Field("page") int page);


    /**
     *  车辆--邀请司机列表
     * @param token
     * @param car_id  车辆id
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/car/invite_driver")
    Observable<DriverListBean>  invite_driver(@Field("token") String token
            , @Field("car_id") int car_id);


    /**
     * 车辆--邀请司机
     * @param token
     * @param car_id
     * @param driver_id
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/car/add_driver")
    Observable<Base>  add_driver(@Field("token") String token
            , @Field("car_id") int car_id, @Field("driver_id") int driver_id);


    @FormUrlEncoded
    @POST("index.php/home/car/del_driver")
    Observable<Code>  del_driver(@Field("token") String token
            , @Field("car_driverid") int car_driverid);

    /**
     *  添加车辆
     * @param token
     * @param license  车牌号
     * @param models_id 车型models_id
     * @param commander_id  车长commander_id
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/car/add_car")
    Observable<Code>  add_car(@Field("token") String token
            , @Field("license") String license , @Field("models_id") String models_id , @Field("commander_id") String commander_id);




    /**
     *  车辆类型
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/car/models")
    Observable<CarModelBean>  models(@Field("token") String token);


    /**
     * 车长
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/car/commander")
    Observable<CheChangBean>  chechang(@Field("token") String token);


    /**
     * 财务管理
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/jion/manage")
    Observable<MoneyManBean>  moneymanage(@Field("token") String token);


    /**
     * 忘记密码-短信验证
     * @param mobile
     * @param code 验证码
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/passport/forget_code")
    Observable<Code>  forget_code(@Field("mobile") String mobile,@Field("code") String code);

    /**
     * 忘记密码
     * @param mobile   手机号
     * @param password  新密码
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/passport/forget_ms")
    Observable<Code>  forget_ms(@Field("mobile") String mobile,@Field("password") String password);


    /**
     *  车辆--邀请司机查询
     * @param mobile
     * @param keyword 关键词
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/car/sel_driver")
    Observable<YaoDriverBean>  sel_driver(@Field("mobile") String mobile, @Field("keyword") String keyword);

    /**
     *
     * @param token
     * @param license
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/car/select_car")
    Observable<HomeCarInfoBean>  select_car(@Field("token") String token, @Field("license") String license);


    /**
     * 提现--页面展示
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/jion/withdrawals")
    Observable<TiXianBean>  withdrawals(@Field("token") String token);

    /**
     * 提现
     * @param token
     * @param cash_id 银行卡id
     * @param money 提现金额
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/jion/tixian")
    Observable<Base>  tixian(@Field("token") String token,@Field("cash_id") String cash_id,@Field("money") Float money);


    /**
     * 银行卡--添加
     * @param token
     * @param card 银行卡号
     * @param card_name  卡类型
     * @param name
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/commonpart/add_cash")
    Observable<Code>  add_cash(@Field("token") String token,@Field("card") String card,@Field("card_name") String card_name
    ,@Field("name") String name);


    /**
     * 银行卡-卡列表
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/commonpart/list_card")
    Observable<BankListBean>  list_card(@Field("token") String token);


    /**
     * 银行卡--删除
     * @param token
     * @param card_id 银行卡id
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/commonpart/del_cash")
    Observable<Code>  del_cash(@Field("token") String token,@Field("card_id") String card_id);


    /**
     * 银行卡--设置默认
     * @param token
     * @param card_id 银行卡id
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/commonpart/default_card")
    Observable<Code>  default_card(@Field("token") String token,@Field("card_id") String card_id);

    /**
     * 财务管理--收入明细
     * @param token
     * @param page
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/jion/income_log")
    Observable<MoneyLog>  income_log(@Field("token") String token, @Field("page") int page);


    /**
     * 财务管理--提现记录
     * @param token
     * @param page
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/jion/cash_log")
    Observable<MoneyLog>  cash_log(@Field("token") String token, @Field("page") int page);


}
