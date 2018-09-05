package com.zhuye.huochebanghuozhu.bean;

/**
 * Created by Administrator on 2018/1/18 0018.
 */

public class DriverDetailBean extends Base {
    /**
     * data : {"uid":"司机id","name":"司机姓名","face":"头像","mobile":"手机号","city":"城市","count":"接单数量","license":"车牌","type":"0表示空闲 1表示运输中"}
     * message :
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
         * uid : 司机id
         * name : 司机姓名
         * face : 头像
         * mobile : 手机号
         * city : 城市
         * count : 接单数量
         * license : 车牌
         * type : 0表示空闲 1表示运输中
         */

        private String uid;
        private String name;
        private String face;
        private String mobile;
        private String city;
        private String count;
        private String license;
        private String type;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFace() {
            return face;
        }

        public void setFace(String face) {
            this.face = face;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getLicense() {
            return license;
        }

        public void setLicense(String license) {
            this.license = license;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
