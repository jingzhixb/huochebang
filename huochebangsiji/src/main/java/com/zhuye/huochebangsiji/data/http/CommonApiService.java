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
import com.zhuye.huochebangsiji.bean.JiaoFouBean;
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

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;


/**
 * Created by Administrator on 2018/1/3 0003.
 */

public interface CommonApiService {
    /**
     * 银行卡-卡列表
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/commonpart/list_card")
    Observable<BankListBean>  list_card(@Field("token") String token);
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
     * 提现
     * @param token
     * @param cash_id 银行卡id
     * @param money 提现金额
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/jion/tixian")
    Observable<Code>  tixian(@Field("token") String token,@Field("cash_id") String cash_id,@Field("money") Float money);

    /**
     * 提现--页面展示
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/jion/withdrawals")
    Observable<TiXianBean>  withdrawals(@Field("token") String token);


    @FormUrlEncoded
    @POST("index.php/home/passport/code")
    Observable<Base> getCode(@Field("mobile") String mobile);

    @FormUrlEncoded
    @POST("index.php/home/passport/register")
    Observable<RegeiserCode>  getregister(@Field("mobile") String mobile
            , @Field("password") String password, @Field("code") String code, @Field("type") int type);



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
    Observable<Code>  edit_info(@Field("token") String token,@Field("province_id") Integer province_id,
                                @Field("city_id") Integer city_id,
                                @Field("start_city") Integer start_city,
                                @Field("end_city") Integer end_city);



    /**
     * 财务管理
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/jion/manage")
    Observable<MoneyManBean>  moneymanage(@Field("token") String token);



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
    Observable<Code>  forget_ms(@Field("mobile") String mobile, @Field("password") String password);

    /**
     * 忘记密码-短信验证
     * @param mobile
     * @param code 验证码
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/passport/forget_code")
    Observable<Code>  forget_code(@Field("mobile") String mobile, @Field("code") String code);

    /**
     * 更换手机号--填写新手机号
     * @param token
     * @param mobile
     * @param code
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/commonpart/new_mobile")
    Observable<Code>  new_mobile(@Field("token") String token, @Field("mobile") String mobile, @Field("code") String code);


    /**
     * 城市-三级
     * @param
     * @return
     */
   //
    @POST("index.php/home/commonpart/city")
    Observable<CitysBean>  city();

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
     * 审核资料--个人司机--车辆选择
     * @return
     */
    @POST("index.php/home/passport/commander_models")
    Observable<PeiSongBean>  acommander_models();


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
            , @Field("type_id[]") List<String> type_id
            , @Field("weight") Float weight
            , @Field("car_require[]") List<String> car_require
            , @Field("car_info") String car_info
            , @Field("price") Float price
            , @Field("invoice") int invoice
            , @Field("mobile") String mobile
            , @Field("hand_mode[]") List<String> hand_mode
            , @Field("time") String time
            , @Field("start_provinceid") int start_provinceid
            , @Field("start_cityid") int start_cityid
            , @Field("start_areaid") int start_areaid
            , @Field("end_provinceid") int end_provinceid
            , @Field("end_cityid") int end_cityid
            , @Field("end_areaid") int end_areaid
            , @Field("remark") String remark
            , @Field("typeid_bz") String typeid_bz
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
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/ownercenter/system_news")
    Observable<SystemMessage>  system_news(@Field("page") int page);


    /**
     *  货源大厅
     * @param token
     * @param city 定位城市名称
     * @param page
     * @param leng_id  车长id
     * @param
     * @param type_id  货物类型
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/goods/good")
    Observable<HuoYuanBean>  good(@Field("token") String token, @Field("city") String city,
                                  @Field("page") int page
    , @Field("leng_id") String leng_id, @Field("models_id") String model_id, @Field("good_typeid") String type_id);


    /**
     *  货源详情
     * @param token
     * @param good_id 	货源id
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/goods/good_detail")
    Observable<GoodDetailBean>  good_detail(@Field("token") String token, @Field("good_id") int good_id);


    /**
     * 个人中心--加盟商列表
     * @param token
     * @param page
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/drivercenter/jion_list")
    Observable<JiaMengBean>  jion_list(@Field("token") String token, @Field("page") int page);


    /**
     * 个人中心--加盟商--查询
     * @param encode  	加盟商编码
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/drivercenter/jion_select")
    Observable<JiaMengSinBean>  jion_select(@Field("encode") String encode);


    /**
     *  个人中心--加盟商--申请加入加盟商
     * @param token
     * @param jion_id 加盟商id
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/drivercenter/apply_jion")
    Observable<Code>  apply_jion(@Field("token") String token, @Field("jion_id") int jion_id);


    /**
     * 个人中心--油卡服务
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/drivercenter/oilcard")
    Observable<OilCardListBean>  oilcard(@Field("token") String token);


    /**
     * 个人中心--油卡充值记录
     * @param token
     * @param page
     * @param type  1表示充值 2表示消费
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/drivercenter/oil_recharge")
    Observable<OilChargeListBean>  oil_recharge(@Field("token") String token, @Field("page") int page
            , @Field("type") int type);



    @FormUrlEncoded
    @POST("index.php/home/drivercenter/oil_recharge")
    Observable<OilChargeListBean>  oil_recharge(@Field("token") String token, @Field("page") int page);

    /**
     * 货源--车长
     * @return
     */
    @POST("index.php/home/goods/commander")
    Observable<PeiSongBean>  commander();

    /**
     * 货源--货物类型
     * @return
     */
    @POST("index.php/home/goods/goodtype")
    Observable<PeiSongBean>  goodsgoodtype();


    /**
     * 货源--车型
     * @return
     */
    @POST("index.php/home/goods/models")
    Observable<PeiSongBean>  models();


    /**
     * 货源大厅--筛选
     * @return
     */
    @POST("index.php/home/goods/shaixuan")
    Observable<PeiSongBean>  shaixuan();




    /**
     * 退登
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/passport/logout")
    Observable<Code>  logout(@Field("token") String token);


    /**
     * vip购买
     * @return
     */
    @POST("index.php/home/Drivercenter/vip")
    Observable<VipListBean>  vip();


    /**
     * vip购买---支付宝
     * @param token
     * @param vip_id
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/Alipay/vip")
    Observable<AliPayBean>  maivip(@Field("token") String token,@Field("vip_id") int vip_id);
 /**
     * vip购买---微信
     * @param token
     * @param vip_id
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/wxpay/vip")
    Observable<WeixinBean> maivipWeixin(@Field("token") String token,@Field("vip_id") int vip_id);


    /**
     * 司机大厅--定制搜索
     * @return
     */
    @POST("index.php/home/joincenter/select")
    Observable<PeiSongBean>  select();


    /**
     * vip--特权说明
     * @return
     */
    @POST("index.php/home/article/index")
    Observable<String> index();



    /**
     *  货源大厅--判断查看是否付费
     * @param token
     * @param good_id
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/goods/good_view")
    Observable<ChaKanBean> good_view(@Field("token") String token, @Field("good_id") int good_id);


    /**
     *  货源大厅--货源查看支付--支付宝
     * @param token
     * @param good_id  货源id
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/alipay/good_cost")
    Observable<Code>  good_cost(@Field("token") String token, @Field("good_id") int good_id);


    /**
     *  货源详情--我要接单
     * @param token
     * @param good_id  货源id
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/driver/order")
    Observable<Code>  order(@Field("token") String token, @Field("good_id") int good_id);


    /**
     *  司机端--订单列表
     * @param page
     * @param token 1表示全部 2表示待成交 3表示进行中 4表示已完成 5表示历史订单
     * @param state
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/driver/order_list")
    Observable<OrdersBean>  order_list(@Field("page") int page, @Field("token") String token, @Field("state") int state);


    /**
     * 司机---确认订单价格
     * @param token
     * @param order_id 订单id
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/driver/confirm_order")
    Observable<Code>  confirm_order(@Field("token") String token, @Field("order_id") int order_id);


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
     * 司机---上传货物单|卸货单

     * @return
     */
    @Multipart
    @POST("index.php/home/driver/weight_img")
    Observable<Code>  weight_img(@PartMap Map<String, RequestBody> map,@Part MultipartBody.Part file);

    /**司机---查看已上传货物单|卸货单
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/ownerorder/view_weight")
    Observable<BangDanBean>  view_weight(@Field("token") String token, @Field("order_id") int order_id,@Field("type")int type);


    /**
     *  司机--确认货物送达
     * @param token
     * @param order_id
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/driver/service")
    Observable<Code>  service(@Field("token") String token, @Field("order_id") int order_id);


    /**
     * 订单详情
     * @param token
     * @param order_id
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/ownerorder/order_detail")
    Observable<OrderDetailBean> order_detail(@Field("token") String token, @Field("order_id") int order_id);


    /**
     * 订单进程
     * @param token
     * @param order_id
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/ownerorder/order_process")
    Observable<JinChengBean>  order_process(@Field("token") String token, @Field("order_id") int order_id);


    /**
     * 消息条数
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/commonpart/news_num")
    Observable<NewsNumBean>  news_num(@Field("token") String token);

    /**
     * 消息查看
     * @param token
     * @param type 1表示系统消息 2表示个人消息
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/commonpart/view_news")
    Observable<Base>  view_news(@Field("token") String token, @Field("type") int type);

    /**
     * 查看货源信息--余额支付
     * @param token
     * @param good_id
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/driver/pay_balance")
    Observable<Base>  pay_balance(@Field("token") String token, @Field("good_id") int good_id);

    /**
     * 查看货源信息--积分支付
     * @param token
     * @param good_id
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/driver/balance_scode")
    Observable<Base>  balance_scode(@Field("token") String token, @Field("good_id") int good_id);
    /**
     * 查看货源信息--支付宝支付
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/alipay/good_cost")
    Observable<AliPayBean> pay_aliay(@Field("token") String token,@Field("good_id")int good_id);

    /**
     * 查看货源信息--微信支付
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/wxpay/good_cost")
    Observable<WeixinBean> pay_weixin(@Field("token") String token, @Field("good_id")int good_id);


    /**
     * 油卡充值--余额支付
     * @param token
     * @param money
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/home/drivercenter/balance_oil")
    Observable<Base> balance_oil(@Field("token") String token, @Field("money") float money);

    @FormUrlEncoded
    @POST("index.php/home/commonpart/edit_face")
    Observable<Code>  edit_face(@Field("token") String token,@Field("name") String name,@Field("face") String face);

    @FormUrlEncoded
    @POST("index.php/home/alipay/oil")
    Observable<Code> alipay_oil(@Field("token") String token, @Field("money") float money);




    @FormUrlEncoded
    @POST("index.php/home/wxpay/oil")
    Observable<WeixinBean> wxpay_oil(@Field("token") String token, @Field("money") float money);


    @FormUrlEncoded
    @POST("index.php/home/ownerorder/order_del")
    Observable<Base> cancel_order(@Field("token") String token
            , @Field("order_id") String order_id
            , @Field("type") String type
    );
}
