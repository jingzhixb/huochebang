package com.zhuye.huochebang.bean;

/**
 * Created by Administrator on 2018/1/4 0004.
 */

public class UploadImgBean extends Base{
    /**
     * data : {"face":"/Uploads/Picture/2018-01-04/video-131448421.jpg"}
     * message : 上传成功
     * code : 200
     */

    private DataBean data;


    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }


    public static class DataBean {
        /**
         * face : /Uploads/Picture/2018-01-04/video-131448421.jpg
         */

        private String face;

        public String getFace() {
            return face;
        }

        public void setFace(String face) {
            this.face = face;
        }
    }
}
