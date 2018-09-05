package com.zhuye.huochebangsiji.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/15 0015.
 */

public class OilChargeListBean  extends Base{
    /**
     * data : [{"money":"金额","intro":"描述","week":"周几","card":"充值油卡说明","create_time":"时间"}]
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
         * money : 金额
         * intro : 描述
         * week : 周几
         * card : 充值油卡说明
         * create_time : 时间
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
