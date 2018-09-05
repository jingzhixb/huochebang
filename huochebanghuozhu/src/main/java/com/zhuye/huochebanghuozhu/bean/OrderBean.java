package com.zhuye.huochebanghuozhu.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/19 0019.
 */

public class OrderBean extends Base {
    /**
     * data : [{"order_id":"订单id","face":"司机头像","name":"司机姓名","mobile":"司机手机号","start_city":"起始地址","end_city":"目的地","leng":"车长","models":"货车类型","goodtype":"货物类型","weight":"重量","price":"价格","type":"0表示待司机确认 6表示待支付 1表示已付款 2表示已上传装榜单 3表示已上传卸榜单 5表示已完成 "}]
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
         * order_id : 订单id
         * face : 司机头像
         * name : 司机姓名
         * mobile : 司机手机号
         * start_city : 起始地址
         * end_city : 目的地
         * leng : 车长
         * models : 货车类型
         * goodtype : 货物类型
         * weight : 重量
         * price : 价格
         * type : 0表示待司机确认 6表示待支付 1表示已付款 2表示已上传装榜单 3表示已上传卸榜单 5表示已完成
         */

        private String order_id;
        private String face;
        private String name;
        private String mobile;
        private String start_city;
        private String end_city;
        private String leng;
        private String models;
        private String goodtype;
        private String weight;
        private String price;
        private String type;
        private String valid;
        public String getValid() {
            return valid;
        }

        public void setValid(String valid) {
            this.valid = valid;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
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

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getStart_city() {
            return start_city;
        }

        public void setStart_city(String start_city) {
            this.start_city = start_city;
        }

        public String getEnd_city() {
            return end_city;
        }

        public void setEnd_city(String end_city) {
            this.end_city = end_city;
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

        public String getGoodtype() {
            return goodtype;
        }

        public void setGoodtype(String goodtype) {
            this.goodtype = goodtype;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
