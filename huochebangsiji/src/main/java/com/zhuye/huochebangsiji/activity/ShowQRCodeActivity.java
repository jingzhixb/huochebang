package com.zhuye.huochebangsiji.activity;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.google.zxing.WriterException;
import com.google.zxing.common.BitmapUtils;
import com.zhuye.huochebangsiji.R;
import com.zhuye.huochebangsiji.base.BaseActivity;
import com.zhuye.huochebangsiji.bean.InvateBean;
import com.zhuye.huochebangsiji.data.GetData;
import com.zhuye.huochebangsiji.utils.SharedPreferencesUtil;

import butterknife.BindView;

public class ShowQRCodeActivity extends BaseActivity {


    private static final int GETQRCODE = 10;
    @BindView(R.id.code)
    ImageView code;

    @Override
    protected int getResId() {
        return R.layout.activity_show_qrcode;
    }


    @Override
    protected void initData() {
        super.initData();
        GetData.invate(SharedPreferencesUtil.getInstance().getString("token"), this, GETQRCODE);
    }

    @Override
    public void success(int requestcode, Object o) {
        super.success(requestcode, o);
        switch (requestcode) {
            case GETQRCODE:
                InvateBean data = (InvateBean) o;
                String content = data.getData().getUrl();
                Bitmap bitmap = null;
                try {
                    bitmap = BitmapUtils.create2DCode(content);
                    //  mImage.setImageBitmap(bitmap);
                    code.setImageBitmap(bitmap);
                } catch (WriterException e) {
                    e.printStackTrace();
                }

                break;
        }
    }


}
