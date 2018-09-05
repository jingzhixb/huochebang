package com.zhuye.huochebanghuozhu.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/8 0008.
 */

public class BankListBean extends Base {
    /**
     * data : [{"cash_id":"2","card":"45628792222","card_name":"招商银行","is_default":"1"}]
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
         * cash_id : 2
         * card : 45628792222
         * card_name : 招商银行
         * is_default : 1
         */

        private String cash_id;
        private String card;
        private String card_name;
        private String is_default;

        public String getCash_id() {
            return cash_id;
        }

        public void setCash_id(String cash_id) {
            this.cash_id = cash_id;
        }

        public String getCard() {
            return card;
        }

        public void setCard(String card) {
            this.card = card;
        }

        public String getCard_name() {
            return card_name;
        }

        public void setCard_name(String card_name) {
            this.card_name = card_name;
        }

        public String getIs_default() {
            return is_default;
        }

        public void setIs_default(String is_default) {
            this.is_default = is_default;
        }
    }
}
