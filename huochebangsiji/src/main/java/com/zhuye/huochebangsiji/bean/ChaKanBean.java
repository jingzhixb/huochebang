package com.zhuye.huochebangsiji.bean;

/**
 * Created by Administrator on 2018/1/18 0018.
 */

public class ChaKanBean extends Base {

    /**
     * data : {"type":"0表示可以免费查看 1表示需付费","good_id":"货源id","price":"表示查看费用","balance_price":"余额","scode":"需要积分","balance_scode":"用户剩余积分"}
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
         * type : 0表示可以免费查看 1表示需付费
         * good_id : 货源id
         * price : 表示查看费用
         * balance_price : 余额
         * scode : 需要积分
         * balance_scode : 用户剩余积分
         */

        private String type;
        private String good_id;
        private String price;
        private String balance_price;
        private String scode;
        private String balance_scode;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getGood_id() {
            return good_id;
        }

        public void setGood_id(String good_id) {
            this.good_id = good_id;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getBalance_price() {
            return balance_price;
        }

        public void setBalance_price(String balance_price) {
            this.balance_price = balance_price;
        }

        public String getScode() {
            return scode;
        }

        public void setScode(String scode) {
            this.scode = scode;
        }

        public String getBalance_scode() {
            return balance_scode;
        }

        public void setBalance_scode(String balance_scode) {
            this.balance_scode = balance_scode;
        }
    }
}
