package com.zhuye.huochebangsiji.data;

import android.os.Handler;
import android.util.Log;

import com.zhuye.huochebangsiji.base.BaseView;
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
import com.zhuye.huochebangsiji.bean.NewsNumBean;
import com.zhuye.huochebangsiji.bean.OilCardListBean;
import com.zhuye.huochebangsiji.bean.OilChargeListBean;
import com.zhuye.huochebangsiji.bean.OrderDetailBean;
import com.zhuye.huochebangsiji.bean.OrdersBean;
import com.zhuye.huochebangsiji.bean.PeiSongBean;
import com.zhuye.huochebangsiji.bean.PhoneLoginCde;
import com.zhuye.huochebangsiji.bean.RechargeBean;
import com.zhuye.huochebangsiji.bean.RegeiserCode;
import com.zhuye.huochebangsiji.bean.SystemMessage;
import com.zhuye.huochebangsiji.bean.VipListBean;
import com.zhuye.huochebangsiji.bean.WeixinBean;
import com.zhuye.huochebangsiji.bean.XiaoFeiBean;
import com.zhuye.huochebangsiji.data.http.CommonApi;
import com.zhuye.huochebangsiji.data.http.MyObserver;
import com.zhuye.huochebangsiji.utils.SharedPreferencesUtil;

import java.io.File;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/1/11 0011.
 */

public class GetData {
    public static void edit_face(String token, String name, String face, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().edit_face(token, name,face).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<Base>(baseView) {
                    @Override
                    public void onNext(@NonNull Base liuYanBean) {
                        if (liuYanBean.getCode() == 200) {
                            baseView.success(requestcode, liuYanBean);
//                            if (liuYanBean.getData() == null) {
//                                baseView.empty();
//                            } else {
//
//                            }
                        } else {
                            baseView.empty();
                        }
                        baseView.finishLoding();
                    }
                });
    }
    public static Handler handler;
    public static int WX_FLAG = 0;// 1 货源支付； 2 VIP支付

    public static Handler getHandler() {
        return handler;
    }

    public static void bindHandler(Handler h) {
        handler = h;
    }

    public static void add_cash(String token, String card, String card_name, String name, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().add_cash(token, card, card_name, name).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<Base>(baseView) {
                    @Override
                    public void onNext(@NonNull Base liuYanBean) {
                        if (liuYanBean.getCode() == 200) {
                            //                            if(liuYanBean.getData()==null){
                            //                                baseView.empty();
                            //                            }else {
                            //                                baseView.success(requestcode,liuYanBean);
                            //                            }
                            baseView.success(requestcode, liuYanBean);
                        } else {
                            baseView.error(liuYanBean);
                        }
                        baseView.finishLoding();
                    }
                });
    }


    public static void list_card(String token, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().list_card(token).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<BankListBean>(baseView) {
                    @Override
                    public void onNext(@NonNull BankListBean liuYanBean) {
                        if (liuYanBean.getCode() == 200) {
                            if (liuYanBean.getData() == null || liuYanBean.getData().size() == 0) {
                                baseView.empty();
                            } else {
                                baseView.success(requestcode, liuYanBean);
                            }
                        } else {
                            baseView.empty();
                        }
                        baseView.finishLoding();
                    }
                });
    }

    public static void tixian(String token, String cash_id, Float money, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().tixian(token, cash_id, money).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<Base>(baseView) {
                    @Override
                    public void onNext(@NonNull Base liuYanBean) {
                        if (liuYanBean.getCode() == 200) {
                            baseView.success(requestcode, liuYanBean);
                        } else {
                            baseView.error(liuYanBean);
                        }
                        baseView.finishLoding();
                    }
                });
    }

    public static void withdrawals(String token, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().withdrawals(token).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<Base>(baseView) {
                    @Override
                    public void onNext(@NonNull Base liuYanBean) {
                        if (liuYanBean.getCode() == 200) {
                            //                            if(liuYanBean.getData()==null){
                            //                                baseView.empty();
                            //                            }else {
                            //                                baseView.success(requestcode,liuYanBean);
                            //                            }
                            baseView.success(requestcode, liuYanBean);
                        } else {
                            baseView.error(liuYanBean);
                        }
                        baseView.finishLoding();
                    }
                });
    }


    public static void codelogin(String mobile, String password, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().codelogin(mobile, password).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<PhoneLoginCde>(baseView) {
                    @Override
                    public void onNext(@NonNull PhoneLoginCde liuYanBean) {
                        if (liuYanBean.getCode() == 200) {
//                            if(liuYanBean.getData()==null){
//                                baseView.empty();
//                            }else {
//                                baseView.success(requestcode,liuYanBean);
//                            }
                            baseView.success(requestcode, liuYanBean);
                        } else {
                            baseView.error(liuYanBean);
                        }
                        baseView.finishLoding();
                    }
                });
    }


    public static void passLogin(String mobile, String password, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().passLogin(mobile, password).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<PhoneLoginCde>(baseView) {
                    @Override
                    public void onNext(@NonNull PhoneLoginCde liuYanBean) {
                        if (liuYanBean.getCode() == 200) {
//                            if(liuYanBean.getData()==null){
//                                baseView.empty();
//                            }else {
//                                baseView.success(requestcode,liuYanBean);
//                            }
                            baseView.success(requestcode, liuYanBean);
                        } else {
                            baseView.error(liuYanBean);
                        }
                        baseView.finishLoding();
                    }
                });
    }


    public static void getrecharge(String token, int page, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().recharge(token, page).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RechargeBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        baseView.loding();
                    }

                    @Override
                    public void onNext(@NonNull RechargeBean code) {
                        Log.i("as", code.toString());
                        if (code.getCode() == 200) {
                            baseView.success(requestcode, code);
                        } else {
                            baseView.empty();
                        }
                        baseView.finishLoding();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i("as", e.toString());
                        baseView.finishLoding();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public static void consumption(String token, int page, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().consumption(token, page).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<XiaoFeiBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        baseView.loding();
                    }

                    @Override
                    public void onNext(@NonNull XiaoFeiBean code) {
                        Log.i("as", code.toString());
                        if (code.getCode() == 200) {
                            baseView.success(requestcode, code);
                        } else {
                            baseView.empty();
                        }
                        baseView.finishLoding();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i("as", e.toString());
                        baseView.finishLoding();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public static void getrecharge1(String token, int page, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().recharge(token, page).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<RechargeBean>(baseView) {
                    @Override
                    public void onNext(@NonNull RechargeBean rechargeBean) {
                        if (rechargeBean.getCode() == 200) {
                            baseView.success(requestcode, rechargeBean);
                        } else {
                            baseView.empty();
                        }
                        baseView.finishLoding();
                    }
                });
    }


    public static void liuyanmessage(String token, int page, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().liuyanmessage(token, page).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<LiuYanBean>(baseView) {
                    @Override
                    public void onNext(@NonNull LiuYanBean liuYanBean) {
                        if (liuYanBean.getCode() == 200) {
//                            if(liuYanBean.getData()==null ||liuYanBean.getData().size()==0){
//                                baseView.empty();
//                            }else {
                            baseView.success(requestcode, liuYanBean);
//                            }
                        } else {
                            baseView.empty();
                        }
                        baseView.finishLoding();
                    }
                });
    }

    public static void system_news(int page, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().system_news(page).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<SystemMessage>(baseView) {
                    @Override
                    public void onNext(@NonNull SystemMessage liuYanBean) {
                        if (liuYanBean.getCode() == 200) {
                            if (liuYanBean.getData() == null || liuYanBean.getData().size() == 0) {
                                baseView.empty();
                            } else {
                                baseView.success(requestcode, liuYanBean);
                            }
                        } else {
                            baseView.empty();
                        }
                        baseView.finishLoding();
                    }
                });
    }

    public static void goodtype(int page, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().goodtype().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<PeiSongBean>(baseView) {
                    @Override
                    public void onNext(@NonNull PeiSongBean liuYanBean) {
                        if (liuYanBean.getCode() == 200) {
                            if (liuYanBean.getData() == null || liuYanBean.getData().size() == 0) {
                                baseView.empty();
                            } else {
                                baseView.success(requestcode, liuYanBean);
                            }
                        } else {
                            baseView.empty();
                        }
                        baseView.finishLoding();
                    }
                });
    }

    public static void fubu(int page, final BaseView baseView, final int requestcode, List<String> type_id, Float weight,
                            List<String> car_require, String car_info,
                            Float price, int invoice, String mobile, List<String> hand_mode, String time,
                            int start_provinceid, int start_cityid,
                            int start_areaid, int end_provinceid,
                            int end_cityid, int end_areaid,
                            String remark, String typeid_bz) {
        CommonApi.getInstance().fabu(SharedPreferencesUtil.getInstance().getString("token"), type_id, weight, car_require, car_info, price, invoice, mobile, hand_mode, time, start_provinceid, start_cityid, start_areaid, end_provinceid, end_cityid, end_areaid, remark, typeid_bz).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<Base>(baseView) {
                    @Override
                    public void onNext(@NonNull Base liuYanBean) {
                        // // TODO: 2018/1/12 0012 有问题啊 
                        if (liuYanBean.getCode() == 200) {
                            baseView.success(requestcode, liuYanBean);
                        } else if (liuYanBean.getCode() == 201) {
                            baseView.error(liuYanBean);
                        } else if (liuYanBean.getCode() == 208) {
                            baseView.error(liuYanBean);
                        } else if (liuYanBean.getCode() == 208) {
                            baseView.error(liuYanBean);
                        } else if (liuYanBean.getCode() == 288) {
                            // 错误码
                            baseView.error(liuYanBean);
                        }
                        baseView.finishLoding();
                    }
                });
    }

    public static void city(int page, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().city().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<CitysBean>(baseView) {
                    @Override
                    public void onNext(@NonNull CitysBean liuYanBean) {
                        if (liuYanBean.getCode() == 200) {
                            if (liuYanBean.getData() == null || liuYanBean.getData().size() == 0) {
                                baseView.empty();
                            } else {
                                baseView.success(requestcode, liuYanBean);
                            }
                        } else {
                            baseView.empty();
                        }
                        baseView.finishLoding();
                    }
                });
    }

    public static void commander_models(int page, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().commander_models().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<PeiSongBean>(baseView) {
                    @Override
                    public void onNext(@NonNull PeiSongBean liuYanBean) {
                        if (liuYanBean.getCode() == 200) {
                            if (liuYanBean.getData() == null || liuYanBean.getData().size() == 0) {
                                baseView.empty();
                            } else {
                                baseView.success(requestcode, liuYanBean);
                            }
                        } else {
                            baseView.empty();
                        }
                        baseView.finishLoding();
                    }
                });
    }

    public static void hade_model(int page, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().hade_model().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<PeiSongBean>(baseView) {
                    @Override
                    public void onNext(@NonNull PeiSongBean liuYanBean) {
                        if (liuYanBean.getCode() == 200) {
                            if (liuYanBean.getData() == null || liuYanBean.getData().size() == 0) {
                                baseView.empty();
                            } else {
                                baseView.success(requestcode, liuYanBean);
                            }
                        } else {
                            baseView.empty();
                        }
                        baseView.finishLoding();
                    }
                });
    }


    public static void good(String token, String city, int page, String leng_id, String model_id, String type_id, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().good(token, city, page, leng_id, model_id, type_id).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<HuoYuanBean>(baseView) {
                    @Override
                    public void onNext(@NonNull HuoYuanBean liuYanBean) {
                        if (liuYanBean.getCode() == 200) {
                            if (liuYanBean.getData() == null || liuYanBean.getData().size() == 0) {
                                baseView.empty();
                            } else {
                                baseView.success(requestcode, liuYanBean);
                            }
                        } else {
                            baseView.empty();
                        }
                        baseView.finishLoding();
                    }
                });
    }

    public static void good_detail(String token, int good_id, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().good_detail(token, good_id).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<GoodDetailBean>(baseView) {
                    @Override
                    public void onNext(@NonNull GoodDetailBean liuYanBean) {
                        if (liuYanBean.getCode() == 200) {
                            if (liuYanBean.getData() == null) {
                                baseView.empty();
                            } else {
                                baseView.success(requestcode, liuYanBean);
                            }
                        } else {
                            baseView.empty();
                        }
                        baseView.finishLoding();
                    }
                });
    }


    public static void jion_list(String token, int page, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().jion_list(token, page).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<JiaMengBean>(baseView) {
                    @Override
                    public void onNext(@NonNull JiaMengBean liuYanBean) {
                        if (liuYanBean.getCode() == 200) {
                            if (liuYanBean.getData() == null || liuYanBean.getData().size() == 0) {
                                baseView.empty();
                            } else {
                                baseView.success(requestcode, liuYanBean);
                            }
                        } else {
                            baseView.empty();
                        }
                        baseView.finishLoding();
                    }
                });
    }

    public static void jion_select(String encode, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().jion_select(encode).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<JiaMengSinBean>(baseView) {
                    @Override
                    public void onNext(@NonNull JiaMengSinBean liuYanBean) {
                        if (liuYanBean.getCode() == 200) {
                            if (liuYanBean.getData() == null) {
                                baseView.empty();
                            } else {
                                baseView.success(requestcode, liuYanBean);
                            }
                        } else {
                            baseView.empty();
                        }
                        baseView.finishLoding();
                    }
                });
    }


    public static void apply_jion(String token, int jion_id, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().apply_jion(token, jion_id).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<Code>(baseView) {
                    @Override
                    public void onNext(@NonNull Code liuYanBean) {
                        if (liuYanBean.getCode() == 200) {
                            baseView.success(requestcode, liuYanBean);
                        } else {
                            baseView.error(liuYanBean);
                        }
                        baseView.finishLoding();
                    }
                });
    }

    public static void oilcard(String token, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().oilcard(token).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<OilCardListBean>(baseView) {
                    @Override
                    public void onNext(@NonNull OilCardListBean liuYanBean) {
                        if (liuYanBean.getCode() == 200) {
                            if (liuYanBean.getData() == null) {
                                baseView.empty();
                            } else {
                                baseView.success(requestcode, liuYanBean);
                            }
                        } else {
                            baseView.empty();
                        }
                        baseView.finishLoding();
                    }
                });
    }

    public static void oil_recharge(String token, int page, int type, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().oil_recharge(token, page, type).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<OilChargeListBean>(baseView) {
                    @Override
                    public void onNext(@NonNull OilChargeListBean liuYanBean) {
                        if (liuYanBean.getCode() == 200) {
                            if (liuYanBean.getData() == null || liuYanBean.getData().size() == 0) {
                                baseView.empty();
                            } else {
                                baseView.success(requestcode, liuYanBean);
                            }
                        } else {
                            baseView.empty();
                        }
                        baseView.finishLoding();
                    }
                });
    }


    public static void oil_recharge(String token, int page, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().oil_recharge(token, page).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<OilChargeListBean>(baseView) {
                    @Override
                    public void onNext(@NonNull OilChargeListBean liuYanBean) {
                        if (liuYanBean.getCode() == 200) {
                            if (liuYanBean.getData() == null || liuYanBean.getData().size() == 0) {
                                baseView.empty();
                            } else {
                                baseView.success(requestcode, liuYanBean);
                            }
                        } else {
                            baseView.empty();
                        }
                        baseView.finishLoding();
                    }
                });
    }


    public static void commander(int page, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().commander().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<PeiSongBean>(baseView) {
                    @Override
                    public void onNext(@NonNull PeiSongBean liuYanBean) {
                        if (liuYanBean.getCode() == 200) {
                            if (liuYanBean.getData() == null || liuYanBean.getData().size() == 0) {
                                baseView.empty();
                            } else {
                                baseView.success(requestcode, liuYanBean);
                            }
                        } else {
                            baseView.empty();
                        }
                        baseView.finishLoding();
                    }
                });
    }


    public static void goodsgoodtype(int page, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().goodsgoodtype().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<PeiSongBean>(baseView) {
                    @Override
                    public void onNext(@NonNull PeiSongBean liuYanBean) {
                        if (liuYanBean.getCode() == 200) {
                            if (liuYanBean.getData() == null || liuYanBean.getData().size() == 0) {
                                baseView.empty();
                            } else {
                                baseView.success(requestcode, liuYanBean);
                            }
                        } else {
                            baseView.empty();
                        }
                        baseView.finishLoding();
                    }
                });
    }

    public static void models(int page, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().models().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<PeiSongBean>(baseView) {
                    @Override
                    public void onNext(@NonNull PeiSongBean liuYanBean) {
                        if (liuYanBean.getCode() == 200) {
                            if (liuYanBean.getData() == null || liuYanBean.getData().size() == 0) {
                                baseView.empty();
                            } else {
                                baseView.success(requestcode, liuYanBean);
                            }
                        } else {
                            baseView.empty();
                        }
                        baseView.finishLoding();
                    }
                });
    }

    public static void logout(int page, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().logout(SharedPreferencesUtil.getInstance().getString("token")).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<Code>(baseView) {
                    @Override
                    public void onNext(@NonNull Code liuYanBean) {
                        if (liuYanBean.getCode() == 200) {
                            baseView.success(requestcode, liuYanBean);
                        } else {
                            baseView.error(liuYanBean);
                        }
                        baseView.finishLoding();
                    }
                });
    }

    public static void shaixuan(String token, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().shaixuan().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<PeiSongBean>(baseView) {
                    @Override
                    public void onNext(@NonNull PeiSongBean liuYanBean) {
                        if (liuYanBean.getCode() == 200) {
                            if (liuYanBean.getData() == null && liuYanBean.getData().size() != 0) {
                                baseView.empty();
                            } else {
                                baseView.success(requestcode, liuYanBean);
                            }
                        } else {
                            baseView.empty();
                        }
                        baseView.finishLoding();
                    }
                });
    }

    public static void acommander_models(final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().acommander_models().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<PeiSongBean>(baseView) {
                    @Override
                    public void onNext(@NonNull PeiSongBean liuYanBean) {
                        if (liuYanBean.getCode() == 200) {
                            if (liuYanBean.getData() == null && liuYanBean.getData().size() != 0) {
                                baseView.empty();
                            } else {
                                baseView.success(requestcode, liuYanBean);
                            }
                        } else {
                            baseView.empty();
                        }
                        baseView.finishLoding();
                    }
                });
    }

    public static void vip(String token, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().vip().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<VipListBean>(baseView) {
                    @Override
                    public void onNext(@NonNull VipListBean liuYanBean) {
                        if (liuYanBean.getCode() == 200) {
                            if (liuYanBean.getData() == null && liuYanBean.getData().size() != 0) {
                                baseView.empty();
                            } else {
                                baseView.success(requestcode, liuYanBean);
                            }
                        } else {
                            baseView.empty();
                        }
                        baseView.finishLoding();
                    }
                });
    }


    public static void maivip(String token, int vip_id, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().maivip(token, vip_id).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<AliPayBean>(baseView) {
                    @Override
                    public void onNext(@NonNull AliPayBean liuYanBean) {
                        if (liuYanBean.getCode() == 200) {
                            baseView.success(requestcode, liuYanBean);
                        } else {
                            baseView.error(liuYanBean);
                        }
                        baseView.finishLoding();
                    }
                });
    }

    public static void maivipWeixin(String token, int vip_id, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().maivipWeixin(token, vip_id).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<WeixinBean>(baseView) {
                    @Override
                    public void onNext(@NonNull WeixinBean liuYanBean) {
                        if (liuYanBean.getCode() == 200) {
                            baseView.success(requestcode, liuYanBean);
                        } else {
                            baseView.error(liuYanBean);
                        }
                        baseView.finishLoding();
                    }
                });
    }

    public static void select(String token, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().select().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<PeiSongBean>(baseView) {
                    @Override
                    public void onNext(@NonNull PeiSongBean liuYanBean) {
                        if (liuYanBean.getCode() == 200) {
                            if (liuYanBean.getData() == null && liuYanBean.getData().size() != 0) {
                                baseView.empty();
                            } else {
                                baseView.success(requestcode, liuYanBean);
                            }
                        } else {
                            baseView.empty();
                        }
                        baseView.finishLoding();
                    }
                });
    }

    public static void index(String token, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().index().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<String>(baseView) {
                    @Override
                    public void onNext(@NonNull String liuYanBean) {
                        baseView.success(requestcode, liuYanBean);

                        baseView.finishLoding();
                    }
                });
    }


    public static void good_view(String token, int good_id, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().good_view(token, good_id).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<ChaKanBean>(baseView) {
                    @Override
                    public void onNext(@NonNull ChaKanBean liuYanBean) {
                        if (liuYanBean.getCode() == 200) {
                            if (liuYanBean.getData() == null) {
                                baseView.empty();
                            } else {
                                baseView.success(requestcode, liuYanBean);
                            }
                        } else {
                            baseView.empty();
                        }
                        baseView.finishLoding();
                    }
                });
    }

    public static void good_cost(String token, int good_id, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().good_cost(token, good_id).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<Code>(baseView) {
                    @Override
                    public void onNext(@NonNull Code liuYanBean) {
                        if (liuYanBean.getCode() == 200) {

                            baseView.success(requestcode, liuYanBean);
                        } else {
                            baseView.error(liuYanBean);
                        }
                        baseView.finishLoding();
                    }
                });
    }

    public static void pay_balance(String token, int good_id, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().pay_balance(token, good_id).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<Base>(baseView) {
                    @Override
                    public void onNext(@NonNull Base liuYanBean) {
                        if (liuYanBean.getCode() == 200) {

                            baseView.success(requestcode, liuYanBean);
                        } else {
                            baseView.error(liuYanBean);
                        }
                        baseView.finishLoding();
                    }
                });
    }

    public static void balance_scode(String token, int good_id, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().balance_scode(token, good_id).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<Base>(baseView) {
                    @Override
                    public void onNext(@NonNull Base liuYanBean) {
                        if (liuYanBean.getCode() == 200) {

                            baseView.success(requestcode, liuYanBean);
                        } else {
                            baseView.error(liuYanBean);
                        }
                        baseView.finishLoding();
                    }
                });
    }

    public static void pay_aliay(String token, int good_id, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().pay_aliay(token, good_id).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<AliPayBean>(baseView) {
                    @Override
                    public void onNext(AliPayBean aliPayBean) {
                        if (aliPayBean.getCode() == 200) {
                            baseView.success(requestcode, aliPayBean);
                        } else {
                            baseView.error(aliPayBean);
                        }
                        baseView.finishLoding();
                    }


                });
    }

    public static void pay_weixin(String token, int good_id, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().pay_weixin(token, good_id).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<WeixinBean>(baseView) {
                    @Override
                    public void onNext(@NonNull WeixinBean liuYanBean) {
                        if (liuYanBean.getCode() == 200) {

                            baseView.success(requestcode, liuYanBean);
                        } else {
                            baseView.error(liuYanBean);
                        }
                        baseView.finishLoding();
                    }
                });
    }

    public static void order(String token, int good_id, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().order(token, good_id).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<Code>(baseView) {
                    @Override
                    public void onNext(@NonNull Code liuYanBean) {
                        if (liuYanBean.getCode() == 200) {

                            baseView.success(requestcode, liuYanBean);
                        } else {
                            baseView.error(liuYanBean);
                        }
                        baseView.finishLoding();
                    }
                });
    }

    public static void invate(String token, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().invate(token).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<InvateBean>(baseView) {
                    @Override
                    public void onNext(@NonNull InvateBean liuYanBean) {
                        if (liuYanBean.getCode() == 200) {
                            if (liuYanBean.getData() == null) {
                                baseView.empty();
                            } else {
                                baseView.success(requestcode, liuYanBean);
                            }
                        } else {
                            baseView.empty();
                        }
                        baseView.finishLoding();
                    }
                });
    }


    public static void order_list(String token, int page, int state, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().order_list(token, page, state).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<OrdersBean>(baseView) {
                    @Override
                    public void onNext(@NonNull OrdersBean liuYanBean) {
                        if (liuYanBean.getCode() == 200) {
                            if (liuYanBean.getData() == null && liuYanBean.getData().size() != 0) {
                                baseView.empty();
                            } else {
                                baseView.success(requestcode, liuYanBean);
                            }
                        } else {
                            baseView.empty();
                        }
                        baseView.finishLoding();
                    }
                });
    }


    public static void confirm_order(String token, int order_id, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().confirm_order(token, order_id).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<Code>(baseView) {
                    @Override
                    public void onNext(@NonNull Code liuYanBean) {
                        if (liuYanBean.getCode() == 200) {
                            baseView.success(requestcode, liuYanBean);
                        } else {
                            baseView.error(liuYanBean);
                        }
                        baseView.finishLoding();
                    }
                });
    }

    public static void cancel_order(String token, int order_id, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().cancel_order(token, order_id).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<Code>(baseView) {
                    @Override
                    public void onNext(@NonNull Code liuYanBean) {
                        if (liuYanBean.getCode() == 200) {
                            baseView.success(requestcode, liuYanBean);
                        } else {
                            baseView.error(liuYanBean);
                        }
                        baseView.finishLoding();
                    }
                });
    }


    public static void weight_img(String token, File filepath, String order_id, String type
            , String f_unit, String s_unit, String good_name, String car_card, String m_weight
            , String p_weight, String weight, String driver_name, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().weight_img(token, filepath, order_id, type, f_unit, s_unit, good_name, car_card, m_weight
                , p_weight, weight, driver_name).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<Code>(baseView) {
                    @Override
                    public void onNext(@NonNull Code liuYanBean) {
                        if (liuYanBean.getCode() == 200) {
                            baseView.success(requestcode, liuYanBean);
                        } else {
                            baseView.error(liuYanBean);
                        }
                        baseView.finishLoding();
                    }
                });
    }

    public static void service(String token, int order_id, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().service(token, order_id).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<Code>(baseView) {
                    @Override
                    public void onNext(@NonNull Code liuYanBean) {
                        if (liuYanBean.getCode() == 200) {
                            baseView.success(requestcode, liuYanBean);
                        } else {
                            baseView.error(liuYanBean);
                        }
                        baseView.finishLoding();
                    }
                });
    }

    public static void view_weight(String token, int order_id, int type, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().view_weight(token, order_id, type).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<BangDanBean>(baseView) {
                    @Override
                    public void onNext(@NonNull BangDanBean liuYanBean) {
                        if (liuYanBean.getCode() == 200) {
                            baseView.success(requestcode, liuYanBean);
                        } else {
                            baseView.error(liuYanBean);
                        }
                        baseView.finishLoding();
                    }
                });
    }


    public static void edit_info(String token, Integer province_id, Integer city_id, Integer start_city, Integer endcity_id, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().edit_info(token, province_id, city_id, start_city, endcity_id).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<Code>(baseView) {
                    @Override
                    public void onNext(@NonNull Code liuYanBean) {
                        if (liuYanBean.getCode() == 200) {
                            baseView.success(requestcode, liuYanBean);
                        } else {
                            baseView.error(liuYanBean);
                        }
                        baseView.finishLoding();
                    }
                });
    }

    public static void new_mobile(String token, String mobile, String code, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().new_mobile(token, mobile, code).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<Code>(baseView) {
                    @Override
                    public void onNext(@NonNull Code liuYanBean) {
                        if (liuYanBean.getCode() == 200) {
                            baseView.success(requestcode, liuYanBean);
                        } else {
                            baseView.error(liuYanBean);
                        }
                        baseView.finishLoding();
                    }
                });
    }

    public static void order_detail(String token, int order_id, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().order_detail(token, order_id).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<OrderDetailBean>(baseView) {
                    @Override
                    public void onNext(@NonNull OrderDetailBean liuYanBean) {
                        if (liuYanBean.getCode() == 200) {
                            if (liuYanBean.getData() == null) {
                                baseView.empty();
                            } else {
                                baseView.success(requestcode, liuYanBean);
                            }
                        } else {
                            baseView.empty();
                        }
                        baseView.finishLoding();
                    }
                });
    }

    public static void order_process(String token, int order_id, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().order_process(token, order_id).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<JinChengBean>(baseView) {
                    @Override
                    public void onNext(@NonNull JinChengBean liuYanBean) {
                        if (liuYanBean.getCode() == 200) {
                            if (liuYanBean.getData() == null) {
                                baseView.empty();
                            } else {
                                baseView.success(requestcode, liuYanBean);
                            }
                        } else {
                            baseView.empty();
                        }
                        baseView.finishLoding();
                    }
                });
    }


    public static void getCode(String mobile, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().getCode(mobile).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<Base>(baseView) {
                    @Override
                    public void onNext(@NonNull Base liuYanBean) {
                        if (liuYanBean.getCode() == 200) {
//                            if(liuYanBean.getData()==null){
//                                baseView.empty();
//                            }else {
//                                baseView.success(requestcode,liuYanBean);
//                            }
                            baseView.success(requestcode, liuYanBean);
                        } else {
                            baseView.empty();
                        }
                        baseView.finishLoding();
                    }
                });
    }

    public static void getRegeister(String mobile, String password, String code, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().getRegeister(mobile, password, code).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<RegeiserCode>(baseView) {
                    @Override
                    public void onNext(@NonNull RegeiserCode liuYanBean) {
                        if (liuYanBean.getCode() == 200) {
//                            if(liuYanBean.getData()==null){
//                                baseView.empty();
//                            }else {
//                                baseView.success(requestcode,liuYanBean);
//                            }
                            baseView.success(requestcode, liuYanBean);
                        } else {
                            baseView.error(liuYanBean);
                        }
                        baseView.finishLoding();
                    }
                });
    }

    public static void news_num(String token, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().news_num(token).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<NewsNumBean>(baseView) {
                    @Override
                    public void onNext(@NonNull NewsNumBean liuYanBean) {
                        if (liuYanBean.getCode() == 200) {
//                            if(liuYanBean.getData()==null){
//                                baseView.empty();
//                            }else {
//                                baseView.success(requestcode,liuYanBean);
//                            }
                            baseView.success(requestcode, liuYanBean);
                        } else {
                            baseView.error(liuYanBean);
                        }
                        baseView.finishLoding();
                    }
                });
    }

    public static void view_news(String token, int type, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().view_news(token, type).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<Base>(baseView) {
                    @Override
                    public void onNext(@NonNull Base liuYanBean) {
                        if (liuYanBean.getCode() == 200) {
//                            if(liuYanBean.getData()==null){
//                                baseView.empty();
//                            }else {
//                                baseView.success(requestcode,liuYanBean);
//                            }
                            baseView.success(requestcode, liuYanBean);
                        } else {
                            baseView.error(liuYanBean);
                        }
                        baseView.finishLoding();
                    }
                });
    }

    public static void balance_oil(String token, float type, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().balance_oil(token, type).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<Base>(baseView) {
                    @Override
                    public void onNext(@NonNull Base liuYanBean) {
                        if (liuYanBean.getCode() == 200) {
//                            if(liuYanBean.getData()==null){
//                                baseView.empty();
//                            }else {
//                                baseView.success(requestcode,liuYanBean);
//                            }
                            baseView.success(requestcode, liuYanBean);
                        } else {
                            baseView.error(liuYanBean);
                        }
                        baseView.finishLoding();
                    }
                });
    }


    public static void alipay_oil(String token, float type, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().alipay_oil(token, type).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<Code>(baseView) {
                    @Override
                    public void onNext(@NonNull Code liuYanBean) {
                        if (liuYanBean.getCode() == 200) {
//                            if(liuYanBean.getData()==null){
//                                baseView.empty();
//                            }else {
//                                baseView.success(requestcode,liuYanBean);
//                            }
                            baseView.success(requestcode, liuYanBean);
                        } else {
                            baseView.error(liuYanBean);
                        }
                        baseView.finishLoding();
                    }
                });
    }


    public static void wxpay_oil(String token, float type, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().wxpay_oil(token, type).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<WeixinBean>(baseView) {
                    @Override
                    public void onNext(@NonNull WeixinBean liuYanBean) {
                        if (liuYanBean.getCode() == 200) {
//                            if(liuYanBean.getData()==null){
//                                baseView.empty();
//                            }else {
//                                baseView.success(requestcode,liuYanBean);
//                            }
                            baseView.success(requestcode, liuYanBean);
                        } else {
                            baseView.error(liuYanBean);
                        }
                        baseView.finishLoding();
                    }
                });
    }



    public static void cancel_order(String token, String order_id, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().cancel_order(token, order_id).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<Base>(baseView) {
                    @Override
                    public void onNext(@NonNull Base liuYanBean) {
                        if (liuYanBean.getCode() == 200) {
//                            if(liuYanBean.getData()==null){
//                                baseView.empty();
//                            }else {
//                                baseView.success(requestcode,liuYanBean);
//                            }
                            baseView.success(requestcode, liuYanBean);
                        } else {
                            baseView.error(liuYanBean);
                        }
                        baseView.finishLoding();
                    }
                });
    }
}
