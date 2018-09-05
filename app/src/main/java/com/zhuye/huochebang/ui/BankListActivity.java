package com.zhuye.huochebang.ui;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.zhuye.huochebang.BaseActivity;
import com.zhuye.huochebang.R;
import com.zhuye.huochebang.adapter.money.BankListAdapter;
import com.zhuye.huochebang.base.BaseHolder;
import com.zhuye.huochebang.bean.BankListBean;
import com.zhuye.huochebang.bean.Code;
import com.zhuye.huochebang.data.http.CommonApi;
import com.zhuye.huochebang.data.http.GetData;
import com.zhuye.huochebang.utils.SharedPreferencesUtil;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class BankListActivity extends BaseActivity {


    private static final int BANKLI = 10;
    private static final int REBANKLI = 11;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.banklist)
    RecyclerView banklist;
    @BindView(R.id.tianjia)
    Button tianjia;

    @Override
    protected int getResId() {
        return R.layout.activity_bank_list;
    }

    BankListAdapter adapter;
    @Override
    protected void initView() {
        super.initView();
        adapter = new BankListAdapter(BankListActivity.this);
        banklist.setAdapter(adapter);
        banklist.setLayoutManager(new LinearLayoutManager(BankListActivity.this));
    }
    BankListBean code;


    @Override
    public void success(int requestcode, Object o) {
        super.success(requestcode, o);
        switch (requestcode){
            case BANKLI:
                code = (BankListBean) o;
                adapter.addData(code.getData());
                break;
            case REBANKLI:
                code  = (BankListBean) o;
                adapter.clear();
                adapter.addData(code.getData());
                banklist.scrollToPosition(0);
                break;
        }
    }

    @Override
    protected void initData() {
        super.initData();

        GetData.list_card( SharedPreferencesUtil.getInstance().getString("token"),BankListActivity.this,
                BANKLI);
//
//        OkGo.<String>post(Contans.BASE+"index.php/home/commonpart/list_card")
//                .params("token", SharedPreferencesUtil.getInstance().getString("token"))
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(Response<String> response) {
//                        Log.i("as", response.body());
//                        if(response.body().contains("201")){
//                            toast("没有银行卡，请添加");
//                        }else if(response.body().contains("200")){
//                            code = new Gson().fromJson(response.body(),BankListBean.class);
//                            adapter.addData(code.getData());
//                        }
//                    }
//
//                    @Override
//                    public void onError(Response<String> response) {
//                        super.onError(response);
//                        Log.i("as", response.body());
//                    }
//                });
//        CommonApi.getInstance().list_card(SharedPreferencesUtil.getInstance().getString("token")).subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<BankListBean>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//                        Log.i("as", d.toString());
//                    }
//
//                    @Override
//                    public void onNext(@NonNull BankListBean code) {
//                        if (code.getCode() == 200) {
//                            adapter.addData(code.getData());
//                        }
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        Log.i("as", e.toString());
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
    }

    @Override
    protected void initListener() {
        super.initListener();
        adapter.setOnItemLongClickListener(new BaseHolder.OnItemLongClickListener() {
            @Override
            public void OnItemLongClick(View view, int position) {

                deleteBank(position);
               // toast("haha");
            }
        });


        adapter.setOnItemClickListener(new BaseHolder.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                shezhimoren(position);
            }
        });
    }

    private void shezhimoren(final int position) {
        final AlertDialog dialo = new AlertDialog.Builder(BankListActivity.this).create();
        View view = View.inflate(BankListActivity.this, R.layout.aliet_tousu, null);
        dialo.setView(view);
        view.findViewById(R.id.queren).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialo != null && dialo.isShowing()) {
                    dialo.dismiss();
                }
                CommonApi.getInstance().default_card(SharedPreferencesUtil.getInstance().getString("token"),code.getData().get(position).getCash_id()).subscribeOn(io.reactivex.schedulers.Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Code>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {
                                Log.i("as",d.toString());
                            }

                            @Override
                            public void onNext(@NonNull Code code) {
                                Log.i("as",code.toString());
                                if(code.getCode() == 200){
//                                    adapter.removeData(position);
                                    ischage = true;
                                    card =  BankListActivity.this.code.getData().get(position).getCard();
                                    card_name =  BankListActivity.this.code.getData().get(position).getCard_name();
                                    cash_id =  BankListActivity.this.code.getData().get(position).getCash_id();
                                    for(int i = 0;i<BankListActivity.this.code.getData().size();i++){
                                        BankListActivity.this.code.getData().get(i).setIs_default("0");
                                    }
                                   BankListActivity.this.code.getData().get(position).setIs_default("1");
                                    //adapter.updataItem(BankListActivity.this.code.getData().get(position),position);
                                    //adapter.notifyItemChanged();
                                    adapter.clear();
                                    adapter.addData2(BankListActivity.this.code.getData(),0);
                                }
                                toast(code.getMessage());
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                Log.i("as",e.toString());
                            }

                            @Override
                            public void onComplete() {

                            }
                        });

            }
        });

        view.findViewById(R.id.quxiao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialo != null && dialo.isShowing()) {
                    dialo.dismiss();
                }
            }
        });
        dialo.show();

    }

    private void deleteBank(final int position) {
        final AlertDialog dialog = new AlertDialog.Builder(BankListActivity.this).create();
        View view = View.inflate(BankListActivity.this, R.layout.aliet_tousu, null);
        dialog.setView(view);
        final EditText et = view.findViewById(R.id.tousu);
        view.findViewById(R.id.queren).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                CommonApi.getInstance().del_cash(SharedPreferencesUtil.getInstance().getString("token"),code.getData().get(position).getCash_id()).subscribeOn(io.reactivex.schedulers.Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Code>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {
                                Log.i("as",d.toString());
                            }

                            @Override
                            public void onNext(@NonNull Code code) {
                                Log.i("as",code.toString());
                                if(code.getCode() == 200){
                                    toast("已删除");
                                    adapter.removeData(position);
                                    BankListActivity.this.code.getData().remove(position);
                                }
                            }
                            @Override
                            public void onError(@NonNull Throwable e) {
                                Log.i("as",e.toString());
                            }

                            @Override
                            public void onComplete() {

                            }
                        });

            }
        });

        view.findViewById(R.id.quxiao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    @OnClick({R.id.back, R.id.tianjia})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                Intent in = new Intent();
                if(!ischage){
                }else {
                    in.putExtra("card",card);
                    in.putExtra("card_name",card_name);
                    in.putExtra("cash_id",cash_id);
                }
                in.putExtra("ischage",ischage);
                setResult(10000,in);
                finish();
                break;
            case R.id.tianjia:
                startActivityForResult(new Intent(BankListActivity.this, AddBankActivity.class),10);
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        GetData.list_card( SharedPreferencesUtil.getInstance().getString("token"),BankListActivity.this,
                REBANKLI);
    }

    Boolean ischage = false;
    String card;
    String card_name;
    String cash_id;
    @Override
    public void onBackPressed() {
        Intent in = new Intent();
        if(!ischage){
        }else {
            in.putExtra("card",card);
            in.putExtra("card_name",card_name);
            in.putExtra("cash_id",cash_id);
        }

        in.putExtra("ischage",ischage);
        setResult(10000,in);
        finish();
    }
}
