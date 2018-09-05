package com.zhuye.huochebangsiji.fragment;

import android.net.Uri;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.zhuye.huochebangsiji.R;
import com.zhuye.huochebangsiji.utils.FilesUtil;
import com.zhuye.huochebangsiji.widget.RoundedCornerImageView;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.iwf.photopicker.PhotoPicker;

/**
 * Created by Administrator on 2018/1/23 0023.
 */

public class BaseRegeistFragment extends BaseFragment {
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.touxiang)
    RoundedCornerImageView touxiang;
    @BindView(R.id.shenfenzhen)
    ImageView shenfenzhen;
    @BindView(R.id.shenfenfan)
    ImageView shenfenfan;
    Unbinder unbinder;

    @Override
    protected void initView() {

    }

    @Override
    protected int getResId() {
        return R.layout.base_regeist_fragment;
    }

    @Override
    public void success(int requestcode, Object o) {

    }



    @OnClick({R.id.touxiang, R.id.shenfenzhen, R.id.shenfenfan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.touxiang:
                selecttouxiang();
                break;
            case R.id.shenfenzhen:
                selectzheng();
                break;
            case R.id.shenfenfan:
                selectfan();
                break;
        }
    }



    private void selectfan() {
        PhotoPicker.builder()
                .setPhotoCount(1)//可选择图片数量
                .setShowCamera(true)//是否显示拍照按钮
                .setShowGif(true)//是否显示动态图
                .setPreviewEnabled(true)//是否可以预览
                .start(getActivity(), 307);
    }

    private void selectzheng() {
        PhotoPicker.builder()
                .setPhotoCount(1)//可选择图片数量
                .setShowCamera(true)//是否显示拍照按钮
                .setShowGif(true)//是否显示动态图
                .setPreviewEnabled(true)//是否可以预览
                .start(getActivity(), 308);
    }

    private void selecttouxiang() {
        PhotoPicker.builder()
                .setPhotoCount(1)//可选择图片数量
                .setShowCamera(true)//是否显示拍照按钮
                .setShowGif(true)//是否显示动态图
                .setPreviewEnabled(true)//是否可以预览
                .start(getActivity(), PhotoPicker.REQUEST_CODE);
    }

    public void setName(String nam){
        name.setText(nam);
    }
    public String getName(){
        return name.getText().toString().trim();
    }


    public void setTouxiang(String imagePath){
        File bitmap = FilesUtil.getSmallBitmap(getActivity(), imagePath);//压缩图片
        touxiang.setImageURI(Uri.fromFile(bitmap));
    }

    public void setshenzheng(String imagePath){
        File bitmap = FilesUtil.getSmallBitmap(getActivity(), imagePath);//压缩图片
        shenfenzhen.setImageURI(Uri.fromFile(bitmap));
    }
    public void setshenfan(String imagePath){
        File bitmap = FilesUtil.getSmallBitmap(getActivity(), imagePath);//压缩图片
        shenfenfan.setImageURI(Uri.fromFile(bitmap));
    }

    public ImageView getshenfan(){
       return  shenfenfan;
    }

    public ImageView getshenzheng(){
        return  shenfenzhen;
    }

    public AppCompatImageView gettou(){
        return  touxiang;
    }
}
