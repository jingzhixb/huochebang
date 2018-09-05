package com.zhuye.huochebanghuozhu.bean;

/**
 * Created by Administrator on 2018/1/10 0010.
 */

public class UserInfoBean extends Base {

    /**
     * data : {"name":"姓名","face":"头像","mobile":"手机号","scode":"积分","money":"余额","province":"省","province_id":"省id","city":"市","city_id":"市id","type":"0表示无车司机 1表示有车司机","license":"车牌号","models":"车型","leng":"车长","jion":"0表示未申请加入加盟商 1表示已申请加入加盟商","audit":"无车司机申请加入加盟商 0表示审核中 1表示通过 2表示拒绝","reason":"无车司机申请加入加盟商被拒绝原因","jion_encode":"无车司机--申请加入加盟商编码","jion_name":"无车司机--申请的加盟商编码","start_city":"司机路线--启时城市id","start_cityname":"司机路线--启时城市名称","end_city":"司机路线--目的地id","end_cityname":"司机路线--目的地名称","vip":"司机端--1表示vip用户 0非vip"}
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
         * name : 姓名
         * face : 头像
         * mobile : 手机号
         * scode : 积分
         * money : 余额
         * province : 省
         * province_id : 省id
         * city : 市
         * city_id : 市id
         * type : 0表示无车司机 1表示有车司机
         * license : 车牌号
         * models : 车型
         * leng : 车长
         * jion : 0表示未申请加入加盟商 1表示已申请加入加盟商
         * audit : 无车司机申请加入加盟商 0表示审核中 1表示通过 2表示拒绝
         * reason : 无车司机申请加入加盟商被拒绝原因
         * jion_encode : 无车司机--申请加入加盟商编码
         * jion_name : 无车司机--申请的加盟商编码
         * start_city : 司机路线--启时城市id
         * start_cityname : 司机路线--启时城市名称
         * end_city : 司机路线--目的地id
         * end_cityname : 司机路线--目的地名称
         * vip : 司机端--1表示vip用户 0非vip
         */

        private String name;
        private String face;
        private String mobile;
        private String scode;
        private String money;
        private String province;
        private String province_id;
        private String city;
        private String city_id;
        private String type;
        private String license;
        private String models;
        private String leng;
        private String jion;
        private String audit;
        private String reason;
        private String jion_encode;
        private String jion_name;
        private String start_city;
        private String start_cityname;
        private String end_city;
        private String end_cityname;
        private String vip;

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

        public String getScode() {
            return scode;
        }

        public void setScode(String scode) {
            this.scode = scode;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getProvince_id() {
            return province_id;
        }

        public void setProvince_id(String province_id) {
            this.province_id = province_id;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCity_id() {
            return city_id;
        }

        public void setCity_id(String city_id) {
            this.city_id = city_id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getLicense() {
            return license;
        }

        public void setLicense(String license) {
            this.license = license;
        }

        public String getModels() {
            return models;
        }

        public void setModels(String models) {
            this.models = models;
        }

        public String getLeng() {
            return leng;
        }

        public void setLeng(String leng) {
            this.leng = leng;
        }

        public String getJion() {
            return jion;
        }

        public void setJion(String jion) {
            this.jion = jion;
        }

        public String getAudit() {
            return audit;
        }

        public void setAudit(String audit) {
            this.audit = audit;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getJion_encode() {
            return jion_encode;
        }

        public void setJion_encode(String jion_encode) {
            this.jion_encode = jion_encode;
        }

        public String getJion_name() {
            return jion_name;
        }

        public void setJion_name(String jion_name) {
            this.jion_name = jion_name;
        }

        public String getStart_city() {
            return start_city;
        }

        public void setStart_city(String start_city) {
            this.start_city = start_city;
        }

        public String getStart_cityname() {
            return start_cityname;
        }

        public void setStart_cityname(String start_cityname) {
            this.start_cityname = start_cityname;
        }

        public String getEnd_city() {
            return end_city;
        }

        public void setEnd_city(String end_city) {
            this.end_city = end_city;
        }

        public String getEnd_cityname() {
            return end_cityname;
        }

        public void setEnd_cityname(String end_cityname) {
            this.end_cityname = end_cityname;
        }

        public String getVip() {
            return vip;
        }

        public void setVip(String vip) {
            this.vip = vip;
        }
    }
}
