package com.zhuye.huochebanghuozhu.bean;

/**
 * Created by Administrator on 2018/1/19 0019.
 */

public class GoodsBean  extends Base{
    /**
     * data : {"start_addr":"出发地","end_addr":"目的地","good_type":"货物类型","weight":"货物重量","car_require":"车辆要求","price":"价格","invoice":"0无发票 1有发票","mobile":"联系人","intro":"配送说明","time":"卸货日期","driver_face":"司机头像","driver_name":"司机姓名","driver_mobile":"司机手机号"}
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
         * start_addr : 出发地
         * end_addr : 目的地
         * good_type : 货物类型
         * weight : 货物重量
         * car_require : 车辆要求
         * price : 价格
         * invoice : 0无发票 1有发票
         * mobile : 联系人
         * intro : 配送说明
         * time : 卸货日期
         * driver_face : 司机头像
         * driver_name : 司机姓名
         * driver_mobile : 司机手机号
         */

        private String start_addr;
        private String end_addr;
        private String good_type;
        private String weight;
        private String car_require;
        private String price;
        private String invoice;
        private String mobile;
        private String intro;
        private String time;
        private String driver_face;
        private String driver_name;
        private String driver_mobile;

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

        public String getGood_type() {
            return good_type;
        }

        public void setGood_type(String good_type) {
            this.good_type = good_type;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
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

        public String getInvoice() {
            return invoice;
        }

        public void setInvoice(String invoice) {
            this.invoice = invoice;
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

        public String getDriver_face() {
            return driver_face;
        }

        public void setDriver_face(String driver_face) {
            this.driver_face = driver_face;
        }

        public String getDriver_name() {
            return driver_name;
        }

        public void setDriver_name(String driver_name) {
            this.driver_name = driver_name;
        }

        public String getDriver_mobile() {
            return driver_mobile;
        }

        public void setDriver_mobile(String driver_mobile) {
            this.driver_mobile = driver_mobile;
        }
    }
}
