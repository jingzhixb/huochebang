package com.zhuye.huochebang.data.http;

import com.zhuye.huochebang.bean.BankListBean;
import com.zhuye.huochebang.bean.Base;
import com.zhuye.huochebang.bean.BaseView;
import com.zhuye.huochebang.bean.CarListBean;
import com.zhuye.huochebang.bean.Code;
import com.zhuye.huochebang.bean.DriverListBean;
import com.zhuye.huochebang.bean.HomeCarInfoBean;
import com.zhuye.huochebang.bean.MoneyLog;
import com.zhuye.huochebang.bean.PhoneLoginCde;
import com.zhuye.huochebang.bean.RegeiserCode;
import com.zhuye.huochebang.utils.SharedPreferencesUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/1/11 0011.
 */

public class GetData {
    public static void logout(int page, final BaseView baseView, final int requestcode){
        CommonApi.getInstance().logout(SharedPreferencesUtil.getInstance().getString("token")).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<Code>(baseView) {
                    @Override
                    public void onNext(@NonNull Code liuYanBean) {
                        if (liuYanBean.getCode()==200){
                            baseView.success(requestcode,liuYanBean);
                        }else {
                            baseView.error(liuYanBean);
                        }
                        baseView.finishLoding();
                    }
                });
    }



    public static void getCode(String mobile , final BaseView baseView, final int requestcode){
        CommonApi.getInstance().getCode(mobile).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<Base>(baseView) {
                    @Override
                    public void onNext(@NonNull Base liuYanBean) {
                        if (liuYanBean.getCode()==200){
//                            if(liuYanBean.getData()==null){
//                                baseView.empty();
//                            }else {
//                                baseView.success(requestcode,liuYanBean);
//                            }
                            baseView.success(requestcode,liuYanBean);
                        }else {
                            baseView.error(liuYanBean);
                        }
                        baseView.finishLoding();
                    }
                });
    }



    public static void tixian(String token,String cash_id,Float money, final BaseView baseView, final int requestcode){
        CommonApi.getInstance().tixian(token,cash_id,money).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<Base>(baseView) {
                    @Override
                    public void onNext(@NonNull Base liuYanBean) {
                        if (liuYanBean.getCode()==200){
                            //                            if(liuYanBean.getData()==null){
                            //                                baseView.empty();
                            //                            }else {
                            //                                baseView.success(requestcode,liuYanBean);
                            //                            }
                            baseView.success(requestcode,liuYanBean);
                        }else {
                            baseView.error(liuYanBean);
                        }
                        baseView.finishLoding();
                    }
                });
    }

    public static void withdrawals(String token, final BaseView baseView, final int requestcode){
        CommonApi.getInstance().withdrawals(token).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<Base>(baseView) {
                    @Override
                    public void onNext(@NonNull Base liuYanBean) {
                        if (liuYanBean.getCode()==200){
                            //                            if(liuYanBean.getData()==null){
                            //                                baseView.empty();
                            //                            }else {
                            //                                baseView.success(requestcode,liuYanBean);
                            //                            }
                            baseView.success(requestcode,liuYanBean);
                        }else {
                            baseView.error(liuYanBean);
                        }
                        baseView.finishLoding();
                    }
                });
    }




    public static void add_cash(String token,String card,String card_name,String name, final BaseView baseView, final int requestcode){
        CommonApi.getInstance().add_cash(token,card,card_name,name).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<Base>(baseView) {
                    @Override
                    public void onNext(@NonNull Base liuYanBean) {
                        if (liuYanBean.getCode()==200){
                            //                            if(liuYanBean.getData()==null){
                            //                                baseView.empty();
                            //                            }else {
                            //                                baseView.success(requestcode,liuYanBean);
                            //                            }
                            baseView.success(requestcode,liuYanBean);
                        }else {
                            baseView.error(liuYanBean);
                        }
                        baseView.finishLoding();
                    }
                });
    }




    public static void getRegeister(String mobile, String password, String  code,final BaseView baseView, final int requestcode){
        CommonApi.getInstance().getRegeister(mobile,password,code).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<RegeiserCode>(baseView) {
                    @Override
                    public void onNext(@NonNull RegeiserCode liuYanBean) {
                        if (liuYanBean.getCode()==200){
//                            if(liuYanBean.getData()==null){
//                                baseView.empty();
//                            }else {
//                                baseView.success(requestcode,liuYanBean);
//                            }
                            baseView.success(requestcode,liuYanBean);
                        }else {
                            baseView.error(liuYanBean);
                        }
                        baseView.finishLoding();
                    }
                });
    }


    public static void passLogin(String mobile, String password,final BaseView baseView, final int requestcode){
        CommonApi.getInstance().passLogin(mobile,password).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<PhoneLoginCde>(baseView) {
                    @Override
                    public void onNext(@NonNull PhoneLoginCde liuYanBean) {
                        if (liuYanBean.getCode()==200){
//                            if(liuYanBean.getData()==null){
//                                baseView.empty();
//                            }else {
//                                baseView.success(requestcode,liuYanBean);
//                            }
                            baseView.success(requestcode,liuYanBean);
                        }else {
                            baseView.error(liuYanBean);
                        }
                        baseView.finishLoding();
                    }
                });
    }

    public static void codelogin(String mobile, String password,final BaseView baseView, final int requestcode){
        CommonApi.getInstance().codelogin(mobile,password).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<PhoneLoginCde>(baseView) {
                    @Override
                    public void onNext(@NonNull PhoneLoginCde liuYanBean) {
                        if (liuYanBean.getCode()==200){
//                            if(liuYanBean.getData()==null){
//                                baseView.empty();
//                            }else {
//                                baseView.success(requestcode,liuYanBean);
//                            }
                            baseView.success(requestcode,liuYanBean);
                        }else {
                            baseView.error(liuYanBean);
                        }
                        baseView.finishLoding();
                    }
                });
    }



    public static void add_driver(String token, int car_id,int driver_id,final BaseView baseView, final int requestcode){
        CommonApi.getInstance().add_driver(token,car_id,driver_id).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<Base>(baseView) {
                    @Override
                    public void onNext(@NonNull Base liuYanBean) {
                        if (liuYanBean.getCode()==200){
                            //                            if(liuYanBean.getData()==null){
                            //                                baseView.empty();
                            //                            }else {
                            //                                baseView.success(requestcode,liuYanBean);
                            //                            }
                            baseView.success(requestcode,liuYanBean);
                        }else {
                            baseView.error(liuYanBean);
                        }
                        baseView.finishLoding();
                    }
                });
    }



    public static void select_car(String token, String license,final BaseView baseView, final int requestcode){
        CommonApi.getInstance().select_car(token,license).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<HomeCarInfoBean>(baseView) {
                    @Override
                    public void onNext(@NonNull HomeCarInfoBean liuYanBean) {
                        if (liuYanBean.getCode()==200){
                            //                            if(liuYanBean.getData()==null){
                            //                                baseView.empty();
                            //                            }else {
                            //                                baseView.success(requestcode,liuYanBean);
                            //                            }
                            baseView.success(requestcode,liuYanBean);
                        }else {
                            baseView.error(liuYanBean);
                        }
                        baseView.finishLoding();
                    }
                });
    }






    public static void homecarinfo(String token,int page, final BaseView baseView, final int requestcode){
        CommonApi.getInstance().homecarinfo(token,page).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<HomeCarInfoBean>(baseView) {
                    @Override
                    public void onNext(@NonNull HomeCarInfoBean liuYanBean) {
                        if (liuYanBean.getCode()==200){
                            if(liuYanBean.getData()==null ||liuYanBean.getData().size()==0){
                                baseView.empty();
                            }else {
                                baseView.success(requestcode,liuYanBean);
                            }
                        }else {
                            baseView.empty();
                        }
                        baseView.finishLoding();
                    }
                });
    }


    public static void homecainvite_driverrinfo(String token,int page, final BaseView baseView, final int requestcode){
        CommonApi.getInstance().homecainvite_driverrinfo(token,page).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<DriverListBean>(baseView) {
                    @Override
                    public void onNext(@NonNull DriverListBean liuYanBean) {
                        if (liuYanBean.getCode()==200){
                            if(liuYanBean.getData()==null ||liuYanBean.getData().size()==0){
                                baseView.empty();
                            }else {
                                baseView.success(requestcode,liuYanBean);
                            }
                        }else {
                            baseView.empty();
                        }
                        baseView.finishLoding();
                    }
                });
    }



    public static void income_log(String token,int page, final BaseView baseView, final int requestcode){
        CommonApi.getInstance().income_log(token,page).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<MoneyLog>(baseView) {
                    @Override
                    public void onNext(@NonNull MoneyLog liuYanBean) {
                        if (liuYanBean.getCode()==200){
                            if(liuYanBean.getData()==null ||liuYanBean.getData().size()==0){
                                baseView.empty();
                            }else {
                                baseView.success(requestcode,liuYanBean);
                            }
                        }else {
                            baseView.empty();
                        }
                        baseView.finishLoding();
                    }
                });
    }

    public static void getTixianData(String token,int page, final BaseView baseView, final int requestcode){
        CommonApi.getInstance().cash_log(token,page).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<MoneyLog>(baseView) {
                    @Override
                    public void onNext(@NonNull MoneyLog liuYanBean) {
                        if (liuYanBean.getCode()==200){
                            if(liuYanBean.getData()==null ||liuYanBean.getData().size()==0){
                                baseView.empty();
                            }else {
                                baseView.success(requestcode,liuYanBean);
                            }
                        }else {
                            baseView.empty();
                        }
                        baseView.finishLoding();
                    }
                });
    }



    public static void driver(String token,int page, final BaseView baseView, final int requestcode){
        CommonApi.getInstance().driver(token,page).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<DriverListBean>(baseView) {
                    @Override
                    public void onNext(@NonNull DriverListBean liuYanBean) {
                        if (liuYanBean.getCode()==200){
                            if(liuYanBean.getData()==null ||liuYanBean.getData().size()==0){
                                baseView.empty();
                            }else {
                                baseView.success(requestcode,liuYanBean);
                            }
                        }else {
                            baseView.empty();
                        }
                        baseView.finishLoding();
                    }
                });
    }

    public static void car_list(String token,int page, final BaseView baseView, final int requestcode){
        CommonApi.getInstance().car_list(token,page).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<CarListBean>(baseView) {
                    @Override
                    public void onNext(@NonNull CarListBean liuYanBean) {
                        if (liuYanBean.getCode()==200){
                            if(liuYanBean.getData()==null ||liuYanBean.getData().size()==0){
                                baseView.empty();
                            }else {
                                baseView.success(requestcode,liuYanBean);
                            }
                        }else {
                            baseView.empty();
                        }
                        baseView.finishLoding();
                    }
                });
    }

    public static void agree(String token, int driver_jionid, int driver_id, final BaseView baseView, final int requestcode, String... car_id){
        CommonApi.getInstance().agree(token,driver_jionid,driver_id,car_id).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<DriverListBean>(baseView) {
                    @Override
                    public void onNext(@NonNull DriverListBean liuYanBean) {
                        if (liuYanBean.getCode()==200){
                            if(liuYanBean.getData()==null ||liuYanBean.getData().size()==0){
                                baseView.empty();
                            }else {
                                baseView.success(requestcode,liuYanBean);
                            }
                        }else {
                            baseView.empty();
                        }
                        baseView.finishLoding();
                    }
                });
    }
    public static void refuse(String token, int driver_jionid, String reason,final BaseView baseView, final int requestcode){
        CommonApi.getInstance().refuse(token,driver_jionid,reason).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<Code>(baseView) {
                    @Override
                    public void onNext(@NonNull Code liuYanBean) {
                        if (liuYanBean.getCode()==200){
//                            if(liuYanBean.getData()==null ||liuYanBean.getData().size()==0){
//                                baseView.empty();
//                            }else {
                                baseView.success(requestcode,liuYanBean);
//                            }
                        }else {
                            baseView.empty();
                        }
                        baseView.finishLoding();
                    }
                });
    }
    public static void list_card(String token, final BaseView baseView, final int requestcode){
        CommonApi.getInstance().list_card(token).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<BankListBean>(baseView) {
                    @Override
                    public void onNext(@NonNull BankListBean liuYanBean) {
                        if (liuYanBean.getCode()==200){
                            if(liuYanBean.getData()==null ||liuYanBean.getData().size()==0){
                                baseView.empty();
                            }else {
                                baseView.success(requestcode,liuYanBean);
                            }
                        }else {
                            baseView.empty();
                        }
                        baseView.finishLoding();
                    }
                });
    }



//    public static void getrecharge(String token, int page, final BaseView baseView, final int requestcode ){
//        CommonApi.getInstance().recharge(token,page).subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<RechargeBean>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//                        baseView.loding();
//                    }
//
//                    @Override
//                    public void onNext(@NonNull RechargeBean code) {
//                        Log.i("as", code.toString());
//                        if (code.getCode().equals("200")){
//                            baseView.success(requestcode,code);
//                        }else {
//                            baseView.empty();
//                        }
//                        baseView.finishLoding();
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        Log.i("as", e.toString());
//                        baseView.finishLoding();
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//    }
//
//    public static void consumption(String token, int page, final BaseView baseView, final int requestcode ){
//        CommonApi.getInstance().consumption(token,page).subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<XiaoFeiBean>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//                        baseView.loding();
//                    }
//
//                    @Override
//                    public void onNext(@NonNull XiaoFeiBean code) {
//                        Log.i("as", code.toString());
//                        if (code.getCode().equals("200")){
//                            baseView.success(requestcode,code);
//                        }else {
//                            baseView.empty();
//                        }
//                        baseView.finishLoding();
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        Log.i("as", e.toString());
//                        baseView.finishLoding();
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//    }
//
//    public static void getrecharge1(String token, int page, final BaseView baseView, final int requestcode ){
//        CommonApi.getInstance().recharge(token,page).subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new MyObserver<RechargeBean>(baseView) {
//                    @Override
//                    public void onNext(@NonNull RechargeBean rechargeBean) {
//                        if (rechargeBean.getCode().equals("200")){
//                            baseView.success(requestcode,rechargeBean);
//                        }else {
//                            baseView.empty();
//                        }
//                        baseView.finishLoding();
//                    }
//                });
//    }


}
