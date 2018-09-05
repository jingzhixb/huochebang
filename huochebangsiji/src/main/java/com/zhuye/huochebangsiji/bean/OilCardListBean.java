package com.zhuye.huochebangsiji.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/15 0015.
 */

public class OilCardListBean extends Base {
    /**
     * data : {"money":"100","oilcard_log":[{"money":"交易金额","intro":"充值/消费","week":"星期","card":"交易说明","create_time":"交易时间"}]}
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
         * money : 100
         * oilcard_log : [{"money":"交易金额","intro":"充值/消费","week":"星期","card":"交易说明","create_time":"交易时间"}]
         */

        private String money;
        private String balance_money;
        private String cord;

        public String getBalance_money() {
            return balance_money;
        }

        public void setBalance_money(String balance_money) {
            this.balance_money = balance_money;
        }

        public String getCord() {
            return cord;
        }

        public void setCord(String cord) {
            this.cord = cord;
        }

        private List<OilcardLogBean> oilcard_log;

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public List<OilcardLogBean> getOilcard_log() {
            return oilcard_log;
        }

        public void setOilcard_log(List<OilcardLogBean> oilcard_log) {
            this.oilcard_log = oilcard_log;
        }

        public static class OilcardLogBean {
            /**
             * money : 交易金额
             * intro : 充值/消费
             * week : 星期
             * card : 交易说明
             * create_time : 交易时间
             */

            private String money;
            private String intro;
            private String week;
            private String card;
            private String create_time;

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getIntro() {
                return intro;
            }

            public void setIntro(String intro) {
                this.intro = intro;
            }

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public String getCard() {
                return card;
            }

            public void setCard(String card) {
                this.card = card;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }
        }
    }
}
