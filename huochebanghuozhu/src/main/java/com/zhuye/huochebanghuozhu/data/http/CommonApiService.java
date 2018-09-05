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

import java.util.List;

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
    Observable<Base>  logout(@Field("token") String token);

    /**
     * 订单详情
     * @param token
     * @param order_id
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/ownerorder/order_detail")
    Observable<OrderDetailBean> order_detail(@Field("token") String token, @Field("order_id") int order_id);

    @FormUrlEncoded
    @POST("index.php/home/passport/code")
    Observable<Base> getCode(@Field("mobile") String mobile);

    @FormUrlEncoded
    @POST("index.php/home/passport/register")
    Observable<RegeiserCode>  getregister(@Field("mobile") String mobile
            , @Field("password") String password, @Field("code") String code, @Field("type") int type);


    /**
     * 消息查看
     * @param token
     * @param type 1表示系统消息 2表示个人消息
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/commonpart/view_news")
    Observable<Base>  view_news(@Field("token") String token, @Field("type") int type);

    @FormUrlEncoded
    @POST("index.php/home/passport/login")
    Observable<PhoneLoginCde>  passLogin(@Field("mobile") String mobile
            , @Field("password") String password,@Field("type") int type);


    @FormUrlEncoded
    @POST("index.php/home/passport/code_login")
    Observable<PhoneLoginCde>  codelogin(@Field("mobile") String mobile
            , @Field("code") String code, @Field("type") int type);

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
            , @Field("card1") String card1, @Field("card2") String card2, @Field("license") String license, @Field("driving_book") String driving_book
            , @Field("cord") String cord
            , @Field("encode") String encode, @Field("type") String type
            , @Field("face") String face
            , @Field("token") String token);


    /**
     * 用户审核信息
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/passport/message")
    Observable<ShenHeBean>  message(@Field("token") String token);


    /**
     * 个人信息
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/commonpart/user_info")
    Observable<UserInfoBean>  user_info(@Field("token") String token);


    /**
     * 个人信息编辑
     * @param token
     * @param province_id
     * @param city_id
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/commonpart/edit_info")
    Observable<Code>  edit_info(@Field("token") String token,@Field("province_id") int province_id,
                                @Field("city_id") int city_id,
                                @Field("start_city") int start_city,
                                @Field("end_city") int end_city);


    /**
     * 更换手机号--原手机号获取
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/commonpart/mobile")
    Observable<MobileBean>  getmobile(@Field("token") String token);


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
     * 忘记密码-短信验证
     * @param mobile
     * @param code 验证码
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/passport/forget_code")
    Observable<Code>  forget_code(@Field("mobile") String mobile,@Field("code") String code);

    /**
     * 更换手机号--填写新手机号
     * @param token
     * @param mobile
     * @param code
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/commonpart/new_mobile")
    Observable<Code>  new_mobile(@Field("token") String token,@Field("mobile") String mobile,@Field("code") String code);


    /**
     * 城市-三级
     * @param
     * @return
     */
   //
    @FormUrlEncoded
    @POST("index.php/home/commonpart/city")
    Observable<CitysBean>  city(@Field("type")Integer type);

    /**
     * 个人中心---客服
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/ownercenter/server")
    Observable<MobileBean>  server(@Field("token") String token);

    /**
     *  邀请好友
     * @param token
     * @param type  1表示安卓 2表示苹果
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/owner/invate")
    Observable<InvateBean>  invate(@Field("token") String token, @Field("type") int type);


    /**
     * 发布-判断是否已交保证金
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/owner/bond")
    Observable<JiaoFouBean>  jiaofou(@Field("token") String token);

    /**
     * 发布--配送说明
     * @return
     */
    @POST("index.php/home/owner/hade_model")
    Observable<PeiSongBean>  hade_model();


    /**
     * 发布--货物类型
     * @return
     */
    @POST("index.php/home/owner/goodtype")
    Observable<PeiSongBean>  goodtype();


    /**
     * 发布--车辆要求
     * @return
     */
    @POST("index.php/home/owner/commander_models")
    Observable<PeiSongBean>  commander_models();


    /**
     *   发布
     * @param token
     * @param type_id 货物类型
     * @param weight 货物重量
     * @param car_require  车辆要求
     * @param car_info  车辆细节描述
     * @param price  运费
     * @param invoice  1表示发票有 0没有
     * @param mobile 手机号
     * @param hand_mode  配送说明----装卸方式id
     * @param time  	装车时间
     * @param start_provinceid 始发地省id
     * @param start_cityid  始发地市id
     * @param start_areaid  始发地区域id
     * @param end_provinceid 目的地省id
     * @param end_cityid 目的地市id
     * @param end_areaid 目的地区域id
     * @param remark 配送说明---备注
     * @param typeid_bz 货物类型备注
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/owner/fabu")
    Observable<Base>  fabu(@Field("token") String token
    ,@Field("type_id[]") List<String> type_id
            ,@Field("weight") Float weight
            ,@Field("car_require[]") List<String> car_require
            ,@Field("car_info") String car_info
            ,@Field("price") Float price
            ,@Field("invoice") int invoice
            ,@Field("mobile") String mobile
            ,@Field("hand_mode[]") List<String> hand_mode
            ,@Field("time") String time
            ,@Field("start_provinceid") int start_provinceid
            ,@Field("start_cityid") int start_cityid
            ,@Field("start_areaid") int start_areaid
            ,@Field("end_provinceid") int end_provinceid
            ,@Field("end_cityid") int end_cityid
            ,@Field("end_areaid") int end_areaid
            ,@Field("remark") String remark
            ,@Field("typeid_bz") String typeid_bz
            ,@Field("start_addr") String start_addr
            ,@Field("end_addr") String end_addr
    );


    /**
     * 钱包--消费记录
     * @param token
     * @param page
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/ownercenter/consumption")
    Observable<XiaoFeiBean>  consumption(@Field("token") String token, @Field("page") int page);


    /**
     * 钱包--线下充值
     * @param token
     * @param page
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/ownercenter/recharge")
    Observable<RechargeBean>  recharge(@Field("token") String token, @Field("page") int page);


    /**
     * 个人中心--消息--我的消息
     * @param token
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/ownercenter/news")
    Observable<LiuYanBean>  liuyanmessage(@Field("token") String token,@Field("page") int page);


    /**
     * 个人中心--消息--系统消息
     * @param page
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/ownercenter/system_news")
    Observable<SystemMessage> system_news(@Field("page") int page);


    /**
     * 司机大厅
     * @param page
     * @param start_city
     * @param end_city
     * @param array 定制搜索数组
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/joincenter/driver")
    Observable<DaTingBean>  driver(@Field("page") int page, @Field("start_city") Integer start_city, @Field("end_city") Integer end_city, @Field("array[]") List<Integer> array);


    /**
     * 司机大厅--定制搜索
     * @return
     */
    @POST("index.php/home/joincenter/select")
    Observable<PeiSongBean>  select();


    /**
     * 司机大厅--详情
     * @param token
     * @param uid  司机id
     * @param car_id  车辆id
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/joincenter/driver_detail")
    Observable<DriverDetailBean>  driver_detail(@Field("token") String token, @Field("uid") int uid, @Field("car_id") int car_id);


    /**
     * 个人中心--货物信息
     * @param page
     * @param type  0未接单 1已接单
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/ownercenter/goods")
    Observable<GoodsDeailBean>  goods(@Field("page") int page, @Field("type") int type, @Field("token") String token);


    /**
     * 个人中心--货物详情
     * @param good_id 货物信息id
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/ownercenter/good_detail")
    Observable<GoodsBean>  good_detail(@Field("good_id") int good_id, @Field("token") String token);


    /**
     * 货主端--订单列表
     * @param page
     * @param token
     * @param state 	1表示全部 2表示待付款 3表示进行中 4表示待确认 5表示历史订单
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/ownerorder/order")
    Observable<OrderBean>  order(@Field("page") int page, @Field("token") String token, @Field("state") int state);


    /**
     * 货主--查看过磅单
     * @param order_id 订单id
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/ownerorder/view_weight")
    Observable<BangDanBean> view_weight(@Field("order_id") int order_id, @Field("token") String token, @Field("type")int type);

    /**
     * 货主--查看过磅单
     * @param order_id 订单id
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/ownerorder/finish")
    Observable<Code> order_finish(@Field("order_id") int order_id, @Field("token") String token);

    /**
     * 订单进程
     * @param order_id
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/ownerorder/order_process")
    Observable<XingChengBean>  order_process(@Field("order_id") int order_id, @Field("token") String token);


    /**
     * 货主--修改订单价格
     * @param order_id
     * @param token
     * @param price
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/ownerorder/modify_price")
    Observable<Code>  modify_price(@Field("order_id") int order_id, @Field("token") String token
    ,@Field("price") float price);


    /**
     * 取消订单
     * @param token
     * @param order_id
     * @param type 1表示司机端取消订单 2表示货主端取消订单
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/ownerorder/cancel_order")
    Observable<Code>  cancel_order(@Field("token") String token, @Field("order_id") int order_id,@Field("type") int type);

    /**
     *保證金 支付寶支付
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/alipay/bond")
    Observable<AliPayBean> pay_aliay(@Field("token") String token);

    /**
     /*保證金 微信支付
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/wxxpay/bond")
    Observable<WeixinBean> pay_bao_weixin(@Field("token") String token);

    /**
     * 订单支付 微信
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/wxxpay/order")
    Observable<WeixinBean> pay_weixin(@Field("token") String token,@Field("order_id") int order_id);

    /**
     *订单支付 支付宝支付
     * @param token
     * @param order_id
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/Alipay/order")
    Observable<AliPayBean> pay_order(@Field("token") String token,@Field("order_id") int order_id);
    /**
     * 消息条数
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/commonpart/news_num")
    Observable<NewsNumBean>  news_num(@Field("token") String token);

    @FormUrlEncoded
    @POST("index.php/home/commonpart/edit_face")
    Observable<Code>  edit_face(@Field("token") String token,@Field("name") String name,@Field("face") String face);

    @FormUrlEncoded
    @POST("index.php/home/ownerorder/order_del")
    Observable<Base> cancel_order(@Field("token") String token
            , @Field("order_id") String order_id
            , @Field("type") String type
    );
}
