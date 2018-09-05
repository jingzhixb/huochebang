package com.zhuye.huochebang.ui;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.shawnlin.numberpicker.NumberPicker;
import com.zhuye.huochebang.BaseActivity;
import com.zhuye.huochebang.R;
import com.zhuye.huochebang.bean.CarModelBean;
import com.zhuye.huochebang.bean.CheChangBean;
import com.zhuye.huochebang.bean.Code;
import com.zhuye.huochebang.bean.MessageEvent;
import com.zhuye.huochebang.data.http.CommonApi;
import com.zhuye.huochebang.utils.SharedPreferencesUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddCarActivity extends BaseActivity {


    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.chepai)
    EditText chepai;
    @BindView(R.id.chexing)
    EditText chexing;
    @BindView(R.id.chechang)
    EditText chechang;
    @BindView(R.id.next)
    Button next;
    @BindView(R.id.haomaiv)
    ImageView haomaiv;
    @BindView(R.id.chexingiv)
    ImageView chexingiv;
    @BindView(R.id.chechangiv)
    ImageView chechangiv;
    private CheChangBean changcode;

    @Override
    protected int getResId() {
        return R.layout.activity_add_car;
    }

    CarModelBean code;

    @Override
    protected void initData() {
        super.initData();
        CommonApi.getInstance().models(SharedPreferencesUtil.getInstance().getString("token")).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CarModelBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i("as", d.toString());
                    }

                    @Override
                    public void onNext(@NonNull CarModelBean code) {
                        Log.i("as", code.toString());
                        if (code.getCode() == 200) {
                            AddCarActivity.this.code = code;
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i("as", e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        CommonApi.getInstance().chechang(SharedPreferencesUtil.getInstance().getString("token")).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CheChangBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i("as", d.toString());
                    }

                    @Override
                    public void onNext(@NonNull CheChangBean code) {
                        Log.i("as", code.toString());
                        if (code.getCode() == 200) {
                            AddCarActivity.this.changcode = code;
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i("as", e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @OnClick({R.id.back, R.id.next, R.id.chexing, R.id.chechang,R.id.chechangiv,R.id.chexingiv,R.id.haomaiv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.next:

                tijiao();
                break;
            case R.id.chexing:
                break;
            case R.id.chechang:
                break;
            case R.id.haomaiv:
                break;
            case R.id.chexingiv:
                selectCheXing();
                break;

            case R.id.chechangiv:
                selectChechang();
                break;
        }
    }

    private void selectChechang() {
        final AlertDialog dialog = new AlertDialog.Builder(AddCarActivity.this).create();
        View view = View.inflate(AddCarActivity.this, R.layout.aliet, null);
        dialog.setView(view);

//        List<String> data = new ArrayList();
//        for(int i = 10 ;i< 60;i++){
//            data.add(i+"");
//        }
        final String[] city = new String[changcode.getData().size()];
        for (int i = 0; i < changcode.getData().size(); i++) {
            city[i] = changcode.getData().get(i).getLeng();
        }
        NumberPicker picker = view.findViewById(R.id.picker);
        picker.setDisplayedValues(city);
        picker.setMinValue(0);
        picker.setMaxValue(city.length - 1);
        picker.setValue(0);
        //picker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        picker.setWrapSelectorWheel(false);
        dialog.show();


        picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                Log.i("acy", i1 + "asdfsd");
                changpos = i1;
            }
        });
        view.findViewById(R.id.queren).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //personAge.setText(city[pos]);
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }

                chechang.setText(changcode.getData().get(changpos).getLeng());
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
    }

    int changpos;
    int pos;

    private void selectCheXing() {
        final AlertDialog dialo = new AlertDialog.Builder(AddCarActivity.this).create();
        View view = View.inflate(AddCarActivity.this, R.layout.aliet, null);
        dialo.setView(view);

//        List<String> data = new ArrayList();
//        for(int i = 10 ;i< 60;i++){
//            data.add(i+"");
//        }
        final String[] city = new String[code.getData().size()];
        for (int i = 0; i < code.getData().size(); i++) {
            city[i] = code.getData().get(i).getModels();
        }
        NumberPicker picker = view.findViewById(R.id.picker);
        picker.setDisplayedValues(city);
        picker.setMinValue(0);
        picker.setMaxValue(city.length - 1);
        picker.setValue(0);
        //picker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        picker.setWrapSelectorWheel(false);
        dialo.show();


        Log.i("as", code.getData().toString());
        picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                // Log.i("acy",i+"");
//                Log.i("acy",i1+"asdfsd");
                pos = i1;
            }
        });
        view.findViewById(R.id.queren).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //personAge.setText(city[pos]);
                if (dialo != null && dialo.isShowing()) {
                    dialo.dismiss();
                }

                Log.i("as", pos + 1 + "");
                chexing.setText(code.getData().get(pos).getModels());
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

    }

    private void tijiao() {
        if (isEmpty(chepai)) {
            toast("车牌不能为空");
            return;
        }
        if (isEmpty(chexing)) {
            toast("车型不能为空");
            return;
        }
        if (isEmpty(chechang)) {
            toast("车长不能为空");
            return;
        }

        CommonApi.getInstance().add_car(SharedPreferencesUtil.getInstance().getString("token"), getString(chepai), pos + 1 + "", changpos + 1 + "").subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Code>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i("as", d.toString());
                    }

                    @Override
                    public void onNext(@NonNull Code code) {
                        Log.i("as", code.toString());
                        if (code.getCode() == 200) {
                            toast(code.getMessage());
                            //finish();
                            EventBus.getDefault().post(new MessageEvent());
                            finish();
                            //startActivity(new Intent(AddCarActivity.this,CarManagerActivity.class));
                            //finish();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i("as", e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
