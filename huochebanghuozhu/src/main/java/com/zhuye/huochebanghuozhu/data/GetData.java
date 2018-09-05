package com.zhuye.huochebanghuozhu.data;

import android.os.Handler;
import android.util.Log;

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
import com.zhuye.huochebanghuozhu.bean.JiaoFouBean;
import com.zhuye.huochebanghuozhu.bean.LiuYanBean;
import com.zhuye.huochebanghuozhu.bean.OrderBean;
import com.zhuye.huochebanghuozhu.bean.OrderDetailBean;
import com.zhuye.huochebanghuozhu.bean.PhoneLoginCde;
import com.zhuye.huochebanghuozhu.bean.RechargeBean;
import com.zhuye.huochebanghuozhu.bean.RegeiserCode;
import com.zhuye.huochebanghuozhu.bean.SystemMessage;
import com.zhuye.huochebanghuozhu.bean.WeixinBean;
import com.zhuye.huochebanghuozhu.bean.XiaoFeiBean;
import com.zhuye.huochebanghuozhu.bean.XingChengBean;
import com.zhuye.huochebanghuozhu.data.http.CommonApi;
import com.zhuye.huochebanghuozhu.data.http.MyObserver;
import com.zhuye.huochebanghuozhu.presenter.BaseView;
import com.zhuye.huochebanghuozhu.utils.SharedPreferencesUtil;

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
    public static Handler handler;
    public static Handler getHandler() {
        return handler;
    }
    public static void bindHandler(Handler h) {
        handler = h;
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
                        //baseView.error();
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

    public static void system_news(int page, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().system_news(page).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<SystemMessage>(baseView) {
                    @Override
                    public void onNext(@NonNull SystemMessage liuYanBean) {
                        if (liuYanBean.getCode() == 200) {
                            baseView.success(requestcode, liuYanBean);
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
                            String remark, String typeid_bz,String start_addr
            , String end_addr) {
        CommonApi.getInstance().fabu(SharedPreferencesUtil.getInstance().getString("token"), type_id, weight, car_require, car_info, price, invoice, mobile, hand_mode, time, start_provinceid, start_cityid,
                start_areaid, end_provinceid, end_cityid, end_areaid, remark, typeid_bz,start_addr,end_addr).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<Base>(baseView) {
                    @Override
                    public void onNext(@NonNull Base liuYanBean) {
                        // // TODO: 2018/1/12 0012 有问题啊 
                        if (liuYanBean.getCode() == 200) {
                            baseView.success(requestcode, liuYanBean);
                        } else {
                            // 错误码
                            baseView.error(liuYanBean);
                        }
                        baseView.finishLoding();
                    }
                });
    }


    public static void logout(int page, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().logout(SharedPreferencesUtil.getInstance().getString("token")).subscribeOn(Schedulers.newThread())
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


    public static void city(Integer type, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().city(type).subscribeOn(Schedulers.newThread())
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


    public static void driver(int page,
                              Integer start_city, Integer end_city,
                              List<Integer> array, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().driver(page, start_city, end_city, array).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<DaTingBean>(baseView) {
                    @Override
                    public void onNext(@NonNull DaTingBean liuYanBean) {
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


    public static void select(final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().select().subscribeOn(Schedulers.newThread())
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

    public static void driver_detail(String token, int uid, int car_id, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().driver_detail(token, uid, car_id).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<DriverDetailBean>(baseView) {
                    @Override
                    public void onNext(@NonNull DriverDetailBean liuYanBean) {
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


    public static void goods(int page,
                             int type, String token,
                             final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().goods(page, type, token).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<GoodsDeailBean>(baseView) {
                    @Override
                    public void onNext(@NonNull GoodsDeailBean liuYanBean) {
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

    public static void good_detail(int good_id, String token,
                                   final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().good_detail(good_id, token).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<GoodsBean>(baseView) {
                    @Override
                    public void onNext(@NonNull GoodsBean liuYanBean) {
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


    public static void order(int page,
                             String token, int type,
                             final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().order(page, token, type).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<OrderBean>(baseView) {
                    @Override
                    public void onNext(@NonNull OrderBean liuYanBean) {
                        if (liuYanBean.getCode() == 200) {
                            if (liuYanBean.getData() == null || liuYanBean.getData().size() == 0) {
//                                if(requestcode == 17){
//                                    baseView.success(requestcode,liuYanBean);
//                                }else {
                                baseView.empty();
//                                }
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


    public static void view_weight(int order_id,
                                   String token,
                                   int type,
                                   final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().view_weight(order_id, token, type).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<BangDanBean>(baseView) {
                    @Override
                    public void onNext(@NonNull BangDanBean liuYanBean) {
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

    public static void order_finish(int order_id,
                                    String token,
                                    final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().order_finish(order_id, token).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<Code>(baseView) {
                    @Override
                    public void onNext(@NonNull Code liuYanBean) {
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

    public static void order_process(
            String token, int page,
            final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().order_process(page, token).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<XingChengBean>(baseView) {
                    @Override
                    public void onNext(@NonNull XingChengBean liuYanBean) {
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

    public static void modify_price(int order_id,
                                    String token, float price,
                                    final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().modify_price(order_id, token, price).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<Code>(baseView) {
                    @Override
                    public void onNext(@NonNull Code liuYanBean) {
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


    public static void cancel_order(String token, int order_id, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().cancel_order(token, order_id).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<Code>(baseView) {
                    @Override
                    public void onNext(@NonNull Code liuYanBean) {
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

    public static void edit_info(String token, int province_id, int city_id, int start_city, int endcity_id, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().edit_info(token, province_id, city_id, start_city, endcity_id).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<Code>(baseView) {
                    @Override
                    public void onNext(@NonNull Code liuYanBean) {
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

    public static void new_mobile(String token, String mobile, String code, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().new_mobile(token, mobile, code).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<Code>(baseView) {
                    @Override
                    public void onNext(@NonNull Code liuYanBean) {
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


    public static void jiaofou(
            String token,
            final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().jiaofou(token).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<JiaoFouBean>(baseView) {
                    @Override
                    public void onNext(@NonNull JiaoFouBean liuYanBean) {
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

    public static void pay_aliay(String token, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().pay_aliay(token).subscribeOn(Schedulers.newThread())
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

    /**
     * 保证金 微信支付
     *
     * @param token
     * @param baseView
     * @param requestcode
     */
    public static void pay_bao_weixin(String token, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().pay_bao_weixin(token).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<WeixinBean>(baseView) {
                    @Override
                    public void onNext(WeixinBean aliPayBean) {
                        if (aliPayBean.getCode() == 200) {
                            baseView.success(requestcode, aliPayBean);
                        } else {
                            baseView.error(aliPayBean);
                        }
                        baseView.finishLoding();
                    }


                });
    }

    /**
     * 订单  微信支付
     *
     * @param token
     * @param order_id
     * @param baseView
     * @param requestcode
     */
    public static void pay_weixin(String token, int order_id, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().pay_weixin(token, order_id).subscribeOn(Schedulers.newThread())
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

    public static void pay_order(String token, int order_id, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().pay_order(token, order_id).subscribeOn(Schedulers.newThread())
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


    public static void edit_face(String token, String name, String face, final BaseView baseView, final int requestcode) {
        CommonApi.getInstance().edit_face(token, name,face).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<Code>(baseView) {
                    @Override
                    public void onNext(@NonNull Code liuYanBean) {
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
