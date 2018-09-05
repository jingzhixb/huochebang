package com.zhuye.huochebangsiji.bean;

/**
 * Created by Administrator on 2018/1/30 0030.
 */

public class JinChengBean extends Base {
    /**
     * data : {"order_sn":"订单编号","place_order":"司机接单时间","driver_confirm":"司机确认金额时间","owner_pay":"货主支付时间","driver_waybill":"司机上传货单时间","driver_landing":"司机上传卸榜单时间","driver_finish":"司机确认完成时间","owner_finish":"货主确认完成时间"}
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
         * order_sn : 订单编号
         * place_order : 司机接单时间
         * driver_confirm : 司机确认金额时间
         * owner_pay : 货主支付时间
         * driver_waybill : 司机上传货单时间
         * driver_landing : 司机上传卸榜单时间
         * driver_finish : 司机确认完成时间
         * owner_finish : 货主确认完成时间
         */

        private String order_sn;
        private String place_order;
        private String driver_confirm;
        private String owner_pay;
        private String driver_waybill;
        private String driver_landing;
        private String driver_finish;
        private String owner_finish;

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public String getPlace_order() {
            return place_order;
        }

        public void setPlace_order(String place_order) {
            this.place_order = place_order;
        }

        public String getDriver_confirm() {
            return driver_confirm;
        }

        public void setDriver_confirm(String driver_confirm) {
            this.driver_confirm = driver_confirm;
        }

        public String getOwner_pay() {
            return owner_pay;
        }

        public void setOwner_pay(String owner_pay) {
            this.owner_pay = owner_pay;
        }

        public String getDriver_waybill() {
            return driver_waybill;
        }

        public void setDriver_waybill(String driver_waybill) {
            this.driver_waybill = driver_waybill;
        }

        public String getDriver_landing() {
            return driver_landing;
        }

        public void setDriver_landing(String driver_landing) {
            this.driver_landing = driver_landing;
        }

        public String getDriver_finish() {
            return driver_finish;
        }

        public void setDriver_finish(String driver_finish) {
            this.driver_finish = driver_finish;
        }

        public String getOwner_finish() {
            return owner_finish;
        }

        public void setOwner_finish(String owner_finish) {
            this.owner_finish = owner_finish;
        }
    }
}
