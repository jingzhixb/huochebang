package com.zhuye.huochebangsiji.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitmapUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.zhuye.huochebangsiji.R;
import com.zhuye.huochebangsiji.activity.EditInfoActivity;
import com.zhuye.huochebangsiji.activity.JiFenActivity;
import com.zhuye.huochebangsiji.activity.JiaMengListActivity;
import com.zhuye.huochebangsiji.activity.LookDingDanActivity;
import com.zhuye.huochebangsiji.activity.MeJiFenActivity;
import com.zhuye.huochebangsiji.activity.MessageActivity;
import com.zhuye.huochebangsiji.activity.MoneyMangerActivity;
import com.zhuye.huochebangsiji.activity.SettingActivity;
import com.zhuye.huochebangsiji.bean.InvateBean;
import com.zhuye.huochebangsiji.bean.MobileBean;
import com.zhuye.huochebangsiji.bean.NewsNumBean;
import com.zhuye.huochebangsiji.bean.UserInfoBean;
import com.zhuye.huochebangsiji.contants.Contans;
import com.zhuye.huochebangsiji.contract.MeFragmentContract;
import com.zhuye.huochebangsiji.data.GetData;
import com.zhuye.huochebangsiji.presenter.MeFragmentpresenter;
import com.zhuye.huochebangsiji.utils.SharedPreferencesUtil;
import com.zhuye.huochebangsiji.widget.RoundedCornerImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/1/9 0009.
 */

public class MeFragment extends BaseFragment implements MeFragmentContract.MeFragmentView {
    private static final int NEWSNUM = 18;
    @BindView(R.id.me_touxiang)
    RoundedCornerImageView meTouxiang;
    @BindView(R.id.me_name)
    TextView meName;
    @BindView(R.id.me_edit)
    TextView meEdit;
    @BindView(R.id.message_number)
    TextView messageNumber;
    @BindView(R.id.mejifencont)
    TextView mejifencont;
    @BindView(R.id.me_dingdan_go)
    ImageView meDingdanGo;
    @BindView(R.id.me_suoyou)
    LinearLayout meSuoyou;
    @BindView(R.id.me_daifukuan)
    LinearLayout meDaifukuan;
    @BindView(R.id.me_jinxing)
    LinearLayout meJinxing;
    @BindView(R.id.me_queren)
    LinearLayout meQueren;
    @BindView(R.id.me_lishi)
    LinearLayout meLishi;
    @BindView(R.id.me_qianbao)
    LinearLayout meQianbao;
    @BindView(R.id.me_friends)
    LinearLayout meFriends;
    @BindView(R.id.me_kefu)
    LinearLayout meKefu;
    @BindView(R.id.me_setting)
    LinearLayout meSetting;
    @BindView(R.id.me_huowumessage)
    LinearLayout meHuowumessage;
    Unbinder unbinder;
    @BindView(R.id.message)
    LinearLayout message;
    Unbinder unbinder1;
    @BindView(R.id.me_jinfen)
    LinearLayout meJinfen;
    LinearLayout me_yue;
    TextView yueecount;
    @BindView(R.id.vip)
    TextView vip;
//    @BindView(R.id.yueecount)
//    TextView yueecount;
    @BindView(R.id.me_yue)
    LinearLayout meYue;
    @BindView(R.id.me_xj)
    RelativeLayout meXj;
    @BindView(R.id.imageView)
    ImageView imageView;


    @Override
    protected void initView() {
        me_yue = rootView.findViewById(R.id.me_yue);
        yueecount = rootView.findViewById(R.id.yueecount);
    }


    @Override
    protected void initListener() {
        super.initListener();
        me_yue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), JiFenActivity.class);
                intent.putExtra("jifen", bean.getData().getScode());
                intent.putExtra("face", bean.getData().getFace());
                intent.putExtra("name", bean.getData().getName());
                startActivity(intent);
            }
        });
    }

    @Override
    protected int getResId() {
        return R.layout.fragment_me;
    }


    @OnClick({R.id.me_jinfen, R.id.message, R.id.me_touxiang, R.id.me_edit, R.id.me_dingdan_go, R.id.me_suoyou, R.id.me_daifukuan, R.id.me_jinxing, R.id.me_queren, R.id.me_lishi, R.id.me_qianbao, R.id.me_friends, R.id.me_kefu, R.id.me_setting, R.id.me_huowumessage})
    public void onViewClicked(View view) {
        Intent intent = new Intent(getActivity(), LookDingDanActivity.class);
        switch (view.getId()) {
            case R.id.me_jinfen:
                startActivity(new Intent(getActivity(), MeJiFenActivity.class));
                break;
            case R.id.message:

                Intent intent1 = new Intent(getActivity(), MessageActivity.class);
                intent1.putExtra("system", beanss.getData().getSystem_num());
                intent1.putExtra("person", beanss.getData().getNews_num());
                startActivity(intent1);
                break;
            case R.id.me_touxiang:
                break;
            case R.id.me_edit:

                Intent inten = new Intent(getActivity(), EditInfoActivity.class);
                inten.putExtra("jifen", bean.getData().getScode());
                inten.putExtra("face", bean.getData().getFace());
                inten.putExtra("mobile", bean.getData().getMobile());
                inten.putExtra("suozai", bean.getData().getCity());
                inten.putExtra("shifa", bean.getData().getStart_cityname());
                inten.putExtra("mudi", bean.getData().getEnd_cityname());
                inten.putExtra("chepai",bean.getData().getLicense()==null?"":bean.getData().getLicense());
                inten.putExtra("name", bean.getData().getName());

                inten.putExtra("suozaisheng", bean.getData().getProvince_id());
                inten.putExtra("suozaishi", bean.getData().getCity_id());
                inten.putExtra("shifaid", bean.getData().getStart_city());
                inten.putExtra("mudiid", bean.getData().getEnd_city());

                startActivity(inten);
                //startActivity(new Intent(getActivity(), EditInfoActivity.class));
                break;
            case R.id.me_dingdan_go:
                break;
            case R.id.me_suoyou:
                intent.putExtra("type", 1);
                startActivity(intent);
                break;
            case R.id.me_daifukuan:
                intent.putExtra("type", 2);
                startActivity(intent);
                break;
            case R.id.me_jinxing:
                intent.putExtra("type", 3);
                startActivity(intent);
                break;
            case R.id.me_queren:
                intent.putExtra("type", 4);
                startActivity(intent);
                break;
            case R.id.me_lishi:
                intent.putExtra("type", 5);
                startActivity(intent);
                break;
            case R.id.me_qianbao:
                startActivity(new Intent(getActivity(), MoneyMangerActivity.class));
                break;
            case R.id.me_friends:
                startActivity(new Intent(getActivity(), JiaMengListActivity.class));
                //startActivity(new Intent(getActivity(), InvateFriendsActivity.class));
                break;
            //邀请好友
            case R.id.me_kefu:
                //startActivity(new Intent(getActivity(), ShowQRCodeActivity.class));
                meFragmentpresenter.yaoqing(12);
                break;
            case R.id.me_setting:
                //startActivity(new Intent(getActivity(), SettingActivity.class));
                meFragmentpresenter.getKefuInfo(11);
                break;
            case R.id.me_huowumessage:
                //startActivity(new Intent(getActivity(), MessageActivity.class));
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
        }
    }

    MeFragmentpresenter meFragmentpresenter;

    @Override
    protected void initData() {
        super.initData();
        meFragmentpresenter = new MeFragmentpresenter(this);
        meFragmentpresenter.loadData(10);
        GetData.news_num(SharedPreferencesUtil.getInstance().getString("token"), this, NEWSNUM);

    }

    @Override
    public void loding() {

    }

    @Override
    public void finishLoding() {

    }


    UserInfoBean bean;

    @Override
    public void success(int requestcode, Object o) {
        switch (requestcode) {
            case 10:
                bean = (UserInfoBean) o;
                Glide.with(getActivity()).load(Contans.BASE_URL + bean.getData().getFace()).into(meTouxiang);
                meName.setText(bean.getData().getName());
                mejifencont.setText(bean.getData().getScode() + "");
                yueecount.setText(bean.getData().getMoney() + "");

                SharedPreferencesUtil.getInstance().putString("mobile", bean.getData().getMobile());
                int type = Integer.parseInt(bean.getData().getType());
                if (type == 1) {
                    meFriends.setVisibility(View.GONE);
                } else {
                    meFriends.setVisibility(View.VISIBLE);
                }

                if (bean.getData().getVip().equals("1")) {
                    vip.setVisibility(View.VISIBLE);
                }
                break;
            case 11:
                MobileBean mobileBean = (MobileBean) o;
                alertdialog(mobileBean);
                break;

            case 12:
                InvateBean invateBean = (InvateBean) o;
                // ((MainActivity) getActivity()).toast(invateBean.getData().getUrl());
                alertYaoQing(invateBean);
                break;

            case NEWSNUM:
                beanss = (NewsNumBean) o;
                messageNumber.setText(Integer.parseInt(beanss.getData().getSystem_num()) + Integer.parseInt(beanss.getData().getNews_num()) + "");
                break;
        }


    }


    @Override
    public void onResume() {
        super.onResume();
        initData();
    }


//    @Override
//    public void onPause() {
//        super.onPause();
//        EventBus.getDefault().unregister(this);
//    }


    NewsNumBean beanss;
    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(getActivity(), "成功了", Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(getActivity(), "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(getActivity(), "取消了", Toast.LENGTH_LONG).show();

        }
    };

    private void alertYaoQing(final InvateBean invateBean) {
        final AlertDialog dial = new AlertDialog.Builder(getActivity()).create();
        View view = View.inflate(getActivity(), R.layout.aliet_yanqing, null);
        dial.setView(view);
        dial.show();
        Bitmap bitmap = null;

        ImageView iv = view.findViewById(R.id.qrcode);
        try {
            bitmap = BitmapUtils.create2DCode(invateBean.getData().getUrl());
            //  mImage.setImageBitmap(bitmap);
            iv.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        final Bitmap finalBitmap = bitmap;


        view.findViewById(R.id.queren).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //personAge.setText(city[pos]);
                if (dial != null && dial.isShowing()) {
                    dial.dismiss();
                }
//                UMImage image = new UMImage(getActivity(), finalBitmap);//bitmap文件

                UMImage image = new UMImage(getActivity(), "http://qr.liantu.com/api.php?text=" + invateBean.getData().getUrl());//bitmap文件

                UMWeb web = new UMWeb("http://qr.liantu.com/api.php?text=" + invateBean.getData().getUrl());
                web.setTitle("有货啦司机");//标题
                web.setThumb(image);  //缩略图
                web.setDescription("有货啦司机");//描述


                new ShareAction(getActivity()).withText("hello").
                        withMedia(web).
                        setDisplayList(SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN,
                                SHARE_MEDIA.WEIXIN_CIRCLE)
                        .setCallback(shareListener).open();
            }
        });
        view.findViewById(R.id.quxiao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dial != null && dial.isShowing()) {
                    dial.dismiss();
                }
            }
        });
    }

    private void alertdialog(final MobileBean mobileBean) {
        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();
        View view = View.inflate(getActivity(), R.layout.aliet, null);
        dialog.setView(view);
        dialog.show();


        ((TextView) view.findViewById(R.id.phone)).setText(mobileBean.getData().getMobile());
        view.findViewById(R.id.queren).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //personAge.setText(city[pos]);
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                //打电话
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mobileBean.getData().getMobile()));
                // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
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

    @Override
    public void empty() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        //EventBus.getDefault().register(this);
        return rootView;
    }


    //todo eventbus 事件订阅处理  bugly 使用


//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onMessageEvent(MessageEvent event) {/* Do something */
//        toast(event.getNum()+"");
//        if(event.getType()==1){
//            messageNumber.setText(Integer.parseInt(event.getNum()+beanss.getData().getNews_num())+"");
//        }else if(event.getType()==2){
//            messageNumber.setText(Integer.parseInt(event.getNum()+beanss.getData().getSystem_num())+"");
//        }
//    };


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
        //todo  点击我的订单闪退
        //EventBus.getDefault().register(this);
    }
}
