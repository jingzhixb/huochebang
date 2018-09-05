package com.zhuye.huochebanghuozhu.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/18 0018.
 */

public class DaTingBean extends Base{
    /**
     * data : [{"uid":"用户id","face":"头像","name":"姓名","car_id":"车辆id","mobile":"手机号","vip":"1表示vip 0非vip","license":"车牌号","leng":"车长","models":"车型","type":"0表示空闲 1表示运输中"}]
     * message :
     * code : 200
     */


    private List<DataBean> data;



    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * uid : 用户id
         * face : 头像
         * name : 姓名
         * car_id : 车辆id
         * mobile : 手机号
         * vip : 1表示vip 0非vip
         * license : 车牌号
         * leng : 车长
         * models : 车型
         * type : 0表示空闲 1表示运输中
         */

        private String uid;
        private String face;
        private String name;
        private String car_id;
        private String mobile;
        private String vip;
        private String license;
        private String leng;
        private String models;
        private String type;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getFace() {
            return face;
        }

        public void setFace(String face) {
            this.face = face;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCar_id() {
            return car_id;
        }

        public void setCar_id(String car_id) {
            this.car_id = car_id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getVip() {
            return vip;
        }

        public void setVip(String vip) {
            this.vip = vip;
        }

        public String getLicense() {
            return license;
        }

        public void setLicense(String license) {
            this.license = license;
        }

        public String getLeng() {
            return leng;
        }

        public void setLeng(String leng) {
            this.leng = leng;
        }

        public String getModels() {
            return models;
        }

        public void setModels(String models) {
            this.models = models;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
