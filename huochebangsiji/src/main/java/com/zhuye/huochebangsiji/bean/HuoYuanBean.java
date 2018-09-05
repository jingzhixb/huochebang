package com.zhuye.huochebangsiji.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/15 0015.
 */

public class HuoYuanBean  extends Base{
    /**
     * data : [{"good_id":"货源id","start_addr":"开始地址","end_addr":"目的地","leng":"车长","model":"车类型","weight":"货物重量","type_name":"货物类型","price":"运费","mobile":"手机号","face":"发布头像","name":"姓名","create_time":"1小时前发布"}]
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
         * good_id : 货源id
         * start_addr : 开始地址
         * end_addr : 目的地
         * leng : 车长
         * model : 车类型
         * weight : 货物重量
         * type_name : 货物类型
         * price : 运费
         * mobile : 手机号
         * face : 发布头像
         * name : 姓名
         * create_time : 1小时前发布
         */

        private String good_id;
        private String start_addr;
        private String end_addr;
        private String leng;
        private String model;
        private String weight;
        private String type_name;
        private String price;
        private String mobile;
        private String face;
        private String name;
        private String create_time;
        private String type;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        private String url;

        public String getGood_id() {
            return good_id;
        }

        public void setGood_id(String good_id) {
            this.good_id = good_id;
        }

        public String getStart_addr() {
            return start_addr;
        }

        public void setStart_addr(String start_addr) {
            this.start_addr = start_addr;
        }

        public String getEnd_addr() {
            return end_addr;
        }

        public void setEnd_addr(String end_addr) {
            this.end_addr = end_addr;
        }

        public String getLeng() {
            return leng;
        }

        public void setLeng(String leng) {
            this.leng = leng;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
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

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}
