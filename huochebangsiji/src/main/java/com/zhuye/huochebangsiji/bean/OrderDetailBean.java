package com.zhuye.huochebangsiji.bean;

/**
 * Created by Administrator on 2018/1/30 0030.
 */

public class OrderDetailBean extends Base {
    /**
     * data : {"name":"司机端--货主名称","face":"司机端","mobile":"手机号","goodtype":"货物类型","weight":"重量","start_addr":"起始地址","start_lng":"出发地经度","start_lat":"出发地纬度","end_addr":"目的地","end_lng":"目的地经度","end_lat":"目的地纬度","leng":"车长","models":"车型","car_type":"1表示整车 2表示拼车","price":"价格","peihuo":"装卸说明","time":"卸货时间","waybill":"货单","landing":"卸货单","type":"0表示待司机确认 1表示已付款 2表示已上传装榜单 3表示已上传卸榜单 5表示货主确认完成 6司机已确认"}
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
         * name : 司机端--货主名称
         * face : 司机端
         * mobile : 手机号
         * goodtype : 货物类型
         * weight : 重量
         * start_addr : 起始地址
         * start_lng : 出发地经度
         * start_lat : 出发地纬度
         * end_addr : 目的地
         * end_lng : 目的地经度
         * end_lat : 目的地纬度
         * leng : 车长
         * models : 车型
         * car_type : 1表示整车 2表示拼车
         * price : 价格
         * peihuo : 装卸说明
         * time : 卸货时间
         * waybill : 货单
         * landing : 卸货单
         * type : 0表示待司机确认 1表示已付款 2表示已上传装榜单 3表示已上传卸榜单 5表示货主确认完成 6司机已确认
         */

        private String name;
        private String face;
        private String mobile;
        private String goodtype;
        private String weight;
        private String start_addr;
        private String start_lng;
        private String start_lat;
        private String end_addr;
        private String end_lng;
        private String end_lat;
        private String leng;
        private String models;
        private String car_type;
        private String price;
        private String peihuo;
        private String time;
        private String waybill;
        private String landing;
        private String type;

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

        public String getStart_addr() {
            return start_addr;
        }

        public void setStart_addr(String start_addr) {
            this.start_addr = start_addr;
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

        public String getEnd_addr() {
            return end_addr;
        }

        public void setEnd_addr(String end_addr) {
            this.end_addr = end_addr;
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

        public String getCar_type() {
            return car_type;
        }

        public void setCar_type(String car_type) {
            this.car_type = car_type;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getPeihuo() {
            return peihuo;
        }

        public void setPeihuo(String peihuo) {
            this.peihuo = peihuo;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getWaybill() {
            return waybill;
        }

        public void setWaybill(String waybill) {
            this.waybill = waybill;
        }

        public String getLanding() {
            return landing;
        }

        public void setLanding(String landing) {
            this.landing = landing;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
