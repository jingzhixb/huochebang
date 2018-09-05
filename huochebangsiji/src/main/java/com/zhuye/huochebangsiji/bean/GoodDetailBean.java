package com.zhuye.huochebangsiji.bean;

/**
 * Created by Administrator on 2018/1/15 0015.
 */

public class GoodDetailBean  extends Base{
    /**
     * data : {"good_id":"货源id","face":"头像","name":"姓名","type_name":"货物类型","weight":"重量","start_addr":"出发地","end_addr":"目的地","car_require":"货源要求","price":"运费","mobile":"手机号","intro":"配送说明","time":"装卸的时间","start_lng":"出发地经度","start_lat":"出发地纬度","end_lng":"目的地经度","end_lat":"目的地纬度"}
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
         * good_id : 货源id
         * face : 头像
         * name : 姓名
         * type_name : 货物类型
         * weight : 重量
         * start_addr : 出发地
         * end_addr : 目的地
         * car_require : 货源要求
         * price : 运费
         * mobile : 手机号
         * intro : 配送说明
         * time : 装卸的时间
         * start_lng : 出发地经度
         * start_lat : 出发地纬度
         * end_lng : 目的地经度
         * end_lat : 目的地纬度
         */

        private String good_id;
        private String face;
        private String name;
        private String type_name;
        private String weight;
        private String start_addr;
        private String end_addr;
        private String car_require;
        private String price;
        private String mobile;
        private String intro;
        private String time;
        private String start_lng;
        private String start_lat;
        private String end_lng;
        private String end_lat;

        public String getGood_id() {
            return good_id;
        }

        public void setGood_id(String good_id) {
            this.good_id = good_id;
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

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
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

        public String getCar_require() {
            return car_require;
        }

        public void setCar_require(String car_require) {
            this.car_require = car_require;
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

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getStart_lng() {
            return start_lng;
        }

        public void setStart_lng(String start_lng) {
            this.start_lng = start_lng;
        }

        public String getStart_lat() {
            return start_lat;
        }

        public void setStart_lat(String start_lat) {
            this.start_lat = start_lat;
        }

        public String getEnd_lng() {
            return end_lng;
        }

        public void setEnd_lng(String end_lng) {
            this.end_lng = end_lng;
        }

        public String getEnd_lat() {
            return end_lat;
        }

        public void setEnd_lat(String end_lat) {
            this.end_lat = end_lat;
        }
    }
}
