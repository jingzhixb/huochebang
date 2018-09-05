package com.zhuye.huochebanghuozhu.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.zhuye.huochebanghuozhu.R;
import com.zhuye.huochebanghuozhu.activity.EditInfoActivity;
import com.zhuye.huochebanghuozhu.activity.HuoWuDetailActivity;
import com.zhuye.huochebanghuozhu.activity.LookDingDanActivity;
import com.zhuye.huochebanghuozhu.activity.MeJiFenActivity;
import com.zhuye.huochebanghuozhu.activity.MessageActivity;
import com.zhuye.huochebanghuozhu.activity.MywoloatActivity;
import com.zhuye.huochebanghuozhu.activity.SettingActivity;
import com.zhuye.huochebanghuozhu.bean.InvateBean;
import com.zhuye.huochebanghuozhu.bean.MobileBean;
import com.zhuye.huochebanghuozhu.bean.NewsNumBean;
import com.zhuye.huochebanghuozhu.bean.UserInfoBean;
import com.zhuye.huochebanghuozhu.contants.Contans;
import com.zhuye.huochebanghuozhu.contract.MeFragmentContract;
import com.zhuye.huochebanghuozhu.presenter.MeFragmentpresenter;
import com.zhuye.huochebanghuozhu.utils.FilesUtil;
import com.zhuye.huochebanghuozhu.utils.MD5Utils;
import com.zhuye.huochebanghuozhu.utils.SharedPreferencesUtil;
import com.zhuye.huochebanghuozhu.widget.RoundedCornerImageView;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.zhuye.huochebanghuozhu.R.id.phone;

/**
 * Created by Administrator on 2018/1/9 0009.
 */

public class MeFragment extends BaseFragment implements MeFragmentContract.MeFragmentView{
    @BindView(R.id.me_touxiang)
    RoundedCornerImageView meTouxiang;
    @BindView(R.id.me_name)
    TextView meName;
    @BindView(R.id.me_edit)
    TextView meEdit;
//    @BindView(R.id.message_numbers)
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
    LinearLayout message;
    Unbinder unbinder;

    @Override
    protected void initView() {
        message = rootView.findViewById(R.id.message);
        messageNumber = rootView.findViewById(R.id.message_numbers);
    }

    @Override
    protected int getResId() {
        return R.layout.fragment_me;
    }


    @Override
    protected void initListener() {
        super.initListener();
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MessageActivity.class));
            }
        });
    }

    @OnClick({R.id.jifen,R.id.me_touxiang, R.id.me_edit, R.id.me_dingdan_go, R.id.me_suoyou, R.id.me_daifukuan, R.id.me_jinxing, R.id.me_queren, R.id.me_lishi, R.id.me_qianbao, R.id.me_friends, R.id.me_kefu, R.id.me_setting, R.id.me_huowumessage})
    public void onViewClicked(View view) {
        Intent intent = new Intent(getActivity(), LookDingDanActivity.class);
        switch (view.getId()) {
            case R.id.jifen:
                startActivity(new Intent(getActivity(), MeJiFenActivity.class));
                break;
            case R.id.me_touxiang:
                break;
            case R.id.me_edit:

                Intent inten = new Intent(getActivity(), EditInfoActivity.class);
                inten.putExtra("jifen",bean.getData().getScode());
                inten.putExtra("face",bean.getData().getFace());
                inten.putExtra("name",bean.getData().getName());
                inten.putExtra("mobile",bean.getData().getMobile());
                inten.putExtra("suozai",bean.getData().getCity());
                inten.putExtra("shifa",bean.getData().getStart_cityname());
                inten.putExtra("mudi",bean.getData().getEnd_cityname());
                inten.putExtra("chepai",bean.getData().getLicense()==null?"":bean.getData().getLicense());

                inten.putExtra("suozaisheng",bean.getData().getProvince_id());
                inten.putExtra("suozaishi",bean.getData().getCity_id());
                inten.putExtra("shifaid",bean.getData().getStart_city());
                inten.putExtra("mudiid",bean.getData().getEnd_city());

                startActivity(inten);
                break;
            case R.id.me_dingdan_go:
                break;
            case R.id.me_suoyou:
                intent.putExtra("type",1);
                startActivity(intent);
                break;
            case R.id.me_daifukuan:
                intent.putExtra("type",2);
                startActivity(intent);
                break;
            case R.id.me_jinxing:
                intent.putExtra("type",3);
                startActivity(intent);
                break;
            case R.id.me_queren:
                intent.putExtra("type",4);
                startActivity(intent);
                break;
            case R.id.me_lishi:
                intent.putExtra("type",5);
                startActivity(intent);
                break;
            case R.id.me_qianbao:
                startActivity(new Intent(getActivity(), MywoloatActivity.class));
                break;
            case R.id.me_friends:
                meFragmentpresenter.yaoqing(12);
                //startActivity(new Intent(getActivity(), InvateFriendsActivity.class));
                break;
            case R.id.me_kefu:
                meFragmentpresenter.getKefuInfo(11);
                break;
            case R.id.me_setting:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            case R.id.me_huowumessage:
                Intent intent1 = new Intent(getActivity(), HuoWuDetailActivity.class);
                intent1.putExtra("type",1);
                startActivity(intent1);
                break;
        }
    }
    MeFragmentpresenter meFragmentpresenter;
    @Override
    protected void initData() {
        super.initData();
        meFragmentpresenter= new MeFragmentpresenter(this);
        meFragmentpresenter.loadData(10);
        meFragmentpresenter.news_num(10101);
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    @Override
    public void loding() {

    }

    @Override
    public void finishLoding() {

    }


    UserInfoBean bean;
    @Override
    public void success(int requestcode,Object o) {
        switch (requestcode){
            case 10:
                bean = (UserInfoBean) o;
                Glide.with(getActivity()).load(Contans.BASE_URL+bean.getData().getFace()).into(meTouxiang);
                meName.setText(bean.getData().getName());
                mejifencont.setText(bean.getData().getScode()+"");
                SharedPreferencesUtil.getInstance().putString("mobile",bean.getData().getMobile());

                break;
            case 11:
                MobileBean mobileBean = (MobileBean) o;
                alertdialog(mobileBean);
                break;

            case 12:
                InvateBean invateBean = (InvateBean) o;
               // ((MainActivity)getActivity()).toast(invateBean.getData().getUrl());
                alertYaoQing(invateBean);
                break;
            case 10101:
                NewsNumBean beanss = (NewsNumBean) o;
                messageNumber.setText(Integer.parseInt(beanss.getData().getSystem_num())+Integer.parseInt(beanss.getData().getNews_num())+"");
                break;
        }


    }
    String cha;
    private void alertYaoQing(final InvateBean invateBean) {
        final AlertDialog dial = new AlertDialog.Builder(getActivity()).create();
        View view = View.inflate(getActivity(), R.layout.aliet_yanqing, null);
        dial.setView(view);
        dial.show();
        Bitmap bitmap = null;

        ImageView iv =  view.findViewById(R.id.qrcode);
        try {
            bitmap = BitmapUtils.create2DCode(invateBean.getData().getUrl());
            //  mImage.setImageBitmap(bitmap);
            iv.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
            return;
        }

//         cha= FilesUtil.getCachePath(getActivity());
//
//         cha += MD5Utils.MD5(String.valueOf(Math.random()));
//
//
//       if( !FilesUtil.saveBitmap2File(bitmap,cha)){
//           toast("失败");
//           return;
//       }

        final Bitmap finalBitmap = bitmap;
        view.findViewById(R.id.queren).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //personAge.setText(city[pos]);
                if (dial != null && dial.isShowing()) {
                    dial.dismiss();
                }
//                UMImage image = new UMImage(getActivity(), finalBitmap);//bitmap文件
//                new ShareAction(getActivity()).withText("hello").
//                        setDisplayList(SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN)
//                        .setCallback(shareListener).withMedia(image).open();
                UMImage image = new UMImage(getActivity(), "http://qr.liantu.com/api.php?text="+invateBean.getData().getUrl());//bitmap文件

                UMWeb web = new UMWeb("http://qr.liantu.com/api.php?text="+invateBean.getData().getUrl());
                web.setTitle("有货啦货主");//标题
                web.setThumb(image);  //缩略图
                web.setDescription("有货啦货主");//描述

                new ShareAction(getActivity()).withText("hello").
                        withMedia(web).
                        setDisplayList(SHARE_MEDIA.QQ,SHARE_MEDIA.QZONE,SHARE_MEDIA.WEIXIN,
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
            Toast.makeText(getActivity(),"成功了",Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(getActivity(),"失败"+t.getMessage(),Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(getActivity(),"取消了",Toast.LENGTH_LONG).show();

        }
    };
    private void alertdialog(final MobileBean mobileBean) {
        final AlertDialog dialog = new  AlertDialog.Builder(getActivity()).create();
        View view = View.inflate(getActivity(),R.layout.aliet,null);
        dialog.setView(view);
        dialog.show();


        ((TextView)view.findViewById(phone)).setText(mobileBean.getData().getMobile());
        view.findViewById(R.id.queren).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //personAge.setText(city[pos]);
                if(dialog!=null&&dialog.isShowing()){
                    dialog.dismiss();
                }
                //打电话
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+mobileBean.getData().getMobile()));
               // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.quxiao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialog!=null&&dialog.isShowing()){
                    dialog.dismiss();
                }
            }
        });
    }

    @Override
    public void empty() {

    }
}
