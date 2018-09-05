package com.zhuye.huochebanghuozhu.bean;

/**
 * Created by Administrator on 2018/1/8 0008.
 */

public class TiXianBean extends Base {
    /**
     * data : {"money":"87878","cash_id":"2","card_name":"招商银行","card":"2222"}
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
         * money : 87878
         * cash_id : 2
         * card_name : 招商银行
         * card : 2222
         */

        private String money;
        private String cash_id;
        private String card_name;
        private String card;

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getCash_id() {
            return cash_id;
        }

        public void setCash_id(String cash_id) {
            this.cash_id = cash_id;
        }

        public String getCard_name() {
            return card_name;
        }

        public void setCard_name(String card_name) {
            this.card_name = card_name;
        }

        public String getCard() {
            return card;
        }

        public void setCard(String card) {
            this.card = card;
        }
    }
}
